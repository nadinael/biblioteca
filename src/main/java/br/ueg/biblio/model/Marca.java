package br.ueg.biblio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "marcacao")
public class Marca extends Simples {

    @NotBlank(message = "O nome deve ser preenchido.")
    @Size(min = 3, max = 50, message = "Verifique a quantidade de carácteres no campo nome.")
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase();
    }

}
