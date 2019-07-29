package ru.tiwar.bot.page;

import lombok.Getter;
import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.model.Person;

public class MainPage extends BasePage {
    private static String MAIN_PAGE_PATH = "";
    @Getter
    private Person person = new Person(0, 0);
    private ArenaPage arenaPage;
    private CavePage cavePage;
    private CareerPage careerPage;
    private CampaignPage campaignPage;
    private TrainPage trainPage;

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
        this.trainPage = new TrainPage(config);
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

    public CampaignPage goToCampaign() {
        return campaignPage.goToCampaign();
    }

    public TrainPage goToTraining() {
        return trainPage.goToTraining();
    }
}
