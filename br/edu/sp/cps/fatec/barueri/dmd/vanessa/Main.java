package br.edu.sp.cps.fatec.barueri.dmd.vanessa;

public class Main {

    public static void main (String[] args) {

        Gato g1 = new Gato("nome", 4, "FRAJOLA");

        Gato g2 = new Gato("nome", 4, "TRICOLOR");

        System.out.println(g1.nome);
        g1.nome = "Rodrigo Nestor";
        System.out.println(g1.nome);

        System.out.println(g2.nome);
        g2.nome = "Nerfer Pitou";
        System.out.println(g2.nome);

    }

}
