package com.example.agendaapi.repository;

import com.example.agendaapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpfOrEmail(String cpf, String email);

}
