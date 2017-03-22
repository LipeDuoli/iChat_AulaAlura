package br.com.duoli.ichat_alura.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.greenrobot.eventbus.EventBus;

import br.com.duoli.ichat_alura.activity.MainActivity;
import br.com.duoli.ichat_alura.event.MensagemEvent;
import br.com.duoli.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallback implements Callback<Mensagem> {

    private EventBus eventBus;
    private Context context;

    public OuvirMensagensCallback(EventBus eventBus, Context context) {
        this.eventBus = eventBus;
        this.context = context;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()){
            Mensagem mensagem = response.body();

            eventBus.post(new MensagemEvent(mensagem));
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
    }
}
