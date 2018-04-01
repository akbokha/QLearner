
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author s123188
 */
public class QLearnerTest {

    /** Auxiliary method
     * @param result */
    public static void assertPattern(String expectedPattern, String result) {
        if (result.matches(expectedPattern)) {
            assertEquals(true,true);
        } else {
            assertEquals(expectedPattern,result);
        }
    }
    
    /**
     * Test of execute method, of class QLearner.
     */
    @Test
    public void testExecute() {
        System.out.print("execute");
        QLearner instance = new QLearner();
        Integer[][] reward = {
            {null, null, null, null, 0,    null},
            {null, null, null, 0,    null, 100 },
            {null, null, null, 0,    null, null},
            {null, 0,    0,    null, 0,    null},
            {0,    null, null, 0,    null, 100 },
            {null, 0,    null, null, 0,    100 }
        };
        
        Integer[][] paths = {
            {5, 5}, {3, 1, 5}
        };
        
        String result = instance.execute(reward, paths, 0d, 1);
        System.out.format(" [%s]\n",result);
        
        assertPattern("4 5 3 (1|2|4) (0|3|5) 5", result);
    }
    
    
}