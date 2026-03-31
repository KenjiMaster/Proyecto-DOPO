
package unitTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TowerContestCTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TowerContestCTest
{
    /**
     * Default constructor for test class TowerContestTest
     */
    public TowerContestCTest()
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
    public void accordingGPShouldSolveExampleFromProblem() {
        long n = 4, h = 9;
        String result = TowerContest.solve(n, h);
        assertNotEquals("Imposible", result);
    }
 
    @Test
    public void accordingGPShouldNotSolveImpossibleCase() {
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