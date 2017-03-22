package br.com.duoli.ichat_alura.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.duoli.ichat_alura.R;
import br.com.duoli.ichat_alura.modelo.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemAdapter extends BaseAdapter {

    @BindView(R.id.iv_avatar_mensagem)
    ImageView mAvatarMensagem;
    @BindView(R.id.tv_texto)
    TextView mTexto;

    @Inject
    Picasso picasso;

    private Activity activity;
    private List<Mensagem> mensagens;
    private int idDoCliente;

    public MensagemAdapter(int idDoCliente, List<Mensagem> mensagens, Activity activity) {
        this.idDoCliente = idDoCliente;
        this.mensagens = mensagens;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem, parent, false);
        ButterKnife.bind(this, linha);

        Mensagem mensagem = getItem(position);
        mTexto.setText(mensagem.getTexto());
        if (idDoCliente != mensagem.getId()) {
            linha.setBackgroundColor(Color.CYAN);
        }
        picasso.with(activity).load("http://api.adorable.io/avatars/285/"+ mensagem.getId() +".png").into(mAvatarMensagem);

        return linha;
    }
}
