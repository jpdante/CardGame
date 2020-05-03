package br.anhembi.cardgame.manager;

import br.anhembi.cardgame.exception.JogoJaIniciouException;
import br.anhembi.cardgame.model.Carta;
import br.anhembi.cardgame.model.CorCarta;
import br.anhembi.cardgame.model.Jogador;
import br.anhembi.cardgame.model.TipoCarta;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class UnoManager {
    private boolean iniciou;
    private Stack<Carta> baralho;
    private Stack<Carta> mesa;
    private LinkedList<Jogador> jogadores;
    private int nextId;

    public UnoManager() {
        mesa = new Stack<>();
        baralho = new Stack<>();
        jogadores = new LinkedList<>();
        nextId = 0;
    }

    public void inicializarBaralho() {
        inicializarNumericaColorida(CorCarta.Vermelha);
        inicializarNumericaColorida(CorCarta.Amarela);
        inicializarNumericaColorida(CorCarta.Verde);
        inicializarNumericaColorida(CorCarta.Azul);

        inicializar8Coloridas(TipoCarta.Comprar2);
        inicializar8Coloridas(TipoCarta.Inverter);
        inicializar8Coloridas(TipoCarta.Pular);

        inicializar4(TipoCarta.Curinga);
        inicializar4(TipoCarta.CuringaComprar4);

        baralho.push(new Carta(TipoCarta.CuringaTrocarMao));
    }

    public void embaralharBaralho() {
        Collections.shuffle(baralho);
    }

    public String mostrarBaralho() {
        if (baralho.empty()) {
            return "O baralho está vazio!";
        } else {
            StringBuilder msg = new StringBuilder();
            for (Carta carta : baralho) {
                msg.append(carta.toString()).append(System.lineSeparator());
            }
            return msg.toString();
        }
    }

    public int getNextId() {
        return ++this.nextId;
    }

    public boolean estaIniciado() {
        return this.iniciou;
    }

    public void addJogador(Jogador jogador) throws JogoJaIniciouException {
        if (iniciou) throw new JogoJaIniciouException("Nao é possivel adicionar jogadores no meio do jogo!");
        jogadores.add(jogador);
    }

    public void removeJogador(Jogador jogador) throws JogoJaIniciouException {
        if (iniciou) throw new JogoJaIniciouException("Nao é possivel remover jogadores no meio do jogo!");
        jogadores.remove(jogador);
    }

    public Jogador[] getJogadores() {
        return jogadores.toArray(new Jogador[0]);
    }

    public Jogador findJogador(String info) {
        int id = 0;
        try {
            id = Integer.parseInt(info);
        } catch (Exception ignored) { }
        for(Jogador jogador : jogadores) {
            if (jogador.getNome().equalsIgnoreCase(info)) {
                return jogador;
            }
            if (jogador.getId() == id) {
                return jogador;
            }
        }
        return null;
    }

    public Carta findCarta(String info, Jogador jogador) {
        try {
            int id = Integer.parseInt(info);
            for(Carta carta : jogador.getCartas()) {
                if (carta.getId() == id) {
                    return carta;
                }
            }
            return null;
        } catch (Exception ignored) {
            return null;
        }
    }

    public LinkedList<Jogador> getListaJogadores() {
        return jogadores;
    }

    private void inicializar4(TipoCarta tipo) {
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
    }

    private void inicializar8Coloridas(TipoCarta tipo) {
        baralho.push(new Carta(tipo, CorCarta.Vermelha));
        baralho.push(new Carta(tipo, CorCarta.Vermelha));
        baralho.push(new Carta(tipo, CorCarta.Amarela));
        baralho.push(new Carta(tipo, CorCarta.Amarela));
        baralho.push(new Carta(tipo, CorCarta.Verde));
        baralho.push(new Carta(tipo, CorCarta.Verde));
        baralho.push(new Carta(tipo, CorCarta.Azul));
        baralho.push(new Carta(tipo, CorCarta.Azul));
    }

    private void inicializarNumericaColorida(CorCarta cor) {
        int numero = 0;
        for (int i = 0; i < 19; i++) {
            baralho.push(new Carta(TipoCarta.Numerica, cor, numero));
            numero++;
            if (numero > 9) {
                numero = 1;
            }
        }
    }

    public void iniciarJogo() {
        iniciou = true;
        embaralharBaralho();

        for (Jogador jogador : jogadores) {
            jogador.addCarta(baralho.pop());
            jogador.addCarta(baralho.pop());
            jogador.addCarta(baralho.pop());
            jogador.addCarta(baralho.pop());
            jogador.addCarta(baralho.pop());
            jogador.addCarta(baralho.pop());
            jogador.addCarta(baralho.pop());
        }
        mesa.push(baralho.pop());
    }

    public String mostrarMesa() {
        if (mesa.empty()) {
            return "O baralho está vazio!";
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Carta Atual: ")
                    .append(mesa.peek().toString())
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append("Historico:")
                    .append(System.lineSeparator());
            int index = 1;
            for (Carta carta : mesa) {
                msg.append(index)
                        .append(": ")
                        .append(carta.toString())
                        .append(System.lineSeparator());
                index++;
            }
            return msg.toString();
        }
    }

    public Carta comprarCarta(Jogador jogador) {
        if (baralho.size() > 0) {
            Carta carta = baralho.pop();
            jogador.addCarta(carta);
            return carta;
        } else {
            Carta emJogo = mesa.pop();
            for (int i = 0; i < mesa.size(); i++) {
                baralho.push(mesa.pop());
            }
            embaralharBaralho();
            mesa.push(emJogo);
            Carta carta = baralho.pop();
            jogador.addCarta(carta);
            return carta;
        }
    }

    public boolean jogarCarta(Jogador jogador, Carta carta) {
        if(jogador.constainsCarta(carta)) {
            mesa.push(carta);
            jogador.removeCarta(carta);
            return true;
        } else return false;
    }

}
