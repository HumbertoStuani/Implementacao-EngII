package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_evento")
public class TipoEvento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nometipo")
    private String nomeTipo;

    public TipoEvento() {
        this(0L,"");
    }

    public TipoEvento(Long idset, String nome) {
        id = idset;
        this.nomeTipo = nome;
    }

    public void setNomeTipo(String nome)
    {nomeTipo = nome;}

    public String getNomeTipo()
    {return nomeTipo;}

    public void setId(long idSet)
    {this.id = idSet;}

    public Long getId()
    {return id;}

}
