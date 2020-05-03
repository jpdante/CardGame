package br.anhembi.cardgame;
import br.anhembi.cardgame.exception.JogoJaIniciouException;
import br.anhembi.cardgame.manager.UnoManager;
import br.anhembi.cardgame.model.Jogador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().run(args);
    }

    private boolean running;
    private Scanner in;
    private UnoManager unoManager;
    boolean jogoIniciado = false;

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
        System.out.println("3. Adicionar Jogador");
        System.out.println("4. Mostrar Jogadores");
        System.out.println("5. Iniciar Jogo");
        System.out.println("6. Mostrar Mesa");
        System.out.println("7. Comprar Carta");
        System.out.println("8. Jogar Carta");
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
                case 3:
                    System.out.print("Digite o nome do jogador: ");
                    String nome = in.nextLine();
                    if(nome == null || nome.equals("")){
                        System.out.println("< Erro: O nome não pode ficar em branco >");
                        break;
                    }
                    try {
                        unoManager.addJogador(new Jogador(nome));
                        System.out.println("  < Jogado criado >");
                    } catch(JogoJaIniciouException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("  < Jogadores >");
                    if(unoManager.getListaJogadores().size() == 0){
                        System.out.println("  < Não existem jogadores no momento >");
                    } else{
                        for(Jogador jogador : unoManager.getJogadores()){
                            System.out.println(jogador.toString());
                        }
                    }
                    break;
                case 5:
                    if(jogoIniciado == false){
                        if(unoManager.getListaJogadores().size() < 2){
                            System.out.println("  < Crie pelo menos 2 jogadores >");
                        } else {
                            unoManager.iniciarJogo();
                            System.out.println("  < Jogo Iniciado >");
                            jogoIniciado = true;
                        }
                    } else {
                        System.out.println("  < Opa! O jogo já foi iniciado >");
                    }
                    break;
                case 6:
                    if(jogoIniciado == true){
                        System.out.println("  < Carta da Mesa >");
                        System.out.println(unoManager.mostrarMesa());
                    } else{
                        System.out.println("< Inicie o jogo primeiro :) >");
                    }
                    break;
                case 7:
                    //precisa do jogador como parametro
                    //unoManager.comprarCarta();
                    System.out.println("  < Carta comprada >");
                    break;
                case 8:
                    //precisa do jogador e de alguma carta como parametro
                    System.out.println("  < Carta jogada >");
                    //unoManager.jogarCarta();
                    break;
                case 0:
                    System.out.println("  < Obrigado por jogar! >");
                    running = false;
                    break;
                default:
                    System.out.println("  < Digite uma opção válida >");
            }
        } catch(Exception ex) {
            System.out.println("A entrada deve ser um numero!");
            System.out.println("Exception: Falha ao converter '" + cmd + "' para um numero inteiro.");
        }
    }
}