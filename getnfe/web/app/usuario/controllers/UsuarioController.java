/**
 * Controller responsável por gerenciar as operações relacionadas aos usuários no sistema.
 * 
 * <p>Fornece endpoints para:
 * <ul>
 *   <li>Exibir o formulário de cadastro de usuário</li>
 *   <li>Salvar um novo usuário</li>
 *   <li>Buscar um usuário pelo ID</li>
 *   <li>Remover um usuário pelo ID</li>
 * </ul>
 *
 * <p>Utiliza o serviço {@link UsuarioService} para realizar as operações de negócio.
 *
 * <p>Mapeado para o caminho base "/usuarios".
 *
 * @author elton.almeida
 */
package com.br.compol.getnfe.web.app.usuario.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.compol.getnfe.core.service.usuario.UsuarioService;
import com.br.compol.getnfe.enums.GruposDeUsuario;
import com.br.compol.getnfe.web.app.usuario.dtos.UsuarioForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ModelAndView findAll() {
        var model = Map.of(
                "usuario", usuarioService.findAll());
        return new ModelAndView("lista_de_usuarios", model);
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var model = Map.of(
                "usuarioForm", new UsuarioForm(),
                "grupoDeUsuario", GruposDeUsuario.values());
        return new ModelAndView("formulario_de_usuario", model);

    }

    @PostMapping("/cadastrar")
    public String save(@ModelAttribute UsuarioForm form) {
        usuarioService.save(form);
        return "redirect:/usuarios";

    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        var usuario = usuarioService.findById(id);
        var model = Map.of(
                "usuario", usuario);
        return new ModelAndView("perfil_do_usuario", model);

    }

    @GetMapping("/remover/{id}")
    public String removerPorId(@PathVariable Long id) {
        usuarioService.removerPorId(id);
        return "redirect:/usuarios";

    }

    @GetMapping("/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable Long id){
        var usuario = usuarioService.findById(id);
        var model = Map.of("usuarioForm",usuario,"grupoDeUsuario",GruposDeUsuario.values()); 
        return new ModelAndView("formulario_de_atualizacao_de_usuario",model);
    }
   
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,@ModelAttribute UsuarioForm usuarioForm){
        System.out.println(usuarioForm);
        usuarioService.atualizar(id, usuarioForm); 
        return "redirect:/usuarios/" + id ;

    }


}
