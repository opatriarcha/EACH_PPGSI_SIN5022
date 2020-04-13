
Para instalar a dependência CoffeeMakerTest no maven local:
mvn install:install-file -Dfile=/home/orlando/Desktop/SIN5022-Testes-De-Software/repository/SIN5022_testes_software/CoffeeMakerTest/lib/CoffeeMaker.jar -DgroupId=each.usp.ach2006.adaptedfromcsc326.eler -DartifactId=coffeeMaker -Dversion=1.0 -Dpackaging=jar

# SIN5022_testes_software
Repository for SIN5022 exercises.

## Exercício CoffeeMaker

Uma receita consiste de um nome, um preço, unidades de café, unidades de leite, unidades de açúcar, e unidades de chocolate.
maior do que 0

O preço não pode ser 0 e é expresso por um número inteiro que representa quantos
centavos custa a bebida

O nome da receita não pode ser vazio.

As unidades podem ter valor 0, mas pelo menos um ingrediente deve ter unidade

Deve haver uma forma de saber se a receita foi criada com sucesso ou se houve problema com os valores dos ingredientes.

### Análise de Classes de Equivalência Recipe
| Entrada                   | Classes Válidas        | Classes Inválidas     |
|---------------------------|------------------------|-----------------------|
|nome                       | nome >= 0  C1          |"" C2                  |
|preco                      | preco > 0  C3          |preco <= 0 C4          |
|amtCoffee                  | amtCoffee >= 0 C5      |amtCoffee < 0 C6       |
|amtChocolate               | amtChocolate >= 0 C7   |amtChocolate < 0  C8   |
|amtSugar                   | amtSugar >= 0     C9   |amtSugar < 0 C10       |
|amtMilk                    | amtMilk  >= 0     C11  |amtMilk < 0  C12       |
|amtSugar, amtChocolate, amtCoofee, amtMilk|(amtSugar > 0 or <br> amtChocolate > 0 or <br> amtMilk > 0 or <br> amtCoffee > 0  V13   |(amtSugar <= 0 or <br> amtChocolate <= 0 or <br> amtMilk <= 0 or <br> amtCoffee <= 0) V14                       |
| Feedback receita criada   | Receita criada com sucesso  | Receita nao criada|

### Análise de Valor Limite Recipes
| Entrada                     |  Classes Válidas     | Classes Inválidas         |
|-----------------------------|----------------------|---------------------------|
|nome                       | nome >= 0  (V1)        |nome="" V2, nome="  " v2.1 |
|preco                      | preco=5  (V3)          |preco=-1 (V4), preco=0(V5) |
|amtCoffee                  | amtCoffee=5 (V5)       |amtCoffee=-1 (V6)          |
|amtChocolate               | amtChocolate=5 (V7)    |amtChocolate=-1 (V8)       |
|amtSugar                   | amtSugar=5 (V9)        |amtSugar=-1 (v10)          |
|amtMilk                    | amtMilk=5 (V11)        |amtMilk=-1  (V12)          |
|amtSugar, amtChocolate, amtCoofee, amtMilk|amtSugar=1, amtChocolate=1,  amtMilk=1 amtCoffee=1  (V13) <br>AmtSugar=1, amtChocolate=0,  amtMilk=0 amtCoffee=0  (V15)<br>	amtSugar=0, amtChocolate=1,  amtMilk=0 amtCoffee=0  (V16)<br>	amtSugar=0, amtChocolate=0,  amtMilk=0 amtCoffee=1  (V17)<br>amtSugar=0, amtChocolate=0,  amtMilk=0 amtCoffee=1  (V18)|amtSugar=0, amtChocolate=0,  amtMilk=0 amtCoffee=0 <br> 	V14|


##### Em enradas compostas como a chamada ao construtor de Recipe, considerar na tabela apenas o atributo em teste, os demais sao inseridos corretaente.
### Casos de teste Recipes
| ID | Entrada          | Oráculo            | Classe de Equivalência |
|----|------------------|--------------------|------------------------|
|1   | nome="teste"     | true               | C1, V1                 |
|2   | nome=""          | false              | C2, V2                 |
|3   | nome="  "        | false              | C2, V2.1               |
|4   | preco = 5        | true               | C3, V3                 |
|5   | preco = -1       | false              | C4, V4                 |
|6   | preco = 0        | false              | C4, V5                 |
|7   | amtCoffee = 5    | true               | C5, V5                 |
|8   | amtCoffee = -1   | false              | C6, V6                 |
|9   | amtChocolate = 5 | true               | C7, V7                 |
|10  | amtChocolate = -1| false              | C8, V8                 |
|11  | amtSugar = 5     | true               | C9, V9                 |
|12  | amtSugar = -1    | false              | C10, V10               |
|13  | amtMilk = 5      | true               | C11, V11               |
|14  | amtMilk = -1     | false              | C12, V12               |
|15  |amtSugar=1, amtChocolate=1,  amtMilk=1 amtCoffee=1  |true|(V13) |
|16  |amtSugar=1, amtChocolate=0,  amtMilk=0 amtCoffee=0  |true   |(V15)   |
|17  |amtSugar=0, amtChocolate=1,  amtMilk=0 amtCoffee=0  |true   |(V16)   |
|18  |amtSugar=0, amtChocolate=0,  amtMilk=0 amtCoffee=1  |true   |(V17)   |
|19  |amtSugar=0, amtChocolate=0,  amtMilk=1 amtCoffee=0  |true   |(V18)   |
|20  |amtSugar=0, amtChocolate=0,  amtMilk=0 amtCoffee=0  |erro   |(V14, C16)   |





Somente três receitas podem ser cadastradas na máquina de café.

Cada receita tem que ter um nome único na lista de receitas

Não pode haver duas receitas com as mesmas quantidades de ingredientes. O
preço e as unidades da receita devem ser números inteiros.


Remover Receita
Uma receita pode ser apagada da máquina de café se ela está na lista de receitas da máquina.
Deve haver uma forma de saber se a receita foi apagada da máquina ou não.

Reposição
As unidades de cada ingrediente são representadas por números inteiros e positivos.ok
O limite máximo de estoque para cada ingrediente deve ser de no máximo 100 unidades. ok
Os estoques dos ingredientes só são consumidos na máquina quando uma
receita é produzida.
A máquina já começa com um estoque de 20 unidades para cada ingrediente. ok
Deve haver uma forma de saber se o acréscimo de unidades de um ingrediente foi realizado com sucesso. ok

Verificar estoque de ingredientes
Deve ser possível verificar quantas unidades existem de cada ingrediente. ok

Obter receitas
Deve ser possível obter todas as receitas cadastradas na máquina

### Análise de Classes de Equivalência CoffeeMaker
|Funcao                     | Entrada                   | Classes Válidas          | Classes Inválidas     |
|---------------------------|---------------------------|--------------------------|-----------------------|
|checkChocolateInventory    |sem adicionar estoque       |   20 unidades           | 20 < estoque < 20     |
|addChocolateInventory      |adiciona 10 unidades        |   30 unidades           | 30 < estoque < 30     |
|checkChocolateInventory    |adicionar as 10 unidades    |   30 unidades           | 30 < estoque < 30     |
|checkCoffeeInventory       |sem adicionar estoque       |   20 unidades           | 20 < estoque < 20     |
|addCoffeeInventory         |adiciona 10 unidades        |   30 unidades           | 30 < estoque < 30     |
|checkCoffeeInventory       |adicionar as 10 unidades    |   30 unidades           | 30 < estoque < 30     |
|checkMilkInventory         |sem adicionar estoque       |   20 unidades           | 20 < estoque < 20     |
|addMilkInventory           |adiciona 10 unidades        |   30 unidades           | 30 < estoque < 30     |
|checkMilkInventory         |adicionar as 10 unidades    |   30 unidades           | 30 < estoque < 30     |
|checkSugarInventory        |sem adicionar estoque       |   20 unidades           | 20 < estoque < 20     |
|addSugarInventory          |adiciona 10 unidades        |   30 unidades           | 30 < estoque < 30     |
|checkSugarInventory        |adicionar as 10 unidades    |   30 unidades           | 30 < estoque < 30     |
|addRecipe                  |A receita (nome)            |   3 com nomes diferentes| duas om nomes iguais  |
|addRecipe                  |A receita (qtd)             |   3 com nomes diferentes| 4 receitas            |
|getRecipes                 |inserir receitas            |   3 com nomes diferentes|                       | *Seria somente um caso de teste? O que colocar aqui?
|getRecipes                 |inserir receitas            |   3 com nomes diferentes|                       | *Seria somente um caso de teste? O que colocar aqui?   |   |
|deleteRecipe               |inserir receitas antes      |   quantidade Correta    | Quantidade incorreta  |
|deleteRecipe               |receita                     |   nome existente        | nome inexistente, nome nulo  |
|addRecipe                  |receita (quantidades)       |   qtds diferentes       | 2 qtds iguais, 3 qtds iguais |


### Análise de Valor Limite CoffeeMaker
|Funcao                     | Entrada                     |  Classes Válidas     | Classes Inválidas                         |
|---------------------------|-----------------------------|----------------------|-------------------------------------------|
| addChocolateInventory     |  parametro unico            | 10, 80               | param=0 V1, param=-1 v2, param = 100 v3   |
| addCoffeeInventory        |  parametro unico            | 10, 80               | param=0 V1, param=-1 v2, param = 100 v3   |
| addMilkInventory          |  parametro unico            | 10, 80               | param=0 V1, param=-1 v2, param = 100 v3   |
| addSugarInventory         |  parametro unico            | 10, 80               | param=0 V1, param=-1 v2, param = 100 v3   |
