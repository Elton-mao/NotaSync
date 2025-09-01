package com.br.compol.getnfe.core.service.emitente;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.compol.getnfe.core.mappers.emitente.EmitenteMapper;
import com.br.compol.getnfe.core.model.notafiscal.Emitente;
import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.core.model.notafiscal.NotaFiscal;
import com.br.compol.getnfe.core.repositories.notafiscal.EmitenteRepository;
import com.br.compol.getnfe.core.utils.NumberUtils;
import com.br.compol.getnfe.web.app.emitente.dto.EmitenteListItem;
import lombok.RequiredArgsConstructor;

/**
 * Implementação do serviço para gerenciar entidades Emitente.
 * Esta classe fornece métodos para criar, recuperar e listar entidades
 * Emitente,
 * bem como calcular o valor total de suas notas fiscais associadas.
 * Utiliza EmitenteRepository para acesso aos dados e EmitenteMapper para
 * mapeamento
 * entre objetos de domínio e DTOs.
 * 
 * Métodos:
 * - {@link #create(NfeProc)}: Cria uma nova entidade Emitente, caso ela não
 * exista.
 * - {@link #getEmitenteById(Long)}: Recupera uma entidade Emitente pelo seu ID
 * e calcula o valor total de suas notas fiscais.
 * - {@link #listAllEmitentes()}: Lista todas as entidades Emitente.
 * - {@link #calcularValorTotalDeNotas(List)}: Calcula o valor total de uma
 * lista de entidades NotaFiscal.
 * 
 * Dependências:
 * - {@link EmitenteRepository}: Repositório para acesso aos dados de Emitente.
 * - {@link EmitenteMapper}: Mapper para conversão entre Emitente e DTOs
 * relacionados.
 * 
 * Anotações:
 * - {@link Service}: Marca esta classe como um componente de serviço do Spring.
 * - {@link RequiredArgsConstructor}: Gera um construtor com os campos
 * obrigatórios.
 */
@Service
@RequiredArgsConstructor
public class EmitenteServiceImpl implements EmitenteService {

    private final EmitenteRepository emitenteRepository;
    private final EmitenteMapper emitenteMapper;

    @Override
    public Emitente create(NfeProc nfeProc) {
        return emitenteRepository.findByCnpj(nfeProc.getEmitente().getCnpj())
                .orElseGet(() -> {
                    var novoEmitente = emitenteMapper.nfeProcToEmitente(nfeProc);
                    return emitenteRepository.save(novoEmitente);
                });

    }

    @Override
    public EmitenteListItem getEmitenteById(Long id) {
        var emitente = emitenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Emitente não encontrado com o ID: " + id));
        var emitenteListItem = emitenteMapper.toEmitenteListItem(emitente);
        var valorTotalEmitido = calcularValorTotalDeNotas(emitente.getNotasFiscais());
        emitenteListItem.setValorTotalEmitido(NumberUtils.formatMoney(valorTotalEmitido));
        return emitenteListItem;
    }

    @Override
    public List<EmitenteListItem> listAllEmitentes() {
        return emitenteRepository.findAll()
                .stream()
                .map(emitenteMapper::toEmitenteListItem)
                .toList();

    }

    @Override
    public BigDecimal calcularValorTotalDeNotas(List<NotaFiscal> notasFiscais) {
        return notasFiscais.stream()
                .map(nota -> nota.getValorTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
