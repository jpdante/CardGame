package br.anhembi.cardgame;
import br.anhembi.cardgame.manager.UnoManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().run(args);
    }

    private boolean running;
    private Scanner in;
    private UnoManager unoManager;



    public void run(String[] args) {
        running = true;
        in = new Scanner(System.in);
        unoManager = new UnoManager();

        unoManager.inicializarBaralho();
        //unoManager.EmbaralharBaralho();
    
        while(running) {
            mostrarComandos();
            processarComandos(in.nextLine());
        }


    }
    public void mostrarComandos() {
        System.out.println();
        System.out.println("~-~-~- Comandos UNO -~-~-~");
        System.out.println("1. Embaralhar Baralho");
        System.out.println("2. Mostrar Baralho");
        System.out.println("3. Adicionar jogador");
        System.out.println("4. Mostrar Jogadores");
        System.out.println("5. Iniciar Jogo");
        System.out.println("6. Mostrar Mesa");
        System.out.println("0. Sair");
        System.out.println();
        System.out.print(">");
    }
}