package ru.tiwar.bot.strategy.actions;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Data
@Accessors(chain = true)
public class WaitAction extends Action {
    public static final WaitAction WAIT_5_TO_10MINUTES = new WaitAction(TimeUnit.MINUTES, 5L, 5L);
    private static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    public TimeUnit tu = TimeUnit.SECONDS;
    public Long value;
    public Long error = 0L;

    public WaitAction(long secondsDelay) {
        this(TimeUnit.SECONDS, secondsDelay, 0L);
    }

    public WaitAction(TimeUnit tu, long value, long error) {
        this.tu = tu;
        this.value = value;
        this.error = error;
    }

    public WaitAction(WaitAction waitAction) {
        this.tu = waitAction.tu;
        this.value = waitAction.value;
        this.error = waitAction.error;
    }

    public WaitAction set(long timeoutSec, long error) {
        tu = TimeUnit.SECONDS;
        value = timeoutSec;
        this.error = error;
        return this;
    }

    public WaitAction set(WaitAction waitAction) {
        tu = waitAction.tu;
        value = waitAction.value;
        error = waitAction.error;
        return this;
    }

    public long toSeconds() {
        return tu.toSeconds(value);
    }

    @Override
    public void doAction() {
        try {
            long sleepTime = tu.toSeconds(value + RANDOM.nextLong(error));
            System.out.println("Sleep: " + sleepTime + "sec.");
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
