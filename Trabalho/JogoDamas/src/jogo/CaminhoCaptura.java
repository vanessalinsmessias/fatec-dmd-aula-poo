package jogo;
import java.util.*;
import modelo.Posicao;
public class CaminhoCaptura {
    private List<Posicao> destinos = new ArrayList<Posicao>();
    private List<Posicao> capturadas = new ArrayList<Posicao>();
    public void adicionar(Posicao destino, Posicao capturada) { destinos.add(destino); capturadas.add(capturada); }
    public List<Posicao> getDestinos() { return destinos; }
    public List<Posicao> getCapturadas() { return capturadas; }
    public int tamanho() { return destinos.size(); }
}
