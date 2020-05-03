package br.anhembi.cardgame.model;

import java.util.ArrayList;

public class Jogador {

    private int id;
    private String nome;
    private ArrayList<Carta> cartasNaMao;

    public Jogador(int id, String nome) {
        this.id = id;
        cartasNaMao = new ArrayList<>();
        this.nome = nome;
    }

    public int getId() {
        return this.id;
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

    public boolean constainsCarta(Carta carta) {
        return cartasNaMao.contains(carta);
    }

    public String mostrarCartas() {
        StringBuilder msg = new StringBuilder();
        int last = 0;
        for(Carta carta : cartasNaMao) {
            if(last == cartasNaMao.size() - 1) {
                msg.append(carta.toString()).append(System.lineSeparator());
            } else {
                msg.append("    ").append(carta.toString()).append(',').append(System.lineSeparator());
            }
        }
        return msg.toString();
    }

    @Override
    public String toString() {
        return "Jogador{" + "id=" + id + ", nome='" + nome + '\'' + ", cartasNaMao=[" + System.lineSeparator() + mostrarCartas() + "]}";
    }
}
