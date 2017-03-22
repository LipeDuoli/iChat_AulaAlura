package br.com.duoli.ichat_alura.callback;

import retrofit2.Call;
import retrofit2.Response;

public class EnviarMensagemCallback implements retrofit2.Callback<Void> {

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()){

        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
}
