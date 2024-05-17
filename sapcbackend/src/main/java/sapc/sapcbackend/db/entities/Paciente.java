package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_internacao")
    private Date data_internacao;


    public Paciente(Long id, String descricao, Date data_internacao) {
        this.id = id;
        this.descricao = descricao;
        this.data_internacao = data_internacao;
    }

    public Paciente ()
    {
    }
}
