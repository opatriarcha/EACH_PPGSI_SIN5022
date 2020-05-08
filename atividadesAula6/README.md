### Relatório de Ferramentas de Teste de Mutação e operadores

* Geração: Como é feita a geração dos mutantes;
* Seleção: Como é feita a seleçãod os testes;
* Inserção: Como é feita a inserção dos mutantes;
* Deteção: Como é feita a detecção de mutantes;
* Saída: Qual o formato de saída;
* SF: Saída Formatada (estruturada);
* Licença: Licença do software;




| Produto      | Geração       | Seleção   | Inserção       | Detecção | Saída              | SF* | Licença | Mailing List  |
|--------------|---------------|-----------|----------------|----------|--------------------|---- |---------|---------------|
|Jester        | Codigo-Fonte  | ?         | ?              | ?        | Código anotado     | Não | MIT     | Não           |
|Simple Jester | Codigo-Fonte  | ?         | ?              | ?        | Código anotado     | Não | MIT     | Não           |
|Jumble        | BCEL          | Covenção  | ND ClassLoader | EE Fine  | Plain Text         | Não | Apache2 | Sim           |
|PIT           | ASM           | Cobertura | instrumentação | EE Fine  | Código anotado     | Sim | Apache2 | Sim           |
|MuJava        | Codigo-Fonte? | Manual    | N/A            | N/A      | Código anotado     | Não | N/A     | Não           |
|Javalanche    | ASM           | Cobertura | Schemata       | EE Fine? | Plain Text Visual  | Sim | LGPL    | Não           |
|Muclipse      | Codigo-Fonte  |  ?        |      ?         | NAIVE?   | Código Anotado     | Não | MIT     | Não           |


* SimpleJeester é uma outra versão do próprio Jester.
* Muclipse é somente um plugin para o eclipse IDE que faz a interface com o MuJava.
* É importante observar a tabela abaixo pensando não somente em features como em performance. As ferramentas que analisam e alteram o código fonte devem ser bem mais lentas em termos de tempo de execução que as ferramentas que trabalham com algum tipo de instrumentação de bytecode.
* Ferramentas que não tem saída estruturada são mais complicadas de se realizar algum tipo de integração, retroalimentação ou automatização.
* Aparentemente a ferramenta PIT é a que mais se destaca, principalmente por ter integrações prontas e outras em andamento, com gradle, maven, Intellij IDEA e Eclipse.
* PIT permite o uso de extreme mutations com a ferramente PIT-Descartes, aparentemente para otimizar o tempo agasto rodando testes de mutação mais grosseiros (extremos) num primeiro momento e refina-los com testes de mutação com os operadores tradicionais posteriormente.
* A Ferramenta PIT está em constante evolução. Há commits no repositório de código de 12 dias atrás.
* A maioria das outras ferramentas não está ativa em termos de desenvolvimento.


### Operadores em java da solução muJava
O Mu Java foi utilizado na descrição dos operadores por ter documentação mais organizada e aparentemente ter mais operadores.

 MuJava separa os operadores em operadores de Métodos e Operadores de classe (O.O.).

#### Operadores de Métodos
|Operador| Nome completo Descritivo   |
|--------|----------------------------|
|AOR| Arithmetic Operator Replacement |
|AOI| Arithmetic Operator Insertion   |
|AOD| Arithmetic Operator Deletion    |
|ROR| Relational Operator Replacement |
|COR| Conditional Operator Replacement|
|COI| Conditional Operator Insertion  |
|COD| Conditional Operator Deletion   |
|SOR| Shift Operator Replacement      |
|LOR| Logical Operator Replacement    |
|LOI| Logical Operator Insertion      |
|LOD| Logical Operator Deletion       |
|ASR| Assignment Operator Replacement |
|SDL| Statement DeLetion              |
|VDL| Variable DeLetion               |
|CDL| Constant DeLetion               |
|ODL| Operator DeLetion               |

#### Operadores de Classes

|Feature da linguagem | Operador | Descrição                                              |
|---------------------|----------|--------------------------------------------------------|
|Encapsulamento       | AMC      |Access modifier change                                  |
|Herança              |IHD       |Hiding variable deletion                                |
|Herança              |IHI       |Hiding variable insertion                               |
|Herança              |IOD       |Overriding method deletion                              |
|Herança              |IOP       |Overriding method calling position change               |
|Herança              |IOR       |Overriding method rename                                |
|Herança              |ISI       |super keyword insertion                                 |
|Herança              |ISD       |super keyword deletion                                  |
|Herança              |IPC       |Explicit call to a parent’s constructor deletion        |
|Polimorfismo         |PNC       |new method call with child class type                   |
|Polimorfismo         |PMD       |Member variable declaration with parent class type      |
|Polimorfismo         |PPD       |Parameter variable declaration with child class type    |
|Polimorfismo         |PCI       |Type cast operator insertion                            |
|Polimorfismo         |PCC       |Cast type change                                        |
|Polimorfismo         |PCD       |Type cast operator deletion                             |
|Polimorfismo         |PRV       |Reference assignment with other comparable variable     |
|Polimorfismo         |OMR       |Overloading method contents replace                     |
|Polimorfismo         |OMD       |Overloading method deletion                             |
|Polimorfismo         |OAC       |Arguments of overloading method call change             |
|Específico Java      |JTI       |this keyword insertion                                  |
|Específico Java      |JTD       |this keyword deletion                                   |
|Específico Java      |JSI       |static modifier insertion                               |
|Específico Java      |JSD       |static modifier deletion                                |
|Específico Java      |EOA       |Reference assignment and content assignment replacement |
|Específico Java      |EOC       |Reference comparison and content comparison replacement |
|Específico Java      |EAM       |Acessor method change                                   |
|Específico Java      |EMM       |Modifier method change                                  |

#### Considerações sobre os operadores de mutação específicos para plataforma e linguagem JAVA.
* Sinto falta de operadores que trouxeram maior dinamismo à linguagem como Generics, por exemplo. Generics é uma feature relativamente complexa, altamente dependente de mecanismos O.O (Herança e polimorfismo) em java é foco de muitas falhas.

* Não vi nada relacionado ao controle numérico, por exemplo, na transformação de tipos de dados maiores para menores ( BigInteger para Integer ou pior, em decimais na redução de precisão )

* Não vi nada relacionado ao controle de exceções.
