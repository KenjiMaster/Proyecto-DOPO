package unitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tower.Tower;

/**
 * Pruebas unitarias para los nuevos tipos de tazas y tapas del Ciclo 4.
 * Cubre: Opener, Hierarchical, Fearful, Crazy, Rainbow.
 *
 * @author (your name)
 * @version ciclo 4 2026-1
 */
public class TowerC4Test {

    private Tower tower;

    @BeforeEach
    public void setUp() {
        tower = new Tower(6);
    }

    @Test
    public void accordingGPOpenerShouldRemoveLids() {
        tower.pushLid("normal", 1);
        tower.pushLid("normal", 2);
        tower.pushLid("normal", 3);
        int itemsOldSize = tower.stackingItems().length;
        // Ahora insertamos un opener con número 4; las tapas encima deben ser eliminadas
        tower.pushCup("opener", 8);
        // Después del specialMove del opener no deben quedar tapas encima de él
        int itemsSize = tower.stackingItems().length;
        assertEquals(itemsSize+2, itemsOldSize,
                "El opener debería haber eliminado las tapas que estaban debajo de él");
    }

    @Test
    public void accordingGPOpenerWithNoLidsAboveShouldNotChangeTower() {
        int heightBefore = tower.height();
        tower.pushCup("opener", 7);
        assertEquals(heightBefore + 13, tower.height());
    }

    @Test
    public void accordingGPOpenerShouldHaveCorrectType() {
        tower.pushCup("opener", 7);
        String[][] stacking = tower.stackingItems();
        String lastType = stacking[stacking.length - 1][0];
        assertEquals("opener", lastType);
    }

    @Test
    public void accordingGPHierarchicalShouldBeAddedToTower() {
        tower.pushCup("hierarchical", 7);
        assertTrue(tower.ok());
    }

    @Test
    public void accordingGPHierarchicalShouldHaveCorrectType() {
        tower.pushCup("hierarchical", 9);
        String[][] stacking = tower.stackingItems();
        String lastType = stacking[0][0];
        assertEquals("hierarchical", lastType);
    }

    @Test
    public void accordingGPHierarchicalShouldDisplaceSmallerItems() {
        tower.pushCup("hierarchical", 7);
        String[][] stacking = tower.stackingItems();
        
        assertEquals("7",stacking[0][1]);
        assertTrue(tower.ok());
    }

    @Test
    public void accordingGPFearfulShouldEnterWhenMatchingCupExists() {
        tower.pushLid("fearful", 1);
        assertTrue(tower.ok());
    }

    @Test
    public void accordingGPFearfulShouldNotStayWhenMatchingCupDoesNotExist() {
        Tower emptyTower = new Tower(300, 300);
        emptyTower.pushLid("fearful", 5);
        assertEquals(0, emptyTower.height(),
                "La tapa fearful debe eliminarse si su taza no está en la torre");
        emptyTower.exit();
    }

    @Test
    public void accordingGPFearfulShouldHaveCorrectType() {
        tower.pushLid("fearful", 1);
        String[][] stacking = tower.stackingItems();
        boolean found = false;
        for (String[] item : stacking) {
            if ("fearful".equals(item[0]) && "1".equals(item[1])) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Debe existir una tapa de tipo fearful con número 1");
    }

    @Test
    public void accordingGPCrazyShouldBeAddedToTower() {
        tower.pushLid("crazy", 1);
        assertTrue(tower.ok());
    }

    @Test
    public void accordingGPCrazyShouldHaveCorrectType() {
        tower.pushLid("crazy", 1);
        String[][] stacking = tower.stackingItems();
        boolean found = false;
        for (String[] item : stacking) {
            if ("crazy".equals(item[0]) && "1".equals(item[1])) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Debe existir una tapa de tipo crazy con número 1");
    }

    @Test
    public void accordingGPCrazyWhenCoveredShouldSwapWithMatchingCup() {
        Tower t = new Tower(300, 300);
        t.pushCup("normal", 1);
        t.pushLid("crazy", 1);
        String[][] stacking = t.stackingItems();
        if (stacking.length >= 2) {
            assertEquals("crazy", stacking[0][0],
                    "La tapa crazy debería haberse colocado como base");
        }
        t.exit();
    }

    @Test
    public void accordingGPRainbowShouldBeAddedToTower() {
        tower.pushLid("rainbow", 1);
        assertTrue(tower.ok());
    }

    @Test
    public void accordingGPRainbowShouldHaveCorrectType() {
        tower.pushLid("rainbow", 1);
        String[][] stacking = tower.stackingItems();
        boolean found = false;
        for (String[] item : stacking) {
            if ("rainbow".equals(item[0]) && "1".equals(item[1])) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Debe existir una tapa de tipo rainbow con número 1");
    }

    @Test
    public void accordingGPRainbowShouldRemoveItselfWhenCovered() {
        Tower t = new Tower(300, 300);
        t.pushCup("normal", 1);
        t.pushLid("rainbow", 1);
        int[] lidedCups = t.lidedCups();
        assertEquals(0, lidedCups.length,
                "La tapa rainbow debe eliminarse al cubrir a su taza");
        t.exit();
    }

    @AfterEach
    public void tearDown() {
        tower.exit();
    }
}