package br.ueg.biblio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "editora")
public class Editora extends Simples {

    @NotBlank(message = "campo obrigatório!")
    @Size(min = 3, max = 100, message = "Verifique a quantidade de carácteres no campo nome.")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotNull(message = "Uma cidade é necessária.")
    @ManyToOne(targetEntity = Cidade.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @NotBlank(message = "É necessário um CEP.")
    @Size(max = 9, min = 9)
    @Column(nullable = false, name = "cep_endereco", length = 9)
    private String cep;

    @Column(name = "email", nullable = true, length = 150)
    private String email;

    @Column(nullable = true, name = "site", length = 150)
    private String website;

    @Column(name = "telefone", nullable = true, length = 15)
    private String telefone;

    @NotBlank(message = "O campo de endereço deve ser preenchido.")
    @Size(min = 5, max = 100, message = "Verifique a quantidade de carácteres no campo endereço.")
    @Column(name = "endereco", nullable = false, length = 100)
    private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.toUpperCase();
    }

}
