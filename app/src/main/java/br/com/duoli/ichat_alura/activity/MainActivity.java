package br.com.duoli.ichat_alura.activity;

import android.app.usage.UsageEvents;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.duoli.ichat_alura.R;
import br.com.duoli.ichat_alura.adapter.MensagemAdapter;
import br.com.duoli.ichat_alura.app.ChatApplication;
import br.com.duoli.ichat_alura.callback.EnviarMensagemCallback;
import br.com.duoli.ichat_alura.callback.OuvirMensagensCallback;
import br.com.duoli.ichat_alura.component.ChatComponent;
import br.com.duoli.ichat_alura.event.MensagemEvent;
import br.com.duoli.ichat_alura.modelo.Mensagem;
import br.com.duoli.ichat_alura.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_mensagens)
    ListView mMensagens;
    @BindView(R.id.et_texto)
    EditText mTexto;
    @BindView(R.id.btn_enviar)
    Button mButton;
    @BindView(R.id.iv_avatar_usuario)
    ImageView mAvatarUsuario;

    private int idDoCliente = 1;
    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;

    @Inject
    Picasso picasso;

    @Inject
    EventBus eventBus;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        picasso.with(this).load("http://api.adorable.io/avatars/285/"+ idDoCliente +".png").into(mAvatarUsuario);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        if(savedInstanceState != null){
            mensagens = (List<Mensagem>) savedInstanceState.getSerializable("mensagens");
        } else {
            mensagens = new ArrayList<>();
        }

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        mMensagens.setAdapter(adapter);

        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallback(eventBus, this));

        eventBus.register(this);

    }

    @Subscribe
    public void colocaNaLista(MensagemEvent mensagemEvent) {
        mensagens.add(mensagemEvent.mensagem);

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        mMensagens.setAdapter(adapter);

    }

    @Subscribe
    public void ouvirMensagem(MensagemEvent mensagemEvent) {
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallback(eventBus, this));
    }

    @OnClick(R.id.btn_enviar)
    public void onClick() {
        chatService.enviar(new Mensagem(idDoCliente, mTexto.getText().toString())).enqueue(new EnviarMensagemCallback());

        mTexto.getText().clear();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mTexto.getWindowToken(), 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mensagens", (ArrayList<Mensagem>) mensagens);
    }

}
