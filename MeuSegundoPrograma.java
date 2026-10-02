public class MeuSegundoPrograma {


    public static void imprimir(int[] p) {

        System.out.print("[");


        for (int i = 0; i < p.length; i++) {

            System.out.print(p[i]);


            if (i < p.length - 1) {

                System.out.print(",");

            }

        }


        System.out.print("]");

    }


    public static int somar(int[] p) {

        int soma = 0;


        for (int i = 0; i < p.length; i++) {

            soma += p[i];

        }


        return soma;

    }


    public static void main(String[] args) {


        int[] v = new int[6];


        // preenchendo o vetor com valores

        for (int i = 0; i < v.length; i++) {

            v[i] = i + 1; // 1, 2, 3, 4, 5, 6

        }


        imprimir(v);

        System.out.println();

        System.out.println("Soma: " + somar(v));

    }

}