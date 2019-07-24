package ru.tiwar.bot.page;

import ru.tiwar.bot.config.Config;

public class MainPage extends BasePage {
    private static String MAIN_PAGE_PATH = "";
    private ArenaPage arenaPage;

    public MainPage() {
        init();
    }

    public MainPage(Config config) {
        this.config = config;
        init();
    }

    private void init() {
        this.arenaPage = new ArenaPage(config);
    }

    public MainPage openMainPage() {
        openPage(MAIN_PAGE_PATH);
        return this;
    }

    public ArenaPage openArenaPage() {
        openPage(MAIN_PAGE_PATH);
        return arenaPage;
    }
}
