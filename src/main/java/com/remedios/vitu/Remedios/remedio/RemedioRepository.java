package com.remedios.vitu.Remedios.remedio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RemedioRepository extends JpaRepository<EntidadeRemedio, Long> {

    //Quando o spring não nos da um metodo ja pronto, nós podemos criar o nosso proprio metodo
    List<EntidadeRemedio> findAllByAtivoTrue();

}
