package br.com.duoli.ichat_alura.app;

import android.app.Application;

import br.com.duoli.ichat_alura.component.ChatComponent;
import br.com.duoli.ichat_alura.component.DaggerChatComponent;
import br.com.duoli.ichat_alura.modulo.ChatModule;

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder()
                .chatModule(new ChatModule(this))
                .build();
    }

    public ChatComponent getComponent(){
        return component;
    }
}
