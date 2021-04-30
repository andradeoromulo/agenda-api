package com.example.agendaapi.controller.dto;

import com.example.agendaapi.model.Usuario;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioComEnderecoDTO {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String dataNascimento;
    private List<EnderecoDTO> enderecos;

    public UsuarioComEnderecoDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cpf = usuario.getCpf();
        this.dataNascimento = usuario.getDataNascimento().format(formatter);
        this.enderecos = usuario.getEnderecos().stream().map(EnderecoDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }
}
