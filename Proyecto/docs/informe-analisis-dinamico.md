# Informe de Análisis Dinámico – Cobertura de Pruebas (JaCoCo)
**Proyecto:** StakingItems  
**Herramienta:** JaCoCo 0.8.11  

---

## Resultado Inicial

### Resumen general

| Métrica | Perdidas | Total | Cobertura |
|---|---|---|---|
| Instrucciones | 572 | 3.737 | **84 %** |
| Ramas | 80 | 278 | **71 %** |
| Complejidad ciclomática (Cxty) | 69 | 283 | — |
| Líneas | 143 | 872 | — |
| Métodos | 19 | 144 | — |
| Clases | 0 | 17 | 100 % |

### Resultado por paquete

| Paquete | Instrucciones perdidas | Cov. instrucciones | Ramas perdidas | Cov. ramas |
|---|---|---|---|---|
| `domain.tower` | 369 de 3.041 | **87 %** | 57 de 240 | **76 %** |
| `domain.shapes` | 203 de 696 | **70 %** | 23 de 38 | **39 %** |

### Análisis enfocado: `domain.tower` – Ramas (Missed Branches)

El paquete de dominio principal (`domain.tower`) cuenta con **10 clases**, **104 métodos** y **679 líneas**. En cuanto a ramas:

- **Ramas cubiertas:** 183 de 240 → **76 %**
- **Ramas perdidas:** 57

Este valor se encuentra **por encima del mínimo exigido (75 %)** en cobertura de instrucciones, pero la cobertura de ramas de `domain.tower` (76 %) aún puede mejorar para alejarse del umbral y fortalecer la calidad de las pruebas.

Las 57 ramas no cubiertas representan decisiones condicionales (`if/else`, operadores ternarios, `switch`, etc.) que ningún caso de prueba actual ejercita. Las causas más frecuentes en este tipo de proyectos son:

- Condiciones de error o casos borde no probados (p. ej., parámetros inválidos, colecciones vacías).
- Ramas de validación (`ok()`) que solo se activan con entradas fuera de rango.
- Bloques `else` implícitos en guardas de método que nunca se ejecutan en las pruebas actuales.

### Decisiones posibles para tomar

Para alcanzar la meta de **>75 % de cobertura** de código de dominio (instrucciones) **y mejorar la cobertura de ramas** en `domain.tower`, se identifican las siguientes acciones:

1. **Revisar los métodos con mayor número de ramas perdidas** dentro de `domain.tower` (navegando al reporte detallado por clase en JaCoCo).
2. **Agregar casos de prueba de valores límite y escenarios de error** para los métodos `pushCup`, `pushLid`, `removeCup`, `removeLid`, `swap`, `cover`, `orderTower`, `reverseT​ower` y `swapToReduce`, que son los de mayor complejidad ciclomática.
3. **Cubrir las ramas del método `ok()`**, probando condiciones en que la torre no es válida.
4. **Cubrir ramas en `stackingItems()` y `lidedCups()`**, que devuelven arreglos y pueden tener rutas alternativas sin probar.

---

## Resultado Final

### Mejoras realizadas

Se agregaron **8 nuevos casos de prueba** enfocados en los métodos de eliminación del paquete `domain.tower`. Los métodos cubiertos por los nuevos tests fueron:

- `popCup()` – extracción de la taza superior de la torre.
- `popLid()` – extracción de la tapa superior de la torre.
- `removeCup(int i)` – eliminación de una taza por posición.
- `removeLid(int i)` – eliminación de una tapa por posición.

Estos métodos concentraban una parte significativa de las ramas no cubiertas en el resultado inicial, especialmente los casos en que se intenta operar sobre posiciones inválidas o la torre se encuentra en un estado borde (vacía, un solo elemento, etc.).

### Resumen general

| Métrica | Perdidas | Total | Cobertura | Δ vs. inicial |
|---|---|---|---|---|
| Instrucciones | 359 | 3.737 | **90 %** | +6 pp |
| Ramas | 65 | 278 | **76 %** | +5 pp |
| Complejidad ciclomática (Cxty) | 61 | 283 | — | — |
| Líneas | 97 | 872 | — | — |
| Métodos | 15 | 144 | — | — |
| Clases | 0 | 17 | 100 % | = |

### Resultado por paquete

| Paquete | Instrucciones perdidas | Cov. instrucciones | Ramas perdidas | Cov. ramas |
|---|---|---|---|---|
| `domain.tower` | 156 de 3.041 | **94 %** | 42 de 240 | **82 %** |
| `domain.shapes` | 203 de 696 | **70 %** | 23 de 38 | **39 %** |

### Análisis enfocado: `domain.tower` – Ramas (Missed Branches)

| Indicador | Inicial | Final | Mejora |
|---|---|---|---|
| Ramas cubiertas | 183 / 240 | 198 / 240 | +15 ramas |
| Cobertura de ramas | 76 % | **82 %** | +6 pp |
| Cobertura de instrucciones | 87 % | **94 %** | +7 pp |

La cobertura de ramas de `domain.tower` pasó de **76 %** a **82 %**, superando ampliamente el umbral mínimo exigido del 75 %. La cobertura de instrucciones del proyecto completo alcanzó el **90 %**, también por encima de la meta.

Las 42 ramas que permanecen sin cubrir corresponden principalmente a rutas de error poco frecuentes y validaciones internas de otros métodos no priorizados en este ciclo (como `swap`, `cover` y `swapToReduce`), cuya cobertura queda como mejora futura.

---

