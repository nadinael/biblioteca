package br.ueg.biblio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "autor")
public class Autor extends Simples {

    @NotBlank(message = "O nome deve ser preenchido.")
    @Size(min = 3, max = 100, message = "Verifique a quantidade de carácteres no campo nome.")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 240, message = "Limite de 40 carácteres.")
    @Column(name = "pais_origem", nullable = true, length = 40)
    private String paisOrigem;

    @Size(max = 240, message = "Limite de 240 carácteres.")
    @Column(name = "observacao", nullable = true, length = 240)
    private String observacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem.toUpperCase();
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao.toUpperCase();
    }

}
