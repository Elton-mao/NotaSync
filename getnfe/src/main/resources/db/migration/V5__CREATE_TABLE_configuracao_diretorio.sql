CREATE TABLE configuracao_diretorio(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    caminho_diretorio VARCHAR(255) NOT NULL UNIQUE,
    tipo_diretorio VARCHAR(50) NOT NULL UNIQUE
);