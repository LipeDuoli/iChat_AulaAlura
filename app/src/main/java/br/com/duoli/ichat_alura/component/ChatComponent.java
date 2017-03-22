package br.com.duoli.ichat_alura.component;

import br.com.duoli.ichat_alura.activity.MainActivity;
import br.com.duoli.ichat_alura.adapter.MensagemAdapter;
import br.com.duoli.ichat_alura.modulo.ChatModule;
import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);
    void inject(MensagemAdapter adapter);
}
