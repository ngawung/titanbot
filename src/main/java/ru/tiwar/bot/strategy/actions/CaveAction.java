package ru.tiwar.bot.strategy.actions;

import lombok.Getter;
import ru.tiwar.bot.helper.CommonHelper;
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
                startSearchAndMining(cp);
            }
            System.out.println(cavePage.getCaveProcess());
        } catch (Exception e) {
            cavePage.makeScreenShot();
            throw new RuntimeException(e);
        }
    }

    private void startSearchAndMining(CavePage.CaveProcess caveProcess) {
        switch (caveProcess.getStatus()) {
            case DONE:
                cavePage.clickNewSearchButton();
                break;
            case READY_FOR_MINE:
                cavePage.clickStartMiningButton();
            case FIGHT:
                cavePage.goToCave();
                if (mainPage.refreshPersonHpMp().isReadyForArenaFight()) {
                    cavePage.clickStartFightButton();
                    if (cavePage.checkMonsterIsDefeated()) {
                        System.out.printf("Fight in mine: win");
                    } else {
                        System.out.println("Fight in mine: defeat");
                    }
                    cavePage.clickBackToCaveButton();
                    break;
                } else {
                    System.out.println("There is no hp/mp for fight. Sleeping for 20 sec.");
                    CommonHelper.sleepFor(20L);
                    startSearchAndMining(cavePage.checkCaveProcess());
                }
        }
        CommonHelper.sleepFor(2L);
        CavePage.CaveProcess process = cavePage.checkCaveProcess();
        if (process.getStatus() == CavePage.CaveStatus.FIGHT) {
            startSearchAndMining(process);
        }
    }
}
