package factorialservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.Callable;


/**
 * User: pyotruk
 * Date: 2015-06-27
 */

public class Task implements Callable {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    private final long n;

    public Task(long n) {
        this.n = n;
    }

    @Override
    public BigInteger call() {
        log.info("Task started, n=" + n);
        BigInteger r = factorial(n);
        log.info("Task finished, n=" + n + ", result=" + r);
        return r;
    }

    public static BigInteger factorial(long n) {
        BigInteger r = BigInteger.valueOf(1);
        for(long i=2; i<=n; ++i) r = r.multiply(BigInteger.valueOf(i));
        return r;
    }
}
