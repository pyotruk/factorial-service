package factorialservice;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * User: pyotruk
 * Date: 2015-07-24
 */

public class TaskTests {

    @Test
    public void testFactorial() {
        BigInteger r = Task.factorial(8);
        assertEquals("8!=40320", BigInteger.valueOf(40320), r);
    }

}
