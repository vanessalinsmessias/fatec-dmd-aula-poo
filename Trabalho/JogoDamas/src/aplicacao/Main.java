package aplicacao;
import java.util.*;
import jogo.*;
import modelo.*;
import persistencia.*;

public class Main {
    public static void main(String[] args) {
        Scanner entrada=new Scanner(System.in);
        Partida partida=new Partida();
        RepositorioPartida arquivos=new ArquivoPartida();
        System.out.println("=================================\n          JOGO DE DAMAS\n=================================");
        System.out.println("Comandos: NOVO Ana Pedro | MOVER A3 B4 | ESTADO | SALVAR partida.txt | CARREGAR partida.txt | SAIR");
        while(true) {
            System.out.print("> ");
            if(!entrada.hasNextLine()) break;
            String linha=entrada.nextLine().trim();
            if(linha.isEmpty()) continue;
            String[] partes=linha.split("\\s+");
            String comando=partes[0].toUpperCase();
            try {
                if(comando.equals("SAIR")) { System.out.println("Programa encerrado."); break; }
                else if(comando.equals("NOVO")) {
                    if(partes.length!=3) { System.out.println("Use: NOVO nomeBrancas nomePretas"); continue; }
                    partida.nova(partes[1],partes[2]); System.out.println("Partida iniciada. Jogador atual: "+partida.nomeAtual()+" (Brancas).");
                } else if(comando.equals("MOVER")) {
                    if(partes.length!=3) { System.out.println("Use: MOVER origem destino"); continue; }
                    String resposta=partida.mover(Posicao.ler(partes[1]),Posicao.ler(partes[2]));
                    System.out.println(resposta);
                    if(partida.existe() && "EM_ANDAMENTO".equals(partida.getStatus())) System.out.println("Proximo jogador: "+partida.nomeAtual()+" ("+partida.getTurno()+")");
                    else if(partida.existe()) System.out.println("Vencedor: "+(partida.getStatus().equals("VITORIA_BRANCAS")?partida.nome(Cor.BRANCAS):partida.nome(Cor.PRETAS)));
                } else if(comando.equals("ESTADO")) {
                    if(!partida.existe()) System.out.println("Nenhuma partida iniciada.");
                    else System.out.print(partida.estado());
                } else if(comando.equals("SALVAR")) {
                    if(partes.length!=2) { System.out.println("Use: SALVAR nome.txt"); continue; }
                    if(!partida.existe()) { System.out.println("Nao ha partida para salvar."); continue; }
                    if(partida.emCaptura()) { System.out.println("Nao e permitido salvar durante uma captura multipla."); continue; }
                    arquivos.salvar(partes[1],partida.salvarTexto()); System.out.println("Partida salva com sucesso em partidas/"+partes[1]+".");
                } else if(comando.equals("CARREGAR")) {
                    if(partes.length!=2) { System.out.println("Use: CARREGAR nome.txt"); continue; }
                    List<String> dados=arquivos.carregar(partes[1]);
                    Partida temporaria=new Partida(); temporaria.carregarTexto(dados);
                    partida=temporaria; System.out.println("Partida carregada com sucesso.");
                } else System.out.println("Comando desconhecido.");
            } catch(Exception e) { System.out.println("Erro: "+e.getMessage()); }
        }
        entrada.close();
    }
}
