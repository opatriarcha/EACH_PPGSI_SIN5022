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
