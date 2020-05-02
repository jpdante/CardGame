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
            processarComando(in.nextLine());
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

    public void processarComando(String cmd) {
        try {
            int option = Integer.parseInt(cmd);
            System.out.println();
            switch (option) {
                case 1:
                    unoManager.embaralharBaralho();
                    System.out.println("  < Baralho Embaralhado >");
                    break;
                case 2:
                    System.out.println("~-~-~- Baralho -~-~-~");
                    System.out.println(unoManager.mostrarBaralho());
                    break;
                case 4:
                    System.out.println("  < Jogadores >");
                    break;
                case 5:
                    System.out.println("  < Jogo Iniciado >");
                    break;
            }
        } catch(Exception ex) {
            System.out.println("A entrada deve ser um numero!");
            System.out.println("Exception: Falha ao converter '" + cmd + "' para um numero inteiro.");
        }
    }
}