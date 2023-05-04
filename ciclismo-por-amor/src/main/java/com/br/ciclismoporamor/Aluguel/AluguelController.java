package com.br.ciclismoporamor.Aluguel;

import com.br.ciclismoporamor.Aluguel.dto.DevolveBikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public Page<Aluguel> listAlugueis(@RequestParam(required = false) String id_bike, Pageable pageable){
        return aluguelService.listarAlugueis(id_bike, pageable);

    }

    @PutMapping("/{identificador}")
    public Aluguel editAluguel(@PathVariable String identificador, @RequestBody DevolveBikeDTO devolveBikeDTO){
        return aluguelService.devolverBike(identificador, devolveBikeDTO);
    }


    @DeleteMapping("/{identificador}")
    public void deletAluguel(@PathVariable String identificador){
        aluguelService.deleteAluguel(identificador);
    }
    
}
