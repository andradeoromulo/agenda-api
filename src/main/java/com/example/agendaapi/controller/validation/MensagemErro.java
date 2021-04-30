package com.example.agendaapi.controller.validation;

public class MensagemErro {

    private String campo;
    private String erro;

    public MensagemErro(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
