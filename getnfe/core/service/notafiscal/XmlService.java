package com.br.compol.getnfe.core.service.notafiscal;



/**
 * Interface que representa um serviço para manipulação de operações XML relacionadas à Nota Fiscal.
 */
public interface XmlService {

    /**
     * Importa todos os arquivos XML.
     * Este método é responsável por processar e manipular todos os arquivos XML
     * que precisam ser importados para o sistema.
     */
    void importarTodosXmls();

    /**
     * Busca um arquivo XML específico pelo seu nome.
     *
     * @param nomeDoArquivo o nome do arquivo XML a ser buscado.
     */
    void buscarXml(String nomeDoArquivo);
}

