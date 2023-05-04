package com.br.ciclismoporamor.Aluguel;

import com.br.ciclismoporamor.Aluguel.dto.SaveAluguelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

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

    public Aluguel saveAluguel(SaveAluguelDTO saveAluguelDTO){
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("http://localhost:8080/bike/", String.class);
//        if (response.getStatusCode().is2xxSuccessful()) {
//            String id_bike = response.getBody();
//            if (id_bike != null){
        Aluguel aluguel = new Aluguel();
        aluguel.setIdentificador(UUID.randomUUID().toString());
        aluguel.setDiaHoraInicio(LocalDateTime.now());
        aluguel.setOrigem(saveAluguelDTO.getOrigem());
        aluguel.setStatus(AluguelStatus.CONFIRMADO);
        aluguel.setCoordOrigem(saveAluguelDTO.getCoordInicial());
//        aluguel.setIdBike(id_bike);
        aluguelRepository.save(aluguel);
        return aluguel;
//            }
//        }
//        throw new RuntimeException("Não tem bicicletas disponíveis");

    }
}
