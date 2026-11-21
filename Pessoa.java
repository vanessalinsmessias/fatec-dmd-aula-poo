import java.util.Scanner;

public class Pessoa {

      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        private String nome;
        private String sobrenome;
        private int cpf;
        private String corDoCabelo;

        public Pessoa(String n, int c) {
            nome = n.split (" ")[0];
            sobrenome = n.split(" ") [0];
            cpf = c;
        }

        public String getCorDoCabelo() {
                return corDoCabelo;
        }

        public String getCorDoCabelo() {
            return corDoCabelo;
        }

        public void setCorDoCabelo(String c) {
            corDoCabelo = c;
        }

}
