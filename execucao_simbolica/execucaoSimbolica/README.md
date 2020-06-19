## Projeto Final da Disciplina: Execução Simbólica
### Documentação básica para referência.


* Classe principal: br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta.TestInputGenerator.

* Classe de teste Principal: br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta.TestInputGeneratorTest

* Configurações básicas: br.com.each.ppgsi.testeDeSoftware.commons.Commons.
FROM_NUMBER = -1;
TO_NUMBER = 10;
* RESOLUTION_DESTINATION_PATH = Caminho do arquivo de destino da resolução desejada
* COMPLETE_RESOLUTION_DESTINATION_PATH  = Caminho do arquivo de destino da resolução desejada, porém informando a variável antes do valor
* CONSTRAINTS_DESTINATION_PATH = Caminho do arquivo onde estão impressas as expressões e restrições geradas pelo interpretator antes de inserir n Choco.
* BOUNDARY_ANALYSIS_DESTINATION_PATH = Caminho do arquivo de saida da análise de valor limite primeiro campo: instrução original, segundo campo: valores sugeridos

#### Descrição da Solução.

* O primeiro componente é um analisador Léxico simples do formato do arquivo de entrada.
* Em seguida é aplicado o algoritmo ShuntingYard para traduzir as expressões infix em Reverse Polish Notation, de modo a permitir um melhor controle de pilha( pelo menos eu achei melhor).
* Em seguida foi criado uma estrutura baseada no padrão Interpreter (GoF), uma vez que a proposta exigia uma tradução a DSL externa de entrada para DSL interna do Choco Solver. Como resultado as operações em RPN são traduzidas para espressões no Interpretador. Existem portanto uma classe para cada Token da DSL de entrada. Ex: IntegerVariableExpression, IntegerConstantExpression, GreaterThanExpression, EqualsExpression etc. tais expressões são encadeadas formando a cadeia de expressões esperada pelo Choco.
* Logo após a aplicação das restrições ao Choco, são gerados os arquivos de saida no diretorio /src/java/resource( padrão do Maven);
* Após esta primeira Etapa, o mesmo algoritmo é utilizado para gerar o básico das análise de Valor Limite.

* Para gerar o valor limite, a estratégia ( fraca ) foi alterar as expressões de entrada originais, mexendo nos operadores e deixando o solucionador derestrições achar os novos valores de limite. Não deu certo para todos os casos, e não contempla interdependência entre variáveis.

* Foram feita algumas alterações no algoritmo ShuntingYard original, que permitiu a utilização de expressões matemáticas maiores que apenas uma adição ou subtração. Não foi testado até o momento, o balanceamento de instruções mais complexas entre parênteses, mas em teoria era para funcionar. Uma as alterações foi a inclusão de operadores relacionais e foi baixado sua precedência. Provavelmente operações AND e OR também funcionam, mas não cheguei a testar a fundo até o momento.

* Existem 2 arquivos de teste no projeto em /src/resources. Um formal pré disponibilizado e outro que criei um pouquinho mais compexo e com a resolução do programa triângulo no caminho do teste do tipo isósceles.

* Para testar de maneira mais automatizada, o roteiro foi: Criar as restrições no solver à mão, executar o programa e depois comparar as constraints geradas pelo programa e pelo choco, em modo texto, com "String.equals" mesmo. Existe um arquivo de teste chamado restricoes_teste.txt com uma gama maior de testes que estão automatizados. Ao inserir linhas nele o teste é executado levando em conta as novas linhas. Pode-se comentar cada linha com um caracter "#".  

* IMPORTANTE: Foi seguido o padrão do arquivo texto para o parsing, respeitando os espaços e a ausência dele quando ocorre. Erros doidos ocorrem quando alguma coisa sai do padrão no Analisador Léxico.

* Todas as configurações parametrizáveis ficam na classe Commons, como propriedades estáticas de classe.

* A análise de valor limite não ficou legal, falha bastante.

* Para rodar de manera plug and play deve-se manter a configuração abaixo do maven e executar o comando no prompt ou shell: ***mvn install:install-file -Dfile=${diretorio do jar do choco}/choco-solver-2.1.5.jar-DgroupId=choco -DartifactId=choco -Dversion=2.1.5 -Dpackaging=jar***

        <dependency>
            <groupId>choco</groupId>
            <artifactId>choco</artifactId>
            <version>2.1.5</version>
        </dependency>

* Ainda há muita gambiarra em código e precisa de muita refatoração
