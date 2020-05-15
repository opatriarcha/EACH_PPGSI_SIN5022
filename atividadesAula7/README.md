### Atividades Referentes à Aula 7.

#### Descrição Do mecanismo para geração automatizda de Casos de Teste.

Estou considerando aqui que estamos falando de gerar os casos de teste via JUnit e testes estruturais. 1 classe de teste JUnit por Classe de negocio.

**Passo 1 -** Via introspecção e reflexão varrer a classe a ser testada, armazenando metadados dos métodos (Nome, parâmetros, tipos dos parâmetros, etc). Armazenaria isso provavelmente em numa lista de mapas, criando um repositório de metadados. No caso do triângulo, eu teria nesse momento o conhecimento dos parẫmetros.

**Passo 2 -** Olhando para o código fonte (porque em reflexão acredito nao ser possível mapear ou observar estruturas de dados dentro dos métodos em java ), eu tentaria ler o conteudo do método tokenizando as instruções de desvio e seus operadores lógicos (IFs &&, & |, ||, >, <, >=, <=, ==, !=, metodo equals e compareTo ) e tentaria recompor a sequência lógica em um grafo que represente o fluxo do programa, assim como números mágicos e as variáveis.

**Passo 3 -** O problema da semântica. Sem a intervenção de um humano precisariamos descobrir os valores possiveis de LA, LB e LC, ou o que significam as variáveis em termos do problema, então eu criaria uma DSL que defina essa semântica, para ser usada ao se especificar a função. Então nesse modelo o documento de requisitos deve ser baseado nessa DSL. Tal DSL pode ser um português estruturado formalmente definido.

**Passo 4 -** Juntando tudo. A partir do documento de requisitos escrito com a DSL, e lido pelo gerador de casos de teste e do grafo da parte 2 talves seja possível inferir via uma árvore de decisão, por exemplo, os valores, uma vez que teríamos a relação entre as variáveis no grafo e no documento de requisitos.

**Passo 5 -** Criar outro algoritmo que para cada caminho do grafo crie um caso de teste utiliando as relações entre variáveis e numeros mágicos. Os métodos do Junit seriam as partes mais fáceis pois podem ser utilizados templates simples para a construção.

**Pontos fortes**:

  * Se conseguirmos criar o grafo, poderiamos ter também á partir dele um indicador rudimentar de cobertura.

  * Essa estratégia poderia ser utilizada também para testes de mutação, uma vez que tokenizamos as estruturas de controle e geramos os arquivos do JUnit. Poderiam ser gerados os programas P' como clones modificados via codigo fonte, compilados e adicionados as suites de testes. (daria para manter sistematicamente o historico dos mutantes de maneira que um humano possa ler).


**Pontos Fracos:**

  * Não sei o qual factível é essa análise do corpo de métodos sem utilizar manipulação de bytecode de alguma maneira (javaAssist, BCEL, ASM, etc ).

  * É esperado um grande dispêndio de recursos computacionais. ( via manipulação de bytecode seria computacionalmente rápido )

  * De qualquer maneira uma generalização desse programa seria bastante complexa.

  * Não tratamos aqui neste desenho de nenhum teste de integração, mas por outro lado é muito comum termos coisas como mocks, por exemplo dentro dos testes de unidade.

  * O maior problema talvez seja montar o grafo de fluxo.

**Devaneios:**
  * É bem possível que não precise da DSL do Passo 3. Talvez uma estratégia baseada em DSl seja mais viável para testes de caixa preta...


**Avaliação da solução**
  Em tempo de experimentação, verificaria se os casos de testes atingem alto percentual de branch e line coverage. Poderia aplicar os testes de mutação para ver se algum mutante represente um caso não coberto pela heuristica, utilizando o score de mutação.
