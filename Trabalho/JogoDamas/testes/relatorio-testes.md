# Relatório de testes

**Referência:** roteiro TM01–TM23 e TA01–TA10 do PDF do trabalho.
**Ambiente:** compilação com `javac` em modo Java 8 e execução real da classe `aplicacao.Main` pelo terminal do JDK.
**Observação:** os fluxos foram executados no terminal automatizando a entrada de comandos. Não foi feita uma sessão manual dentro da interface gráfica do Eclipse; recomenda-se repetir a demonstração no Eclipse antes da apresentação.

## Verificação técnica

- Compilação de todos os arquivos `.java`: **APROVADA** (código de saída 0). Foram exibidos apenas avisos do JDK atual sobre as opções `-source 8` e `-target 8`.
- Inicialização e comandos do terminal: **APROVADOS**.
- Salvamento e carregamento em execuções separadas: **APROVADOS**.
- Cenários de captura simples, obrigatória, múltipla, para trás e maior captura: **APROVADOS**.
- Promoção, movimento longo/para trás da dama e bloqueio: **APROVADOS**.
- Arquivos inválidos/inexistentes/vazios e tentativa de salvar durante captura: **APROVADOS**.

## Resultados dos testes do PDF

| Teste | Resultado observado na execução | Situação |
|---|---|---|
| TM01 | Tabuleiro inicial 8x8, 12 peças por lado, brancas iniciam. | **APROVADO — terminal/JDK** |
| TM02 | A3 foi para B4; A3 ficou vazia e o turno passou às pretas. | **APROVADO — terminal/JDK** |
| TM03 | Movimento horizontal rejeitado; estado e turno preservados. | **APROVADO — terminal/JDK** |
| TM04 | Destino ocupado rejeitado. | **APROVADO — terminal/JDK** |
| TM05 | Movimento de peça adversária rejeitado. | **APROVADO — terminal/JDK** |
| TM06 | Coordenada Z9 rejeitada sem encerrar o programa. | **APROVADO — terminal/JDK** |
| TM07 | Movimentos A3-B4 e B6-A5 aceitos; turno retornou às brancas. | **APROVADO — terminal/JDK** |
| TM08 | Segundo movimento consecutivo das brancas rejeitado. | **APROVADO — terminal/JDK** |
| TM09 | Captura C3-E5 executada e D4 removida. | **APROVADO — terminal/JDK** |
| TM10 | Movimento comum rejeitado porque havia captura obrigatória. | **APROVADO — terminal/JDK** |
| TM11 | Captura C3-E5 seguida de E5-G7; peças capturadas removidas ao final. | **APROVADO — terminal/JDK** |
| TM12 | Captura para trás E5-C3 aceita. | **APROVADO — terminal/JDK** |
| TM13 | Rota de uma captura rejeitada; rota de duas capturas aceita. | **APROVADO — terminal/JDK** |
| TM14 | A7-B8 promoveu a peça a dama (símbolo B). | **APROVADO — terminal/JDK** |
| TM15 | Dama percorreu C3-F6. | **APROVADO — terminal/JDK** |
| TM16 | Dama percorreu F6-C3 para trás. | **APROVADO — terminal/JDK** |
| TM17 | Dama não atravessou a peça própria em E5. | **APROVADO — terminal/JDK** |
| TM18 | Última peça preta capturada; Ana declarada vencedora e novos movimentos bloqueados. | **APROVADO — terminal/JDK** |
| TM19 | Partida identificou vitória das brancas por ausência de movimentos das pretas. | **APROVADO — terminal/JDK** |
| TM20 | Partida salva em arquivo após A3-B4. | **APROVADO — terminal/JDK** |
| TM21 | Arquivo recarregado em nova execução; B4, A3 vazia, nomes e turno PRETAS preservados. | **APROVADO — terminal/JDK** |
| TM22 | Arquivo inexistente gerou mensagem de erro e a aplicação continuou. | **APROVADO — terminal/JDK** |
| TM23 | Arquivo incompatível rejeitado; partida anterior permaneceu carregada. | **APROVADO — terminal/JDK** |
| TA01 | Comando desconhecido informou erro e aplicação continuou. | **APROVADO — terminal/JDK** |
| TA02 | MOVER sem coordenadas foi rejeitado com instrução de uso. | **APROVADO — terminal/JDK** |
| TA03 | Casa clara foi rejeitada. | **APROVADO — terminal/JDK** |
| TA04 | Origem sem peça foi rejeitada. | **APROVADO — terminal/JDK** |
| TA05 | Movimento para trás de peça comum sem captura foi rejeitado. | **APROVADO — terminal/JDK** |
| TA06 | Movimento após encerramento foi rejeitado. | **APROVADO — terminal/JDK** |
| TA07 | Arquivo vazio foi rejeitado; aplicação continuou. | **APROVADO — terminal/JDK** |
| TA08 | Comandos e coordenadas em minúsculas foram reconhecidos. | **APROVADO — terminal/JDK** |
| TA09 | Salvamento durante captura múltipla foi bloqueado; sequência pôde continuar. | **APROVADO — terminal/JDK** |
| TA10 | NOVO após partida encerrada reinicializou tabuleiro e turno. | **APROVADO — terminal/JDK** |

## Cenários preparados

Os arquivos de estado estão em `testes/cenarios/`. Copie o cenário correspondente para `partidas/` e carregue com `CARREGAR nome-do-arquivo.txt`. Os arquivos `TM23-invalido.txt` e `TA07-vazio.txt` são intencionalmente inválido e vazio.

## Pendências antes da apresentação

Repetir os mesmos fluxos pelo Console do Eclipse, conforme o professor solicita, e atualizar este relatório caso o comportamento observado na IDE seja diferente. O projeto também foi preparado para execução pelo terminal integrado do VS Code.
