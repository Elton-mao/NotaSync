package com.br.compol.getnfe.core.service.dashboard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.br.compol.getnfe.core.service.emitente.EmitenteService;
import com.br.compol.getnfe.core.service.notafiscal.NotaFiscalService;
import com.br.compol.getnfe.web.app.dashboard.dtos.DashBoardListItem;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotaFiscalListItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashboardService {
  private final NotaFiscalService  notaFiscalService;

  private final EmitenteService emitenteService; 

  @Override
  public DashBoardListItem getDashboardData() {
    //busca as notas fiscais do último mês
    List<NotaFiscalListItem> notasFiscais = notaFiscalService.findByDataEmissaoBetween(
        LocalDate.now().withDayOfMonth(1).atStartOfDay(),
        LocalDateTime.now());
    //calcula o valor total das notas fiscais do último mês
    BigDecimal valorTotalUltimoMes = notasFiscais.stream()
        .map(n -> new BigDecimal(n.getValorTotal().toString()))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    //conta a quantidade de emitentes
    int emitentes = emitenteService.listAllEmitentes().size(); 
    //monta o DTO de retorno
    return DashBoardListItem.builder()
        .qtdadeNfe(notasFiscais.size())
        .qtdFornecedores(emitentes)
        .valorTotalNfeUltimoMes(valorTotalUltimoMes)
        .build();
  } 
  //método para obter os totais dos últimos 3 meses
  public Map<String, BigDecimal> getTotaisUltimos3Meses() {
    LocalDate hoje = LocalDate.now();
    LocalDate primeiroDia3MesesAtras = hoje.minusMonths(2).withDayOfMonth(1); // Ex: se hoje é maio, começa em março
  
    LocalDate ultimoDiaMesAtual = hoje.withDayOfMonth(hoje.lengthOfMonth());

    LocalDateTime inicio = primeiroDia3MesesAtras.atStartOfDay();

    LocalDateTime fim = ultimoDiaMesAtual.atTime(LocalTime.MAX);
    
    List<NotaFiscalListItem> notas = notaFiscalService.findByDataEmissaoBetween(inicio, fim);
    // Agrupa por mês e soma os valores
    return notas.stream()
        .collect(Collectors.groupingBy(
            nf -> nf.getDataEmissao().getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR")),
            Collectors.reducing(BigDecimal.ZERO, NotaFiscalListItem::getValorTotal, BigDecimal::add)
        ));
}


}
