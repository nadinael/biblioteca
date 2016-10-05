package br.ueg.biblio.model;

import br.ueg.biblio.model.enumerated.ClassesConhecimentoHumano;
import br.ueg.biblio.model.enumerated.TipoTitulo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "titulo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Titulo extends Simples {

    /*Atributos que podem ser nulos*/
    @Size(max = 240, message = "Sintetize sua observação, ela passou do limite permitido de 240 letras.")
    @Column(name = "observacao", nullable = true, length = 240)
    private String observacao;

    @Size(max = 20, message = "O nº de CDD possui mais de 20 dígitos, isso não é permitido. Verifque se digitou corretamente.")
    @Column(nullable = true, name = "cdd", length = 20)
    private String cdd; //opt

    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data_cadastro")
    private Calendar dataCadastro;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = true, name = "data_publicacao")
    private Date data;

    @Column(nullable = true, name = "paginas")
    private int paginas;

    @Column(nullable = true, name = "volume")
    private int volume;

    @Size(max = 20, message = "O ISBN possui mais de 20 dígitos, isso não é permitido. Verifique se é um ISBN válido.")
    @Column(name = "isbn", length = 20, nullable = true)
    private String isbn;

    @Size(max = 25, message = "O nº de CDU possui mais de 25 dígitos, isso não é permitido. Verifque se digitou corretamente.")
    @Column(name = "cdu", nullable = true, length = 25)
    private String cdu;

    /*Valores que devem ser preenchidos*/
    @NotBlank(message = "Um nome válido é obrigatório.")
    @Size(min = 2, max = 150, message = "Verifique a quantidade de letras no nome.")
    @Column(nullable = false, length = 150, name = "nome")
    private String nome;

    @NotBlank(message = "Um código de barras (UCP) é obrigatório.")
    @Size(min = 5, max = 30, message = "Verifique a quantidade de letras no código de barras.")
    @Column(name = "cod_barras", nullable = false, length = 30)
    private String codBarras;

    @Column(name = "edicao", nullable = false)
    private int edicao;

    @NotNull(message = "Defina uma classe ao título.")
    @Enumerated(EnumType.STRING)
    @Column(name = "area_abordagem", nullable = false, length = 20)
    private ClassesConhecimentoHumano areaCon;

    @NotNull(message = "Defina o tipo do título.")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tiulo", nullable = false, length = 30)
    private TipoTitulo tipoDoTitulo;
    /*------------------------------------------------------*/
    @NotNull(message = "Uma editora deve ser definida.")
    @ManyToOne(targetEntity = Editora.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    @NotNull(message = "Idioma deve ser definido.")
    @ManyToOne(targetEntity = Idioma.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

    /*JOINS*/
    @OneToMany(fetch = FetchType.EAGER, targetEntity = Exemplar.class, cascade = {CascadeType.PERSIST})//AKI DEVE SER PERSIST
    @JoinTable(name = "titulo_exemplar",
            joinColumns = {
                @JoinColumn(name = "titulo_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "exemplar_id", nullable = false)})
    private List<Exemplar> copias;

    @NotNull(message = "É preciso pelo menos uma Tag (Marcação) em um título.")
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Marca.class)
    @JoinTable(name = "titulo_marca",
            joinColumns = {
                @JoinColumn(name = "titulo_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "marca_id")})
    private List<Marca> marcadores;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Autor.class)
    @JoinTable(name = "titulo_autor",
            joinColumns = {
                @JoinColumn(name = "titulo_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "autor_id", nullable = false)})
    private List<Autor> autores;

    public Titulo() {
        this.marcadores = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.copias = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras.toUpperCase();
    }

    public ClassesConhecimentoHumano getAreaCon() {
        return areaCon;
    }

    public void setAreaCon(ClassesConhecimentoHumano areaCon) {
        this.areaCon = areaCon;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getCdd() {
        return cdd;
    }

    public void setCdd(String cdd) {
        this.cdd = cdd;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipoTitulo getTipoDoTitulo() {
        return tipoDoTitulo;
    }

    public void setTipoDoTitulo(TipoTitulo tipoTitulo) {
        this.tipoDoTitulo = tipoTitulo;
    }

    public List<Marca> getMarcadores() {
        return marcadores;
    }

    public List<Exemplar> getCopias() {
        return copias;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void adicionarAutor(Autor autor) {
        this.autores.add(autor);
    }

    public void removerAutor(Autor autor) {
        this.getAutores().remove(autor);
    }

    public void adicionarCopia(Exemplar exemplar) {
        this.copias.add(exemplar);
    }

    public void removerCopia(Exemplar exemplar) {
        this.copias.remove(exemplar);
    }

    public void adicionarTag(Marca tag) {
        this.marcadores.add(tag);
    }

    public void removerTag(Marca tag) {
        this.getMarcadores().remove(tag);
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCdu() {
        return cdu;
    }

    public void setCdu(String cdu) {
        this.cdu = cdu;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
