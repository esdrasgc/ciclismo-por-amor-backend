package com.br.ciclismoporamor.Aluguel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer>{
    Aluguel findByIdentificador(String identificador);

   List<Aluguel> findByIdBike(String idBike);
}
