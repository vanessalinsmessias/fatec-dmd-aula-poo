public class TesteMatriz {

    public static void main(String[] args) {

        int[][] sala = {
            {1, 0, 0, 1, 1, 1},
            {1, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0},
            {0, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1}
        };

        int fileiras = sala.length;
        int cadeirasPorFileira = sala[0].length;
        int totalCadeiras = fileiras * cadeirasPorFileira;
        int pessoasPresentes = 0;

        for (int i = 0; i < sala.length; i++) {
            for (int j = 0; j < sala[i].length; j++) {
                pessoasPresentes += sala[i][j];
            }
        }

        // Imprime a sala, com um espaço extra no lugar do corredor
        for (int i = 0; i < sala.length; i++) {
            for (int j = 0; j < sala[i].length; j++) {
                System.out.print(sala[i][j] + " ");
                if (j == 2) {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Fileiras: " + fileiras);
        System.out.println("Cadeiras por fileira: " + cadeirasPorFileira);
        System.out.println("Total de cadeiras: " + totalCadeiras);
        System.out.println("Pessoas presentes: " + pessoasPresentes);
        System.out.println("Cadeiras vazias: " + (totalCadeiras - pessoasPresentes));
    }
}