package com.connection.databaseconnection.evento.convidado;


import com.connection.databaseconnection.evento.Evento;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado,String> {
    Convidado findByRg(String rg);

    Iterable<Convidado> findByEvento(Evento evento);
}