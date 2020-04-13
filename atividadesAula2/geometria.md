# SIN5022_testes_software
Repository for SIN5022 exercises.

### Análise de Classes de Equivalência
| Entrada | Classes Válidas | Classes Inválidas     |
|---------|-----------------|-----------------------|
|L1|L1>0 (C1)|L1<=0 (C7)|
|L2|L2>0 (C2)|L2<=0 (C8)|
|L3|L3>0 (C3)|L3<=0 (C9)|
|L1,L2,L3|Equilátero (C4): L1==L2==L3 <br>Isósceles (C5): L1==L2!=L3,L2==L3!<br>=L1, L1==L3!=L2<br>Escaleno (C6): L1!=L2!=L3!=L1| Não forma triângulo (C10):<br> L1 >= L2 + L3<br> L2 >= L1 + L3<br> L3 >= L1 + L2<br>

### Análise de Valor Limite
| Entrada | Classes Válidas | Classes Inválidas     |
|---------|-----------------|-----------------------|
|    L1   | L1=1(v1)        | L1=0 (v5), L1=-1 (v6) |
|    L2   | L2=1(v2)        | L2=0 (v7), L2=-1 (v8) |
|    L3   | L3=1(v3         | L3=0 (v9), L3=-1 (v10)|
|L1, L2, L3 | Equilátero (1,1,1) (v4)  |Não forma triângulo:<br>L1 = L2 + L3 (v11)<br> L2 = L1 + L3 (v12)<br> L3 = L1 + L2 (v13)   |


### Casos de teste: Valor limite
| ID | Entrada | Oráculo            | Valor-limite |
|----|---------|--------------------|--------------|
|12| 0, 3, 4 |ERRO – valor inválido |v5            |
|13| -1, 3, 4|ERRO – valor inválido |v6            |
|14| 1, 2, 2 |Isósceles             |v1            |
|15| 3, 0, 4 |ERRO – valor inválido |v7            |
|16| 3, -1, 4|ERRO – valor inválido |v8            |
|17| 2, 1, 2 |Isósceles             |v2            |
|18| 2, 3, 0 |ERRO – valor inválido |v9            |
|19| 2, 3, -1|ERRO – valor inválido |v10           |
|20| 2, 2, 1 |Isósceles             |v3            |
|21| 1, 1, 1 |Equilátero            |v4            |
|22| 5, 2, 3 |Não forma triângulo   |v11           |
|23|2, 5, 3  |Nao forma triângulo   |v12           |
|24|2, 3, 5  |Nao forma triângulo   |v13           |

### Casos de teste: Classes de Equivalência
| ID | Entrada | Oráculo            | Classe de Equivalência |
|----|---------|--------------------|------------------------|
|1 | 5,5,5    |Equilátero            |C1,C2,C3,C4            |
|2 | 5,5,6    |Isósceles             |C1,C2,C3,C5.1          |
|3 | 6,5,5    |Isósceles             |C1,C2,C3,C5.2          |
|4 | 6,5,6    |Isósceles             |C1,C2,C3,C5.3          |
|5 | 4,5,6    |Escaleno              |C1,C2,C3,C6            |
|6 | -2,4,5   |ERRO – valor inválido |C7                     |
|7 | 4,-2,5   |ERRO – valor inválido |C8                     |
|8 | 4,5,-2   |ERRO – valor inválido |C9                     |
|9 | 10, 2, 3 |Não forma triângulo   |C10.1                  |
|10| 2, 10, 3 |Não forma triângulo   |C10.2                  |
|11|2, 3, 10  |Não forma triângulo   |C10.3                  |
