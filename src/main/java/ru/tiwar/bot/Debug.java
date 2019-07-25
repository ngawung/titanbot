package ru.tiwar.bot;

import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.page.CavePage;
import ru.tiwar.bot.page.LoginPage;
import ru.tiwar.bot.page.MainPage;

public class Debug {
    public static void main(String[] args) {
        MainPage mp=new LoginPage(Config.DEFAULT).doLogin();
        CavePage cp=mp.goToCave();
        System.out.println("aaa");
        System.out.println("bbb");
    }
}
