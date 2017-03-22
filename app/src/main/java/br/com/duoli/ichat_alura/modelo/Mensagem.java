package br.com.duoli.ichat_alura.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private int id;
    @SerializedName("text")
    private String texto;

    public Mensagem(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
