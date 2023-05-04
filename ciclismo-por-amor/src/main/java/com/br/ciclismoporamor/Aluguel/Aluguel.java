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
    private String origem;

    private double[] coordOrigem;

    private String destino;

    private double[] coordDestino;

    private double tempoDeViagem;

    private double distancia;

    private double preco;

    private double precoPorHora;

    private AluguelStatus status;

    private String idBike;

}   
