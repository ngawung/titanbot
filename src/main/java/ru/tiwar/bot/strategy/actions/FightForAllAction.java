package ru.tiwar.bot.strategy.actions;

import ru.tiwar.bot.page.ArenaPage;
import ru.tiwar.bot.page.MainPage;

public class FightForAllAction extends Action {
    private ArenaPage arenaPage;

    public FightForAllAction(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public void doAction() {
        arenaPage = mainPage.goToArena();
        arenaPage.fightForAllMp();
        arenaPage.refreshPersonState();
        System.out.println(arenaPage.getFights());
    }
}
