# SIN5022_testes_software


## Repository for SIN5022 exercises.

### Silly Pascal  Exercise




### Análise de Classes de Equivalência
| Entrada         | Classes Válidas     | Classes Inválidas      |
|-----------------|---------------------|------------------------|
|Tamanho T        |1 <= t <= 6 (C1)     | t > 6, t = 0  (C4)     |
|Primeiro Caracter|Letra (C2)           | Numeros (C5)           |
|Caracteres       |Letras e Números (C3)| Caractere especial (C6)|

### Casos de teste: Classes de Equivalência
| ID | Entrada | Oráculo            | Classe de Equivalência |
|----|---------|--------------------|------------------------|
|1   |abc      |válido              |C1, C2, C3              |
|2   |""       |invalido            |C4.1                    |
|3   |abcdefgh |inválido            |C4.2                    |
|4   |1abc     |invalido            |C5                      |
|5   | ab-cd   |invalido            |C6                      |


### Análise de Valor Limite
| Entrada             | Classes Válidas       | Classes Inválidas     |
|---------------------|-----------------------|-----------------------|
|Tamanho T            | T=1; T=6 (V1)         |T=0, T=7 (V3)          |
|Primeiro Caractere   | A?, a?, Z?, z? (V2)   |0?, 9? (V4)            |


### Casos de teste: Valor limite
| ID | Entrada | Oráculo            | Valor-limite |
|----|---------|--------------------|--------------|
|6   |a        |válido              |V1.1, v2.2    |
|7   |abcdef   |valido              |V1.2          |
|8   |""       |invalido            |V3.1          |
|9   |abcdefgh |invalido            |V3.2          |
|10  |Abc      |valido              |V4.1, V2.1    |
|11  |Zcv      |valido              |V4.3          |
|12  |zcv      |valido              |V4.4          |
|13  |0abc     |invalido            |V4.1          |
|14  |9abc     |invalido            |V4.2          |

* Caso de teste ID 8 de valor limite igual Caso de teste ID2 em classes de equivalência;
* Caso de teste ID 9 de valor limite igual Caso de teste ID3 em classes de equivalência;
