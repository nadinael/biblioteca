package br.ueg.biblio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais extends Simples {

    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "sigla", nullable = false, length = 4, columnDefinition = "CHAR(3)")
    private String sigla;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
