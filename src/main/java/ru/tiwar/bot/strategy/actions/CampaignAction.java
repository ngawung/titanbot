package ru.tiwar.bot.strategy.actions;

import ru.tiwar.bot.page.CampaignPage;
import ru.tiwar.bot.page.CareerPage;
import ru.tiwar.bot.page.MainPage;

public class CampaignAction extends Action {
    private CampaignPage campaignPage;
    private Long nextCampaignTime;

    public CampaignAction(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public void doAction() {
        if (nextCampaignTime!=null && System.currentTimeMillis()<nextCampaignTime){
            return;
        }
        campaignPage = mainPage.goToCamapign();
        if (campaignPage.getRaidsCount()>0) {
            campaignPage.raid();
        }
        Long secondsToNextCampaign=campaignPage.goToCampaign().getSecondsToStart();
        if (secondsToNextCampaign!=null){
            nextCampaignTime=System.currentTimeMillis()+secondsToNextCampaign*1000L;
        }
    }
}
