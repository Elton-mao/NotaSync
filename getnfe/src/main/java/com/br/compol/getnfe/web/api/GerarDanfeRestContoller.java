package com.br.compol.getnfe.web.api;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
//Serviço externo para integração com uma alltomação python de download de notas da dakhia
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gerardanfe")
public class GerarDanfeRestContoller {

    
    private final RestTemplate restTemplate = new RestTemplate(); 

    private final String danfeApiUrl = "https://ws.meudanfe.com/api/v1/get/nfe/xmltodanfepdf/API";

    @PostMapping
    public ResponseEntity<ByteArrayResource> gerarDanfeViaUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Lê o conteúdo do XML enviado via upload
            String xmlContent = new String(file.getBytes(), StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_XML);

            HttpEntity<String> request = new HttpEntity<>(xmlContent, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(danfeApiUrl, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String base64Pdf = response.getBody();

                // Remove aspas, se presentes
                if (base64Pdf != null && base64Pdf.startsWith("\"") && base64Pdf.endsWith("\"")) {
                    base64Pdf = base64Pdf.substring(1, base64Pdf.length() - 1);
                }

                byte[] pdfBytes = Base64.getDecoder().decode(base64Pdf);
                ByteArrayResource resource = new ByteArrayResource(pdfBytes);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"danfe.pdf\"")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                        .body(new ByteArrayResource(("Erro ao gerar o DANFE: " + response.getStatusCode()).getBytes()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ByteArrayResource(("Erro ao processar o upload do XML: " + e.getMessage()).getBytes()));
        }
    }

}
