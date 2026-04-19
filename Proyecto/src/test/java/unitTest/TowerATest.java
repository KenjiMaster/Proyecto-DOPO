package unitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import domain.tower.Tower;
import domain.tower.TowerContest;
import javax.swing.JOptionPane;

/**
 * Pruebas de aceptación del Ciclo 4.
 * Cada prueba muestra visualmente la simulación, hace una pausa
 * y pregunta al usuario si el comportamiento es correcto.
 *
 * @author (your name)
 * @version ciclo 4 2026-1
 */
public class TowerATest {

    /**
     * Prueba de aceptación 1:
     * Demuestra los tres tipos de tazas y los tres tipos de tapas en una torre visible.
     * Se espera ver:
     * - Una cup normal (gris), una opener (amarillo con círculos) y una hierarchical (azul con triángulos).
     * - Una lid fearful, una crazy y una rainbow con sus marcas visuales distintivas.
     */
    @Test
    public void accordingGPShouldShowAllItemTypesVisually() throws InterruptedException {
        Tower tower = new Tower(500, 500);

        tower.pushCup("normal", 3);
        tower.pushCup("opener", 4);
        tower.pushCup("hierarchical", 5);

        tower.pushLid("normal", 3);
        tower.pushLid("fearful", 4);
        tower.pushLid("rainbow", 5); 
        tower.pushLid("crazy", 6);     
          

        tower.makeVisible();
        
        // Pregunta de aceptación al usuario
        int response1 = JOptionPane.showConfirmDialog(
                null,
                "¿Se pueden distinguir visualmente los tres tipos de tazas\n" +
                "(normal, opener con círculos, hierarchical con triángulos)\n" +
                "y los tipos de tapas (normal, fearful con círculos, crazy con triángulos, rainbow mixto)?",
                "Prueba de Aceptación 1 — Tipos visuales",
                JOptionPane.YES_NO_OPTION
        );
        
        JOptionPane.showMessageDialog(null,
                "Ahora veremos como las tapas cubren a sus tazas.\n" + 
                "(La tapa rainbow 5 deberia eliminarse al cubrir su taza.)\n" + 
                "(La tapa normal 3 la eliminara la taza opener 4)");
        tower.cover();
        Thread.sleep(2000);
        
        int response2 = JOptionPane.showConfirmDialog(
                null,
                "¿Se pudo evidenciar la eliminacion de la tapa rainbow 5?\n"+
                "¿Se pudo observar la eliminacion de la tapa normal 3?",
                "Prueba de Aceptacion 1 - Uso de cover",
                JOptionPane.YES_NO_OPTION
        );
        
        tower.exit();
        assertEquals(JOptionPane.YES_OPTION, response1,
                "El usuario debe confirmar que los tipos se distinguen visualmente");
                
        assertEquals(JOptionPane.YES_OPTION, response2,
                "El usuario debe confirmar que la tapa rainbow se elimino.");
        
    }

    /**
     * Prueba de aceptación 2:
     * Demuestra el comportamiento especial de opener y fearful en una secuencia dinámica.
     *
     * Secuencia:
     * 1. Se crean cups 1, 2, 3 normales.
     * 2. Se apilan tapas normales 1, 2, 3 encima.
     * 3. Se inserta una cup opener 4 → debe eliminar automáticamente todas las tapas encima.
     * 4. Se inserta una tapa fearful 9 (sin cup 9 en la torre) → debe desaparecer sola.
     */
    @Test
    public void accordingGPShouldDemonstrateOpenerAndFearfulBehavior() throws InterruptedException {
        Tower tower = new Tower(500, 500);
        
        tower.pushCup("normal", 1);
        tower.pushCup("normal", 2);
        tower.pushCup("normal", 3);

        tower.pushLid("normal", 1);
        tower.pushLid("normal", 2);
        tower.pushLid("normal", 3);

        tower.makeVisible();
        Thread.sleep(2000);

        JOptionPane.showMessageDialog(null,
                "Estado inicial: 3 tazas con 3 tapas encima.\n" +
                "Ahora se insertará una taza Opener (4).\n" +
                "Observa cómo elimina las tapas que le bloquean el paso.");

        tower.pushCup("opener", 4);
        Thread.sleep(2000);

        JOptionPane.showMessageDialog(null,
                "Las tapas han sido eliminadas por el Opener.\n" +
                "Ahora se intentará insertar una tapa Fearful con número 9\n" +
                "(cuya taza compañera NO está en la torre).\n" +
                "Observa que la tapa desaparece sola.");

        int heightBefore = tower.height();
        tower.pushLid("fearful", 9);
        int heightAfter = tower.height();
        Thread.sleep(2000);

        int response = JOptionPane.showConfirmDialog(
                null,
                "¿Observaste que:\n" +
                "1) La taza Opener eliminó todas las tapas que estaban encima de ella?\n" +
                "2) La tapa Fearful desapareció porque su taza compañera no existía?\n\n" +
                "Altura antes de fearful: " + heightBefore + " | Altura después: " + heightAfter,
                "Prueba de Aceptación 2 — Opener y Fearful",
                JOptionPane.YES_NO_OPTION
        );

        tower.exit();
        assertEquals(heightBefore, heightAfter,
                "La tapa fearful no debería haber cambiado la altura (se auto-eliminó)");

        assertEquals(JOptionPane.YES_OPTION, response,
                "El usuario debe confirmar el comportamiento correcto de opener y fearful");
    }
}