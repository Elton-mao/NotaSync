package com.br.compol.getnfe.web.app.notafiscal.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.compol.getnfe.core.service.notafiscal.StorageService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {
    private final StorageService storage; 
    @PostMapping(
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> uploadXML(@RequestParam List<MultipartFile> arquivos) {
        storage.upload(arquivos);
        return ResponseEntity.status(HttpStatus.CREATED).body("Upload Concluido com Sucesso");
    }

}
