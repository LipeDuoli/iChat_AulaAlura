package br.com.duoli.ichat_alura.modulo;

import android.app.Application;
import android.app.usage.UsageEvents;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.duoli.ichat_alura.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ChatModule {

    public ChatModule(Application app) {
        this.app = app;
    }

    private Application app;

    @Provides
    public ChatService getChatService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);

        return chatService;
    }

    @Provides
    public Picasso getPicasso(){
        return new Picasso.Builder(app).build();
    }

    @Provides
    public EventBus getEventBus(){
        return EventBus.builder().build();
    }
}
