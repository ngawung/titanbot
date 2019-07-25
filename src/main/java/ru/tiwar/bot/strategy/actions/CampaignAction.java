package ru.tiwar.bot.strategy.actions;

import ru.tiwar.bot.page.CampaignPage;
import ru.tiwar.bot.page.CareerPage;
import ru.tiwar.bot.page.MainPage;

public class CampaignAction extends Action {
    private CampaignPage campaignPage;

    public CampaignAction(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public void doAction() {
        campaignPage = mainPage.goToCamapign();
        if (campaignPage.getRaidsCount()>0) {
            campaignPage.raid();
        }
    }
}
