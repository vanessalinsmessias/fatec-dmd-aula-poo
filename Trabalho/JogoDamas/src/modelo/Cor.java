package modelo;
public enum Cor {
    BRANCAS, PRETAS;
    public Cor adversaria() { return this == BRANCAS ? PRETAS : BRANCAS; }
    public int direcao() { return this == BRANCAS ? 1 : -1; }
}
