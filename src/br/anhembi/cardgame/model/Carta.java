package br.anhembi.cardgame.model;

public class Carta {
    private static int nextId = 0;

    private int id;
    private TipoCarta tipo;
    private CorCarta cor;
    private int numero;

    public Carta(TipoCarta tipo) {
        this.id = ++nextId;
        this.tipo = tipo;
    }

    public Carta(TipoCarta tipo, CorCarta cor) {
        this.id = ++nextId;
        this.tipo = tipo;
        this.cor = cor;
    }

    public Carta(TipoCarta tipo, CorCarta cor, int numero) {
        this.id = ++nextId;
        this.tipo = tipo;
        this.cor = cor;
        this.numero = numero;
    }

    public int getId() {
        return this.id;
    }

    public TipoCarta getTipo() {
        return tipo;
    }

    public CorCarta getCor() {
        return cor;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Carta{" + "id=" + id + ", tipo=" + tipo + ", cor=" + cor + ", numero=" + numero + '}';
    }
}

