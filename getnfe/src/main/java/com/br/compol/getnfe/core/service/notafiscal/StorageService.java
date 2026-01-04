package com.br.compol.getnfe.core.service.notafiscal;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

      void upload(List<MultipartFile> file);
      

      
}
