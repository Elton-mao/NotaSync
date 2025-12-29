package com.br.compol.getnfe.core.configurações.diretorios;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.compol.getnfe.web.app.administrador.dtos.DiretorioConfigDetalhes;
import com.br.compol.getnfe.web.app.administrador.dtos.DiretorioConfigForm;

import lombok.RequiredArgsConstructor;
/**
 * Serviço para gerenciar operações relacionadas a diretórios configurados.
 */
@Service
@RequiredArgsConstructor
public class DiretorioService {
    private final DiretorioConfigRepository diretorioRepository; 
    private final DiretorioConfigMapper diretorioMapper;

     /**
     * Obtém o caminho do diretório configurado para o tipo especificado.
     *
     * @param tipo O tipo de diretório a ser obtido.
     * @return O caminho do diretório como um objeto Path.
     * @throws RuntimeException se o diretório não for encontrado ou não existir.
     */
 
    public Path obterDiretorioPorTipo(TipoDiretorio tipo) {
        DiretorioConfig diretorioConfig = diretorioRepository.findByTipoDiretorio(tipo)
                .orElseThrow(() -> new RuntimeException("Configuração de diretório não encontrada para o tipo: " + tipo));
        Path path = Path.of(diretorioConfig.getCaminhoDiretorio());

        if (!Files.exists(path)) {
            throw new RuntimeException("Diretório não encontrado: " + path.toString());
        }

        return path;
    }

    public List<DiretorioConfigDetalhes> buscarTodosDiretorios() {
        List<DiretorioConfig> diretorioConfigs = diretorioRepository.findAll();
        return diretorioConfigs.stream()
                .map(diretorioMapper::toDiretorioConfigDetalhes)
                .toList();
    }
    public DiretorioConfigDetalhes salvarDiretorio(DiretorioConfigForm form){
        DiretorioConfig config = diretorioMapper.toDiretorioConfig(form);
        DiretorioConfig salvo = diretorioRepository.save(config);
        return diretorioMapper.toDiretorioConfigDetalhes(salvo);
    }

}