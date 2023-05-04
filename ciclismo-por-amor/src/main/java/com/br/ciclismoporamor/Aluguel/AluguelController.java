package com.br.ciclismoporamor.Aluguel;

import com.br.ciclismoporamor.Aluguel.dto.InfoAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.SaveAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.DevolveBikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public List<InfoAluguelDTO> listAlugueis(@RequestParam(required = false) String id_bike) {
        return aluguelService.listarAlugueis(id_bike);
    }

    @PostMapping
    public InfoAluguelDTO saveAluguel(@RequestBody SaveAluguelDTO aluguel){
        return aluguelService.saveAluguel(aluguel);
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
