package ru.tiwar.bot.helper;

import java.util.concurrent.TimeUnit;

public class CommonHelper {

    public static void sleepFor(TimeUnit tu, Long value) {
        try {
            tu.sleep(value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleepFor(Long seconds) {
        sleepFor(TimeUnit.SECONDS, seconds);
    }
}
