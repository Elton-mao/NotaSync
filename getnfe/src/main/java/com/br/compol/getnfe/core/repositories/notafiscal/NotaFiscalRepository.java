package com.br.compol.getnfe.core.repositories.notafiscal;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.compol.getnfe.core.model.notafiscal.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Long> {

    List<NotaFiscal> findByDataEmissaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    

}
