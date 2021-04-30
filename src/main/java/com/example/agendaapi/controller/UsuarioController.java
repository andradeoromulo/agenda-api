package com.example.agendaapi.controller;

import com.example.agendaapi.controller.dto.EnderecoDTO;
import com.example.agendaapi.controller.dto.UsuarioComEnderecoDTO;
import com.example.agendaapi.controller.dto.UsuarioDTO;
import com.example.agendaapi.controller.form.EnderecoForm;
import com.example.agendaapi.controller.form.UsuarioForm;
import com.example.agendaapi.exception.CpfOuEmailEmUsoException;
import com.example.agendaapi.exception.UsuarioNaoEncontradoException;
import com.example.agendaapi.model.Endereco;
import com.example.agendaapi.model.Usuario;
import com.example.agendaapi.repository.EnderecoRepository;
import com.example.agendaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {

        Optional<Usuario> usuarioExistente = usuarioRepository.findByCpfOrEmail(usuarioForm.getCpf(), usuarioForm.getEmail());

        if(usuarioExistente.isPresent())
            throw new CpfOuEmailEmUsoException("Já existe um usuário cadastrado com o e-mail ou CPF fornecido");

        Usuario usuario = usuarioForm.toUsuario();
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDTO(usuario));

    }

    @PostMapping("/{usuarioId}/enderecos")
    public ResponseEntity<?> cadastrarEndereco(@PathVariable Long usuarioId, @RequestBody @Valid EnderecoForm enderecoForm) {

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

        if(usuario.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");

        Endereco endereco = enderecoForm.toEndereco(usuario.get());
        enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnderecoDTO(endereco));

    }

    @GetMapping("/{usuarioId}/enderecos")
    public ResponseEntity<?> consultarEnderecos(@PathVariable Long usuarioId) {

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

        if(usuario.isEmpty())
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");

        return ResponseEntity.ok().body(new UsuarioComEnderecoDTO(usuario.get()));

    }

}
