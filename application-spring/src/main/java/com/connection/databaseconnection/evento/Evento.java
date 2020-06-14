package com.connection.databaseconnection.evento;

import com.connection.databaseconnection.evento.convidado.Convidado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "evento", schema="bdGrupo93a")
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long codigo;

    private String nome;
    @NotEmpty
    private String cep;
    @NotEmpty
    private String logradouro;
    @NotEmpty
    private String bairro;
    @NotEmpty
    private String localidade;
    @NotEmpty
    private String uf;
    @NotEmpty
    private String complemento;
    @NotEmpty
    @Column(name = "_data")
    private String data;
    @NotEmpty
    private String horario;
    @NotEmpty
    private String descricao;

   @OneToMany
    private List<Convidado> convidados;
}

