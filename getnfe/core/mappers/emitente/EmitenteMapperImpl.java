package com.br.compol.getnfe.core.mappers.emitente;



import org.springframework.stereotype.Component;

import com.br.compol.getnfe.core.mappers.notafiscal.NotafiscalMapper;
import com.br.compol.getnfe.core.model.notafiscal.Emitente;
import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.web.app.emitente.dto.EmitenteListItem;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmitenteMapperImpl implements EmitenteMapper{
  

  private final NotafiscalMapper notafiscalMapper;
  
    @Override
    public Emitente nfeProcToEmitente(NfeProc nfeProc) {
      return Emitente .builder()
                .cnpj(nfeProc.getEmitente().getCnpj())
                .razaoSocial(nfeProc.getEmitente().getRazaoSocial())
                .inscricaoEstadual(nfeProc.getEmitente().getInscricaoEstadual())
                .build();
    }

	@Override
	public EmitenteListItem toEmitenteListItem(Emitente emitente) {
    var notas = emitente.getNotasFiscais().stream()
                .map(notafiscalMapper::toNotafiscalListItem)
                .toList();
    
      return EmitenteListItem.builder()
                .id(emitente.getId())
                .cnpj(emitente.getCnpj())
                .razaoSocial(emitente.getRazaoSocial())
                .inscricaoEstadual(emitente.getInscricaoEstadual())
                .notasFiscais(notas)
                .totalNotasFiscais(notas.size())
                .build();
                
	}
    
}
