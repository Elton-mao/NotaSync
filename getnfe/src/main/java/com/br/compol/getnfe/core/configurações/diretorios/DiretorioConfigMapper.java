package com.br.compol.getnfe.core.configurações.diretorios;

import org.springframework.stereotype.Component;

import com.br.compol.getnfe.web.app.administrador.dtos.DiretorioConfigDetalhes;
import com.br.compol.getnfe.web.app.administrador.dtos.DiretorioConfigForm;
@Component
public class DiretorioConfigMapper {
    
    public DiretorioConfig toDiretorioConfig(DiretorioConfigForm form){
        return DiretorioConfig.builder()
                .caminhoDiretorio(form.getCaminhoDiretorio())
                .tipoDiretorio(form.getTipoDiretorio())
                .build();
    }

    public DiretorioConfigDetalhes toDiretorioConfigDetalhes(DiretorioConfig config){
        return DiretorioConfigDetalhes.builder()
                .id(config.getId())
                .caminhoDiretorio(config.getCaminhoDiretorio())
                .tipoDiretorio(config.getTipoDiretorio())
                .build();
    }


}
