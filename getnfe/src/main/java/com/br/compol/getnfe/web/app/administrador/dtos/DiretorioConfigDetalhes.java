package com.br.compol.getnfe.web.app.administrador.dtos;

import com.br.compol.getnfe.core.configurações.diretorios.TipoDiretorio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiretorioConfigDetalhes {
    private Long id;
    private String caminhoDiretorio;
    private TipoDiretorio tipoDiretorio;
}
