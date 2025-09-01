package com.br.compol.getnfe.core.model.notafiscal;

import java.util.List;

import com.br.compol.getnfe.core.utils.NumberUtils;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "emitente")
public class Emitente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "cnpj", nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(name = "razao_social", nullable = false, length = 255)
    private String razaoSocial;

    @Column(name = "inscricao_estadual", nullable = true, length = 20)
    private String inscricaoEstadual; 
    
    @OneToMany(mappedBy = "emitente")
    @JsonBackReference
    private List<NotaFiscal> notasFiscais; 


    public String getCnpj(){
        return NumberUtils.formatCNPJ(cnpj);
    }
}
