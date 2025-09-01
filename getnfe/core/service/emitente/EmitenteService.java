package com.br.compol.getnfe.core.service.emitente;

import java.math.BigDecimal;
import java.util.List;

import com.br.compol.getnfe.core.model.notafiscal.Emitente;
import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.core.model.notafiscal.NotaFiscal;
import com.br.compol.getnfe.web.app.emitente.dto.EmitenteListItem;

/**
 * Interface de serviço para gerenciar operações de Emitente.
 */
/**
 * Interface EmitenteService.
 * 
 * Esta interface define os métodos para manipulação de objetos do tipo
 * Emitente,
 * incluindo criação, conversão, listagem e cálculo de valores relacionados.
 */
public interface EmitenteService {

    /**
     * Cria uma instância de Emitente com base no objeto NfeProc fornecido.
     *
     * @param fNfeProc o objeto NfeProc contendo os dados necessários para criar um
     *                 Emitente
     * @return a instância de Emitente criada
     */
    Emitente create(NfeProc fNfeProc);

    /**
     * Converte um objeto Emitente em um EmitenteListItem.
     *
     * @param emitente o objeto Emitente a ser convertido
     * @return uma representação EmitenteListItem do Emitente fornecido
     */
    EmitenteListItem getEmitenteById(Long id);

    /**
     * Lista todos os Emitentes cadastrados.
     *
     * @return uma lista contendo todos os Emitentes
     */
    List<EmitenteListItem> listAllEmitentes();

    /**
     * Calculates the total value of invoices for a given issuer.
     *
     * @param idEmitente the unique identifier of the issuer whose invoices' total
     *                   value is to be calculated
     * @return the total value of all invoices associated with the specified issuer
     *         as a {@link BigDecimal}
     */

    /**
     * Calculates the total value of a list of invoices (NotaFiscal).
     *
     * @param notasFiscais the list of invoices to calculate the total value from
     * @return the total value as a BigDecimal
     */
    BigDecimal calcularValorTotalDeNotas(List<NotaFiscal> notasFiscais);

}
