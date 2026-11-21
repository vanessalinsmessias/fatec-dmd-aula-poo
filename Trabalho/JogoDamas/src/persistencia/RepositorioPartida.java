package persistencia;
import java.io.*;
import java.util.*;
public interface RepositorioPartida {
    void salvar(String nome, String conteudo) throws IOException;
    List<String> carregar(String nome) throws IOException;
}
