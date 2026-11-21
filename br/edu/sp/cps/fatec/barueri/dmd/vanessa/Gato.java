package br.edu.sp.cps.fatec.barueri.dmd.vanessa;

public class Gato {

    static String VARIAVEL_GATO = "1";

    String nome;
    int quantidadePatas;
    String cor;

    public Gato() {

    }

    public Gato(String n, int qtePatas, String c) {
        nome = n;
        quantidadePatas = qtePatas;
        cor = c;
    }

    public void miau() {
        System.out.println("MIAUUUUUUUUUUUU");

    }
}