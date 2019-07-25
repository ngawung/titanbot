package ru.tiwar.bot.page;

import ru.tiwar.bot.config.Config;

public class MainPage extends BasePage {
    private static String MAIN_PAGE_PATH = "";
    private ArenaPage arenaPage;
    private CavePage cavePage;
    private CareerPage careerPage;
    private CampaignPage campaignPage;

    public MainPage() {
        init();
    }

    public MainPage(Config config) {
        this.config = config;
        init();
    }

    private void init() {
        this.arenaPage = new ArenaPage(config);
        this.cavePage = new CavePage(config);
        this.careerPage = new CareerPage(config);
        this.campaignPage = new CampaignPage(config);
    }

    public MainPage openMainPage() {
        openPage(MAIN_PAGE_PATH);
        return this;
    }

    public ArenaPage goToArena() {
        return arenaPage.gotoArena();
    }

    public CavePage goToCave() {
        return cavePage.goToCave();
    }

    public CareerPage goToCareer() {
        return careerPage.goToCareer();
    }

    public CampaignPage goToCamapign() {
        return campaignPage.goToCampaign();
    }
}
