package com.br.ciclismoporamor.Aluguel.dto.bike;

import lombok.Data;

@Data
public class BikeReturnDTO {
    private String identifier;
    private double pricePHour;
    private String model;
    private String type;
}
