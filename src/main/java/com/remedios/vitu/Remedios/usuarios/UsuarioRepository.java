package com.remedios.vitu.Remedios.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<EntidadeUsuario, Long> {

    //criando um novo metodo no repositorio para buscar o usuario pelo login
    UserDetails findByLogin(String login);
}
