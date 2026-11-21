package jogo;
import java.util.*;
import modelo.*;

public class Partida {
    private Tabuleiro tabuleiro = new Tabuleiro();
    private Jogador brancas, pretas;
    private Cor turno;
    private String status;
    private Posicao origemSequencia;
    private List<CaminhoCaptura> caminhosAtivos = new ArrayList<CaminhoCaptura>();
    private int etapa = 0;
    private Posicao posicaoAtual;

    public void nova(String nome1, String nome2) {
        brancas=new Jogador(nome1,Cor.BRANCAS); pretas=new Jogador(nome2,Cor.PRETAS);
        tabuleiro.inicializar(); turno=Cor.BRANCAS; status="EM_ANDAMENTO";
        limparSequencia();
    }
    public boolean existe() { return status!=null; }
    public String getStatus() { return status; }
    public Cor getTurno() { return turno; }
    public boolean emCaptura() { return origemSequencia != null; }
    public String nomeAtual() { return turno==Cor.BRANCAS?brancas.getNome():pretas.getNome(); }
    public String nome(Cor c) { return c==Cor.BRANCAS?brancas.getNome():pretas.getNome(); }
    public Tabuleiro getTabuleiro() { return tabuleiro; }
    private void limparSequencia() { origemSequencia=null; posicaoAtual=null; caminhosAtivos.clear(); etapa=0; }

    public String mover(Posicao origem, Posicao destino) {
        if(!existe()) return "Inicie ou carregue uma partida primeiro.";
        if(!"EM_ANDAMENTO".equals(status)) return "A partida ja terminou.";
        if(origem==null || destino==null || !origem.valida() || !destino.valida()) return "Coordenada invalida.";
        if((origem.getLinha()+origem.getColuna())%2!=0 || (destino.getLinha()+destino.getColuna())%2!=0) return "Use somente casas escuras.";
        Peca p=tabuleiro.get(origem);
        if(p==null) return "Nao existe peca na origem.";
        if(p.getCor()!=turno) return "Essa peca pertence ao adversario.";
        if(origemSequencia!=null && !origem.equals(posicaoAtual)) return "Continue a captura com a mesma peca em "+posicaoAtual+".";

        if(origemSequencia==null) {
            List<CaminhoCaptura> todos=obterCapturas(turno);
            int maior=0; for(CaminhoCaptura c:todos) maior=Math.max(maior,c.tamanho());
            if(maior>0) {
                List<CaminhoCaptura> melhores=new ArrayList<CaminhoCaptura>();
                for(CaminhoCaptura c:todos) if(c.tamanho()==maior && c.getDestinos().get(0).equals(destino)
                        && origem.equals(origens.get(c))) melhores.add(c);
                if(melhores.isEmpty()) return "Captura obrigatoria: escolha uma sequencia com "+maior+" captura(s).";
                origemSequencia=origem; posicaoAtual=origem; caminhosAtivos=melhores; etapa=0;
            } else {
                if(!movimentoSimplesValido(p,origem,destino)) return "Movimento invalido.";
                tabuleiro.limpar(origem); tabuleiro.colocar(destino,p); promover(p,destino);
                trocarTurno(); verificarFim(); return "Movimento realizado.";
            }
        }
        List<CaminhoCaptura> proximos=new ArrayList<CaminhoCaptura>();
        for(CaminhoCaptura c:caminhosAtivos) if(c.getDestinos().size()>etapa && c.getDestinos().get(etapa).equals(destino)) proximos.add(c);
        if(proximos.isEmpty()) return "Destino nao corresponde a captura permitida.";
        // Mantem as pecas capturadas no tabuleiro ate o fim da sequencia.
        Posicao capturada=proximos.get(0).getCapturadas().get(etapa);
        tabuleiro.limpar(posicaoAtual); tabuleiro.colocar(destino,p);
        etapa++; posicaoAtual=destino; caminhosAtivos=proximos;
        boolean continua=false;
        for(CaminhoCaptura c:caminhosAtivos) if(c.tamanho()>etapa) continua=true;
        if(continua) return "Captura realizada. Continue com a mesma peca.";
        Set<Posicao> remover=new HashSet<Posicao>();
        for(CaminhoCaptura c:caminhosAtivos) {
            for(int i=0;i<etapa;i++) remover.add(c.getCapturadas().get(i));
        }
        // Os caminhos ativos compartilham o prefixo executado.
        for(Posicao x:remover) tabuleiro.limpar(x);
        promover(p,posicaoAtual);
        limparSequencia(); trocarTurno(); verificarFim();
        return "Captura concluida.";
    }
    private boolean movimentoSimplesValido(Peca p,Posicao a,Posicao b) {
        if(tabuleiro.get(b)!=null) return false;
        int dl=b.getLinha()-a.getLinha(), dc=b.getColuna()-a.getColuna();
        if(Math.abs(dl)!=Math.abs(dc) || dl==0) return false;
        if(!p.isDama()) return dl==p.getCor().direcao() && Math.abs(dl)==1;
        int passoL=dl>0?1:-1, passoC=dc>0?1:-1;
        int l=a.getLinha()+passoL,c=a.getColuna()+passoC;
        while(l!=b.getLinha()) { if(tabuleiro.get(new Posicao(l,c))!=null) return false; l+=passoL;c+=passoC; }
        return true;
    }
    private void promover(Peca p,Posicao pos) {
        if(!p.isDama() && ((p.getCor()==Cor.BRANCAS && pos.getLinha()==7)||(p.getCor()==Cor.PRETAS && pos.getLinha()==0)))
            tabuleiro.colocar(pos,new Dama(p.getCor()));
    }
    private void trocarTurno() { turno=turno.adversaria(); }
    private void verificarFim() {
        if(tabuleiro.contar(turno)==0) { status=turno==Cor.BRANCAS?"VITORIA_PRETAS":"VITORIA_BRANCAS"; return; }
        if(obterCapturas(turno).isEmpty() && obterMovimentosSemCaptura(turno).isEmpty())
            status=turno==Cor.BRANCAS?"VITORIA_PRETAS":"VITORIA_BRANCAS";
    }
    private List<Movimento> obterMovimentosSemCaptura(Cor cor) {
        List<Movimento> r=new ArrayList<Movimento>();
        for(int l=0;l<8;l++) for(int c=0;c<8;c++) {
            Posicao a=new Posicao(l,c); Peca p=tabuleiro.get(a);
            if(p==null||p.getCor()!=cor) continue;
            List<int[]> dirs=new ArrayList<int[]>(); p.direcoes(dirs);
            if(p.isDama()) {
                for(int[] d:dirs) { int x=l+d[0],y=c+d[1]; while(dentro(x,y)&&tabuleiro.get(new Posicao(x,y))==null) {r.add(new Movimento(a,new Posicao(x,y)));x+=d[0];y+=d[1];} }
            } else for(int[] d:dirs) { Posicao b=new Posicao(l+d[0],c+d[1]); if(b.valida()&&tabuleiro.get(b)==null) r.add(new Movimento(a,b)); }
        }
        return r;
    }
    private boolean dentro(int l,int c) { return l>=0&&l<8&&c>=0&&c<8; }

    private List<CaminhoCaptura> obterCapturas(Cor cor) {
        List<CaminhoCaptura> todos=new ArrayList<CaminhoCaptura>();
        for(int l=0;l<8;l++) for(int c=0;c<8;c++) {
            Posicao inicio=new Posicao(l,c); Peca p=tabuleiro.get(inicio);
            if(p!=null&&p.getCor()==cor) {
                List<CaminhoCaptura> caminhos=new ArrayList<CaminhoCaptura>();
                buscarCapturas(p,inicio,inicio,new HashSet<Posicao>(),new ArrayList<Posicao>(),new ArrayList<Posicao>(),caminhos);
                // Cada caminho recebe a origem no mapa auxiliar.
                for(CaminhoCaptura cp:caminhos) { origens.put(cp,inicio); todos.add(cp); }
            }
        }
        return todos;
    }
    private Map<CaminhoCaptura,Posicao> origens=new IdentityHashMap<CaminhoCaptura,Posicao>();
    private void buscarCapturas(Peca p,Posicao inicio,Posicao atual,Set<Posicao> usadas,List<Posicao> destinos,List<Posicao> capturadas,List<CaminhoCaptura> saida) {
        List<int[]> dirs=new ArrayList<int[]>();
        if(p.isDama()) p.direcoes(dirs);
        else { dirs.add(new int[]{1,1}); dirs.add(new int[]{1,-1}); dirs.add(new int[]{-1,1}); dirs.add(new int[]{-1,-1}); }
        boolean achou=false;
        for(int[] d:dirs) {
            int l=atual.getLinha()+d[0],c=atual.getColuna()+d[1];
            if(!p.isDama()) {
                Posicao meio=new Posicao(l,c),fim=new Posicao(l+ d[0],c+d[1]);
                if(fim.valida() && tabuleiro.get(meio)!=null && tabuleiro.get(meio).getCor()!=p.getCor() && !usadas.contains(meio) && tabuleiro.get(fim)==null) {
                    achou=true; avancar(p,inicio,atual,meio,fim,usadas,destinos,capturadas,saida);
                }
            } else {
                while(dentro(l,c) && tabuleiro.get(new Posicao(l,c))==null) { l+=d[0]; c+=d[1]; }
                if(!dentro(l,c)) continue;
                Posicao alvo=new Posicao(l,c);
                if(tabuleiro.get(alvo).getCor()==p.getCor() || usadas.contains(alvo)) continue;
                l+=d[0];c+=d[1];
                while(dentro(l,c) && tabuleiro.get(new Posicao(l,c))==null) {
                    achou=true; avancar(p,inicio,atual,alvo,new Posicao(l,c),usadas,destinos,capturadas,saida);
                    l+=d[0];c+=d[1];
                }
            }
        }
        if(!achou && !destinos.isEmpty()) {
            CaminhoCaptura cp=new CaminhoCaptura();
            for(int i=0;i<destinos.size();i++) cp.adicionar(destinos.get(i),capturadas.get(i));
            saida.add(cp);
        }
    }
    private void avancar(Peca p,Posicao inicio,Posicao atual,Posicao comida,Posicao destino,Set<Posicao> usadas,List<Posicao> destinos,List<Posicao> capturadas,List<CaminhoCaptura> saida) {
        usadas.add(comida); destinos.add(destino); capturadas.add(comida);
        buscarCapturas(p,inicio,destino,usadas,destinos,capturadas,saida);
        capturadas.remove(capturadas.size()-1); destinos.remove(destinos.size()-1); usadas.remove(comida);
    }
    public String estado() {
        String s="TABULEIRO_INICIO\n"; for(String l:tabuleiro.linhas()) s+=l+"\n";
        s+="TABULEIRO_FIM\nTURNO="+turno+"\nSTATUS="+status+"\n";
        if(brancas!=null) s+="BRANCAS="+brancas.getNome()+"\nPRETAS="+pretas.getNome()+"\n";
        return s;
    }
    public String salvarTexto() {
        String s="DAMAS1\n"+brancas.getNome()+"\n"+pretas.getNome()+"\n"+turno+"\n"+status+"\n";
        for(int l=0;l<8;l++) { for(int c=0;c<8;c++) s+=tabuleiro.get(new Posicao(l,c))==null?'.':tabuleiro.get(new Posicao(l,c)).simbolo(); s+="\n"; }
        return s;
    }
    public void carregarTexto(List<String> linhas) throws Exception {
        if(linhas.size()!=13 || !"DAMAS1".equals(linhas.get(0))) throw new Exception("Formato de arquivo invalido.");
        Cor novoTurno=Cor.valueOf(linhas.get(3));
        String novoStatus=linhas.get(4);
        if(!novoStatus.equals("EM_ANDAMENTO")&&!novoStatus.equals("VITORIA_BRANCAS")&&!novoStatus.equals("VITORIA_PRETAS")) throw new Exception("Status invalido.");
        Peca[][] novas=new Peca[8][8];
        for(int l=0;l<8;l++) {
            String linha=linhas.get(5+l); if(linha.length()!=8) throw new Exception("Tabuleiro invalido.");
            for(int c=0;c<8;c++) {
                char ch=linha.charAt(c); Posicao pos=new Posicao(l,c);
                if(ch=='.') continue;
                if((l+c)%2!=0) throw new Exception("Peca em casa clara.");
                if(ch=='b') novas[l][c]=new PecaComum(Cor.BRANCAS);
                else if(ch=='p') novas[l][c]=new PecaComum(Cor.PRETAS);
                else if(ch=='B') novas[l][c]=new Dama(Cor.BRANCAS);
                else if(ch=='P') novas[l][c]=new Dama(Cor.PRETAS);
                else throw new Exception("Simbolo desconhecido.");
            }
        }
        if(linhas.get(1).trim().isEmpty()||linhas.get(2).trim().isEmpty()) throw new Exception("Nome vazio.");
        brancas=new Jogador(linhas.get(1),Cor.BRANCAS); pretas=new Jogador(linhas.get(2),Cor.PRETAS);
        turno=novoTurno; status=novoStatus; tabuleiro.definir(novas); limparSequencia();
        if("EM_ANDAMENTO".equals(status)) verificarFim();
    }
}
