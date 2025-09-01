
package com.br.compol.getnfe.core.service.notafiscal;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.compol.getnfe.core.mappers.notafiscal.NotafiscalMapper;
import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.core.repositories.notafiscal.NotaFiscalRepository;
import com.br.compol.getnfe.core.service.emitente.EmitenteService;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotaFiscalListItem;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Implementação do serviço para gerenciar entidades de Nota Fiscal.
 * Esta classe fornece métodos para criar, recuperar e listar registros de Nota
 * Fiscal.
 * Utiliza um mapper para converter entre modelos de domínio e DTOs, e um
 * repositório
 * para interagir com o banco de dados.
 * 
 * <p>
 * Anotações:
 * <ul>
 * <li>@Service - Marca esta classe como um componente de serviço do
 * Spring.</li>
 * <li>@RequiredArgsConstructor - Gera um construtor com os campos obrigatórios
 * (campos finais).</li>
 * <li>@Transactional - Garante que o método create seja executado dentro de um
 * contexto transacional.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Dependências:
 * <ul>
 * <li>NotafiscalMapper - Responsável por mapear entre modelos de domínio e
 * DTOs.</li>
 * <li>NotaFiscalRepository - Repositório para acessar os dados de Nota
 * Fiscal.</li>
 * </ul>
 * </p>
 * 
 * Métodos:
 * <ul>
 * <li>{@code create(NfeProc notafiscalForm, String xmlFile)} - Salva uma nova
 * entidade de Nota Fiscal no banco de dados.</li>
 * <li>{@code findById(Long id)} - Recupera uma Nota Fiscal pelo seu ID e a
 * mapeia para um DTO.</li>
 * <li>{@code findAll()} - Recupera todos os registros de Nota Fiscal e os
 * mapeia para uma lista de DTOs.</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
public class NotaFiscalServiceImpl implements NotaFiscalService {

    private final NotafiscalMapper notafiscalMapper;
    private final NotaFiscalRepository notaFiscalRepository;
    private final EmitenteService emitenteService;

    @Override
    @Transactional
    public void create(NfeProc nfeProc, String xmlFile) {
        var emitente = emitenteService.create(nfeProc);
        notaFiscalRepository.save(notafiscalMapper.toNotafiscal(nfeProc, xmlFile, emitente));
    }

    @Override
    public NotaFiscalListItem findById(Long id) {
        return notaFiscalRepository.findById(id)
                .map(notafiscalMapper::toNotafiscalListItem)
                .orElseThrow(() -> new IllegalArgumentException("Nota fiscal not found with id: " + id));
    }

    @Override
    public List<NotaFiscalListItem> findAll() {
        return notaFiscalRepository.findAll()
                .stream()
                .map(notafiscalMapper::toNotafiscalListItem)
                .toList();
    }

    @Override
    public List<NotaFiscalListItem> findByDataEmissaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return notaFiscalRepository.findByDataEmissaoBetween(dataInicio, dataFim)
                .stream()
                .map(notaFiscal -> notafiscalMapper.toNotafiscalListItem(notaFiscal))
                .toList();
    }

 

}
