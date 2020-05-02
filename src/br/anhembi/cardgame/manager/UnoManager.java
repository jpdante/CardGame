package br.anhembi.cardgame.manager;

import br.anhembi.cardgame.model.Carta;
import br.anhembi.cardgame.model.CorCarta;
import br.anhembi.cardgame.model.TipoCarta;

import java.util.Stack;

public class UnoManager {
    private Stack<Carta> baralho;

    public UnoManager(){
        baralho = new Stack<>();
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

    public String mostrarBaralho(){
        String baralhoToString = "";
        if (baralho.empty()){
            return "O baralho está vazio!";
        } else {
            for (Carta carta : baralho) {
                baralhoToString = baralhoToString + "\n" + carta.toString();
            }
            return baralhoToString;
        }
    }

    public void inicializar4(TipoCarta tipo){
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
        baralho.push(new Carta(tipo));
    }

    public void inicializar8Coloridas(TipoCarta tipo){
        baralho.push(new Carta(tipo, CorCarta.Vermelha));
        baralho.push(new Carta(tipo, CorCarta.Vermelha));
        baralho.push(new Carta(tipo, CorCarta.Amarela));
        baralho.push(new Carta(tipo, CorCarta.Amarela));
        baralho.push(new Carta(tipo, CorCarta.Verde));
        baralho.push(new Carta(tipo, CorCarta.Verde));
        baralho.push(new Carta(tipo, CorCarta.Azul));
        baralho.push(new Carta(tipo, CorCarta.Azul));
    }

    public void inicializarNumericaColorida(CorCarta cor){
        int numero = 0;
        for(int i = 0; i < 19; i++) {
            baralho.push(new Carta(TipoCarta.Numerica, cor, numero));
            numero++;
            if(numero > 9) {
                numero = 1;
            }
        }
    }

}
