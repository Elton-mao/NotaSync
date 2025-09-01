package com.br.compol.getnfe.core.service.notafiscal;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.compol.getnfe.core.model.notafiscal.NfeProc;
import com.br.compol.getnfe.core.utils.XmlUtils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
/**
 * Implementação da interface XmlService responsável por processar arquivos XML.
 * Este serviço lida com a importação e processamento de arquivos XML de um diretório especificado,
 * extraindo dados e salvando-os no banco de dados. Também inclui tratamento de erros
 * e gerenciamento de arquivos processados e com erros.
 * 
 * <p>Funcionalidades:</p>
 * <ul>
 *   <li>Tarefa agendada para importar todos os arquivos XML de um diretório a cada 60 segundos.</li>
 *   <li>Processa arquivos XML individuais extraindo a tag <nfeProc> e convertendo-a em um DTO.</li>
 *   <li>Move arquivos processados com sucesso para um diretório "processados".</li>
 *   <li>Trata erros movendo arquivos problemáticos para um diretório "erro" e registrando mensagens de erro.</li>
 *   <li>Fornece um método para processar um arquivo XML específico pelo nome.</li>
 * </ul>
 * 
 * <p>Dependências:</p>
 * <ul>
 *   <li>{@link NotaFiscalService} - Serviço para lidar com operações de Nota Fiscal.</li>
 *   <li>{@link XmlUtils} - Classe utilitária para processamento de XML.</li>
 *   <li>{@link NfeProc} - DTO que representa os dados da Nota Fiscal.</li>
 * </ul>
 * 
 * <p>Métodos:</p>
 * <ul>
 *   <li>{@code importarTodosXmls()} - Método agendado para importar e processar todos os arquivos XML no diretório.</li>
 *   <li>{@code buscarXml(String nomeDoArquivo)} - Processa um arquivo XML específico pelo nome.</li>
 * </ul>
 * 
 * <p>Exceções:</p>
 * <ul>
 *   <li>Lança {@link RuntimeException} para diretórios inválidos, arquivos ausentes ou erros de processamento.</li>
 * </ul>
 * 
 * <p>Nota:</p>
 * Certifique-se de que os diretórios para arquivos "processados" e "erro" sejam criados manualmente antes de executar o serviço.
 */
@RequiredArgsConstructor
@Service
public class XmlServiceImpl implements XmlService{
    private final NotaFiscalService notaFiscalService;

    @Override
    @Scheduled(fixedDelay = 300000)// Executa a cada 60 segundos
    public void importarTodosXmls() {
        //
        String diretorio = "C:\\Users\\elton\\OneDrive\\Documentos\\xml";
    //   String diretorio = "/home/administrador/app/base-xml";
        
        String caminhoProcessados = "C:\\Users\\elton\\OneDrive\\Documentos\\xml\\processados\\";
       //caminho do servidor  
    //   var caminhoProcessados = "/home/administrador/app/base-xml/processados";

        File pasta = new File(diretorio);

        if (!pasta.exists() || !pasta.isDirectory()) {
            throw new RuntimeException("Diretório inválido: " + diretorio);
        }

        File[] arquivosXml = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (arquivosXml == null || arquivosXml.length == 0) {
            throw new RuntimeException("Nenhum arquivo XML encontrado no diretório.");
        }

        int sucesso = 0;
        int erro = 0;
        List<String> mensagensErro = new ArrayList<>();

        for (File xmlFile : arquivosXml) {
            try {
                String xmlString = Files.readString(xmlFile.toPath(), StandardCharsets.UTF_8);
                // String nfeXml = extrairTagNfeProc(xmlString);
                String nfeXml = XmlUtils.extrairTagNfeProc(xmlString);
                if (nfeXml == null) {
                    mensagensErro.add("Tag <nfeProc> não encontrada no arquivo: " + xmlFile.getName());
                    erro++;
                    continue;
                }

                JAXBContext context = JAXBContext.newInstance(NfeProc.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                NfeProc notaDTO = (NfeProc) unmarshaller.unmarshal(new StringReader(nfeXml));

                Files.move(
                        xmlFile.toPath(),
                        new File(caminhoProcessados, xmlFile.getName())
                                .toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                sucesso++;

                // método que salva no banco

                notaFiscalService.create(notaDTO, Paths.get(caminhoProcessados, xmlFile.getName()).toString());
            } catch (Exception ex) {
                erro++;
                mensagensErro.add("Erro no arquivo " + xmlFile.getName() + ": " + ex.getMessage());
                ex.printStackTrace();

                // Move o arquivo com erro para a pasta "erro"
                try {
                    File pastaErro = new File(diretorio + "//erro"); // já foi criada manualmente
                    Files.move(
                            xmlFile.toPath(),
                            new File(pastaErro, xmlFile.getName()).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException moveEx) {
                    System.out.println("Erro ao mover o arquivo para a pasta 'erro': " + xmlFile.getName());
                    moveEx.printStackTrace();
                }
            }

        }

        System.out.println("Importação concluída. Sucesso: " + sucesso + ", Erros: " + erro);
        mensagensErro.forEach(System.out::println);
    }

    @Override
    public void buscarXml(String nomeDoArquivo) {
        int sucesso = 0;
        int erro = 0;
        try {

            String diretorio = "/home/administrador/app/base-xml"; // <--diretório
            String caminhoProcessados = "/home/administrador/app/base-xml/processados"; // <--diretório
            File xmlFile = new File(diretorio, nomeDoArquivo);
            if (!xmlFile.exists()) {
                throw new RuntimeException("Arquivo XML não encontrado: " + nomeDoArquivo);
            }

            // Lê o conteudo do arquivo
            String xmlString = Files.readString(xmlFile.toPath(), StandardCharsets.UTF_8);
            var nfeXml = XmlUtils.extrairTagNfeProc(xmlString);
            JAXBContext context = JAXBContext.newInstance(NfeProc.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            NfeProc notaDTO = (NfeProc) unmarshaller.unmarshal(new StringReader(nfeXml));

            Files.move(
                    xmlFile.toPath(),
                    new File(caminhoProcessados, xmlFile.getName())
                            .toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            sucesso++;

           notaFiscalService.create(notaDTO, Paths.get(caminhoProcessados, xmlFile.getName()).toString());
        } catch (Exception ex) {
            erro ++;
          throw new RuntimeException("Erro ao processar o arquivo: " + nomeDoArquivo, ex);
         
        }
        System.out.println("Importação concluída. Sucesso: " + sucesso + ", Erros: " + erro);
    }
    }
    

