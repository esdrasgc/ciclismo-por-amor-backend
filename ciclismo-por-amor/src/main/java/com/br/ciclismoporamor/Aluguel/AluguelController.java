package com.br.ciclismoporamor.Aluguel;

import com.br.ciclismoporamor.Aluguel.dto.InfoAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.SaveAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.DevolveBikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/aluguel")
@Api(tags = "Aluguel")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public List<InfoAluguelDTO> listAlugueis(@RequestParam(required = false) String id_bike, @RequestParam(required = false) AluguelStatus status) {
        return aluguelService.listarAlugueis(id_bike, status);
    }

    @PostMapping
    @ApiOperation("Salva um novo aluguel")
    public InfoAluguelDTO saveAluguel(@RequestBody SaveAluguelDTO aluguel){
        return aluguelService.saveAluguel(aluguel);
    }

    @PutMapping("/{identificador}")
    @ApiOperation("Devolve uma bicicleta alugada")
    public Aluguel editAluguel(
            @ApiParam(value = "Identificador do aluguel", example = "12345") @PathVariable String identificador,
            @RequestBody DevolveBikeDTO devolveBikeDTO
    ){
        return aluguelService.devolverBike(identificador, devolveBikeDTO);
    }

    @DeleteMapping("/{identificador}")
    @ApiOperation("Deleta um aluguel")
    public void deletAluguel(
            @ApiParam(value = "Identificador do aluguel", example = "12345") @PathVariable String identificador
    ){
        aluguelService.deleteAluguel(identificador);
    }
}
