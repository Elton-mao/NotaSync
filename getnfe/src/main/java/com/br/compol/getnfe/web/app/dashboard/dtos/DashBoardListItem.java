package com.br.compol.getnfe.web.app.dashboard.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.br.compol.getnfe.core.utils.NumberUtils;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotaFiscalListItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DashBoardListItem {
    private int qtdadeNfe;
    private int qtdFornecedores;
    private BigDecimal valorTotalNfeUltimoMes;
    @Builder.Default
    private List <NotaFiscalListItem> ultimasNfe5dias = new ArrayList<>();
    public String getValorTotalNfeUltimoMes(){
        return NumberUtils.formatMoney(valorTotalNfeUltimoMes);
    }

}
