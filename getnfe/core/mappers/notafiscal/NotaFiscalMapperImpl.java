package com.br.compol.getnfe.core.mappers.notafiscal;


import org.springframework.stereotype.Component;

import com.br.compol.getnfe.core.model.notafiscal.Emitente;
import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.core.model.notafiscal.NotaFiscal;
import com.br.compol.getnfe.core.utils.DataUtils;
import com.br.compol.getnfe.core.utils.NumberUtils;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotaFiscalListItem;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotafiscalForm;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotaFiscalMapperImpl implements NotafiscalMapper {

    
  
    /**
     * Converte um objeto NfeProc para um objeto NotaFiscal.
     * 
     * Este método mapeia os dados de uma instância de NfeProc para uma instância de
     * NotaFiscal.
     * Caso o emitente associado ao NfeProc não exista no banco de dados,
     * ele cria um novo emitente usando o emitenteMapper e o salva no banco de
     * dados.
     * 
     * @param nfeproc O objeto NfeProc contendo os dados a serem convertidos.
     * @return Um objeto NotaFiscal contendo os dados mapeados.
     */
    @Override
    public NotaFiscal toNotafiscal(NfeProc nfeproc, String caminhoArquivoXml, Emitente emitente) {
        return NotaFiscal.builder()
                .serie(nfeproc.getSerie())
                .numeroDaNota(nfeproc.getIdentificacao().getNumero())
                .dataEmissao(DataUtils.parseDataEmissao(nfeproc.getDataEmissao()))
                .chaveAcesso(nfeproc.getChaveNfe())
                .protocoloAutorizacao(nfeproc.getNumeroProtocolo())
                .valorPago(nfeproc.getValorTotalProdutos())
                .valorTotal(nfeproc.getValorTotalNota())
                .emitente(emitente)
                .caminhoArquivoXml(caminhoArquivoXml)
                .build();
    }

    @Override
    public NotafiscalForm toNotafiscalForm(NotaFiscal notaFiscal) {
        return NotafiscalForm.builder()
                .serie(notaFiscal.getSerie())
                .dataEmissao(DataUtils.formatarDataEmissao(notaFiscal.getDataEmissao()))
                .protocoloAutorizacao(notaFiscal.getProtocoloAutorizacao())
                .chaveAcesso(notaFiscal.getChaveAcesso())
                .valorPago(notaFiscal.getValorPago())
                .valorTotal(notaFiscal.getValorTotal())
                .build();
    }

    @Override
    public NotaFiscalListItem toNotafiscalListItem(NotaFiscal notaFiscal) {
        
        return NotaFiscalListItem.builder()
                .id(notaFiscal.getId())
                .serie(notaFiscal.getSerie())
                .numeroDaNota(NumberUtils.formatNumeroDaNota(notaFiscal.getNumeroDaNota()))
                .dataEmissao(notaFiscal.getDataEmissao())
                .protocoloAutorizacao(notaFiscal.getProtocoloAutorizacao())
                .chaveAcesso(notaFiscal.getChaveAcesso())
                .valorPago(notaFiscal.getValorPago())
                .valorTotal(notaFiscal.getValorTotal())
                .emitente(notaFiscal.getEmitente())
                .caminhoArquivoXml(notaFiscal.getCaminhoArquivoXml())
                .build();

    }

}
