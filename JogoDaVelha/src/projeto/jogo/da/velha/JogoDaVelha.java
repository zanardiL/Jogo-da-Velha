package projeto.jogo.da.velha;
import java.util.Scanner;
public class JogoDaVelha {

    static int i;
    static int j;
    static Scanner sc = new Scanner(System.in);
    final static String PLAYER_1 = "\tX";
    final static String PLAYER_2 = "\tO";

    public static void main(String[] args) {
        String nomePlayer1 = "";
        String nomePlayer2 = "";

        saudacao();
        String[][] jogo = new String[5][5];
        menu();

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                tabuleiroRegras();
            case 2:
                rodarJogo(jogo, nomePlayer1, nomePlayer2);
                break;
            case 3:
                System.out.print("Até Logo!");
                break;
        }
    }

    public static void tabuleiro(String[][] jogo){   //construção do tabuleiro
        for (i = 0; i < jogo.length; i++) {
            for (j = 0; j < jogo.length; j++) {
                if ((j == 1 || j==3) && i%2 == 0) {
                    jogo[i][j] = "\t|";
                } else if((i == 1 || i ==3)&& j==0) {
                    jogo[i][j] = "\t--";
                } else if(i == 1 || i ==3) {
                    jogo[i][j] = "----";
                } else jogo[i][j] = "\t";
            }
        }
    }

    public static void printTabuleiro(String[][] jogo) { //imprimir tabuleiro
        for (i = 0; i < jogo.length; i++) {
            for (j = 0; j < jogo.length; j++) {
                System.out.print(jogo[i][j]);
            }
            System.out.println();
        }
    }
    public static boolean verificaVencedor(String[][] jogo) {
        if(jogo[0][0].equals(jogo[0][2]) && jogo[0][0].equals(jogo[0][4]) && !jogo[0][0].equals("\t")) //A=B=C e !=vazio
            return true;
        else if(jogo[2][0].equals(jogo[2][2]) && jogo[2][0].equals(jogo[2][4]) && !jogo[2][0].equals("\t")) //D=E=F e !=vazio
            return true;
        else if(jogo[4][0].equals(jogo[4][2]) && jogo[4][0].equals(jogo[4][4]) && !jogo[4][0].equals("\t")) //G=H=I e !=vazio
            return true;
        else if(jogo[0][0].equals(jogo[2][0]) && jogo[0][0].equals(jogo[4][0]) && !jogo[0][0].equals("\t")) //A=D=G e !=vazio
            return true;
        else if(jogo[0][2].equals(jogo[2][2]) && jogo[0][2].equals(jogo[4][2]) && !jogo[0][2].equals("\t")) //B=E=H e !=vazio
            return true;
        else if(jogo[0][4].equals(jogo[2][4]) && jogo[0][4].equals(jogo[4][4]) && !jogo[0][4].equals("\t")) //C=F=I e !=vazio
            return true;
        else if(jogo[0][0].equals(jogo[2][2]) && jogo[0][0].equals(jogo[4][4]) && !jogo[0][0].equals("\t")) //A=E=I e !=vazio
            return true;
        else if(jogo[0][4].equals(jogo[2][2]) && jogo[0][4].equals(jogo[4][0]) && !jogo[0][4].equals("\t")) //C=E=G e !=vazio
            return true;
        else
            return false;
    }
    public static void rodarJogo(String[][] jogo, String nomePlayer1, String nomePlayer2) {
        if(nomePlayer1.equals("") && nomePlayer2.equals("")) {
            System.out.print("Qual é o nome do primeiro jogador? ");
            nomePlayer1 = sc.next();
            System.out.print("Qual é o nome do segundo jogador? ");
            nomePlayer2 = sc.next();
        }
        int numRodada = 1;
        String vez = PLAYER_1;
        String vezJogador = nomePlayer1;

        tabuleiro(jogo);
        do {
            System.out.println("\n Vez do jogador: " + vezJogador);
            System.out.println("ESCOLHA A POSIÇÃO");
            String coordenada = sc.next();
            coordenada = coordenada.toUpperCase();

            if (verificaVazio(jogo, coordenada)) {
                switch (coordenada) {
                    case "A":
                        jogo[0][0] = vez;
                        break;
                    case "B":
                        jogo[0][2] = vez;
                        break;
                    case "C":
                        jogo[0][4] = vez;
                        break;
                    case "D":
                        jogo[2][0] = vez;
                        break;
                    case "E":
                        jogo[2][2] = vez;
                        break;
                    case "F":
                        jogo[2][4] = vez;
                        break;
                    case "G":
                        jogo[4][0] = vez;
                        break;
                    case "H":
                        jogo[4][2] = vez;
                        break;
                    case "I":
                        jogo[4][4] = vez;
                        break;
                    default:
                        System.out.println("Posição inválida!");
                        System.out.println("Por favor, selecione uma das posições disponíveis: ");
                }
                if (numRodada % 2 != 0) {
                    vez = PLAYER_2;
                    vezJogador = nomePlayer2;
                } else {
                    vez = PLAYER_1;
                    vezJogador = nomePlayer1;
                }
                numRodada +=1;
                printTabuleiro(jogo);

                if(numRodada>9) { //EMPATE
                    System.out.println(nomePlayer1 + " e " + nomePlayer2 + " empataram!\n" +
                            "REVANCHE? ");
                    System.out.println("\t 1 -SIM\n" +
                            "\t 2 -NÃO");
                    int revanche = sc.nextInt();
                    if(revanche == 1)
                        rodarJogo(jogo, nomePlayer1, nomePlayer2);
                    else {
                        System.out.println("Até logo!");
                        System.exit(0);
                    }
                }
            }
        }
        while (verificaVencedor(jogo)==false) ;
    }
    public static boolean verificaVazio(String[][] jogo, String coordenada) {
        boolean vazio = false;
        switch (coordenada) {
            case "A":
                if (jogo[0][0].equals("\t"))
                    vazio = true;
                break;
            case "B":
                if (jogo[0][2].equals("\t"))
                    vazio = true;
                break;
            case "C":
                if (jogo[0][4].equals("\t"))
                    vazio = true;
                break;
            case "D":
                if (jogo[2][0].equals("\t"))
                    vazio = true;
                break;
            case "E":
                if (jogo[2][2].equals("\t"))
                    vazio = true;
                break;
            case "F":
                if (jogo[2][4].equals("\t"))
                    vazio = true;
                break;
            case "G":
                if (jogo[4][0].equals("\t"))
                    vazio = true;
                break;
            case "H":
                if (jogo[4][2].equals("\t"))
                    vazio = true;
                break;
            case "I":
                if (jogo[4][4].equals("\t"))
                    vazio = true;
                break;
            default:
                vazio = false;
                break;
        }
        return vazio;
    }



    private static void saudacao () {
        System.out.println("*** Bem vindo ao jogo da velha. Divirta-se!***");
    }
    private static void menu() {
        System.out.print("Você gostaria de iniciar uma partida? \n" +
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

