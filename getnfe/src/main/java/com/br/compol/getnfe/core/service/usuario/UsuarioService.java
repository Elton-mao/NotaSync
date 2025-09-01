package com.br.compol.getnfe.core.service.usuario;

import java.util.List;

import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioDetails;
import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioForm;


 /** 
 * Interface para operações relacionadas ao gerenciamento de usuários.
 * Fornece métodos para salvar, buscar, listar e remover usuários do sistema.
 */
public interface UsuarioService {
    
    UsuarioDetails save(UsuarioForm usuarioForm);
    /**
     * Busca usuário pelo seu ID.
     *
     * @param id o identificador do usuario para busca
     */
    UsuarioDetails findById(Long id);
    /**
     * returna lista de todos os usuarios no banco de dados
     * @return List de usuarios convertidos em DTO 
     */
    List<UsuarioDetails> findAll();
    /**
     * Remove um usuário pelo seu ID.
     *
     * @param id o identificador do usuário a ser removido
     */
    void removerPorId(Long id);

    /**
     * Atualiza as informações de um usuário com base nos dados fornecidos no {@link UsuarioForm}.
     *
     * @param usuarioForm objeto contendo os dados atualizados do usuário
     */
    public UsuarioDetails atualizar(Long id,UsuarioForm usuarioForm); 
}
