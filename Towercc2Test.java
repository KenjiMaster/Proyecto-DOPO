

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TowerTest.
 *
 * @author  Daniel Garcia and Stiven Peña
 * @version 1
 */
public class Towercc2Test
{   
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    @Test
    public void accordingGPShouldCreateTowerWithElements(){
        Tower tower = new Tower(6);
        assertEquals(36,tower.height());
        tower.exit();
    }
    
    @Test
    public void accordingGPShouldNotCreateElementsInTower(){
        Tower tower = new Tower(6);
        for(int i=1;i<=6;i++) tower.pushCup(i);
        assertEquals(36,tower.height());
        tower.exit();
    }
    
    @Test
    public void accordingGPShouldSwapElementsInTower(){
        Tower tower = new Tower(6);
        tower.swap(new String[] {"cup","3"},new String[] {"cup","1"});
        String[][] testOrder = new String[][] {{"cup","3"},{"cup","2"},{"cup","1"},{"cup","4"},{"cup","5"},{"cup","6"}};
        for(int i=0; i<testOrder.length;i++){
            assertArrayEquals(testOrder[i],tower.stackingItems()[i]);
        }
        tower.exit();
    }
    
    @Test
    public void accordingGPShouldNotSwapElementsIfNotExists(){
        Tower tower = new Tower(6);
        tower.swap(new String[] {"cup","8"},new String[] {"cup","10"});
        assertEquals(false,tower.ok());
        tower.exit();
    }
    
    @Test
    public void accordingGPShouldCoverCupsWithLid(){
        Tower tower = new Tower(6);
        int oldLidedCups = tower.lidedCups().length;
        for(int i=1;i<4;i++) tower.pushLid(i);
        tower.cover();
        int newLidedCups = tower.lidedCups().length;
        assertEquals(0,oldLidedCups);
        assertEquals(3,newLidedCups);
        tower.exit();
    }
    
    @Test
    public void accordingGPShouldNotCoverCupsIfNotHaveLid(){
        Tower tower = new Tower(6);
        int oldLidedCups = tower.lidedCups().length;
        tower.cover();
        int newLidedCups = tower.lidedCups().length;
        assertEquals(newLidedCups,oldLidedCups);
        tower.exit();
    }
    
    @Test
    public void accordingGPShouldSwapToReduceHeightInTower(){
        Tower tower = new Tower(6);
        String [][] swapTest = new String[][] {{"cup","1"},{"cup","2"}};
        String[][] swapTower = tower.swapToReduce();
        for(int i=0;i<swapTest.length;i++){
            assertArrayEquals(swapTest[i],swapTower[i]);
        }
        tower.exit();
    }
    
    @Test
    public void accordingGPShouldNotSwapToReduceHeightInTower(){
        Tower tower = new Tower(6);
        tower.reverseTower();
        String[][] swapTower = tower.swapToReduce();
        assertNull(swapTower);
        tower.exit();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}