public class Lista1Ex3 {
    private double real;
    private double imaginaria;

    public Lista1Ex3(double real, double imaginaria) {
        this.real = real;
        this.imaginaria = imaginaria;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginaria() {
        return imaginaria;
    }

    public void setImaginaria(double imaginaria) {
        this.imaginaria = imaginaria;
    }

    public void somar(Lista1Ex3 n) {
        real = real + n.real;
        imaginaria = imaginaria + n.imaginaria;
    }

    public void subtrair(Lista1Ex3 n) {
        real = real - n.real;
        imaginaria = imaginaria - n.imaginaria;
    }

    public void multiplicar(Lista1Ex3 n) {
        double r = real * n.real - imaginaria * n.imaginaria;
        double i = real * n.imaginaria + imaginaria * n.real;

        real = r;
        imaginaria = i;
    }

    public void dividir(Lista1Ex3 n) {
        double divisor = n.real * n.real + n.imaginaria * n.imaginaria;

        if (divisor == 0) {
            System.out.println("Nao pode dividir por zero");
            return;
        }

        double r = (real * n.real + imaginaria * n.imaginaria) / divisor;
        double i = (imaginaria * n.real - real * n.imaginaria) / divisor;

        real = r;
        imaginaria = i;
    }

    public boolean equals(Lista1Ex3 n) {
        if (n == null) {
            return false;
        }
        return real == n.real && imaginaria == n.imaginaria;
    }

    @Override
    public String toString() {
        return real + " + " + imaginaria + "i";
    }

    public double modulo() {
        return Math.sqrt(real * real + imaginaria * imaginaria);
    }

    public static void main(String[] args) {
        Lista1Ex3 n1 = new Lista1Ex3(3, 4);
        Lista1Ex3 n2 = new Lista1Ex3(2, 1);

        System.out.println(n1);
        System.out.println("Modulo: " + n1.modulo());

        n1.somar(n2);
        System.out.println("Soma: " + n1);

        n1.subtrair(n2);
        System.out.println("Subtracao: " + n1);

        n1.multiplicar(n2);
        System.out.println("Multiplicacao: " + n1);

        n1.dividir(n2);
        System.out.println("Divisao: " + n1);
    }
}