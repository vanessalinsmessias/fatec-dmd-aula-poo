package jogo;
import modelo.Posicao;
public class Movimento {
    private Posicao origem, destino;
    public Movimento(Posicao origem, Posicao destino) { this.origem=origem; this.destino=destino; }
    public Posicao getOrigem() { return origem; }
    public Posicao getDestino() { return destino; }
}
