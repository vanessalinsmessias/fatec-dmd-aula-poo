public class Lista4Ex21 {
    public static void main(String[] args) {
        char[][] jogo = new char[3][3];

        // inicializa com vazio
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jogo[i][j] = '-';
            }
        }

        // exibe tabuleiro
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(jogo[i][j] + " ");
            }
            System.out.println();
        }
    }
}