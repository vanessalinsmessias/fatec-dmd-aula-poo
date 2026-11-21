public class Lista1Ex8 {
    private String nome;
    private Lista1Ex8 pai;
    private Lista1Ex8 mae;

    public Lista1Ex8(String nome, Lista1Ex8 pai, Lista1Ex8 mae) {
        this.nome = nome;
        this.pai = pai;
        this.mae = mae;
    }

    public Lista1Ex8(String nome) {
        this.nome = nome;
        pai = null;
        mae = null;
    }

    public boolean equals(Lista1Ex8 p) {
        if (p == null) {
            return false;
        }

        if (!nome.equals(p.nome)) {
            return false;
        }

        if (mae == null && p.mae == null) {
            return true;
        }

        if (mae == null || p.mae == null) {
            return false;
        }

        return mae.nome.equals(p.mae.nome);
    }

    public boolean ehIrmao(Lista1Ex8 p) {
        if (p == null || p == this) {
            return false;
        }

        if (pai != null && p.pai != null && pai.equals(p.pai)) {
            return true;
        }

        if (mae != null && p.mae != null && mae.equals(p.mae)) {
            return true;
        }

        return false;
    }

    public boolean ehAntecessor(Lista1Ex8 p) {
        if (p == null) {
            return false;
        }

        if (pai == p || mae == p) {
            return true;
        }

        if (pai != null && pai.ehAntecessor(p)) {
            return true;
        }

        if (mae != null && mae.ehAntecessor(p)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Lista1Ex8 avo = new Lista1Ex8("Maria");
        Lista1Ex8 pai = new Lista1Ex8("Joao");
        Lista1Ex8 mae = new Lista1Ex8("Ana", null, avo);

        Lista1Ex8 filho1 = new Lista1Ex8("Pedro", pai, mae);
        Lista1Ex8 filho2 = new Lista1Ex8("Lucas", pai, mae);

        System.out.println("Sao irmaos: " + filho1.ehIrmao(filho2));
        System.out.println("Maria e antecessora: " + filho1.ehAntecessor(avo));
        System.out.println("Sao iguais: " + filho1.equals(filho2));
    }
}