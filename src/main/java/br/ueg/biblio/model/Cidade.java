package br.ueg.biblio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "cidade")
public class Cidade extends Simples {

    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(min = 5, max = 120, message = "Verifique a quantidade de carácteres no campo nome.")
    @Column(name = "nome", length = 120, nullable = false)
    private String nome;

    @NotNull(message = "Uma cidade pertence obrigariamente a um estado.")
    @ManyToOne(targetEntity = Estado.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
