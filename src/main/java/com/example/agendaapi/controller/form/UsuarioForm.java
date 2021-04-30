package com.example.agendaapi.controller.form;

import com.example.agendaapi.model.Usuario;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioForm {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @NotBlank
    private String nome;

    @NotBlank @Email
    private String email;

    @NotBlank @CPF
    private String cpf;

    @NotBlank
    private String dataNascimento;

    public UsuarioForm(String nome, String email, String cpf, String dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Usuario toUsuario() {

        LocalDate dataNascimentoDate = LocalDate.parse(dataNascimento, formatter);
        return new Usuario(this.nome, this.email, this.cpf, dataNascimentoDate);

    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
