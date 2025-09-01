package com.br.compol.getnfe.core.service.notafiscal;

import java.time.LocalDateTime;
import java.util.List;

import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotaFiscalListItem;

/**
 * Interface de serviço para gerenciar operações de Nota Fiscal.
 */
public interface NotaFiscalService {

    /**
     * Cria um novo registro de Nota Fiscal.
     *
     * @param notafiscalForm O objeto NfeProc contendo os dados da Nota Fiscal.
     * @param xmlFile O arquivo XML associado à Nota Fiscal.
     */
    void create(NfeProc notafiscalForm, String xmlFile);

    /**
     * Encontra uma Nota Fiscal pelo seu identificador único.
     *
     * @param id O identificador único da Nota Fiscal.
     * @return Um NotaFiscalListItem representando os detalhes da Nota Fiscal.
     */
    NotaFiscalListItem findById(Long id);

    /**
     * Recupera uma lista de todos os registros de Nota Fiscal.
     *
     * @return Uma lista de objetos NotaFiscalListItem representando todos os registros de Nota Fiscal.
     */
    List<NotaFiscalListItem> findAll();
    
    /**
     * Recupera uma lista de objetos NotaFiscalListItem cuja data de emissão 
     * esteja entre as datas de início e fim especificadas.
     *
     * @param dataInicio A data e hora de início do período de emissão (inclusivo).
     * @param dataFim A data e hora de fim do período de emissão (inclusivo).
     * @return Uma lista de objetos NotaFiscalListItem correspondentes ao intervalo de datas especificado.
     */
    List<NotaFiscalListItem> findByDataEmissaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
 