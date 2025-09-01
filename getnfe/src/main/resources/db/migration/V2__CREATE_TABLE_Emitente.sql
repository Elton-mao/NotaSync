CREATE Table emitente(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    razao_social VARCHAR(255) NOT NULL,
    inscricao_estadual VARCHAR(20) NOT NULL
); 
ALTER TABLE nota_fiscal
ADD COLUMN emitente_id BIGINT,
ADD CONSTRAINT fk_nota_fiscal_emitente
FOREIGN KEY (emitente_id) REFERENCES emitente(id);
