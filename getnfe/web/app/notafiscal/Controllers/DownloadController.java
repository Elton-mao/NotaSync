package com.br.compol.getnfe.web.app.notafiscal.Controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.compol.getnfe.core.service.notafiscal.NotaFiscalService;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotaFiscalListItem;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Base64;
import lombok.RequiredArgsConstructor;

import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/download")
public class DownloadController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final NotaFiscalService notaFiscalService;
    private final String MEU_DANFE_API_URL = "https://ws.meudanfe.com/api/v1/get/nfe/xmltodanfepdf/API";

    /**
     * Lida com o download de um arquivo XML associado a uma entidade NotaFiscal.
     *
     * @param id O ID da entidade NotaFiscal cujo arquivo XML será baixado.
     * @return Um ResponseEntity contendo o arquivo XML como um recurso para
     *         download.
     * @throws IOException      Se ocorrer um erro de E/S ao acessar o arquivo.
     * @throws RuntimeException Se o arquivo não existir ou não for legível.
     */
    @GetMapping("/nota/{id}")
    public ResponseEntity<Resource> downloadXml(@PathVariable Long id) throws IOException {
        NotaFiscalListItem notafiscal = notaFiscalService.findById(id);

        Path path = Paths.get(notafiscal.getCaminhoArquivoXml());
        System.out.println("valor do path " + path);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Arquivo não pode ser lido: " + path);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    /**
     * Endpoint para gerar e baixar o DANFE (Documento Auxiliar da Nota Fiscal
     * Eletrônica)
     * como um arquivo PDF para uma Nota Fiscal identificada pelo seu ID.
     *
     * @param id O ID da Nota Fiscal para a qual o DANFE deve ser gerado.
     * @return Um ResponseEntity contendo o PDF gerado como um ByteArrayResource em
     *         caso de sucesso,
     *         ou uma mensagem de erro como um ByteArrayResource em caso de falha.
     * @throws IOException Se ocorrer um erro ao ler o arquivo XML associado à Nota
     *                     Fiscal.
     *
     *                     O método realiza os seguintes passos:
     *                     1. Recupera a Nota Fiscal pelo seu ID usando o
     *                     notaFiscalService.
     *                     2. Lê o arquivo XML associado à Nota Fiscal.
     *                     3. Envia o conteúdo XML para uma API externa
     *                     (MEU_DANFE_API_URL) para gerar o DANFE em formato PDF.
     *                     4. Decodifica o PDF codificado em Base64 retornado pela
     *                     API.
     *                     5. Retorna o PDF como um arquivo para download com os
     *                     cabeçalhos apropriados.
     *
     *                     Tratamento de Erros:
     *                     - Se o arquivo XML não puder ser lido, uma resposta de
     *                     erro com status 500 é retornada.
     *                     - Se a chamada à API externa falhar, uma resposta de erro
     *                     com status 500 é retornada.
     */
    @GetMapping("/nota/{id}/danfe")
    public ResponseEntity<ByteArrayResource> gerarDanfe(@PathVariable Long id) throws IOException {
        NotaFiscalListItem notafiscal = notaFiscalService.findById(id);
        Path pathXml = Paths.get(notafiscal.getCaminhoArquivoXml());

        try {
            String xmlContent = Files.readString(pathXml);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_XML);

            org.springframework.http.HttpEntity<String> request = new org.springframework.http.HttpEntity<>(xmlContent,
                    headers);

            ResponseEntity<String> response = restTemplate.postForEntity(MEU_DANFE_API_URL, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String base64Pdf = response.getBody();
                // Remover as aspas duplas, caso existam
                if (base64Pdf != null && base64Pdf.startsWith("\"") && base64Pdf.endsWith("\"")) {
                    base64Pdf = base64Pdf.substring(1, base64Pdf.length() - 1);
                }
                byte[] pdfBytes = Base64.getDecoder().decode(base64Pdf);
                ByteArrayResource resource = new ByteArrayResource(pdfBytes);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + pathXml.getFileName().toString().replace(".xml", ".pdf")
                                        + "\"")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                //         .body(new ByteArrayResource(("Erro ao gerar o DANFE: " + response.getStatusCode()).getBytes()));
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ByteArrayResource("Erro ao gerar o DANFE: ".getBytes()));
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ByteArrayResource(("Erro ao ler o arquivo XML: " + e.getMessage()).getBytes()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ByteArrayResource(
                            ("Erro ao comunicar com a API Meu Danfe: " + e.getMessage()).getBytes()));
        }
    }

    /**
     * Lida com a visualização do DANFE (Documento Auxiliar da Nota Fiscal Eletrônica)
     * para uma determinada Nota Fiscal pelo seu ID. Este método recupera o arquivo XML
     * associado à Nota Fiscal, envia-o para uma API externa para gerar um PDF,
     * e retorna o PDF como resposta.
     *
     * @param id O ID da Nota Fiscal para visualizar o DANFE.
     * @return Um ResponseEntity contendo o PDF gerado como um ByteArrayResource
     *         com o tipo de conteúdo application/pdf. Se ocorrer um erro, uma mensagem
     *         de erro apropriada é retornada como um ByteArrayResource.
     * @throws IOException Se houver um problema ao ler o arquivo XML.
     */
    @GetMapping("/nota/{id}/danfe/visualizar")
    public ResponseEntity<ByteArrayResource> visualizarDanfe(@PathVariable Long id) throws IOException {
        NotaFiscalListItem notafiscal = notaFiscalService.findById(id);
        Path pathXml = Paths.get(notafiscal.getCaminhoArquivoXml());

        try {
            String xmlContent = Files.readString(pathXml);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_XML);

            org.springframework.http.HttpEntity<String> request = new org.springframework.http.HttpEntity<>(xmlContent,
                    headers);

            ResponseEntity<String> response = restTemplate.postForEntity(MEU_DANFE_API_URL, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String base64Pdf = response.getBody();
                // Remover as aspas duplas, caso existam
                if (base64Pdf != null && base64Pdf.startsWith("\"") && base64Pdf.endsWith("\"")) {
                    base64Pdf = base64Pdf.substring(1, base64Pdf.length() - 1);
                }
                byte[] pdfBytes = Base64.getDecoder().decode(base64Pdf);
                ByteArrayResource resource = new ByteArrayResource(pdfBytes);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "inline; filename=\"" + pathXml.getFileName().toString().replace(".xml", ".pdf") + "\"")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ByteArrayResource(("Erro ao gerar o DANFE: " + response.getStatusCode()).getBytes()));
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ByteArrayResource(("Erro ao ler o arquivo XML: " + e.getMessage()).getBytes()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ByteArrayResource(
                            ("Erro ao comunicar com a API Meu Danfe: " + e.getMessage()).getBytes()));
        }
    }
}
