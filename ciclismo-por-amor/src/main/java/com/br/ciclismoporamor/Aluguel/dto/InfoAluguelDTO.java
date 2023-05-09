package com.br.ciclismoporamor.Aluguel.dto;

import com.br.ciclismoporamor.Aluguel.Aluguel;
import com.br.ciclismoporamor.Aluguel.AluguelStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InfoAluguelDTO {
    private String modelo;
    private double precoPHora;
    private String origem;
    private LocalDateTime diaHoraInicio;

    private String destino;
    private AluguelStatus status;
    private Integer id;

    public static InfoAluguelDTO covert(Aluguel aluguel) {
        InfoAluguelDTO infoAluguelDTO = new InfoAluguelDTO();
        infoAluguelDTO.setModelo(aluguel.getModeloBike());
        infoAluguelDTO.setPrecoPHora(aluguel.getPrecoPorHora());
        infoAluguelDTO.setOrigem(aluguel.getOrigem());
        infoAluguelDTO.setDiaHoraInicio(aluguel.getDiaHoraInicio());
        infoAluguelDTO.setDestino(aluguel.getDestino());
        infoAluguelDTO.setStatus(aluguel.getStatus());
        infoAluguelDTO.setId(aluguel.getId());
        return infoAluguelDTO;

    }
}
