CREATE TABLE nota_fiscal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    serie VARCHAR(10),
    data_emissao DATETIME,
    protocolo_autorizacao VARCHAR(50),
    chave_acesso VARCHAR(50) UNIQUE,
    valor_pago DECIMAL(10,2),
    valor_total DECIMAL(10,2),
    numero_da_nota VARCHAR(150)
   
);
