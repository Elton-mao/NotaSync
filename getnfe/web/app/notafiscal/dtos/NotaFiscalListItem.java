package com.br.compol.getnfe.web.app.notafiscal.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.compol.getnfe.core.model.notafiscal.Emitente;
import com.br.compol.getnfe.core.utils.DataUtils;
import com.br.compol.getnfe.core.utils.NumberUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
* <p>Atributos:</p>
* <ul>
*   <li><b>serie</b>: A série da nota fiscal.</li>
*   <li><b>dataEmissao</b>: A data e hora em que a nota fiscal foi emitida.</li>
*   <li><b>protocoloAutorizacao</b>: O protocolo de autorização da nota fiscal.</li>
*   <li><b>ambiente</b>: O ambiente em que a nota fiscal foi emitida (ex.: produção ou teste).</li>
*   <li><b>versaoXml</b>: A versão do esquema XML usado para a nota fiscal.</li>
*   <li><b>versaoXlst</b>: A versão do XSLT usado para a nota fiscal.</li>
*   <li><b>tipoEmissao</b>: O tipo de emissão da nota fiscal.</li>
*   <li><b>chaveAcesso</b>: A chave de acesso da nota fiscal.</li>
*   <li><b>quantidadeTotalItems</b>: A quantidade total de itens na nota fiscal.</li>
*   <li><b>tributosTotais</b>: O valor total dos tributos na nota fiscal.</li>
*   <li><b>valorPago</b>: O valor total pago pela nota fiscal.</li>
*   <li><b>valorTotal</b>: O valor total da nota fiscal.</li>
*   <li><b>valorTroco</b>: O valor do troco da nota fiscal.</li>
*   <li><b>formaPagamento</b>: O método de pagamento utilizado na nota fiscal.</li>
*   <li><b>siteReceipt</b>: As informações do site do recibo.</li>
*   <li><b>produtos</b>: A lista de produtos incluídos na nota fiscal.</li>
*   <li><b>emitente</b>: O emissor da nota fiscal.</li>
*   <li><b>consumidor</b>: O consumidor associado à nota fiscal.</li>
* </ul>
* 
* <p>Anotações:</p>
* <ul>
*   <li><b>@Data</b>: Gera os métodos getters, setters, equals, hashCode e toString.</li>
*   <li><b>@Builder</b>: Habilita o padrão builder para criação de objetos.</li>
*   <li><b>@AllArgsConstructor</b>: Gera um construtor com todos os campos como parâmetros.</li>
*   <li><b>@Id</b>: Marca o campo como chave primária.</li>
*   <li><b>@GeneratedValue</b>: Especifica a estratégia de geração para os valores da chave primária.</li>
* </ul>
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalListItem {
    
    private Long id;
    
    private String serie;

    private String numeroDaNota; 
    
    private  LocalDateTime dataEmissao;

    private String protocoloAutorizacao;

    private String chaveAcesso;

    private BigDecimal valorPago;

    private BigDecimal valorTotal;

    private Emitente emitente;

    private String caminhoArquivoXml; 

    public String getValorTotalFormatado(){
        return NumberUtils.formatMoney(valorTotal);
    }

    public String getDataEmisaoFormatada(){
        return DataUtils.formataDataHoraBr(dataEmissao);
    }

}
