package com.br.compol.getnfe.core.model.notafiscal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serie", length = 10)
    private String serie;

    @Column(name = "numero_da_nota",nullable = false, unique = true)
    private String numeroDaNota;
    
    @Column(name = "data_emissao")
    private LocalDateTime dataEmissao;

    @Column(name = "protocolo_autorizacao", length = 50)
    private String protocoloAutorizacao;
        
    @Column(name = "chave_acesso", length = 50, unique = true)
    private String chaveAcesso;
      
    @Column(name = "valor_pago", precision = 10, scale = 2)
    private BigDecimal valorPago;
    
    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "emitente_id")
    private Emitente emitente;

    @Column(name = "caminho_arquivo_xml", length = 255)
    private String caminhoArquivoXml; 


}



