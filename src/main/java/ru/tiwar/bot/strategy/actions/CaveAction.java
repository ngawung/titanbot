package ru.tiwar.bot.strategy.actions;

import lombok.Getter;
import ru.tiwar.bot.page.CavePage;
import ru.tiwar.bot.page.MainPage;

public class CaveAction extends Action {
    @Getter
    private CavePage cavePage;

    public CaveAction(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public void doAction() {
        try {
            cavePage = mainPage.goToCave();
            CavePage.CaveProcess cp = cavePage.getCaveProcess();
            if (cp.isReadyForAction()) {
                cavePage.startSearchAndMining();
            }
            System.out.println(cavePage.getCaveProcess());
        } catch (Exception e) {
            cavePage.makeScreenShot();
            throw new RuntimeException(e);
        }
    }
}
