
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


### 6- Comentárioss pessoais ( metodologia, resultados, conclusões)
