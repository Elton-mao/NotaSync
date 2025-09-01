package com.br.compol.getnfe.core.mappers.emitente;

import com.br.compol.getnfe.core.model.notafiscal.Emitente;
import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.web.app.emitente.dto.EmitenteListItem;

public interface EmitenteMapper {
    Emitente nfeProcToEmitente(NfeProc nfeProc);
    EmitenteListItem toEmitenteListItem(Emitente emitente);
    
}
