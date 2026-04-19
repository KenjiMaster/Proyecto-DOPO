# StakingItems – DOPO Cierre 2026-1

**Escuela Colombiana de Ingeniería Julio Garavito**  
**Asignatura:** Programación Orientada a Objetos  
**Entrega:** Proyecto Inicial – Cierre 2026-1 (Refactoring)  
**Fecha de entrega:** Sábado 18 de abril de 2026

---

## Descripción del proyecto

Simulador inspirado en el **Problem J – Stacking Cups** de la maratón de programación internacional 2025. La aplicación permite gestionar una torre de tazas con sus respectivas tapas, incluyendo operaciones de apilamiento, reorganización, consulta y resolución automática del problema de la maratón.

### Autores

| Nombre|
|---|
| _Daniel Garcia_|
| _Jhonatan Peña_|

---

## Estructura del proyecto

```
Proyecto/
├── src/
│   ├── domain/
│   │   ├── tower/          # Lógica principal: Tower, TowerContest
│   │   └── shapes/         # Capa gráfica: Canvas y figuras
│   └── test/               # Pruebas de unidad y aceptación (JUnit)
├── target/
│   ├── site/             
│       ├── JaCoCo/
│       │    └──index.html          # Reporte de cobertura (análisis dinámico)
│       └── pmd.html                # Reporte PMD (análisis estático)
├── docs/
│   ├── ProblemJ.asta         # Diagrama de clases y paquetes (Astah)
│   ├── informe-analisis-dinamico.md
│   └── informe-analisis-estatico.md
└── README.md
```

---

## Requisitos funcionales implementados

- **Gestión de tazas:** `pushCup`, `popCup`, `removeCup`
- **Gestión de tapas:** `pushLid`, `popLid`, `removeLid`
- **Reorganización de la torre:** `orderTower`, `reverseTower`, `swap`, `cover`
- **Consultas:** `height`, `lidedCups`, `stackingItems`, `swapToReduce`
- **Visibilidad:** `makeVisible`, `makeInvisible`
- **Concurso:** `solve`, `simulate` (clase `TowerContest`)

---

## Resultados de calidad

### Análisis dinámico (JaCoCo)

| Paquete | Cobertura instrucciones | Cobertura ramas |
|---|---|---|
| `domain.tower` | 94 % | 82 % |
| `domain.shapes` | 70 % | 39 % |
| **Total** | **90 %** | **76 %** |

> Meta exigida: >75 % de cobertura en código de dominio. ✅ Cumplida.

### Análisis estático (PMD)

| Prioridad | Violaciones iniciales | Violaciones finales |
|---|---|---|
| 1 – Rojo (crítico) | 3 | **0** |
| 3 – Amarillo | ~40 | ~40 |

> Meta exigida: 0 violaciones de Prioridad 1. ✅ Cumplida.

---

## Cómo ejecutar el proyecto

1. Importar el proyecto en **Eclipse IDE**.
2. Asegurarse de tener **JUnit 5** y **JaCoCo** configurados en el `pom.xml` o en el build path.
3. Ejecutar la clase principal desde `domain.tower.Tower` o lanzar el simulador gráfico.
4. Para correr las pruebas: `Run As → JUnit Test` sobre el paquete `test`.
5. Para regenerar reportes PMD y JaCoCo: `Run As → Maven build → Goals: verify`.

---

## Retrospectiva

### 1. ¿Cuáles fueron los mini-ciclos definidos? Justifíquenlos.

Se definieron cinco mini-ciclos organizados de forma incremental, de modo que cada uno entregara un resultado verificable antes de continuar con el siguiente:

| Ciclo | Objetivo |
|---|---|
| 1 | Migración del proyecto de BlueJ a Eclipse y configuración de dependencias con Maven |
| 2 | Generación del reporte de cobertura con JaCoCo e informe dinámico inicial |
| 3 | Generación del reporte de calidad con PMD e informe estático inicial |
| 4 | Escritura de nuevos tests y corrección de las violaciones de Prioridad 1 (rojo) |
| 5 | Elaboración de los informes finales dinámico y estático |

---

### 2. ¿Cuál es el estado actual del proyecto en términos de mini-ciclos? ¿Por qué?

Los cinco mini-ciclos definidos fueron **completados en su totalidad**. Como equipo organizamos el trabajo con suficiente anticipación, lo que permitió validar el cumplimiento de cada ciclo antes de avanzar al siguiente. El resultado es un proyecto que cumple todas las metas de funcionalidad, cobertura de pruebas y calidad de código establecidas para esta entrega.

---

### 3. ¿Cuál fue el tiempo total invertido por cada uno de ustedes?

| Integrante | Horas invertidas |
|---|---|
| Daniel García | 10 h |
| Jhonatan Peña | 10 h |
| **Total** | **20 h** |

---

### 4. ¿Cuál consideran fue el mayor logro? ¿Por qué?

El mayor logro fue **migrar el proyecto de BlueJ a Eclipse** con la configuración correcta de Maven. Esta transición implicó adaptar una estructura de carpetas mucho más elaborada y gestionar dependencias externas (JUnit, JaCoCo, PMD) de forma declarativa, algo que no habiamos hecho. Sin este paso, ninguno de los ciclos siguientes habría sido posible, por lo que representa la base sobre la que se construyó toda la entrega.

---

### 5. ¿Cuál consideran que fue el mayor problema técnico? ¿Qué hicieron para resolverlo?

El mayor problema técnico fue **integrar JaCoCo y PMD dentro del ciclo de construcción de Maven**. Ambas herramientas presentaron complicaciones de configuración en el `pom.xml` que impedían generar los reportes correctamente. Para resolverlo, el equipo consultó la documentación oficial de cada herramienta (EclEmma/JaCoCo y PMD) y los ejemplos de configuración disponibles en los respectivos sitios, hasta lograr que los reportes se generaran de forma estable con `mvn verify`.

---

### 6. ¿Qué hicieron bien como equipo? ¿Qué se comprometen a hacer para mejorar los resultados?

Como equipo logramos una **buena división de los mini-ciclos** y cada uno fue desarrollado en profundidad antes de avanzar al siguiente, lo que evitó acumulación de deuda técnica. Nos comprometemos a mantener esta dinámica de trabajo organizado y a seguir profundizando en cada ciclo con el mismo nivel de detalle.

---

### 7. Considerando las prácticas XP incluidas en los laboratorios, ¿cuál fue la más útil? ¿Por qué?

La práctica más útil fue el **Pair Programming**. Trabajar en parejas permitió que ambos integrantes aportaramos perspectivas distintas ante cada problema, lo que redujo el tiempo de diagnóstico y mejoró la calidad de las decisiones tomadas. En particular, fue clave durante la configuración de Maven y la identificación de las ramas no cubiertas en JaCoCo, donde tener dos personas revisando el mismo problema aceleró significativamente la solución.

---

### 8. ¿Qué referencias usaron? ¿Cuál fue la más útil? Incluyan citas con estándares adecuados.

La referencia más útil fue la documentación oficial de **EclEmma/JaCoCo**, ya que permitió resolver los problemas de configuración de Maven y entender correctamente cómo interpretar los reportes de cobertura de ramas.

**Referencias (formato APA 7.ª edición):**

Adobe. (s. f.). *Cómo utilizar Markdown para escribir documentación*. Adobe Experience Cloud. https://experienceleague.adobe.com/es/docs/contributor/contributor-guide/writing-essentials/markdown#

EclEmma. (2024, 8 de febrero). *EclEmma – Java Code Coverage for Eclipse*. https://www.eclemma.org/index.html

Eclipse Foundation. (s. f.). *Eclipse IDE*. https://eclipseide.org/

PMD Project. (s. f.). *PMD – Static code analyzer*. https://pmd.github.io/

Van Zyl, J., & Redmond, E. (2009, 1 de agosto). *Maven documentation*. Apache Maven. https://maven.apache.org/guides/

---

