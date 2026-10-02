public class ExercicioSala2 {
    public static void main(String[] args) {

        int[][] matriz = {
            {1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 1}
        };

        for (int i = 0; i < matriz.length; i++) {
            System.out.print("{");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]);
                if (j < matriz[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("}");
            if (i < matriz.length - 1) {
                System.out.print(",");
            }
            System.out.println();
        }
    }
}