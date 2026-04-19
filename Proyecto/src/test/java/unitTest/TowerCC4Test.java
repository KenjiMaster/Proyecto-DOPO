package unitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.tower.Tower;

/**
 * Prueba colectiva del Ciclo 4.
 * Verifica la funcionalidad general de Tower con los nuevos tipos.
 * Sigue la misma estructura que TowerCC2Test (JUnit 4).
 *
 * @author (your name)
 * @version ciclo 4 2026-1
 */
public class TowerCC4Test {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void accordingGPShouldCreateTowerWithOpenerCup() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("opener", 1);
        assertEquals(1, tower.height());
        tower.exit();
    }

    @Test
    public void accordingGPShouldNotAddDuplicateOpenerCup() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("opener", 1);
        tower.pushCup("opener", 1);
        assertEquals(false, tower.ok());
        tower.exit();
    }

    @Test
    public void accordingGPShouldCreateTowerWithHierarchicalCup() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("hierarchical", 1);
        assertEquals(1, tower.height());
        tower.exit();
    }

    @Test
    public void accordingGPShouldCreateFearfulLidWhenCupExists() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("normal", 2);
        tower.pushLid("fearful", 2);
        assertEquals(true, tower.ok());
        tower.exit();
    }

    @Test
    public void accordingGPShouldNotKeepFearfulLidWhenCupDoesNotExist() {
        Tower tower = new Tower(300, 300);
        tower.pushLid("fearful", 5);
        assertEquals(0, tower.height());
        tower.exit();
    }

    @Test
    public void accordingGPShouldCreateCrazyLid() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("normal", 1);
        tower.pushLid("crazy", 1);
        assertEquals(true, tower.ok());
        tower.exit();
    }

    @Test
    public void accordingGPShouldCreateRainbowLid() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("normal", 1);
        tower.pushLid("rainbow", 1);
        assertEquals(true, tower.ok());
        tower.exit();
    }

    @Test
    public void accordingGPShouldCoverCupsWithFearfulLid() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("normal", 1);
        tower.pushCup("normal", 2);
        tower.pushLid("fearful", 1);
        tower.pushLid("fearful", 2);
        tower.cover();
        int[] lidedCups = tower.lidedCups();
        assertEquals(2, lidedCups.length);
        tower.exit();
    }

    @Test
    public void accordingGPRainbowShouldNotCoverAfterSpecialMove() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("normal", 1);
        tower.pushLid("rainbow", 1);
        tower.cover();
        int[] lidedCups = tower.lidedCups();
        assertEquals(0, lidedCups.length);
        tower.exit();
    }

    @Test
    public void accordingGPStackingItemsShouldReportOpenerType() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("opener", 3);
        String[][] stacking = tower.stackingItems();
        assertEquals("opener", stacking[stacking.length - 1][0]);
        tower.exit();
    }

    @Test
    public void accordingGPStackingItemsShouldReportHierarchicalType() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("hierarchical", 3);
        String[][] stacking = tower.stackingItems();
        assertEquals("hierarchical", stacking[stacking.length - 1][0]);
        tower.exit();
    }

    @Test
    public void accordingGPShouldSwapOpenerWithNormalCup() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("normal", 1);
        tower.pushCup("opener", 2);
        tower.swap(new String[]{"opener", "2"}, new String[]{"cup", "1"});
        assertEquals(true, tower.ok());
        tower.exit();
    }

    @Test
    public void accordingGPShouldNotSwapNonExistentItems() {
        Tower tower = new Tower(300, 300);
        tower.pushCup("opener", 1);
        tower.swap(new String[]{"opener", "1"}, new String[]{"cup", "99"});
        assertEquals(false, tower.ok());
        tower.exit();
    }

    @AfterEach
    public void tearDown() {
    }
}