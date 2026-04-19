package unitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import domain.tower.TowerContest;

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