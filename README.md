# Projeto e Análise de Algoritmos — Trabalho Prático 3

## Descrição do Problema

Este trabalho consiste em resolver o problema dos **terrenos de Josias**, proposto na disciplina **Projeto e Análise de Algoritmos** do **IFSULDEMINAS — Campus Poços de Caldas**, ministrada pelo **Prof. Douglas Castilho**.

Josias deseja comprar **N terrenos retangulares**, cada um com uma **largura** e **comprimento** inteiros.  
Ele pode comprar os terrenos **individualmente** ou em **grupos sucessivos**, e o custo de um grupo é dado por:

> **custo = (maior largura do grupo) × (maior comprimento do grupo)**

O objetivo é determinar o **menor custo total possível** para comprar todos os terrenos disponíveis.

---

## Formato da Entrada

O programa lê um arquivo de texto (`entrada.txt`) no seguinte formato:

```
N
largura1 comprimento1
largura2 comprimento2
...
larguraN comprimentoN
```

---

## Saída Esperada

O algoritmo imprime na **saída padrão (console)** o **valor mínimo necessário** para comprar todos os terrenos.

### Exemplo

**Entrada**
```
4
100 1
15 15
20 5
1 100
```

**Saída**
```
500
```

---

## Lógica da Solução

A solução foi implementada em **Java**, seguindo so seguintes passos:

1. Leitura e Armazenamento dos Terrenos

        Os terrenos são lidos do arquivo e armazenados em um HashSet para remover duplicatas automaticamente.

2. Remoção de Terrenos Dominados

        Um terreno A é dominado por B quando:

        - largura(B) ≥ largura(A)

        - comprimento(B) ≥ comprimento(A)

        Se isso acontecer, A nunca é vantajoso em nenhum agrupamento, portanto é removido antes da busca.

        A função removerDominados faz isso em O(n²).

3. Ordenação Inicial para melhor poda

        - Os terrenos são ordenados do maior para o menor, usando:

        - max(largura, comprimento)

        Essa estratégia melhora a eficiência do backtracking.

4. Cálculo do Custo Máximo Inicial

        - O algoritmo inicialmente assume o pior caso:

        - Comprar cada terreno individualmente

        Assim, melhorCusto começa com:

        - soma(largura × comprimento de cada lote)


        Isso permite muitas podas no backtracking.

5. Backtracking Exato

        O algoritmo testa todas as possibilidades:

        Para cada terreno:

        - Colocar em um grupo já existente

        - Criar um novo grupo usando apenas ele

        Sempre atualiza:

        - largura máxima

        - comprimento máximo

        - custo acumulado

        E usando backtracking para desfazer mudanças após cada tentativa.

        Também utiliza poda:

        se custoAtual ≥ melhorCusto:
            podar


---

## Análise de Complexidade

A complexidade exata é O(k^N), mas na prática funciona para uma vasta gama de entradas devido à poda pesada.

---

## Autor

**Aluno:** Samuel Cagnani  
**Disciplina:** Projeto e Análise de Algoritmos  
**Professor:** Douglas Castilho  
**Instituição:** IFSULDEMINAS — Campus Poços de Caldas  
**Ano:** 2025
