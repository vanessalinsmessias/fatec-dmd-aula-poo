public class Lista1Ex10 {
    private double[][] valores;
    private int linhas;
    private int colunas;

    public Lista1Ex10(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        valores = new double[linhas][colunas];
    }

    public void setValor(int i, int j, double valor) {
        valores[i][j] = valor;
    }

    public double getValor(int i, int j) {
        return valores[i][j];
    }

    public boolean equals(Lista1Ex10 m) {
        if (m == null || linhas != m.linhas || colunas != m.colunas) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (valores[i][j] != m.valores[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public Lista1Ex10 transposta() {
        Lista1Ex10 m = new Lista1Ex10(colunas, linhas);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                m.valores[j][i] = valores[i][j];
            }
        }

        return m;
    }

    public Lista1Ex10 oposta() {
        Lista1Ex10 m = new Lista1Ex10(linhas, colunas);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                m.valores[i][j] = -valores[i][j];
            }
        }

        return m;
    }

    public void matrizNula() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                valores[i][j] = 0;
            }
        }
    }

    public boolean identidade() {
        if (linhas != colunas) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i == j && valores[i][j] != 1) {
                    return false;
                }

                if (i != j && valores[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean diagonal() {
        if (linhas != colunas) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i != j && valores[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean singular() {
        if (!diagonal()) {
            return false;
        }

        for (int i = 1; i < linhas; i++) {
            if (valores[i][i] != valores[0][0]) {
                return false;
            }
        }

        return true;
    }

    public boolean simetrica() {
        if (linhas != colunas) {
            return false;
        }

        return equals(transposta());
    }

    public boolean antissimetrica() {
        if (linhas != colunas) {
            return false;
        }

        return oposta().equals(transposta());
    }

    public void adicionar(Lista1Ex10 m) {
        if (linhas != m.linhas || colunas != m.colunas) {
            System.out.println("Tamanhos diferentes");
            return;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                valores[i][j] += m.valores[i][j];
            }
        }
    }

    public void subtrair(Lista1Ex10 m) {
        if (linhas != m.linhas || colunas != m.colunas) {
            System.out.println("Tamanhos diferentes");
            return;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                valores[i][j] -= m.valores[i][j];
            }
        }
    }

    public void multiplicar(Lista1Ex10 m) {
        if (colunas != m.linhas) {
            System.out.println("Nao pode multiplicar");
            return;
        }

        double[][] resultado = new double[linhas][m.colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < m.colunas; j++) {
                for (int k = 0; k < colunas; k++) {
                    resultado[i][j] += valores[i][k] * m.valores[k][j];
                }
            }
        }

        valores = resultado;
        colunas = m.colunas;
    }

    public Lista1Ex10 copia() {
        Lista1Ex10 m = new Lista1Ex10(linhas, colunas);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                m.valores[i][j] = valores[i][j];
            }
        }

        return m;
    }

    public void mostrar() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(valores[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Lista1Ex10 a = new Lista1Ex10(2, 2);
        Lista1Ex10 b = new Lista1Ex10(2, 2);

        a.setValor(0, 0, 1);
        a.setValor(0, 1, 2);
        a.setValor(1, 0, 3);
        a.setValor(1, 1, 4);

        b.setValor(0, 0, 5);
        b.setValor(0, 1, 6);
        b.setValor(1, 0, 7);
        b.setValor(1, 1, 8);

        System.out.println("Matriz A:");
        a.mostrar();

        System.out.println("Matriz B:");
        b.mostrar();

        System.out.println("Transposta de A:");
        a.transposta().mostrar();

        System.out.println("Oposta de A:");
        a.oposta().mostrar();

        System.out.println("A e identidade? " + a.identidade());
        System.out.println("A e diagonal? " + a.diagonal());
        System.out.println("A e simetrica? " + a.simetrica());
        System.out.println("A e antissimetrica? " + a.antissimetrica());

        Lista1Ex10 c = a.copia();
        c.adicionar(b);

        System.out.println("Soma:");
        c.mostrar();

        c = a.copia();
        c.subtrair(b);

        System.out.println("Subtracao:");
        c.mostrar();

        c = a.copia();
        c.multiplicar(b);

        System.out.println("Multiplicacao:");
        c.mostrar();

        c.matrizNula();

        System.out.println("Matriz nula:");
        c.mostrar();
    }
}