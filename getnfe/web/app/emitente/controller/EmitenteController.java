package com.br.compol.getnfe.web.app.emitente.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.compol.getnfe.core.service.emitente.EmitenteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/emitente")
public class EmitenteController {
    private final EmitenteService emitenteService; 

    @GetMapping
    public ModelAndView index() {
        var emitentes = emitenteService.listAllEmitentes();
        var model = Map.of("emitente",emitentes);
        return new ModelAndView("emitentes",model); 
}
    
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        var emitente = emitenteService.getEmitenteById(id);
        var notas = emitente.getNotasFiscais();
        var model = Map.of("emitente", emitente, "notas", notas);
        return new ModelAndView("detalhes-emitente",model); 
    }


}