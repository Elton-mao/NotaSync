package com.br.compol.getnfe.web.app.emitente.dto;

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

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EmitenteListItem {
    private Long id; 
    private String cnpj; 
    private String razaoSocial; 
    private String inscricaoEstadual; 
    private List<NotaFiscalListItem> notasFiscais;
    private int totalNotasFiscais;
    private String valorTotalEmitido; // Formato monetário 

    
    public String getCnpj(){
        return NumberUtils.formatCNPJ(cnpj);
    }
    public String getCnpjFormatado() {
    return NumberUtils.formatCNPJ(cnpj);
}



}
