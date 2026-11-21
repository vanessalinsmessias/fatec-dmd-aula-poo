package jogo;
import modelo.*;
public class Tabuleiro {
    private Peca[][] casas = new Peca[8][8];
    public Peca get(Posicao p) { return p==null ? null : casas[p.getLinha()][p.getColuna()]; }
    public void colocar(Posicao p, Peca peca) { casas[p.getLinha()][p.getColuna()]=peca; }
    public void limpar(Posicao p) { casas[p.getLinha()][p.getColuna()]=null; }
    public void inicializar() {
        casas=new Peca[8][8];
        for(int l=0;l<3;l++) for(int c=0;c<8;c++) if((l+c)%2==0) casas[l][c]=new PecaComum(Cor.BRANCAS);
        for(int l=5;l<8;l++) for(int c=0;c<8;c++) if((l+c)%2==0) casas[l][c]=new PecaComum(Cor.PRETAS);
    }
    public String[] linhas() {
        String[] r=new String[8];
        for(int l=7;l>=0;l--) { String s=""; for(int c=0;c<8;c++) s+=casas[l][c]==null?'.':casas[l][c].simbolo(); r[7-l]=s; }
        return r;
    }
    public int contar(Cor cor) {
        int n=0; for(int l=0;l<8;l++) for(int c=0;c<8;c++) if(casas[l][c]!=null && casas[l][c].getCor()==cor) n++;
        return n;
    }
    public Peca[][] copiarCasas() {
        Peca[][] copia=new Peca[8][8];
        for(int l=0;l<8;l++) for(int c=0;c<8;c++) copia[l][c]=casas[l][c];
        return copia;
    }
    public void definir(Peca[][] novas) { casas=novas; }
}
