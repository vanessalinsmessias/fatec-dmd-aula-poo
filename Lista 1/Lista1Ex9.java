public class Lista1Ex9 {
    private String[] elementos;
    private int quantidade;

    public Lista1Ex9() {
        elementos = new String[2];
        quantidade = 0;
    }

    public boolean pertence(String valor) {
        for (int i = 0; i < quantidade; i++) {
            if (elementos[i].equals(valor)) {
                return true;
            }
        }

        return false;
    }

    public void adicionar(String valor) {
        if (valor == null || pertence(valor)) {
            return;
        }

        if (quantidade == elementos.length) {
            String[] novo = new String[elementos.length * 2];

            for (int i = 0; i < elementos.length; i++) {
                novo[i] = elementos[i];
            }

            elementos = novo;
        }

        elementos[quantidade] = valor;
        quantidade++;
    }

    public Lista1Ex9 uniao(Lista1Ex9 c) {
        Lista1Ex9 resultado = new Lista1Ex9();

        for (int i = 0; i < quantidade; i++) {
            resultado.adicionar(elementos[i]);
        }

        for (int i = 0; i < c.quantidade; i++) {
            resultado.adicionar(c.elementos[i]);
        }

        return resultado;
    }

    public Lista1Ex9 inter(Lista1Ex9 c) {
        Lista1Ex9 resultado = new Lista1Ex9();

        for (int i = 0; i < quantidade; i++) {
            if (c.pertence(elementos[i])) {
                resultado.adicionar(elementos[i]);
            }
        }

        return resultado;
    }

    public Lista1Ex9 menos(Lista1Ex9 c) {
        Lista1Ex9 resultado = new Lista1Ex9();

        for (int i = 0; i < quantidade; i++) {
            if (!c.pertence(elementos[i])) {
                resultado.adicionar(elementos[i]);
            }
        }

        return resultado;
    }

    public void mostrar() {
        for (int i = 0; i < quantidade; i++) {
            System.out.println(elementos[i]);
        }
    }

    public static void main(String[] args) {
        Lista1Ex9 a = new Lista1Ex9();
        Lista1Ex9 b = new Lista1Ex9();

        a.adicionar("A");
        a.adicionar("B");
        a.adicionar("C");

        b.adicionar("B");
        b.adicionar("C");
        b.adicionar("D");

        System.out.println("Uniao:");
        a.uniao(b).mostrar();

        System.out.println("Intersecao:");
        a.inter(b).mostrar();

        System.out.println("Diferenca:");
        a.menos(b).mostrar();
    }
}