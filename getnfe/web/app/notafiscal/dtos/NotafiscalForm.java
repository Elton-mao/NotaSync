package com.br.compol.getnfe.web.app.notafiscal.dtos;

import java.math.BigDecimal;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotafiscalForm {
    
    private String serie;

    private String dataEmissao;

    private String protocoloAutorizacao;

    private String tipoEmissao;

    private String chaveAcesso;

    private BigDecimal valorPago;

    private BigDecimal valorTotal;

   
}
