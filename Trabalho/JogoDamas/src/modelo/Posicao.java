package modelo;
public class Posicao {
    private int linha;
    private int coluna;
    public Posicao(int linha, int coluna) { this.linha = linha; this.coluna = coluna; }
    public int getLinha() { return linha; }
    public int getColuna() { return coluna; }
    public boolean valida() { return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8; }
    public static Posicao ler(String texto) {
        if (texto == null || !texto.trim().matches("(?i)[A-H][1-8]")) return null;
        String t = texto.trim().toUpperCase();
        return new Posicao(t.charAt(1)-'1', t.charAt(0)-'A');
    }
    public String toString() { return "" + (char)('A'+coluna) + (linha+1); }
    public boolean equals(Object o) {
        if (!(o instanceof Posicao)) return false;
        Posicao p=(Posicao)o; return linha==p.linha && coluna==p.coluna;
    }
    public int hashCode() { return linha*8+coluna; }
}
