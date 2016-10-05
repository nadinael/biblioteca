package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.core.ControllerCore;
import br.ueg.biblio.accreditation.ControlePagina;
import br.ueg.biblio.model.Autor;
import br.ueg.biblio.model.Editora;
import br.ueg.biblio.model.Exemplar;
import br.ueg.biblio.model.Idioma;
import br.ueg.biblio.model.Marca;
import br.ueg.biblio.model.Titulo;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.enumerated.ClassesConhecimentoHumano;
import br.ueg.biblio.model.enumerated.DisponibilidadeStatus;
import br.ueg.biblio.model.enumerated.TipoTitulo;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import br.ueg.biblio.util.titles.GeradorDeCodigoCopia;
import java.util.Calendar;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@Named(value = "tituloBean")
@ViewScoped
public class TituloBean implements Injetavel {

    private Marca tag;
    private int estoque;
    private Titulo titulo;
    private boolean editando;
    private boolean tabelaPronta;
    private Autor autor;

    @NotNull(message = "A cópia não foi criada corretamente.")
    private Exemplar copia;

    @NotNull(message = "A cópia precisa de um usuário válido logado para ser adicionada.")
    private Usuario usuario;

    @Inject
    private PersistenceCore persistence;

    @Inject
    private RepositoryCore repository;

    private List<Editora> listaEditoras;
    private List<Idioma> listaIdiomas;
    private List<Autor> listaAutores;
    private List<Marca> listaTags;
    private List<Titulo> listaTitulos;


    /*METODOS DE CONFIGURAÇÕES*/
    public void configurarTabela() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.titulo = new Titulo();
            this.tabelaPronta = false;

        } else {
            System.out.println("é postback".toUpperCase());
        }
    }

    public void configurarForm() {
        if (ManagedBeanUtil.isNotPostback()) {
            prepararForm();
        }
    }

    private void prepararForm() {
        this.setTitulo(new Titulo());
        this.copia = new Exemplar();
        this.usuario = ControlePagina.usuarioDaSessao();
    }

    public void carregarIdiomas() {
        this.listaIdiomas = repository.listaDeIdiomas();
    }

    public void carregarAutores() {
        this.listaAutores = repository.listaDeAutores();
    }

    public void carregarTags() {
        this.listaTags = repository.listaDeMarcas();
    }

    public void carregarEditoras() {
        this.listaEditoras = repository.listaDeEditoras();
    }

    public void carregarDados() {
        this.listaTitulos = repository.listaDeTitulos();
        this.tabelaPronta = true;
    }

    /* METODOS DE ACAO */
    public void salvar() {
        ControllerCore.ajustandoNovaObra(titulo);
        boolean r = persistence.persistir(titulo);
        if (r) {
            prepararForm();
            ManagedBeanUtil.guardado();
        } else {
            ManagedBeanUtil.erroSalvar();
        }
    }

    public void adicionarEditora(Editora publisher) {
        this.titulo.setEditora(publisher);
    }

    public void removerAutor() {
        if (this.getAutor() != null) {
            System.out.println("EXEMPLAR: " + this.autor.getNome());
            this.titulo.removerAutor(autor);
        }
    }

    public void adicionarAutor(Autor autor) {
        if (!this.titulo.getAutores().contains(autor)) {
            this.titulo.adicionarAutor(autor);
        } else {
            ManagedBeanUtil.addGrwolInfo("Este autor ja foi adicionado.");
        }

    }

    public void removerCopia() {
        int m = titulo.getCopias().size();
        System.out.println("quantidade de itens: " + m);
        m = m - 1;
        Exemplar cop = titulo.getCopias().get(m);
        System.out.println("EXEMPLAR: " + cop.getCodigo());
        this.titulo.getCopias().remove(m);
    }

    public void adicionarCopia() {
        System.out.println("Adicionando copia".toUpperCase());
        System.out.println(": " + getCopia().getTombo() + " :");
        System.out.println(": " + getCopia().getCodigo() + " :");
        this.titulo.adicionarCopia(copia);
    }

    public void adidionarIdioma(Idioma idioma) {
        this.titulo.setIdioma(idioma);
    }

    public void removerTag() {
        if (this.tag != null) {
            System.out.println("EXEMPLAR: " + this.tag.getDescricao());
            this.titulo.removerTag(tag);
        }
    }

    public void adicionarTag(Marca tag) {
        if (!this.titulo.getMarcadores().contains(tag)) {
            this.titulo.adicionarTag(tag);
        } else {
            ManagedBeanUtil.addGrwolInfo("Esta tag(marca) ja foi adicionada.");
        }

    }

    public void novoExemplar() {
        System.out.println("novo exemplar".toUpperCase());
        this.setCopia(new Exemplar());
        this.getCopia().setUsuario(usuario);
        this.getCopia().setDispStatus(DisponibilidadeStatus.D);
        this.getCopia().setDataCadastro(Calendar.getInstance());
        copia.setCodigo(GeradorDeCodigoCopia.gerar(titulo.getCopias()));
    }

    /* GETTERS E SETTERS */
    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public ClassesConhecimentoHumano[] getAreas() {

        return ClassesConhecimentoHumano.values();

    }

    public TipoTitulo[] getListaTipos() {

        return TipoTitulo.values();

    }

    public List<Editora> getListaEditoras() {
        return listaEditoras;
    }

    public List<Idioma> getListaIdiomas() {
        return listaIdiomas;
    }

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public List<Marca> getListaTags() {
        return listaTags;
    }

    public Exemplar getCopia() {
        System.out.println("RETORNANDO COPIA: " + this.copia.getCodigo());
        return copia;
    }

    public void setCopia(Exemplar copia) {
        System.out.println("AJUSTANDO COPIA: " + this.copia.getCodigo());
        this.copia = copia;
    }

    public int getEstoque() {
        if ((this.titulo.getCopias() == null)
                || (this.titulo.getCopias().isEmpty())) {
            return 0;
        } else {
            estoque = this.titulo.getCopias().size();
            return estoque;
        }
    }

    public boolean isEditando() {
        editando = false;
        return editando;
    }

    public Marca getTag() {
        return tag;
    }

    public void setTag(Marca tag) {
        this.tag = tag;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

    public List<Titulo> getListaTitulos() {
        return listaTitulos;
    }

}
/*METODOS DE CONFIGURAÇÕES*/
 /* METODOS DE ACAO */
 /* GETTERS E SETTERS */
