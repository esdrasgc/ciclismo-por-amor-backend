package com.br.ciclismoporamor.Aluguel;

import com.br.ciclismoporamor.Aluguel.dto.InfoAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.bike.BikeReturnDTO;
import com.br.ciclismoporamor.Aluguel.dto.SaveAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.DevolveBikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.time.Duration;


@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<InfoAluguelDTO> listarAlugueis(String id_bike){
        List<InfoAluguelDTO> lista_final = new ArrayList<>();
        List<Aluguel> lista;

        if (id_bike == null){
            lista = aluguelRepository.findAll();
        } else {
            lista = aluguelRepository.findByIdBike(id_bike); }

        for (Aluguel i : lista){
            lista_final.add(InfoAluguelDTO.covert(i)); }

        return lista_final;
    }

    public InfoAluguelDTO saveAluguel(SaveAluguelDTO saveAluguelDTO){
       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<BikeReturnDTO> response =
               restTemplate.getForEntity("http://localhost:8000/bike/", BikeReturnDTO.class);
       if (response.getStatusCode().is2xxSuccessful()) {
           BikeReturnDTO bike = response.getBody();
           if (bike != null){
                Aluguel aluguel = new Aluguel();
                aluguel.setIdentificador(UUID.randomUUID().toString());
                aluguel.setDiaHoraInicio(LocalDateTime.now());
                aluguel.setOrigem(saveAluguelDTO.getOrigem());
                aluguel.setStatus(AluguelStatus.CONFIRMADO);
                aluguel.setCoordOrigem(saveAluguelDTO.getCoordInicial());
                aluguel.setIdBike(bike.getIdentifier());
                aluguel.setPrecoPorHora(bike.getPricePHour());
                aluguel.setModeloBike(bike.getModel());
                aluguelRepository.save(aluguel);

                return InfoAluguelDTO.covert(aluguel);
           }
       }
//       throw new RuntimeException("Sem bicicletas disponíveis :(");
        Aluguel aluguel = new Aluguel();
        aluguel.setStatus(AluguelStatus.ERRO);
        aluguelRepository.save(aluguel);
        return null;
    }

    public Aluguel devolverBike(String identificador, DevolveBikeDTO devolveBikeDTO){
        Aluguel aluguelDB = aluguelRepository.findByIdentificador(identificador);

        aluguelDB.setCoordDestino(devolveBikeDTO.getCoordDestino());
        aluguelDB.setDestino(devolveBikeDTO.getDestino());
        aluguelDB.setStatus(AluguelStatus.FINALIZADO);
        aluguelDB.setTempoDeViagem(Duration.between(aluguelDB.getDiaHoraInicio(), LocalDateTime.now()));
        aluguelDB.setDistancia(-1);
        aluguelDB.setPreco(-1);

        aluguelRepository.save(aluguelDB);
        return aluguelDB;
    }

    public void deleteAluguel(String identificador){
        Aluguel aluguelDB = aluguelRepository.findByIdentificador(identificador);
        if (aluguelDB != null){
            aluguelRepository.delete(aluguelDB);
        }
    }

}
