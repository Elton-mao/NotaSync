package com.br.compol.getnfe.core.mappers.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.compol.getnfe.core.model.usuario.Usuario;
import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioDetails;
import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioForm;

import lombok.RequiredArgsConstructor;

/**
 * Implementação da interface {@link UsuarioMapper} responsável por mapear objetos entre
 * {@link UsuarioForm}, {@link Usuario} e {@link UsuarioDetails}.
 * 
 * <p>
 * Esta classe fornece métodos para:
 * <ul>
 *   <li>Converter um {@link UsuarioForm} em um {@link Usuario}.</li>
 *   <li>Converter um {@link Usuario} em um {@link UsuarioDetails}.</li>
 *   <li>Atualizar um objeto {@link Usuario} existente com os dados de um {@link UsuarioForm}.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Anotada com {@link org.springframework.stereotype.Component} para permitir injeção de dependência
 * pelo Spring.
 * </p>
 */

@Component
@RequiredArgsConstructor
public class UsuarioMapperImpl implements UsuarioMapper {
    private final PasswordEncoder bcriptEncoder; 
    @Override
    public Usuario toUsuario(UsuarioForm usuarioForm) {
        return Usuario.builder()
                .nome(usuarioForm.getNome())
                .login(usuarioForm.getLogin())
                .email(usuarioForm.getEmail())
                .grupo(usuarioForm.getGrupo())
                .senha(bcriptEncoder.encode(usuarioForm.getSenha()))
                .build();
    }

    @Override
    public UsuarioDetails toUsuarioDetails(Usuario usuario) {
        return UsuarioDetails.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .login(usuario.getLogin())
                .email(usuario.getEmail())
                .grupo(usuario.getGrupo())
                .build();
    }

	

}
