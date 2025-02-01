# ResolvedorKojun
Código em Scala que resolve puzzles de Kojun

O Kojun é um quebra-cabeça lógico que consiste em uma grade de células, cada uma a ser preenchida sob um conjunto de regras. A primeira regra determina que cada célula da grade deve ser preenchida com um número de modo que cada região de tamanho N contenha cada número de 1 a N exatamente uma vez. Essa propriedade implica que a repetição de números numa mesma região é proibida. Em seguida, a segunda regra estabelece que números em células ortogonalmente adjacentes devem ser diferentes. A terceira regra especifica que, se duas células estiverem verticalmente adjacentes na mesma região, o número na célula superior deve ser maior que o número na célula inferior.

## Explicação do código
Scala é uma linguagem multiparadigmas que dá suporte aos paradigmas procedural, orientado a objetos e funcional. A implementação realizada para o jogo Kojun faz uso de algumas definições de tipo, duas funções auxiliares, a declaração dos tabuleiros e ainda de uma classe Verifier que serve para fazer a resolução e verificação do jogo.

### Boards
Neste arquivo incluem-se todos os tabuleiros que são testados para a aplicação, para criar-se novos, basta colocá-los neste arquivo como um Array de Arrays e posteriormente chamá-lo no main com as respectivas operações no verifier.

### Main
O arquivo main é curto e serve para o princípio único de inicializar o verifier e passar para o mesmos o tabuleiro e o tamanho dele, para ser resolvido.

### Matriz
Aqui tem-se somente a definição do tipo tabuleiro, constituído de linhas que por sua vez são arrays da linguagem Scala. Aqui, também há duas funções auxiliares, o obterValor, que recebe o tabuleiro, a posição e retorna o valor naquela posição. E ainda a função mostrarMatriz, que imprime o tabuleiro no terminal. Além disso, o tipo posição simplesmente encapsula um inteiro que representa a coordenada x e outro inteiro que representa a coordenada y.

### Verifier
O verificador, ou como definido no código, a classe Verifier, tem o propósito de ser a classe que faz o backtracking e validação da jogada. A classe tem 12 métodos sendo 2 que lidam com o backtracking, 5 métodos que servem diretamente para validação da jogada e 5 métodos que são reutilizados para obter informações do tabuleiro.

Para a recursão do backtracking, o método utilizado é o solveBoard, a execução ocorre da seguinte maneira: se o tabuleiro terminou o retorna, senão adquire-se a próxima posição e se ela for um valor diferente de zero, resolve-se o tabuleiro para aquele valor, caso não seja verdade verifica-se de 1 até o tamanho da região uma das jogadas possíveis e depois resolve-se o tabuleiro para a próxima posição. Se essa recursão for verdadeira retorna verdadeiro para esta validação senão retorna falso e coloca o valor da linha e da coluna como zero. O método proximaPosicao é simples, obtém simplesmente a próxima posição do tabuleiro quando iterando-se horizontalmente de cima para baixo.

Para os métodos de validação, foram implementadas as seguintes funções: validarNovaJogada, que recebe o tabuleiro, a posição e o valor de teste, e a partir disso realiza as chamadas das regras específicas e retorna verdadeiro se todas as regras são válidas. ChecarUnicidade é um método que realiza a verificação se dada uma lista de valores tem-se todos valores únicos com exceção do zero, para isso utiliza-se os conjuntos e compara-se os seus tamanhos.

ChecarNumerosDiferentesOrtogonais realiza a validação para entender se os valores ortogonais ao valor da tentativa de inserção são diferentes, de novo utiliza-se um conjunto para a verificação. ValidaAdjacenteCimaBaixoNaRegiao recebe o tabuleiro, a posição, o valor de tentativa de entrada, o valor opcional do que está em cima e do que está embaixo, com esses valores, basta comparar se no caso de existir um valor acima, a tentativa de inserção é de fato menor e se existir um valor embaixo a tentativa de inserção é maior, e se tiver tanto em cima quanto embaixo realiza ambas as validações. Por último, Validar Número Regiao verifica se o número tentativa é menor que o tamanho da região.

Já no contexto dos métodos utilizados para obter as informações do tabuleiro tem-se: obterIdentificadorRegiao, que a partir de uma posição retorna o número da região do tabuleiro. obterValoresRegiao retorna todos os valores presentes em uma dada região. obterTamanhoregiao obtém o identificador e conta a quantidade de posições que fazem parte desta região. ObterAcimaBaixoNaRegiao recebe a posição e retorna dois valores opcionais representando a parte de cima que se encontra na mesma região se existir, e a segunda parte da tupla que representa a posição da mesma região, claro se existir. Por último obter valores ortogonais retorna uma lista com os valores ortogonais à posição passada como parâmetro.



## ✒️ Autores
* ##### [Vitor Praxedes Calegari](https://github.com/Vitor-Calegari)
* ##### [Pedro Henrique Taglialenha](https://github.com/Soul-Legend)
* ##### [Gustavo Bodi](https://github.com/GustavoBodi)
