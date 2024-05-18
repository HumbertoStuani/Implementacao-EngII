package sapc.sapcbackend.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "colaborador")
public class Colaborador {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "salario")
    private Double salario;
}
