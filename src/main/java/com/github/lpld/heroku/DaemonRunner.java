package com.github.lpld.heroku;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author leopold
 * @since 5/30/14
 */
public class DaemonRunner {
    public static void main(String[] args) {
        final Runnable runnable = new Worker();
        final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

        new Runnable() {
            @Override
            public void run() {
                runnable.run();
                executorService.schedule(this, 10, TimeUnit.SECONDS);
            }
        }.run();
    }
}
