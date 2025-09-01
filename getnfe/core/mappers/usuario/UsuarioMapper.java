package com.br.compol.getnfe.core.mappers.usuario;

import com.br.compol.getnfe.core.model.usuario.Usuario;
import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioDetails;
import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioForm;

public interface UsuarioMapper {
    Usuario toUsuario(UsuarioForm usuarioForm); 
    UsuarioDetails toUsuarioDetails(Usuario usuario); 
}
