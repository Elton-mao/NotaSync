package com.br.compol.getnfe.web.app.usuario.dtos;

import com.br.compol.getnfe.enums.GruposDeUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {
    private String login;
    
    private String nome;

    private String email;
   
    private GruposDeUsuario grupo; 

    private String senha; 
    
}
