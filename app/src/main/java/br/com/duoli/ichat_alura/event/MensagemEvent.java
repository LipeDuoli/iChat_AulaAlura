package br.com.duoli.ichat_alura.event;

import br.com.duoli.ichat_alura.modelo.Mensagem;

public class MensagemEvent {

    public Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }


}
