package com.br.ciclismoporamor.Aluguel;

import com.br.ciclismoporamor.Aluguel.dto.BikeReturnDTO;
import com.br.ciclismoporamor.Aluguel.dto.SaveAluguelDTO;
import com.br.ciclismoporamor.Aluguel.dto.DevolveBikeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.time.Duration;


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
//       RestTemplate restTemplate = new RestTemplate();
//       ResponseEntity<BikeReturnDTO> response =
//               restTemplate.getForEntity("http://localhost:8000/bike/", BikeReturnDTO.class);
//       if (response.getStatusCode().is2xxSuccessful()) {
//           BikeReturnDTO bike = response.getBody();
//           if (bike != null){
                Aluguel aluguel = new Aluguel();
                aluguel.setIdentificador(UUID.randomUUID().toString());
                aluguel.setDiaHoraInicio(LocalDateTime.now());
                aluguel.setOrigem(saveAluguelDTO.getOrigem());
                aluguel.setStatus(AluguelStatus.CONFIRMADO);
                aluguel.setCoordOrigem(saveAluguelDTO.getCoordInicial());
                aluguel.setIdBike("bk1");
                aluguel.setPrecoPorHora(20.00);
                aluguelRepository.save(aluguel);
                return aluguel;
//           }
//       }
//       throw new RuntimeException("Sem bicicletas disponíveis :(");

//        return null;
    }

    public Aluguel devolverBike(String identificador, DevolveBikeDTO devolveBikeDTO){
        Aluguel aluguelDB = aluguelRepository.findByIdentificador(identificador);
        if (aluguelDB != null){
            aluguelDB.setCoordDestino(devolveBikeDTO.getCoordDestino());
            aluguelDB.setDestino(devolveBikeDTO.getDestino());
            try {
                String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json" +
                        "?destinations=" + String.valueOf(aluguelDB.getCoordDestino()[0]) + "," + String.valueOf(aluguelDB.getCoordDestino()[1]) +
                        "&origins=" + String.valueOf(aluguelDB.getCoordOrigem()[0]) + "," + String.valueOf(aluguelDB.getCoordOrigem()[1] )+
                        "&mode=bicycling" +
                        "&key=AIzaSyAyBLZwCYSJ1JNxFWvP274O1J3DDVseKzU";
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlString, String.class);
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    Map<String, Object> responseMap = objectMapper.readValue(responseEntity.getBody(), new TypeReference<Map<String, Object>>(){});

                    Map<String, Object> row = (Map<String, Object>) ((List<Object>) responseMap.get("rows")).get(0);
                    Map<String, Object> element = (Map<String, Object>) ((List<Object>) row.get("elements")).get(0);
                    Map<String, Object> distance = (Map<String, Object>) element.get("distance");
                    double distancia = Double.valueOf((int) distance.get("value"));
                    aluguelDB.setDistancia(distancia);
                    aluguelDB.setStatus(AluguelStatus.FINALIZADO);
                    aluguelDB.setTempoDeViagem(Duration.between(aluguelDB.getDiaHoraInicio(), LocalDateTime.now()).toMinutes()/60 );
                    aluguelDB.setPreco(aluguelDB.getPrecoPorHora()*aluguelDB.getTempoDeViagem());
                    aluguelRepository.save(aluguelDB);
                    return aluguelDB;
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }


            } catch (Exception e){
                throw new RuntimeException(e);
            }


        }

        return null;

    }

    public void deleteAluguel(String identificador){
        Aluguel aluguelDB = aluguelRepository.findByIdentificador(identificador);
        if (aluguelDB != null){
            aluguelRepository.delete(aluguelDB);
        }
    }

}
