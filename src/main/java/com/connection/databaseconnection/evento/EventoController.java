package com.connection.databaseconnection.evento;

import com.connection.databaseconnection.evento.client.cep.Cep;
import com.connection.databaseconnection.evento.client.cep.ClientViaCep;
import com.connection.databaseconnection.evento.convidado.Convidado;
import com.connection.databaseconnection.evento.convidado.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.ok;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository er;
    @Autowired
    private ConvidadoRepository cr;

    @Autowired
    private ClientViaCep viaCep;

    @PostMapping(path = "/cadastrarEvento")
    public ResponseEntity form(@RequestBody Evento evento) {
      try {
          if(evento == null){
              System.out.println("Cadastro vazio");

          }
      }catch (Exception e){
          e.printStackTrace();
      }

        er.save(evento);
        return ok(evento);

    }

    @GetMapping(path = "/eventos")
    public ResponseEntity listaEventos() {
        Iterable<Evento> eventos = er.findAll();
        if (eventos == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ok(eventos);
        }

    }

    @GetMapping(path = "/eventos/{codigo}")
    public ResponseEntity eventosEspecificos ( @PathVariable("codigo") long codigo){
        Evento evento = er.findByCodigo(codigo);
        return ok(evento);
    }

    @GetMapping(path = "/convidado/{codigo}")
    public ResponseEntity detalhesEvento ( @PathVariable("codigo") long codigo) {
        Evento evento = er.findByCodigo(codigo);
        Iterable<Convidado> convidados = cr.findByEvento(evento);
        if (convidados == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ok(convidados);
        }
    }
    @PostMapping(path = "/convidado/{codigo}")
    public ResponseEntity cadastroConvidado( @RequestBody Convidado convidado, @PathVariable("codigo") long codigo) {
        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);

        try {
            if(convidado.getNomeConvidado() == null){
                System.out.println("Nome do convidado vazio");
            }
            if(convidado.getRg() == 0){
                System.out.println("Rg do convidado vazio");
            }
            if(convidado.getEvento() == null){
                System.out.println("Evento não encontrado");
            }
            cr.save(convidado);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(convidado);
    }

    @DeleteMapping(path="/evento/{codigo}")
    public ResponseEntity deletarEvento ( @PathVariable("codigo") long codigo){
        Evento evento = er.findByCodigo(codigo);
        Iterable<Convidado> convidados = cr.findByEvento(evento);
        cr.deleteAll(convidados);
        er.delete(evento);
        return ok().build();
    }

    @DeleteMapping(path = "/convidado/{rg}")
    public ResponseEntity delete (@PathVariable String rg){
        cr.findByRg(rg);
        cr.deleteById(rg);
        return ok().build();

    }
    @GetMapping("/cep/{cep}")
    public ResponseEntity consultarCep(@PathVariable String cep) {
        Cep cepEncontrado = viaCep.getCep(cep);
        try {
            if (cepEncontrado == null) {
                System.out.println("cep vazio");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(cepEncontrado);
    }



}
