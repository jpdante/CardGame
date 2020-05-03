package br.anhembi.cardgame.model;

import java.util.ArrayList;

public class Jogador {

    private String nome;
    private ArrayList<Carta> cartasNaMao;

    public Jogador(String nome) {
        cartasNaMao = new ArrayList<>();
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void addCarta(Carta carta) {
        cartasNaMao.add(carta);
    }

    public void removeCarta(Carta carta) {
        cartasNaMao.remove(carta);
    }

    public Carta[] getCartas() {
        return cartasNaMao.toArray(new Carta[0]);
    }

    public String mostrarCartas() {
        StringBuilder msg = new StringBuilder();
        for(Carta carta : cartasNaMao) {
            msg.append(carta.toString()).append(System.lineSeparator());
        }
        return msg.toString();
    }

    @Override
    public String toString() {
        return "Jogador{" + "nome='" + nome + '\'' + ", cartasNaMao=" + cartasNaMao + '}';
    }
}
