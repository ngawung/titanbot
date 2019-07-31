package ru.tiwar.bot.strategy.actions;

import ru.tiwar.bot.page.MainPage;
import ru.tiwar.bot.page.clan.DungeonPage;

public class ClanDungeonAction extends Action {
    private static Long nextAttack=0L;

    public ClanDungeonAction(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public void doAction() {
        if (nextAttack<System.currentTimeMillis()) {
            DungeonPage dungeonPage = mainPage.goToClanDungeonPage();
            int attacks = dungeonPage.getAttacksCount();
            for (int i = 0; i < attacks; i++) {
                dungeonPage.attackMonster();
            }
            if (dungeonPage.checkNoHitsLeft()){
                nextAttack=System.currentTimeMillis()+1000L*dungeonPage.getNextHitsDelaySeconds();
            }
        }
    }
}
