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

    public UnoManager(){
        mesa = new Stack<>();
        baralho = new Stack<>();
        jogadores = new LinkedList<>();
    }

    public void inicializarBaralho(){
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

    public String mostrarBaralho(){
        if (baralho.empty()){
            return "O baralho está vazio!";
        } else {
            StringBuilder msg = new StringBuilder();
            for (Carta carta : baralho) {
                msg.append(carta.toString()).append(System.lineSeparator());
            }
            return msg.toString();
        }
    }

    public void addJogador(Jogador jogador) throws JogoJaIniciouException {
        if(iniciou) throw new JogoJaIniciouException("Nao é possivel adicionar jogadores no meio do jogo!");
        jogadores.add(jogador);
    }

    public void removeJogador(Jogador jogador) throws JogoJaIniciouException {
        if(iniciou) throw new JogoJaIniciouException("Nao é possivel remover jogadores no meio do jogo!");
        jogadores.remove(jogador);
    }

    public Jogador[] getJogadores() {
        return jogadores.toArray(new Jogador[0]);
    }

    public LinkedList<Jogador> getListaJogadores(){
        return jogadores;
    }

    private void inicializar4(TipoCarta tipo){
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
    }

    private void inicializar8Coloridas(TipoCarta tipo){
        baralho.push(new Carta(tipo, CorCarta.Vermelha));
        baralho.push(new Carta(tipo, CorCarta.Vermelha));
        baralho.push(new Carta(tipo, CorCarta.Amarela));
        baralho.push(new Carta(tipo, CorCarta.Amarela));
        baralho.push(new Carta(tipo, CorCarta.Verde));
        baralho.push(new Carta(tipo, CorCarta.Verde));
        baralho.push(new Carta(tipo, CorCarta.Azul));
        baralho.push(new Carta(tipo, CorCarta.Azul));
    }

    private void inicializarNumericaColorida(CorCarta cor){
        int numero = 0;
        for(int i = 0; i < 19; i++) {
            baralho.push(new Carta(TipoCarta.Numerica, cor, numero));
            numero++;
            if(numero > 9) {
                numero = 1;
            }
        }
    }

    public void iniciarJogo(){
        inicializarBaralho();
        embaralharBaralho();

        for(Jogador jogador : jogadores){
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

    public String mostrarMesa(){
        return mesa.peek().toString();
    }

    public void comprarCarta(Jogador jogador){
        if(baralho.size() > 0){
            jogador.addCarta(baralho.pop());
        } else{
            Carta emJogo = mesa.pop();
            for(Carta carta : mesa){
                baralho.push(mesa.pop());
            }
            embaralharBaralho();
            mesa.push(emJogo);
            jogador.addCarta(baralho.pop());
        }
    }

    public void jogarCarta(Jogador jogador, Carta carta){
            mesa.push(carta);
            jogador.removeCarta(carta);
    }

}
