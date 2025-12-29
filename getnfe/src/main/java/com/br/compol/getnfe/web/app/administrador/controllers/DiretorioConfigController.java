package com.br.compol.getnfe.web.app.administrador.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.compol.getnfe.core.configurações.diretorios.DiretorioService;
import com.br.compol.getnfe.core.configurações.diretorios.TipoDiretorio;
import com.br.compol.getnfe.web.app.administrador.dtos.DiretorioConfigForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/diretorios")
public class DiretorioConfigController {
    private final DiretorioService service; 
    @GetMapping
    public ModelAndView configuraDiretorios() {
        var model = Map.of(
            "DiretorioConfigForm", new DiretorioConfigForm(),
            "tiposDiretorio", TipoDiretorio.values(),
            "configuracoes", service.buscarTodosDiretorios()
        );
        return new ModelAndView("/configuracao", model);
    }

    @PostMapping
    public String salvarConfigurações(DiretorioConfigForm form) {
        service.salvarDiretorio(form);
        return "redirect:/admin/diretorios";
    }

    @GetMapping("/{id}")
    public ModelAndView atualizarDiretorio(@PathVariable Long id) {
         var model = Map.of(
            "DiretorioConfigForm", service.buscarDiretorioPorId(id),
            "tiposDiretorio", TipoDiretorio.values(),
            "configuracoes", service.buscarTodosDiretorios()
        );
        return new ModelAndView("/atualizar_configuracao", model);
    }
    @PostMapping("/{id}")
    public String atualizarConfigurações(@PathVariable Long id,@ModelAttribute DiretorioConfigForm form) {
        service.atualizarDiretorio(id, form);
        return "redirect:/admin/diretorios";
    }
}
