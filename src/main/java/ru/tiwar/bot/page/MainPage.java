package ru.tiwar.bot.page;

import lombok.Getter;
import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.model.Person;
import ru.tiwar.bot.page.clan.DungeonPage;

public class MainPage extends BasePage {
    private static String MAIN_PAGE_PATH = "";
    @Getter
    private Person person = new Person(0, 0);
    private ArenaPage arenaPage;
    private CavePage cavePage;
    private CareerPage careerPage;
    private CampaignPage campaignPage;
    private TrainPage trainPage;
    private DungeonPage clanDungeonPage;

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
        this.clanDungeonPage = new DungeonPage(config);
    }

    public MainPage openMainPage() {
        openPage(MAIN_PAGE_PATH);
        return this;
    }

    public Person refreshPersonHpMp() {
        return refreshPersonHpMp(person);
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

    public DungeonPage goToClanDungeonPage() {
        return clanDungeonPage.goToClanDungeon();
    }
}
