package modelo;
import java.util.List;
public abstract class Peca {
    private Cor cor;
    public Peca(Cor cor) { this.cor = cor; }
    public Cor getCor() { return cor; }
    public abstract boolean isDama();
    public abstract char simbolo();
    public abstract void direcoes(List<int[]> direcoes);
}
