package task2;

import org.junit.Test;

/**
 * Write a method uncheck that catches all checked
 * exceptions and turns them into unchecked exceptions.
 */
public class Test2 {
    private static Runnable uncheck(ThrowingRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    public void testUncheck() {
        new Thread(uncheck(
                () -> {
                    System.out.println("Zzz");
                    Thread.sleep(1000);
                })).start();

    }
}
