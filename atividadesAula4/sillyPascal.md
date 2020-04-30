#markdown


<img src='https://g.gravizo.com/svg?
 digraph G {
 V1 -> V2;
 V2 -> VX;
 V1 -> V2a;
 V2a -> V3;
 V2a-> V2b;
 V2b -> V3;
 V3 -> VX;
 V3 -> V4;
 V4 -> V5;
 V5 -> VX;
 V4 -> V6;
 V6 -> V6a;
 V6a -> V7;
 V6a -> V6;
 V7 -> VX;
 V6a -> V6b;
 V6b -> V6;
 V6b -> V7;
 V7 -> VX;
 V6 -> V8;
 V8 -> VX;
 }
'/>

Quantidade de Nós: 13 nós contando o nó de saída. (VX) <br>
Nós: V1, V2, V2a, V2b, V3, V4, V5, V6, V6a, V6b, V7, V8, VX <br>
Quantidade de arestas: 21 <br>
Arestas: (V1, V2), (V2, VX), (V1, V2a), (V2a, V3), (V2a-> V2b), (V2b, V3), (V3, VX), (V3, V4), (V4, V5), (V5, VX), (V4, V6), (V6, V6a), (V6a, V7), (V6a, V6), (V7, VX), (V6a, V6b), (V6b, V6), (V6b, V7), (V7, VX), (V6, V8), (V8, VX) <br>
Quantidade de Caminhos Básicos: 7

### Identificação dos Caminhos Básicos
| ID | Caminhos Básicos                                                         |
|----|--------------------------------------------------------------------------|
|1   |V1, V2, VX                                                                |
|2   |V1, V2, V2a, V3, VX                                                       |
|3   |V1, V2, V2a, V2b, V3, VX                                                  |
|4   |V1, V2, V2a, V2b, V4, V5, VX                                              |
|5   |V1, V2, V2a, V2b, V4, V6, V6a, V7, VX                                     |
|6   |V1, V2, V2a, V2b, V4, V6, V6a, V6b, V7, VX                                |
|7   |V1, V2, V2a, V2b, V4, V6, V6a, V6b, V8, VX                                |

### Casos de teste
| ID | Entrada          | Caminhos Básicos                                      |
|----|--------------------------------------------------------------------------|
|1   | null             | V1, V2, VX                                            |
|2   | ""               | V1, V2, V2a, V3, VX                                   |
|3   | abcdefgh         | V1, V2, V2a, V2b, V3, VX                              |
|4   | 6abcd            | V1, V2, V2a, V2b, V4, V5, VX                          |
|5   | a$bcde           | V1, V2, V2a, V2b, V4, V6, V6a, V7, VX                 |
|6   | a4bcde           | V1, V2, V2a, V2b, V4, V6, V6a, V6b, V7, VX            |
|7   | abcde            | V1, V2, V2a, V2b, V4, V6, V6a, V6b, V8, VX            |

espaços em branco?
