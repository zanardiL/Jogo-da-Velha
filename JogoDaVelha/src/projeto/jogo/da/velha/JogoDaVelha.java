package projeto.jogo.da.velha;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JogoDaVelha {

    static int i;
    static int j;
    static Scanner sc = new Scanner(System.in);
    final static String player1 = "\t X";
    final static String player2 = "\t O";
    String nomeUm;
    String nomeDois;

    public static void main(String[] args) {

        System.out.println();
        saudacao();

        String[][] jogo = new String[5][5];
        int opcao;
        menu();

        try {
            do {
                opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        tabuleiroRegras();

                    case 2:
                        System.out.print("Qual é o nome do primeiro jogador? ");
                        String nomeUm = sc.next();

                        System.out.print("Qual é o nome do segundo jogador? ");
                        String nomeDois = sc.next();

                        break;
                    case 3:
                        System.out.print("Até Logo!");

                        break;
                    default:
                        System.out.print("Opção inválida, digite novamente:\n");
                }

            } while (opcao != 1 || opcao != 2 || opcao != 3);
        } catch (InputMismatchException exception) {
            System.out.print("Caractere não reconhecido");
            return;
        }


        tabuleiro(jogo);
        printTabuleiro(jogo);
        jogadas(jogo, player1, player2);

    }

    public static void tabuleiro(String[][] jogo) {   //construção do tabuleiro
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo.length; j++) {

                if (j == 1 || j == 3) {
                    jogo[i][j] = "\t|";
                } else if (i == 1 || i == 3) {
                    jogo[i][j] = "\t---";
                } else jogo[i][j] = "";
            }
        }

    }

    public static void printTabuleiro(String[][] jogo) { //imprimir tabuleiro
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo.length; j++) {
                System.out.print(jogo[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean verificaVencedor(String[][] jogo) {
        if (jogo[0][0].equals(jogo[0][2]) && jogo[0][0].equals(jogo[0][4])) //A=B=C
            return true;
        else if (jogo[2][0].equals(jogo[2][2]) && jogo[2][0].equals(jogo[2][4])) //D=E=F
            return true;
        else if (jogo[4][0].equals(jogo[4][2]) && jogo[4][0].equals(jogo[4][4])) //G=H=I
            return true;
        else if (jogo[0][0].equals(jogo[2][0]) && jogo[0][0].equals(jogo[4][0])) //A=D=G
            return true;
        else if (jogo[0][2].equals(jogo[2][2]) && jogo[0][2].equals(jogo[4][2])) //B=E=H
            return true;
        else if (jogo[0][4].equals(jogo[2][4]) && jogo[0][4].equals(jogo[4][4])) //C=F=I
            return true;
        else if (jogo[0][0].equals(jogo[2][2]) && jogo[0][0].equals(jogo[4][4])) //A=E=I
            return true;
        else if (jogo[0][4].equals(jogo[2][2]) && jogo[0][4].equals(jogo[4][0])) //C=E=G
            return true;
        else
            return false;
    }

    //    public static String[] posicoesDisponiveis(String[] posicoes, String coordenada) {
//        for (i = 0; i < posicoesDisponiveis.length; i++) {
//            if (posicoesDisponiveis[i].equals(coordenada))
//                posicoesDisponiveis[i] = "";
//        }
//        return posicoesDisponiveis;
    // }
    public static void jogadas(String[][] jogo, String player1, String player2) {
        int numRodada = 1;
        String vez = player1;
        String[] posicoesDisponiveis = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        do {
            System.out.println("\n Vez da rodada: " + vez);
            System.out.println("ESCOLHA A POSIÇÃO");
            String coordenada = sc.next();
            coordenada = coordenada.toUpperCase();

            for (i = 0; i < posicoesDisponiveis.length; i++) {
                if (posicoesDisponiveis.equals(coordenada)) {
                    posicoesDisponiveis[i] = "";
                }
            }

            switch (coordenada) {
                case "A":
                    if (jogo[0][0].isBlank())
                        jogo[0][0] = vez;
                    else System.out.println(Arrays.toString(posicoesDisponiveis));
                    printTabuleiro(jogo);
                    break;
                case "B":
                    jogo[0][2] = vez;
                    printTabuleiro(jogo);
                    break;
                case "C":
                    jogo[0][4] = vez;
                    printTabuleiro(jogo);
                    break;
                case "D":
                    jogo[2][0] = vez;
                    printTabuleiro(jogo);
                    break;
                case "E":
                    jogo[2][2] = vez;
                    printTabuleiro(jogo);
                    break;
                case "F":
                    jogo[2][4] = vez;
                    printTabuleiro(jogo);
                    break;
                case "G":
                    jogo[4][0] = vez;
                    printTabuleiro(jogo);
                    break;
                case "H":
                    jogo[4][2] = vez;
                    printTabuleiro(jogo);
                    break;
                case "I":
                    jogo[4][4] = vez;
                    printTabuleiro(jogo);
                    break;
                default:
                    System.out.println("Posição inválida!");
                    //inserir posições disponíveis
            }
            if (vez.equals(player1)) {
                vez = player2;
            } else {
                vez = player1;
            }
        } while (verificaVencedor(jogo));
    }


    private static void saudacao() {
        System.out.println("*** Bem vindo ao jogo da velha. Divirta-se!***");
    }

    private static void menu() {
        System.out.println();
        System.out.println("Você gostaria de iniciar uma partida? \n" +
                "Digite 1 para regras; \n" +
                "Digite 2 para iniciar o jogo;\n" +
                "digite 3 para sair.");


    }

    private static void tabuleiroRegras() {


        String[][] tabuleiro = {{" A ", "|", " B ", "|", " C \n"},
                {"--- ", "", "--- ", "", "---\n"},
                {" D ", "|", " E ", "|", " F \n"},
                {"--- ", "", "--- ", "", "---\n"},
                {" G ", "|", " H ", "|", " I \n"}};

        for (int i = 0; i < tabuleiro.length; i++) {
            int j;
            for (j = 0; j < tabuleiro.length; j++) {

                System.out.print(tabuleiro[i][j]);
            }

        }
        System.out.println("Na sua vez de jogar, você deve digitar qual letra corresponde ao espaço que você quer ocupar.\n");
    }

}

