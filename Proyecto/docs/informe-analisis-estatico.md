# Informe de Análisis Estático – Calidad de Código (PMD)
**Proyecto:** StakingItems  
**Herramienta:** PMD 7.0.0 (via maven-pmd-plugin 3.22.0) 

---

## Resultado Inicial

### Resumen de violaciones por prioridad

| Prioridad | Descripción | Cantidad de violaciones |
|---|---|---|
| **1 (Rojo)** | Errores críticos de diseño / propensión a errores | **3** |
| 3 | Buenas prácticas y estilo | ~40+ |
| 4 | Estilo menor | ~5 |

> El objetivo de esta entrega es **corregir todas las violaciones de Prioridad 1 (rojo)**.

---

### Violaciones de Prioridad 1

#### 1. `domain/shapes/Canvas.java` – línea 19 y línea 193
**Regla:** [`ClassWithOnlyPrivateConstructorsShouldBeFinal`](https://docs.pmd-code.org/pmd-doc-7.0.0/pmd_rules_java_design.html#classwithonlyprivateconstructorsshouldbefinal)  
**Descripción:** La clase (o una clase interna en línea 193) tiene únicamente constructores privados pero no está declarada como `final`.

**Problema:** Cuando todos los constructores son privados, ninguna subclase puede instanciar la clase, por lo que no tiene sentido permitir la herencia. No declarar la clase `final` es un descuido de diseño que PMD marca como error crítico.

**Corrección necesaria:** Agregar el modificador `final` a la declaración de la clase (y/o la clase interna afectada).

---

#### 2. `domain/tower/Tower.java` – línea 514
**Regla:** [`ReturnEmptyCollectionRatherThanNull`](https://docs.pmd-code.org/pmd-doc-7.0.0/pmd_rules_java_errorprone.html#returnemptycollectionratherthannull)  
**Descripción:** Un método retorna `null` en lugar de una colección vacía.

**Problema:** Retornar `null` donde se espera una colección obliga a todos los llamadores a verificar `null` antes de iterar, lo que es una fuente común de `NullPointerException`. PMD lo clasifica como error propenso a fallos (Prioridad 1).

**Corrección necesaria:** Reemplazar el `return null` por la colección vacía del tipo correspondiente.

> Si el tipo de retorno es `String[][]` (como en `stackingItems`), la corrección sería `return new String[][] {};`.

---

### Decisiones tomadas a partir del resultado inicial

| # | Acción | Archivo | Línea(s) |
|---|---|---|---|
| 1 | Agregar `final` a la clase `Canvas` | `Canvas.java` | 19 |
| 2 | Agregar `final` a la clase interna anónima o anidada | `Canvas.java` | 193 |
| 3 | Reemplazar `return null` por colección vacía en el método de línea 514 | `Tower.java` | 514 |

Estas tres correcciones son suficientes para **eliminar todas las violaciones de Prioridad 1** y cumplir la meta del ciclo.

---

## Resultado Final

### Correcciones aplicadas

Se realizaron las tres modificaciones identificadas en el análisis inicial:

1. **`Canvas.java` línea 19** – Se agregó el modificador `final` a la declaración de la clase principal `Canvas`.
2. **`Canvas.java` línea 193** – Se agregó el modificador `final` a la clase interna afectada por la misma regla.
3. **`Tower.java` línea 514** – Se reemplazó el `return null` por `return new String[][] {}` en el método correspondiente.

### Resumen de violaciones por prioridad (estado final)

| Prioridad | Descripción | Inicial | Final | Δ |
|---|---|---|---|---|
| **1 (Rojo)** | Errores críticos | **3** | **0** | −3 ✅ |
| 3 | Buenas prácticas y estilo | ~40+ | ~40+ | sin cambio* |
| 4 | Estilo menor | ~5 | ~5 | sin cambio* |

> \* Las violaciones de prioridad 3 y 4 no son objetivo de esta entrega y se mantienen sin modificar intencionalmente.

### Verificación: Prioridad 1 eliminada

El reporte final de PMD **no contiene ninguna sección de Priority 1**. El informe arranca directamente en Priority 3, lo que confirma que las tres correcciones aplicadas eliminaron por completo los errores críticos:

- ✅ `ClassWithOnlyPrivateConstructorsShouldBeFinal` – resuelto en `Canvas.java` (líneas 19 y 193).
- ✅ `ReturnEmptyCollectionRatherThanNull` – resuelto en `Tower.java` (línea 514).

**Meta cumplida:** todas las reglas de Prioridad 1 (rojo) han sido corregidas.

---

