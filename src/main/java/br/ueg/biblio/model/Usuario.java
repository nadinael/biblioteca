package br.ueg.biblio.model;

import br.ueg.biblio.model.enumerated.Sexo;
import br.ueg.biblio.model.enumerated.StatusCadastro;
import br.ueg.biblio.model.enumerated.TUsuario;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario extends Simples {

    @Transient
    private String senhaSemCriptografia;

    @NotBlank(message = "Um nome válido é obrigatório.")
    @Size(min = 3, max = 100, message = "Verifique a quantidade de letras no campo de nome.")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Um nome de usuário é único e obrigatório.")
    @Size(min = 3, max = 50, message = "Verifique a quantidade de letras no campo de usuário.")
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @NotBlank(message = "Uma senha é obrigatória.")
    @Size(min = 5, max = 250, message = "Verifique a quantidade de letras no campo de senha.")
    @Column(name = "senha", nullable = false, length = 250)
    private String senha;

    //@CPF(message = "CPF inválido")
    @NotBlank(message = "Um CPF é obrigatório.")
    @Size(min = 14, message = "Verifique a quantidade de de letras no campo de CPF.")
    @Column(name = "cpf", nullable = false, length = 14, unique = true)
    private String cpf;

    @NotNull(message = "Um gênero sexual deve ser escolhido.")
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", columnDefinition = "char(1)", nullable = false)
    private Sexo sexo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro", nullable = false)
    private StatusCadastro statusCadastro;

    @NotNull(message = "Escolha uma cidade obrigatoriamente.")
    @ManyToOne(targetEntity = Cidade.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @NotBlank(message = "O campo de endereço deve ser preenchido.")
    @Size(min = 5, max = 100, message = "Verifique a quantidade de letras no campo de endereço.")
    @Column(name = "endereco", nullable = false, length = 100)
    private String endereco;

    @Email(message = "E-Mail inválido")
    @NotBlank(message = "O campo de E-Mail deve ser preenchido.")
    @Size(min = 5, max = 150, message = "Verifique a quantidade de letras no campo de E-Mail.")
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @NotBlank(message = "Um número de telefone é obrigatório.")
    @Size(min = 13, max = 15, message = "Verifique se digitou o número de telefone corretamente.")
    @Column(name = "telefone", nullable = false, length = 15)
    private String telefone;

    @NotNull(message = "O tipo de usuário deve ser informado.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "user_tipo", columnDefinition = "char(1)")
    private TUsuario tipo;

    @NotNull(message = "Erro no campo de data, tente novamente.")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false, name = "data_nascimento")
    private Date dataNascimento;

    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data_cadastro")
    private Calendar dataCadastro;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = true, name = "data_inativado")
    private Calendar dataInativado;

    @NotBlank(message = "É necessário um CEP.")
    @Size(max = 9, min = 9)
    @Column(nullable = false, name = "cep_endereco", length = 9)
    private String cep;

    @Transient
    private List<Multa> listaMulta;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.toUpperCase();
    }

    public TUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TUsuario tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public StatusCadastro getStatusCadastro() {
        return statusCadastro;
    }

    public void setStatusCadastro(StatusCadastro statusCadastro) {
        this.statusCadastro = statusCadastro;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getSenhaSemCriptografia() {
        return senhaSemCriptografia;
    }

    public void setSenhaSemCriptografia(String senhaSemCriptografia) {
        this.senhaSemCriptografia = senhaSemCriptografia;
    }

    public Calendar getDataInativado() {
        return dataInativado;
    }

    public void setDataInativado(Calendar dataInativado) {
        this.dataInativado = dataInativado;
    }

    public List<Multa> getListaMulta() {
        return listaMulta;
    }

    public void setListaMulta(List<Multa> listaMulta) {
        this.listaMulta = listaMulta;
    }

}
