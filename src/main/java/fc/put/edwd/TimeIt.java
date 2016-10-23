package fc.put.edwd;

/**
 * Created by marcin on 22.10.16.
 */
public class TimeIt {
    public static void code(Runnable block) {
        long start = System.currentTimeMillis();
        try {
            block.run();
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("Time taken(ms): " + (end - start));
        }
    }
}
