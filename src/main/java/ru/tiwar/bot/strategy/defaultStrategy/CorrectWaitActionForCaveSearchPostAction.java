package ru.tiwar.bot.strategy.defaultStrategy;

import ru.tiwar.bot.strategy.Default;
import ru.tiwar.bot.strategy.actions.PostAction;
import ru.tiwar.bot.strategy.actions.WaitAction;

public class CorrectWaitActionForCaveSearchPostAction extends PostAction<Default> {
    private static Long ANTI_CHEAT_DELAY_CORRECTION_ERROR = 10L;
    private WaitAction waitAction;

    public CorrectWaitActionForCaveSearchPostAction(WaitAction waitAction) {
        this.waitAction = new WaitAction(waitAction);
    }

    @Override
    public void doPostAction(Default strategy) {
        long caveTimeoutSec = strategy.getCaveAction().getCavePage().getCaveProcess().getTimeoutSec();
        if (caveTimeoutSec < waitAction.toSeconds()) {
            System.out.println("Correct sleepTime:");
            strategy.getWaitAction().set(caveTimeoutSec + 2, ANTI_CHEAT_DELAY_CORRECTION_ERROR);
        } else {
            strategy.getWaitAction().set(waitAction);
        }

    }
}
