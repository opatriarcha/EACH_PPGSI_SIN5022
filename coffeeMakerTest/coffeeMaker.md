
Para instalar a dependência CoffeeMakerTest no maven local:
mvn install:install-file -Dfile=/home/orlando/Desktop/SIN5022-Testes-De-Software/repository/SIN5022_testes_software/CoffeeMakerTest/lib/CoffeeMaker.jar -DgroupId=each.usp.ach2006.adaptedfromcsc326.eler -DartifactId=coffeeMaker -Dversion=1.0 -Dpackaging=jar


ATENÇÃo. Utilizei um framework para criar e reutilizar as fixtures, o FixtureFactory. È como um FactoryGirl do ruby. A dependência foi inserida no pom.xml do maven. Basicamente tenho um arquivo de template java das fixtures e vou reutilizando e alterando conforme cada cenário de teste aparece. Quando tenho q usar uma fixture é so "pedir" pelo nome da fixture ao framework.

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
Deve ser possível obter todas as receitas cadastradas na máquina ok

Preparar Receita
Uma receita deve ser preparada de acordo com a seleção de um usuário e de um valor inserido em
centavos para pagar pela bebida.

Se a receita existe, e o valor pago é o suficiente, então a bebida deve ser
feita, as unidades utilizadas para fazer a bebida devem ser subtraídas do estoque de cada ingrediente, e o
troco (se houver) deve ser retornado para o cliente.

Se o cliente não inserir dinheiro suficiente, a bebida
não deve ser feita e o cliente deve saber que o valor foi insuficiente. ok

Se a quantidade de ingredientes na
máquina não for o suficiente para fazer a receita, a receita não deve ser feita e o cliente deve ser avisado
do motivo. ok

Sempre que não for possível fazer a bebida, o valor pago pelo cliente deve ser devolvido. ok

### Análise de Classes de Equivalência CoffeeMaker
|Funcao                     | Entrada                   | Classes Válidas          | Classes Inválidas            |
|---------------------------|---------------------------|--------------------------|------------------------------|
|checkChocolateInventory    |sem adicionar estoque       |   20 unidades    C1     | 20 < estoque < 20 C2, C3     |
|addChocolateInventory      |adiciona 10 unidades        |   30 unidades    C4     | 30 < estoque < 30 C5, C6     |
|checkChocolateInventory    |adicionar as 10 unidades    |   30 unidades    C7     | estoque=30 demais 20 C8, C9     |
|checkCoffeeInventory       |sem adicionar estoque       |   20 unidades    C10    | 20 < estoque < 20 C11, C12   |
|addCoffeeInventory         |adiciona 10 unidades        |   30 unidades    C13    | 30 < estoque < 30 C14, C15   |
|checkCoffeeInventory       |adicionar as 10 unidades    |   30 unidades    C16    | estoque=30 demais 20 C17, C18   |
|checkMilkInventory         |sem adicionar estoque       |   20 unidades    C19    | 20 < estoque < 20 C20, C21   |
|addMilkInventory           |adiciona 10 unidades        |   30 unidades    C22    | 30 < estoque < 30 C23, C24   |
|checkMilkInventory         |adicionar as 10 unidades    |   30 unidades    C25    | estoque=30 demais 20, C27   |
|checkSugarInventory        |sem adicionar estoque       |   20 unidades    C28    | 20 < estoque < 20 C29, C30   |
|addSugarInventory          |adiciona 10 unidades        |   30 unidades    C31    | 30 < estoque < 30 C32, C33   |
|checkSugarInventory        |adicionar as 10 unidades    |   30 unidades    C34    | estoque=30 demais 20 C35, C36   |
|addRecipe                  |A receita (nome)            |   3 com nomes diferentes C37 | duas om nomes iguais C38  |
|addRecipe                  |A receita (qtd)             |   3 com nomes diferentes C37 | 4 receitas    C39        |
|getRecipes                 |inserir receitas            |   3 com nomes diferentes C37 || *Seria somente um caso de teste? O que colocar aqui?
|getRecipes                 |inserir receitas            |   3 com nomes diferentes C37 || *Seria somente um caso de teste? O que colocar aqui?   |   |
|deleteRecipe               |inserir receitas antes      |   quantidade Correta C38| Quantidade incorreta C39            |
|deleteRecipe               |receita                     |   nome existente  C40   | nome inexistente C41, nome nulo C42 |
|addRecipe                  |receita (quantidades)       |   qtds diferentes  C43  | 2 qtds iguais, 3 qtds iguais    C44 |
|makeCoffee                 |   receita, valor           |  receita existente  C45 | receita inexistente             C46 |
|makeCoffee                 |   receita, valor           |  valor coerente     C47 | valor menor que o necessário     C48|

### Análise de Valor Limite CoffeeMaker
|Funcao                     | Entrada                     |  Classes Válidas     | Classes Inválidas                         |
|---------------------------|-----------------------------|----------------------|-------------------------------------------|
| addChocolateInventory     |  parametro unico            | 10, 80   v1, v2      | param=0 V3, param=-1 v4, param = 100 v5   |
| addCoffeeInventory        |  parametro unico            | 10, 80   v6, v7      | param=0 V8, param=-1 v9, param = 100 v10  |
| addMilkInventory          |  parametro unico            | 10, 80   v11, v12    | param=0 V13, param=-1 v14, param = 100 v15|
| addSugarInventory         |  parametro unico            | 10, 80   v16, v17    | param=0 V18, param=-1 v19, param = 100 v20|


| ID |  Função                   | Entrada                    | Oráculo                 | Classe de Equivalência ou valor        |
|----|---------------------------|----------------------------|-------------------------|----------------------------------------|
|1   |checkChocolateInventory    |sem adicionar estoque       |   20 unidades    C1     | 20 < estoque < 20 C2, C3               |
|2   |addChocolateInventory      |adiciona 10 unidades        |   30 unidades    C4     | 30 < estoque < 30 C5, C6               |
|3   |checkChocolateInventory    |após adicionar as 10 unidades    |   30 unidades    C7     | estoque=30 demais 20 C8, C9               |
|4   |checkCoffeeInventory       |sem adicionar estoque       |   20 unidades    C10    | 20 < estoque < 20 C11, C12             |
|5   |addCoffeeInventory         |adiciona 10 unidades        |   30 unidades    C13    | 30 < estoque < 30 C14, C15             |
|6   |checkCoffeeInventory       |após adicionar as 10 unidades    |   30 unidades    C16    | estoque=30 demais 20 C17, C18             |
|7   |checkMilkInventory         |sem adicionar estoque       |   20 unidades    C19    | 20 < estoque < 20 C20, C21             |
|8   |addMilkInventory           |adiciona 10 unidades        |   30 unidades    C22    | 30 < estoque < 30 C23, C24             |
|9   |checkMilkInventory         |após adicionar as 10 unidades    |   30 unidades    C25    | estoque=30 demais 20 C26, C27             |
|10  |checkSugarInventory        |sem adicionar estoque       |   20 unidades    C28    | 20 < estoque < 20 C29, C30             |
|11  |addSugarInventory          |adiciona 10 unidades        |   30 unidades    C31    | 30 < estoque < 30 C32, C33             |
|12  |checkSugarInventory        |após adicionar as 10 unidades    |   30 unidades    C34    | estoque=30 demais 20 C35, C36             |
|13  |addRecipe                  |A receita (nome)            |   3 com nomes diferentes C37 | duas om nomes iguais C38          |
|14  |addRecipe                  |A receita (qtd)             |   3 com nomes diferentes C37 | 4 receitas    C39                 |
|15  |getRecipes                 |inserir receitas            |   3 com nomes diferentes C37 |                                   |
|17  |deleteRecipe               |inserir receitas antes      |   quantidade Correta C38| Quantidade incorreta C39               |
|18  |deleteRecipe               |receita                     |   nome existente  C40   | nome inexistente C41, nome nulo C42    |
|19  |addRecipe                  |receita (quantidades)       |   qtds diferentes  C43  | 2 qtds iguais, 3 qtds iguais    C44    |
|20  |makeCoffee                 |   receita, valor           |  receita existente  C45 | receita inexistente             C46    |
|21  |makeCoffee                 |   receita, valor           |  valor coerente     C47 | valor menor que o necessário     C48   |
|22  | addChocolateInventory     |  10, 80  v1, v2            | 20, 100              | param=0 V3, param=-1 v4, param = 100 v5   |
|23  | addCoffeeInventory        |  parametro unico           | 10, 80   v6, v7      | param=0 V8, param=-1 v9, param = 100 v10  |
|24  | addMilkInventory          |  parametro unico           | 10, 80   v11, v12    | param=0 V13, param=-1 v14, param = 100 v15|
|25  | addSugarInventory         |  parametro unico           | 10, 80   v16, v17    | param=0 V18, param=-1 v19, param = 100 v20|
