public class Lista1Ex1 {
    private int valor;

    public Lista1Ex1() {
        valor = 0;
    }

    public void zerar() {
        valor = 0;
    }

    public void incrementar() {
        valor++;
    }

    public int getValor() {
        return valor;
    }

    public static void main(String[] args) {
        Lista1Ex1 c = new Lista1Ex1();

        c.incrementar();
        c.incrementar();
        c.incrementar();

        System.out.println(c.getValor());

        c.zerar();
        System.out.println(c.getValor());
    }
}