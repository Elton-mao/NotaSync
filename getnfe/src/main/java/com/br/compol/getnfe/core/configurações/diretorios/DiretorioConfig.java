package com.br.compol.getnfe.core.configurações.diretorios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "configuracao_diretorio")
public class ConfiguracaoDiretorio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "caminho_diretorio", nullable = false, unique = true)
    private String caminhoDiretorio;
    
    @Column(name = "tipo_diretorio", nullable = false, unique = true)
    private TipoDiretorio tipoDiretorio;
}
