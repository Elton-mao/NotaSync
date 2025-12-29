package com.br.compol.getnfe.core.configurações.diretorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiretorioConfigRepository extends JpaRepository<DiretorioConfig, Long> {
       Optional<DiretorioConfig> findByTipoDiretorio(TipoDiretorio tipoDiretorio);    
}
