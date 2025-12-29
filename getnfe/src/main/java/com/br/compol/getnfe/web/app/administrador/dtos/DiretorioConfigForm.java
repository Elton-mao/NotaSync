package com.br.compol.getnfe.web.app.administrador.dtos;

import com.br.compol.getnfe.core.configurações.diretorios.TipoDiretorio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DiretorioConfigForm {

    private String caminhoDiretorio;

    private TipoDiretorio tipoDiretorio;
}
