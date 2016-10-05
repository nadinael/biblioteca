package br.ueg.biblio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "idioma")
public class Idioma extends Simples {

    @NotBlank(message = "Uma descrição do idioma é obrigatória.")
    @Size(min = 5, max = 30, message = "Verifique a quantidade de carácteres.")
    @Column(nullable = false, length = 30, name = "descricao")
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase();
    }

}
