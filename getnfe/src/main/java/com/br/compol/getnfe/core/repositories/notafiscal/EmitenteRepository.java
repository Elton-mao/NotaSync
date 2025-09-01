package com.br.compol.getnfe.core.repositories.notafiscal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.compol.getnfe.core.model.notafiscal.Emitente;

public interface EmitenteRepository extends JpaRepository<Emitente, Long> {
    
    Optional<Emitente> findByCnpj(String cnpj); // Método para buscar o emitente pelo CNPJ
    
}
