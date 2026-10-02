import java.util.InputMismatchException;
import java.util.Scanner;

public class ExercicioJogoDaVelha {

    // Variáveis globais para compartilhar o estado do jogo entre os métodos
    private static char[][] tabuleiro = new char[3][3];
    private static char jogadorAtual = 'X';
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            inicializarTabuleiro();
            jogadorAtual = 'X';
            boolean jogoAtivo = true;

            System.out.println("\n--- JOGO DA VELHA ---");

            while (jogoAtivo) {
                exibirTabuleiro();
                realizarJogada();

                if (verificarVitoria()) {
                    exibirTabuleiro();
                    System.out.println("O jogador " + jogadorAtual + " venceu!");
                    jogoAtivo = false;
                } else if (tabuleiroCompleto()) {
                    exibirTabuleiro();
                    System.out.println("A partida terminou empatada!");
                    jogoAtivo = false;
                } else {
                    trocarJogador();
                }
            }

            System.out.print("\nDeseja iniciar uma nova partida? (s/n): ");
            char resposta = scanner.next().toLowerCase().charAt(0);
            if (resposta != 's') {
                jogarNovamente = false;
            }
        }
        
        System.out.println("Jogo encerrado. Até a próxima!");
        scanner.close();
    }

    private static void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    private static void exibirTabuleiro() {
        System.out.println("\n    1   2   3");
        System.out.println("  +---+---+---+");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println("\n  +---+---+---+");
        }
    }

    private static void realizarJogada() {
        boolean jogadaValida = false;
        
        while (!jogadaValida) {
            try {
                System.out.print("Jogador " + jogadorAtual + ", informe a linha (1-3): ");
                int linha = scanner.nextInt();
                
                System.out.print("Jogador " + jogadorAtual + ", informe a coluna (1-3): ");
                int coluna = scanner.nextInt();

                // Verifica se está dentro dos limites (1 a 3)
                if (linha >= 1 && linha <= 3 && coluna >= 1 && coluna <= 3) {
                    // Ajusta o índice de 1-3 para 0-2 da matriz
                    if (posicaoValida(linha - 1, coluna - 1)) {
                        tabuleiro[linha - 1][coluna - 1] = jogadorAtual;
                        jogadaValida = true;
                    } else {
                        System.out.println("Essa posição já está ocupada. Escolha outra posição.\n");
                    }
                } else {
                    System.out.println("Posição inválida. Permitido apenas números entre 1 e 3.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.\n");
                scanner.next(); // Limpa o buffer do scanner para não causar loop infinito
            }
        }
    }

    private static boolean posicaoValida(int linha, int coluna) {
        return tabuleiro[linha][coluna] == ' ';
    }

    private static boolean verificarVitoria() {
        // Verifica linhas e colunas simultaneamente
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) ||
                (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual)) {
                return true;
            }
        }
        
        // Verifica diagonal principal e secundária
        if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
            (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
            return true;
        }
        
        return false;
    }

    private static boolean tabuleiroCompleto() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Ainda há espaços vazios
                }
            }
        }
        return true; // Não encontrou nenhum espaço vazio
    }

    private static void trocarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }
}

