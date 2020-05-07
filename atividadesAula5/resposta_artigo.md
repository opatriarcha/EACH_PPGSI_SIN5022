
### 1- Qual o Objetivo do artigo?
 O artigo apresenta um novo estudo acerca do relacionamento entre a efetividade e o tamanho da da suite de testes, quando o tamanho da suite é fixado ou não, e ainda tenta indicar se a efetividade está relacionada diretamente com a cobertura de testes.

### 2- Qual o problema ou lacuna de pesquisa  investigado pelo artigo?
  Grande dos artigos da área indicam uma forte tendência de que a efetividade de uma suite de teste está relacionada fortemente com a cobertura dos mesmos. Porém, outros estudos relacionados indicam ou o oposto, ou uma fraca relação entre os dois termos. Porém, as amostras utilizadas entre as pesquisas anteriores não são muito similares, desde programas pequenos até programas com 100.000 SLOC. Este artigo visa realizar um novo estudo equalizando melhor as amostras, em sistemas razoavelmente maiores com mais de uma amosta.

### 3- Os autores definiram quesões de pesquisa? Quais?
  Sim.

  Q1: A efetividade de um suite de teste está relacionada com o numero de casos de teste na suite?

  Q2: A efetividade de uma suite de testes está relacionada com seu *statement, decision(branch)* e / ou *modified condition coverage* quando o número de casos de teste na suite é ignorada?

  Q3:A efetividade de uma suite de testes está relacionada com seu *statement, decision(branch)* e / ou *modified condition coverage* quando o número de casos de teste é constante?

###  4- Descrição do método de pesquisa.
  A metodologia no geral, difere em alguns aspectos dos artigos anteriores sobre o mesmo tema quanto ao objeto de teste  em virtude de o quão realístico os cenários anteriores podem ser, observando o próprio tamanho (SLOC) dos programas em questão e do controle  ou não do tamanho das suites de teste.

  5 grandes programas escritos em java foram escolhidos como objeto da pesquisa, os quais as suites de testes foram criados por desenvolvedores. Foram criadas as suites de teste, calculada a cobertura das suites ( *statement, decision, Modified Condition* ), e depois medida a efetividade da suite usando testes de mutação, buscando representar o potencial da efetividade na detecção de falha da suite de testes.


### 5- Quais foram os resultados principais do artigo?
   Os principais resultados consistem nas respostas diretas da questão de pesquisa:

   Q1: Para alguns grandes programas escritos em java, existe uma correlação de moderada a muito alta entre a efetividade da suite de testes e o numero de métodos de teste que ela contém.

   Q2: Para alguns grandes programas escritos em java, existe uma correlação de moderada  alta entre efetividade e percentual de cobertura de uma suite de teste quando a influência do tamanho da suite é ignorada.

   Q3:  Para grandes programas escritos em java, a correlação entre cobertura e efetividade cai quando o tamanho da suíte é controlado. Após essa queda, a correlação normalmente varia de baixa a moderada, o que significa que geralmente não é seguro supor que a efetividade esteja correlacionada com a cobertura. A correlação é mais forte quando a medição de efetividade não normalizada é usada. Além disso, o tipo de cobertura utilizada teve pouca influência sobre a força do relacionamento.


### 6- Comentários pessoais ( metodologia, resultados, conclusões)

*Vou escrever aqui como se fosse um  bate-papo razoavelmente informal em uma aula pode ser?

Achei interessante a evolução do rigor metodológico aplicado olhando para as pesquisas da área citadas no atigo. Mais que a própria metodologia, me intrigou se a utilização de mutações seria a contra prova mais adequada pra testar essas hipóteses. Ou, se não fosse a única, quais outras seriam? Voltei entao ao artigo *Are a mutants a valid substitute for Real faults in Software testing?* Fraser et. Al. Neste artigo foi indicado que o score de mutação de uma suite de teste é fortemente relacionado com a habilidade da suite de testes de detectar falhas. Então eu me pergunto se por enquanto o score de mutação não pode ser usado largamente unica e especificamente em virtude do despêndio computacional?

Na parte de trabalhos relacionados, eu como não conheço quase nada de pesquisa em teste de software fiquei curioso em como foram eleitos os trabalhos relacionados. Estou acostumado a guiar tudo por revisão sistemática e imaginei que os trabalhos relacionados estão cobertos por uma sistematica de pesquisa. Mas como não está exposto no artigo, fique com as dúvidas: Somente esses são os trabalhos relacionados? São somente os mais relevantes? Quais critérios tornaram esses estudos relevantes? Indice H? Quantidade de Referências? Eu particularmente tento me atentar à metodologia, relação entre objetivos e questões de pesquisa com metodologia e resultados, depois vejo se essa metodologia foi criada naquele momento ou se alguém criou previamente ( para efeito de comparação ). Mas seria mais fácil de eu conhecesse mais de pesquisas dessa área, pois estaria mais familiarizado com algum padrão metodológico q possa existir.

Outro ponto que me intrigou bastante foi a utilização de uma linguagem de tipagem bastante estática (java). Acredito que talvez os scores possam mudar em linguagens mais dinâmicas como Ruby e seu duck typing por exemplo. ( estou pensando assim provavelmente por não conhecer direito os testes de mutação. Talvez após a próxima aula eu mude de ideia drasticamente ou veja o tamanho da bobeira que escrevi)

Eu estou achando também que o título do artigo não está exatamente condizente com as respostas encontradas para as 3 questôes. Eu entendo que a terceira questão de pesquisa veio para amarrar as duas primeiras ou afunilar o resultado e é a que mais tem impacto, e o título se refere à ela, embora para as questões 2 e 3 o resultado foi ligeiramente diferente. Talvez o titulo causou um impacto maior do que deveria, mas isso tb deve ser coisa da minha falta de experiência.

Mais um ponto interessante foram os 4 softwares utilizados como base. São softwares diferentes com propósito totalmente diferentes.

Fiquei pensando também que olha-se muito para o SLOC para definir tamanho de programas, mas eu me intrigo muito quando vejo isso (sei também pode ser muito errado eu ficar divagando essas coisas). Mas em termos de testes estruturais eu pensaria em outras métricas como tamanho funcional ou complexidade ciclomática. Se quanto maior a complexidade de uma aplicação talvez eu tenha muitos caminhos ( olhando para um grafo de fluxo ) onde a aplicação nunca passará, o que diminui o branch coverage e talvez alteraria alguma coisa nos números. Talvez por outro lado a inclusão de testes de mutação mate essa minha dúvida.

Gostei do artigo no final das contas, mas eu precisaria de muito tempo estudando testes de software pra entender a importância desse artigo. No estado que estou hoje, tenho indicios de que foi um bom artigo pelo indice H de onde foi publicado e pela quantidade de referências feitas a ele em tão pouco tempo. Mas desconheco a trajetoria das pesquisas na área pra saber se ele ja é um divisor de águas do tema ou se estou mais uma vez redondamente enganado.
