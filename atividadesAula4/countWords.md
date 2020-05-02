# SIN5022_testes_software
Atividade 4 Parte A

### Atividade 2: Contador de palavras

Considere o método int countWords(String str)
- Este método recebe uma string como parâmetro e retorna a
quantidade de palavras que terminam com a letra “r” ou “s”. Uma
palavra precisa ter no mínimo dois caracteres.
- Exemplos: (“Ar puro”,1), (“Fazer valer a pena”, 2), (“As letras r e s
são usadas sempre”, 3)

### Análise de Classes de Equivalência
| Entrada                        | Classes Válidas           | Classes Inválidas           |
|--------------------------------|---------------------------|-----------------------------|
| palavra                        | Strings tamanho > 2 [C1]  |tamanho = 0   [C2]           |
| palavra                        | Strings tamanho > 2 [C1]  |null          [C3]           |
| palavra                        | Strings tamanho > 2 [C1]  |tamanho = 1   [C4]           |
| frase                          | Frase valida        [C5]  | frase com  tamanho < 2  [C6]|
| palavra                        | ultima letra com r ou s   | não existe                  |



### Casos de teste: Classes de Equivalência
| ID | Entrada | Oráculo            | Classe de Equivalência |
|----|---------|--------------------|------------------------|




### Análise de Valor Limite
| Entrada                     |  Classes Válidas          | Classes Inválidas                  |
|-----------------------------|---------------------------|------------------------------------|
|String                       | tamanho String >= 2  (V1) | Tamanho = 0 (V2), tamanho = 1  V3  |
|String                       | tamanho String >= 2  (V1) | Nulo                           V4  |
|String                       | tamanho String >= 2  (V1) | "  "                           V5  |


### Casos de teste: Valor limite e classes de equivalência
| ID | Entrada   | Oráculo            | Classe       |
|----|-----------|--------------------|--------------|
|1   | nulo      | 0                  |   V4         |
|2   | ""        | 0                  |   V2         |
|3   | "ad"      | 0                  |   V1         |
|4   | "a"       | 0                  |   V3         |
|5   | "as"      | 1                  |   C1         |
|6   | ""        | 0                  |   C2         |
|7   | null      | 0                  |   C3         |
|8   | "s"       | 0                  |   C4         |
|9   | "Testes Fortes error"       | 3|   C5         |
|10  | "r s"     | 0                  |   C4         |
|11  | "  "      | 0                  |   V5         |


### Considerações:
* Os testes deram 100% line coverage e branch coverage.
* Isso ocorreu na primeira tentativa. Mas há de se considerar que existem testes nos casos de teste que talvez não respeitem
* a tecnica de classes de equivalência ou valor limite. (É uma dúvida, inlusive);
* Nesses testes em questão, eu imaginei que alguém poderia ter escrito de alguma amneira diferente o cídigo e quis testar o ID 10 e 11, por exemplo.


## Exercicio 2 - Parte B

## Grafo
<img src='https://g.gravizo.com/svg?
 digraph G {
   V1 -> V2;
   V1 -> V3;
   V2 -> VX;
   V3 -> V4;
   V4 -> V5;
   V5 -> V7;
   V7 -> V8;
   V8 -> V9;
   V9 -> V3;
   V9 -> V16
   V16 -> VX;
   V5 -> V6;
   V6 -> V7;
   V6 -> V9;
   V4 -> V10;
   V10 -> V11;
   V11 -> V12;
   V11 -> V3;
   V12 -> V14;
   V14 -> V15;
   V15 -> V16;
   V16 -> VX;
   V12 -> V13;
   V13 -> V14;
 }
'/>

#### Onde VX é a saída do programa.
