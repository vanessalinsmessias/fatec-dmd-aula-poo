import java.util.Scanner;

public class Lista4Ex4 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            double soma = 0;
            
            for (int i = 0; i < 6; i++) {
                soma += sc.nextDouble();
            }
            
            System.out.println(soma / 6);
        }
    }
}