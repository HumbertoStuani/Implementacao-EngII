package sapc.sapcbackend.db.entities.empresa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="parametrizacao")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="razao_social")
    private String razaoSocial;

    @Column(name="cnpj", unique = true)
    private String cnpj;

    @Column(name="logo_pequena")
    private String logoPequena;

    @Column(name="logo_grande")
    private String logoGrande;

    @Column(name="endereco")
    private String endereco;

    @Column(name="bairro")
    private String bairro;

    @Column(name="cidade")
    private String cidade;

    @Column(name="uf")
    private String uf;

    @Column(name="data_criacao", nullable = true)
    private LocalDateTime data_criacao;

    @Column(name="diretor")
    private String diretor;

    @Column(name="site")
    private String site;

    @Column(name="telefone")
    private String telefone;

    public Empresa() {
    }

    public Empresa(Long id, String razaoSocial, String nome, String cnpj, String logoPequena, String logoGrande, String endereco, String bairro, String cidade, String uf, LocalDateTime data_criacao, String diretor, String telefone, String site) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nome = nome;
        this.cnpj = cnpj;
        this.logoPequena = logoPequena;
        this.logoGrande = logoGrande;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.data_criacao = data_criacao;
        this.diretor = diretor;
        this.telefone = telefone;
        this.site = site;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogoPequena() {
        return logoPequena;
    }

    public void setLogoPequena(String logoPequena) {
        this.logoPequena = logoPequena;
    }

    public String getLogoGrande() {
        return logoGrande;
    }

    public void setLogoGrande(String logoGrande) {
        this.logoGrande = logoGrande;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
