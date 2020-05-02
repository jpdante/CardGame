package br.anhembi.cardgame.model;

public class Carta {
    private TipoCarta tipo;
    private CorCarta cor;
    private int numero;

    public Carta(TipoCarta tipo){
        this.tipo = tipo;
    }

    public Carta(TipoCarta tipo, CorCarta cor){
        this.tipo = tipo;
        this.cor = cor;
    }

    public Carta(TipoCarta tipo,  CorCarta cor, int numero){
        this.tipo = tipo;
        this.cor = cor;
        this.numero = numero;
    }

    public TipoCarta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarta tipo) {
        this.tipo = tipo;
    }

    public CorCarta getCor() {
        return cor;
    }

    public void setCor(CorCarta cor) {
        this.cor = cor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}

