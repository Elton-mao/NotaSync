package com.br.compol.getnfe.core.mappers.notafiscal;

import com.br.compol.getnfe.core.model.notafiscal.Emitente;
import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.core.model.notafiscal.NotaFiscal;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotaFiscalListItem;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotafiscalForm;

public interface NotafiscalMapper {
    NotaFiscal toNotafiscal(NfeProc notafiscalForm, String caminhoArquivoXml,Emitente emitente);
    NotafiscalForm toNotafiscalForm(NotaFiscal notaFiscal);
    NotaFiscalListItem toNotafiscalListItem(NotaFiscal notaFiscal);

}
