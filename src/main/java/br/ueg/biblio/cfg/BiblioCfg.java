package br.ueg.biblio.cfg;

import br.ueg.biblio.model.interfaces.Injetavel;

public class BiblioCfg implements Injetavel {

    private int prazo;
    private boolean sabadoUltil;
    private double taxaDiaria;
    private double taxaInicial;
    private double valorMaximo;

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public boolean isSabadoUltil() {
        return sabadoUltil;
    }

    public void setSabadoUltil(boolean sabadoUltil) {
        this.sabadoUltil = sabadoUltil;
    }

    public double getTaxaDiaria() {
        return taxaDiaria;
    }

    public void setTaxaDiaria(double taxaDiaria) {
        this.taxaDiaria = taxaDiaria;
    }

    public double getTaxaInicial() {
        return taxaInicial;
    }

    public void setTaxaInicial(double taxaInicial) {
        this.taxaInicial = taxaInicial;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

}
