### teste

* Geração: Como é feita a geração dos mutantes;
* Seleção: Como é feita a seleçãod os testes;
* Inserção: Como é feita a inserção dos mutantes;
* Deteção: Como é feita a detecção de mutantes;
* Saída: Qual o formato de saída;
* SF: Saída estruturada;
* Licença: Licença do software;





| Produto      | Geração       | Seleção   | Inserção       | Detecção | Saída              | SF* | Licença | Mailing List  |
|--------------|---------------|-----------|----------------|----------|--------------------|---- |---------|---------------|
|Jester        | Codigo-Fonte  | NAIVE?    | NAIVE?         | NAIVE?   | Código anotado     | Não | MIT     | Não           |
|Simple Jester | Codigo-Fonte  | NAIVE?    | NAIVE?         | NAIVE?   | Código anotado     | Não | MIT     | Não           |
|Jumble        | BCEL          | Covenção  | ND ClassLoader | EE Fine  | Plain Text         | Não | Apache2 | Sim           |
|PIT           | ASM           | Cobertura | instrumentação | EE Fine  | Código anotado     | Sim | Apache2 | Sim           |
|MuJava        | Codigo-Fonte? | Manual    | N/A            | N/A      | Código anotado     | Não | N/A     | Não           |
|Javalanche    | ASM           | Cobertura | Schemata       | EE Fine? | Plain Text Visual  | Sim | LGPL    | Não           |
|Muclipse      | Codigo-Fonte  | NAIVE?    | NAIVE?         | NAIVE?   | Código Anotado     | Não | MIT     | Não           |

* Simples jester é uma outra versão do próprio Jester.
* Muclipse é somente um plugin para o eclipse IDE que faz a interface com o MuJava.


### Operadores em java da solução muJava

* MuJava separa os operadores em operadores de Métodos e Operadores de classe (O.O.).

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
