package com.br.compol.getnfe.web.app.notafiscal.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.compol.getnfe.core.service.notafiscal.NotaFiscalService;
import com.br.compol.getnfe.web.app.notafiscal.dtos.NotaFiscalListItem;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notafiscal")
public class NotaFiscalController {

    private final NotaFiscalService notaFiscalService;

    @GetMapping
    public ModelAndView notasFiscais() {
        var notasFiscais = notaFiscalService.findAll();
        var model = Map.of("notasFiscais", notasFiscais);
        return new ModelAndView("notas-fiscais.html", model);
    }
    @GetMapping("/mes")
    public ModelAndView notasFiscaisDoMes() {
        List<NotaFiscalListItem> notasFiscais = notaFiscalService.findByDataEmissaoBetween(
        LocalDate.now().withDayOfMonth(1).atStartOfDay(),
        LocalDateTime.now());
        var model = Map.of("notasFiscais", notasFiscais);
        return new ModelAndView("notas-fiscais.html", model);
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhesNotaFiscal(@PathVariable Long id) {
        var notaFiscal = notaFiscalService.findById(id);
        var model = Map.of("notaFiscal", notaFiscal);
        return new ModelAndView("detalhes-nota-fiscal.html", model);

}
}
