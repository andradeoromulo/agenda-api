package com.example.agendaapi.controller.form;

import com.example.agendaapi.model.Endereco;
import com.example.agendaapi.model.Usuario;

import javax.validation.constraints.NotBlank;

public class EnderecoForm {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;

    public EnderecoForm(String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco toEndereco(Usuario usuario) {
        return new Endereco(this.logradouro, this.numero, this.complemento, this.bairro, this.cidade, this.estado, this.cep, usuario);
    }
}
