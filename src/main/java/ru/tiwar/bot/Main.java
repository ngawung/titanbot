package ru.tiwar.bot;

import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.page.LoginPage;
import ru.tiwar.bot.page.MainPage;
import ru.tiwar.bot.strategy.Default;
import ru.tiwar.bot.strategy.Strategy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Config config = Config.DEFAULT;
        LoginPage lp = new LoginPage(config);
        lp.doLogin();
        MainPage main = new MainPage(config);
        Strategy defaultStrategy = new Default(main);
        defaultStrategy.start();
    }
}
