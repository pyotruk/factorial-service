package factorialservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * User: pyotruk
 * Date: 2015-06-27
 */

public final class PoolService {

    private static final Logger log = LoggerFactory.getLogger(PoolService.class);

    private static PoolService instance = null;

    private final ExecutorService pool;
    private final ConcurrentLinkedQueue<Future> queue;

    private final static int POOL_SIZE = 10;

    private PoolService() {
        queue = new ConcurrentLinkedQueue<>();
        pool = Executors.newFixedThreadPool(POOL_SIZE);
    }

    public static PoolService getInstance() {
        if (instance == null) {
            instance = new PoolService();
        }
        return instance;
    }

    public void add(long n) {
        queue.offer(pool.submit(new Task(n)));
    }

    public List<String> getResult() {
        ArrayList<String> r = new ArrayList<>();
        for(Future f: queue) {
            if(f.isDone()) {
                try {
                    r.add(f.get().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                r.add("вычисляется");
            }
        }
        return r;
    }

    public void close() {
        pool.shutdown();
    }

}
