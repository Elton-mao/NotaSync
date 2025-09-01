/**
 * Implementação do serviço de usuário que fornece operações para salvar, buscar, listar e remover usuários.
 * Utiliza {@link UsuarioMapper} para conversão entre entidades e DTOs, e {@link UsuarioRepository} para acesso ao repositório de dados.
 * 
 * Métodos:
 * <ul>
 *   <li>{@link #save(UsuarioForm)} - Salva um novo usuário a partir do formulário fornecido.</li>
 *   <li>{@link #findById(Long)} - Busca os detalhes de um usuário pelo seu ID.</li>
 *   <li>{@link #findAll()} - Lista todos os usuários cadastrados.</li>
 *   <li>{@link #removerPorId(Long)} - Remove um usuário pelo seu ID.</li>
 * </ul>
 * 
 * Lança {@link IllegalArgumentException} caso os parâmetros informados sejam inválidos ou não existam.
 * 
 * Anotado com {@link Service} para integração com o Spring e {@link RequiredArgsConstructor} para injeção de dependências via construtor.
 */
package com.br.compol.getnfe.core.service.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.br.compol.getnfe.core.mappers.usuario.UsuarioMapper;
import com.br.compol.getnfe.core.repositories.usuario.UsuarioRepository;
import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioDetails;
import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioForm;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    
    private final UsuarioMapper usuarioMapper;

    private final UsuarioRepository usuarioRepository; 

    /**
     * Salva um novo usuário com base no {@link UsuarioForm} fornecido.
     * <p>
     * Este método mapeia o formulário para uma entidade {@link Usuario}, persiste usando o repositório,
     * e então converte a entidade salva para um DTO {@link UsuarioDetails}.
     * </p>
     *
     * @param form o formulário contendo os dados do usuário a ser salvo
     * @return os detalhes do usuário salvo
     * @throws IllegalArgumentException se o formulário fornecido for nulo ou inválido
     */
    @Override
    public UsuarioDetails save(final UsuarioForm form) {
        return Optional.ofNullable(form)
        .map(usuarioMapper::toUsuario)
        .map(usuarioRepository::save)
        .map(usuarioMapper::toUsuarioDetails)
        .orElseThrow(() -> new IllegalArgumentException("Formulario Invalido")); 
    }

    /**
     * Recupera um {@link UsuarioDetails} pelo seu identificador único.
     *
     * @param id o identificador único do usuário a ser recuperado
     * @return o {@link UsuarioDetails} correspondente ao id fornecido
     * @throws IllegalArgumentException se nenhum usuário com o id especificado existir
     */
    @Override
    public UsuarioDetails findById(Long id) {
       return usuarioRepository.findById(id)
       .map(usuarioMapper::toUsuarioDetails)
       .orElseThrow(() -> new IllegalArgumentException("id informado não Existe")); 
    }

    /**
     * Recupera todos os usuários do repositório e os mapeia para objetos {@link UsuarioDetails}.
     *
     * @return uma lista de {@link UsuarioDetails} representando todos os usuários.
     */
    @Override
    public List<UsuarioDetails> findAll() {
        return usuarioRepository.findAll()
        .stream()
        .map(usuarioMapper::toUsuarioDetails)
        .toList();
    }

    /**
     * Remove um usuário pelo seu identificador único.
     * 
     * Verifica se o usuário com o ID fornecido existe no repositório. 
     * Caso não exista, lança uma IllegalArgumentException.
     * Se existir, remove o usuário correspondente.
     *
     * @param id o identificador único do usuário a ser removido
     * @throws IllegalArgumentException se o ID informado não existir no repositório
     */
    @Override
    public void removerPorId(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Id Informado não existe");
        }
         usuarioRepository.deleteById(id);  
    }

    /**
     * Atualiza os dados de um usuário existente com base no formulário fornecido.
     *
     * @param id O identificador do usuário a ser atualizado.
     * @param usuarioForm O formulário contendo os novos dados do usuário.
     * @return Os detalhes do usuário atualizados.
     * @throws IllegalArgumentException Se o usuário com o ID fornecido não for encontrado.
     */
    @Override
    public UsuarioDetails atualizar(Long id, UsuarioForm usuarioForm) {
        var usuario = usuarioRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        BeanUtils.copyProperties(usuarioForm, usuario,"id","senha"); 
        usuario = usuarioRepository.save(usuario); 
        return usuarioMapper.toUsuarioDetails(usuario);
    }


}
