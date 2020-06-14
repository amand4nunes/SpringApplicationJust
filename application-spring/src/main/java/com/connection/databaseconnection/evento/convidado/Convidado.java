package com.connection.databaseconnection.evento.convidado;

import com.connection.databaseconnection.evento.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data //( @Data )Esta notação permite que eu não precise chamar os gets e sets pois já é chamado internamente por ela
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "convidado", schema="bdGrupo93a")
public class Convidado {

    @Id
    @Column( name = "rg")
    private long rg;

    @Column( name = "nome_convidado")
    private String nomeConvidado;

    @ManyToOne
    @JoinColumn( name = "evento_codigo")
    private Evento evento;


}
