package modelo;
import java.util.List;
public class Dama extends Peca {
    public Dama(Cor cor) { super(cor); }
    public boolean isDama() { return true; }
    public char simbolo() { return getCor()==Cor.BRANCAS ? 'B' : 'P'; }
    public void direcoes(List<int[]> d) {
        d.add(new int[]{1,1}); d.add(new int[]{1,-1});
        d.add(new int[]{-1,1}); d.add(new int[]{-1,-1});
    }
}
