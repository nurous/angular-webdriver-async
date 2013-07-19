package helpers;

import org.joda.time.DateTime;

public class Async {

    public static void waitFor(int waitInterval, int maxWait, Runnable runnable) {
        long startTime = new DateTime().getMillis();
        RuntimeException lastError = null;
        long endTime = startTime + maxWait;
        while (new DateTime().getMillis() < endTime) {
            try {
                runnable.run();
                return;
            } catch (Throwable e) {
                lastError = new RuntimeException(e);
                sleep(waitInterval);
            }
        }

        long waitFailedDuration = new DateTime().getMillis() - startTime;
        System.err.println("waitFor failed after " + waitFailedDuration + " ms - re-throwing last exception");
        throw lastError;
    }

    private static void sleep(int waitInterval) {
        try {
            Thread.sleep(waitInterval);
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
    }
}