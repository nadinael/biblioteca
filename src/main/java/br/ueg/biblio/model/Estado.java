package br.ueg.biblio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;

@Entity
@Table(name = "estado")
public class Estado extends Simples {

    @Column(name = "nome", nullable = false, length = 75)
    private String nome;

    @Column(name = "uf", length = 5, nullable = false)
    private String uf;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Pais pais;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getSigla() {
        return uf;
    }

    public void setSigla(String uf) {
        this.uf = uf;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

}