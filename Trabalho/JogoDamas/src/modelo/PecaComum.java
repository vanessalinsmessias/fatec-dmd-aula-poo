package modelo;
import java.util.List;
public class PecaComum extends Peca {
    public PecaComum(Cor cor) { super(cor); }
    public boolean isDama() { return false; }
    public char simbolo() { return getCor()==Cor.BRANCAS ? 'b' : 'p'; }
    public void direcoes(List<int[]> d) {
        int passo=getCor().direcao();
        d.add(new int[]{passo,-1}); d.add(new int[]{passo,1});
    }
}
