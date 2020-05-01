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
|String                       | tamanho String > 2   (V1) | Tamanho = 0 (V2), tamanho = 1  V3  |
|String                       | tamanho String > 2   (V1) | Nulo   V4                          |


### Casos de teste: Valor limite e classes de equivalência
| ID | Entrada   | Oráculo            | Classe       |
|----|-----------|--------------------|--------------|
|1   | nulo      | 0                  |   V4         |
|2   | ""        | 0                  |   V2         |
|3   | "ad"      | 1                  |   V1         |
|4   | "a"       | 0                  |   V3         |
|5   | "as"      | 1                  |   C1         |
|6   | ""        | 0                  |   C2         |
|7   | null      | 0                  |   C3         |
|8   | "s"       | 0                  |   C4         |
|9   | "Testes Fortes error"       | 3|   C5         |
|10  | "r s"     | 0                  |   C4         |
