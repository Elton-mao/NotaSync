package com.br.compol.getnfe.core.repositories.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.compol.getnfe.core.model.usuario.Usuario;




public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Usuario findByEmail(String email);
   Optional<Usuario>  findByLogin(String login);
}
