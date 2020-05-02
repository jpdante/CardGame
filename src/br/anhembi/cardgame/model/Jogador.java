package br.anhembi.cardgame.model;

import java.util.ArrayList;

public class Jogador {

    private String nome;
    private ArrayList<Carta> cartasNaMao;

    public Jogador(String nome) {
        cartasNaMao = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void AddCarta(Carta carta) {
        cartasNaMao.add(carta);
    }

    public void RemoveCarta(Carta carta) {
        cartasNaMao.remove(carta);
    }

    public Carta[] GetCartas() {
        return cartasNaMao.toArray(new Carta[0]);
    }

    public String MostrarCartas() {
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
