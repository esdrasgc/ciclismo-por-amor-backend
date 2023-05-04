package com.br.ciclismoporamor.Aluguel.dto;

import lombok.Data;

@Data
public class DevolveBikeDTO {
    private String destino;
    private double[] coordDestino;
}
