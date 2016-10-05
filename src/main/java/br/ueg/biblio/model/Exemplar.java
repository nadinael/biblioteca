package br.ueg.biblio.model;

import br.ueg.biblio.model.enumerated.DisponibilidadeStatus;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "exemplar")
public class Exemplar extends Simples {

    @NotNull(message = "A data do cadastro da cópia não foi definido.")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data_cadastro")
    private Calendar dataCadastro;

    @NotNull(message = "Status de disponibilidade da obra está nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "disp_status", nullable = false, length = 20, columnDefinition = "char(1)")
    private DisponibilidadeStatus dispStatus;

    @NotBlank(message = "O código é obrigatório.")
    @Size(min = 5, max = 150, message = "Verifique a quantidade de caracteres no código.")
    @Column(name = "codigo", unique = false, length = 10, nullable = false)
    private String codigo;

    @Size(max = 150, message = "Você ultrapassou o limite máximo de carácteres.")
    @Column(name = "n_tombo", nullable = true, length = 150)
    private String tombo;

    @NotNull(message = "Um usuario precisa estar logado no sistema.")
    @ManyToOne
    private Usuario usuario;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTombo() {
        return tombo;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DisponibilidadeStatus getDispStatus() {
        return dispStatus;
    }

    public void setDispStatus(DisponibilidadeStatus dispStatus) {
        this.dispStatus = dispStatus;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
