package com.br.ciclismoporamor.Aluguel;

import com.br.ciclismoporamor.Aluguel.dto.DevolveBikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public Page<Aluguel> listarAlugueis(String id_bike, Pageable pageable){
        if (id_bike == null){
            return aluguelRepository.findAll(pageable);
        }

        return aluguelRepository.findByIdBike(id_bike, pageable);
    }


    public Aluguel devolverBike(String identificador, DevolveBikeDTO devolveBikeDTO){
        Aluguel aluguelDB = aluguelRepository.findByIdentificador(identificador);

        aluguelDB.setCoordDestino(devolveBikeDTO.getCoorDestino());
        aluguelDB.setDestino(devolveBikeDTO.getDestino());
        aluguelDB.setStatus(AluguelStatus.FINALIZADO);
        aluguelDB.setTempoDeViagem(Duration.between(aluguelDB.getDiaHoraInicio(), LocalDateTime.now()));
        aluguelDB.setDistancia(-1);
        aluguelDB.setPreco(-1);

        aluguelRepository.save(aluguelDB);
        return aluguelDB;
    }


}
