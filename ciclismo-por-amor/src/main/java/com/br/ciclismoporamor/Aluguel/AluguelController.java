package com.br.ciclismoporamor.Aluguel;

import com.br.ciclismoporamor.Aluguel.dto.InfoAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.SaveAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.DevolveBikeDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/aluguel")
@RestControllerAdvice
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    @Operation(summary = "Lista todos os aluguéis")
    public List<InfoAluguelDTO> listAlugueis(@RequestParam(required = false) String id_bike, @RequestParam(required = false) AluguelStatus status) {
        return aluguelService.listarAlugueis(id_bike, status);
    }

    @PostMapping
    @Operation(summary = "Salva um novo aluguel")
    public InfoAluguelDTO saveAluguel(@RequestBody SaveAluguelDTO aluguel){
        return aluguelService.saveAluguel(aluguel);
    }

    @PutMapping("/{identificador}")
    @Operation(summary = "Edita um aluguel")
    public Aluguel editAluguel(
            @PathVariable String identificador,
            @RequestBody DevolveBikeDTO devolveBikeDTO
    ){
        return aluguelService.devolverBike(identificador, devolveBikeDTO);
    }

    @DeleteMapping("/{identificador}")
    @Operation(summary = "Deleta aluguel")
    public void deletAluguel(
            @PathVariable String identificador
    ){
        aluguelService.deleteAluguel(identificador);
    }
}
