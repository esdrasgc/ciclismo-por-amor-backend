package com.br.ciclismoporamor.Aluguel;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Aluguel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(nullable = false, unique = true)
    private String identificador;

    @Column(nullable = false)
    private LocalDateTime diaHoraInicio;

    @NonNull
    @Column(nullable = false)
    private String Origem;

    private double[] coorOrigem = new double[2];

    private String Destino;

    private double[] coordDestino = new double[2];

    private Duration tempoDeViagem;

    private double distancia;

    private double preco;

    private AluguelStatus status;

    private String idBike;

}   
