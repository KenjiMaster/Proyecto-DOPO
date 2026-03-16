

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * The test class TowerContestTest.
 *
 * @author  Daniel Garcia and Stiven Peña
 * @version 1
 */
public class TowerContestTest
{
    /**
     * Default constructor for test class TowerContestTest
     */
    public TowerContestTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    @Test
    public void accordingGPShouldCalculateHeightOfTower() {
        List<Long> nums = Arrays.asList(7L, 3L, 5L, 1L);
        long result = TowerContest.altura(nums);
        assertEquals(9L, result);
    }
 
    @Test
    public void accordingGPShouldNotCalculateHeightWithWrongOrder() {
        List<Long> nums = Arrays.asList(1L, 3L, 5L, 7L);
        long result = TowerContest.altura(nums);
        assertNotEquals(9L, result);
    }
 
    @Test
    public void accordingGPShouldFindMinimoIndex() {
        List<Long> nums = Arrays.asList(7L, 3L, 5L, 1L);
        int result = TowerContest.minimo(nums);
        assertEquals(1, result);
    }
 
    @Test
    public void accordingGPShouldNotFindMinimoIndexInDescendingSequence() {
        List<Long> nums = Arrays.asList(7L, 5L, 3L, 1L);
        int result = TowerContest.minimo(nums);
        assertEquals(-1, result);
    }
 
    @Test
    public void accordingGPShouldFindMaximoIndex() {
        List<Long> nums = Arrays.asList(7L, 3L, 5L, 1L);
        int result = TowerContest.maximo(nums);
        assertEquals(0, result);
    }
 
    @Test
    public void accordingGPShouldNotFindMaximoIndexInAscendingSequence() {
        List<Long> nums = Arrays.asList(1L, 3L, 5L, 7L);
        int result = TowerContest.maximo(nums);
        assertEquals(-1, result);
    }
 
    @Test
    public void accordingGPShouldValidateAchievableHeight() {
        List<Long> nums = new ArrayList<>(Arrays.asList(7L, 5L, 3L, 1L));
        boolean result = TowerContest.validar(nums, 7L, true);
        assertTrue(result);
    }
 
    @Test
    public void accordingGPShouldNotValidateUnachievableHeight() {
        List<Long> nums = new ArrayList<>(Arrays.asList(7L, 5L, 3L, 1L));
        boolean result = TowerContest.validar(nums, 100L, true);
        assertFalse(result);
    }
 
    @Test
    public void accordingGPShouldRespondPermutationWhenSolutionExists() {
        long n = 4, h = 9;
        List<Long> result = TowerContest.respond(n, h);
        assertFalse(result.isEmpty());
    }
 
    @Test
    public void accordingGPShouldNotRespondPermutationWhenNoSolutionExists() {
        long n = 4, h = 100;
        List<Long> result = TowerContest.respond(n, h);
        assertTrue(result.isEmpty());
    }
 
    @Test
    public void accordingGPShouldSolveWhenSolutionExists() {
        long n = 4, h = 9;
        String result = TowerContest.solve(n, h);
        assertNotEquals("Imposible", result);
    }
 
    @Test
    public void accordingGPShouldNotSolveWhenNoSolutionExists() {
        long n = 4, h = 100;
        String result = TowerContest.solve(n, h);
        assertEquals("Imposible", result);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}