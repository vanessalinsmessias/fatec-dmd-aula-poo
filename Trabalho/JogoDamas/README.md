# Jogo de Damas — Trabalho Final de POO

## Identificação
Integrantes: preencher com os nomes dos alunos.  
Disciplina: Programação Orientada a Objetos.  
Linguagem: Java 8 ou superior.  
Projeto Java convencional, sem Maven, Gradle ou bibliotecas externas.

## Descrição
Jogo de damas 8x8 para duas pessoas no mesmo computador, controlado por comandos no terminal. As brancas começam nas linhas 1 a 3 e jogam em direção à linha 8; as pretas começam nas linhas 6 a 8 e jogam em direção à linha 1. A1 é casa escura.

## Requisitos
- JDK 8 ou superior instalado.
- Eclipse IDE ou Visual Studio Code com Extension Pack for Java.
- Nenhuma dependência externa.

### Instalar e configurar o JDK
1. Instale um JDK (8 ou superior; recomenda-se uma versão LTS).
2. Abra um terminal e execute `java -version` e `javac -version`.
3. Se os comandos não forem encontrados, configure a variável `JAVA_HOME` e adicione a pasta `bin` do JDK ao `PATH`.
4. No VS Code, instale o pacote **Extension Pack for Java** e selecione o JDK quando solicitado.

## Executar no Eclipse
1. Abra o Eclipse.
2. Selecione **File > Import > General > Existing Projects into Workspace**.
3. Em **Select root directory**, escolha a pasta `JogoDamas` extraída do ZIP.
4. Marque o projeto e clique em **Finish**.
5. Abra `src/aplicacao/Main.java`.
6. Clique com o botão direito e escolha **Run As > Java Application**.
7. Digite os comandos na aba **Console**.

O projeto inclui `.project`, `.classpath` e configuração de compilação Java 8. Não é necessário criar `module-info.java`.

## Executar no Visual Studio Code
1. Abra a pasta `JogoDamas` em **File > Open Folder**.
2. Instale a extensão **Extension Pack for Java** caso ainda não esteja instalada.
3. Aguarde o carregamento do projeto Java.
4. Abra `src/aplicacao/Main.java` e clique em **Run**, ou pressione F5 e escolha **Executar Jogo de Damas**.
5. Também é possível executar pelo terminal integrado.

### Executar pelo terminal
Na pasta raiz do projeto:

Windows:
```bat
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src\modelo\*.java src\jogo\*.java src\persistencia\*.java src\aplicacao\*.java
java -cp bin aplicacao.Main
```

Linux/macOS:
```bash
mkdir -p bin
javac -encoding UTF-8 -d bin src/modelo/*.java src/jogo/*.java src/persistencia/*.java src/aplicacao/*.java
java -cp bin aplicacao.Main
```

## Comandos
- `NOVO Ana Pedro` — inicia uma partida; primeiro nome joga com as brancas.
- `MOVER A3 B4` — movimenta uma peça. Durante captura múltipla, informe cada etapa em um novo comando `MOVER`.
- `ESTADO` — mostra as oito linhas do tabuleiro, turno, status e nomes.
- `SALVAR partida.txt` — salva em `partidas/partida.txt`. Só é permitido entre turnos.
- `CARREGAR partida.txt` — carrega da pasta `partidas/`.
- `SAIR` — encerra.

Comandos e coordenadas aceitam maiúsculas e minúsculas. Coordenadas válidas vão de A1 a H8; somente casas escuras podem receber peças.

## Classes
- `aplicacao.Main`: lê e interpreta comandos do terminal.
- `modelo.Peca`: classe abstrata que representa características comuns de uma peça.
- `modelo.PecaComum` e `modelo.Dama`: especializações; sobrescrevem símbolo e direções, usadas polimorficamente.
- `modelo.Posicao`: representa e valida coordenadas.
- `modelo.Jogador`: nome e cor de cada participante.
- `modelo.Cor`: cores, direção de avanço e cor adversária.
- `jogo.Tabuleiro`: guarda as 64 casas e representa o estado.
- `jogo.Partida`: controla regras, turnos, capturas, promoção e vitória.
- `jogo.Movimento` e `jogo.CaminhoCaptura`: representam movimentos e sequências.
- `persistencia.RepositorioPartida`: interface de persistência.
- `persistencia.ArquivoPartida`: grava e lê arquivos locais.

## Orientação a Objetos
- **Encapsulamento:** atributos privados e operações realizadas por métodos das classes.
- **Herança:** `PecaComum` e `Dama` estendem `Peca`.
- **Polimorfismo:** o tabuleiro e a partida tratam peças como `Peca`; `direcoes()` e `simbolo()` variam conforme o tipo real.
- **Abstração:** `Peca` é abstrata e `RepositorioPartida` define o contrato para salvar e carregar.
- **Separação de responsabilidades:** terminal, regras, modelo e arquivos ficam em pacotes separados.

## Persistência
Arquivos ficam na pasta relativa `partidas/`. Formato textual próprio iniciado por `DAMAS1`, seguido dos nomes, turno, status e oito linhas de oito caracteres (`b`, `p`, `B`, `P`, `.`). O carregamento valida os dados em uma partida temporária antes de substituir a partida atual.

## Testes manuais
Consulte `testes/relatorio-testes.md` e os arquivos em `testes/cenarios/`. Para carregar um cenário, copie o arquivo desejado para `partidas/` e use `CARREGAR nome-do-arquivo.txt`. Os arquivos de cenário estão no mesmo formato de persistência.

## Limitações conhecidas
- Não implementa regras oficiais completas de empate, conforme o enunciado permite como adicional.
- O jogo segue as regras específicas descritas no PDF; não inclui relógio, histórico de jogadas ou desfazer.
- O relatório diferencia compilação/verificações técnicas executadas dos testes manuais que ainda precisam ser realizados no console do Eclipse.
