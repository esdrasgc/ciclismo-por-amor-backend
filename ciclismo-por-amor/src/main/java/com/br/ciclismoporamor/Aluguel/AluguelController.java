package com.br.ciclismoporamor.Aluguel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public Page<Aluguel> listAlugueis(@RequestParam(required = false) String id_bike, Pageable pageable){
        return aluguelService.listarAlugueis(id_bike, pageable);

    }
    
    
}
