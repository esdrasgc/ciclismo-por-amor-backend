package com.br.ciclismoporamor.Aluguel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    
}
