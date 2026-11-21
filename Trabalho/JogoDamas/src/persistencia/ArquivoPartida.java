package persistencia;
import java.io.*;
import java.util.*;
public class ArquivoPartida implements RepositorioPartida {
    private File pasta = new File("partidas");
    public void salvar(String nome,String conteudo) throws IOException {
        validarNome(nome); if(!pasta.exists()) pasta.mkdirs();
        BufferedWriter w=new BufferedWriter(new FileWriter(new File(pasta,nome)));
        try { w.write(conteudo); } finally { w.close(); }
    }
    public List<String> carregar(String nome) throws IOException {
        validarNome(nome); BufferedReader r=new BufferedReader(new FileReader(new File(pasta,nome)));
        List<String> linhas=new ArrayList<String>(); String linha;
        try { while((linha=r.readLine())!=null) linhas.add(linha); } finally { r.close(); }
        return linhas;
    }
    private void validarNome(String nome) throws IOException {
        if(nome==null || nome.trim().isEmpty() || nome.contains("/") || nome.contains("\\") || nome.contains(".."))
            throw new IOException("Informe somente o nome do arquivo, sem caminhos.");
    }
}
