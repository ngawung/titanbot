package ru.tiwar.bot;

import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.page.LoginPage;
import ru.tiwar.bot.page.MainPage;

import java.util.concurrent.TimeUnit;

import com.codeborne.selenide.Selenide;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Config config = Config.DEFAULT;
        LoginPage lp = new LoginPage(config);
        lp.doLogin();
        MainPage main=new MainPage(config);
        main.openArenaPage().fightForAll();
    }
}
