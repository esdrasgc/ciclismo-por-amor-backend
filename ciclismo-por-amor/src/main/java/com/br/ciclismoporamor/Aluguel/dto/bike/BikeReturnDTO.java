package com.br.ciclismoporamor.Aluguel.dto.bike;

import lombok.Data;

@Data
public class BikeReturnDTO {
    private int id;
    private Float pricePHour;
    private String model;
    private String type;
}
