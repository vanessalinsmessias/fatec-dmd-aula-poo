public class ExercicioFibonacci {
      public static void main(String[] args) {
        
        System.out.println("a) Os 30 primeiros números:");
        int a = 0;
        int b = 1;
        
        for (int i = 0; i < 30; i++) {
            System.out.print(a + " ");
            int proximo = a + b;
            a = b;
            b = proximo;
        }
        
        System.out.println("\n");
        

        System.out.println("b) Números menores que 100:");
        int x = 0;
        int y = 1;
        
        while (x < 100) {
            System.out.print(x + " ");
            int proximo = x + y;
            x = y;
            y = proximo;
        }
    }
}


