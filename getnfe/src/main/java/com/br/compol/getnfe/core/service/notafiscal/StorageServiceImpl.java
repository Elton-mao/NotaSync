package com.br.compol.getnfe.core.service.notafiscal;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.compol.getnfe.core.configurações.diretorios.DiretorioService;
import com.br.compol.getnfe.core.configurações.diretorios.TipoDiretorio;

@Service
public class StorageServiceImpl implements StorageService {
    
    private final DiretorioService diretorioService;
    private final XmlService xmlService; 
 
    public StorageServiceImpl(DiretorioService diretorioService, XmlService xmlService) {
        this.diretorioService = diretorioService; 
        this.xmlService = xmlService;
    }
    

    @Override
    public void upload(List<MultipartFile> arquivos) {
        String nomeArquivo;
        try{
        for (MultipartFile arquivo : arquivos){
           
                if (arquivo.isEmpty()) {
                    throw new RuntimeException("Arquivo vazio: " + arquivo.getOriginalFilename());
                }
                nomeArquivo = Paths.get(Objects.requireNonNull(arquivo.getOriginalFilename()))
                .getFileName().toString();

                 if (!nomeArquivo.endsWith(".xml")) {
                    throw new RuntimeException("Tipo de arquivo inválido: " + arquivo.getOriginalFilename());
                }
                Path caminhoCompleto = diretorioService.obterDiretorioPorTipo(TipoDiretorio.DIR_XML).resolve(nomeArquivo);
                arquivo.transferTo(caminhoCompleto);
            }
            xmlService.importarTodosXmls();

        }
            catch (Exception e){
                    throw new RuntimeException("Erro ao fazer upload do arquivo: ", e);
             }
        }
        

    



}
