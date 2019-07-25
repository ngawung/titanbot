package ru.tiwar.bot.page;

import ru.tiwar.bot.config.Config;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CareerPage extends BasePage {
    private static String PATH = "/career/";
    private static By ATTACK_BTN = By.cssSelector("a[href^='/career/attack'");
    private static String END_TOURNAMENT = "Турнир завершён";
    private static String TOURNAMENT_WILL_OPEN = "Турнир откроется через:";
    private static By TOURNAMENT_WILL_OPEN_DIV = By.tagName("div");
    private static By END_TOURNAMENT_H2 = By.cssSelector("h2[class='dgreen bold']");
    private static String GET_REWARD = "Забрать награду";
    private static String BUY_REWARD = "Забрать награду за ";
    private static By GET_REWARD_SPAN = By.cssSelector("span[class='lbl glow']");

    public CareerPage(Config config) {
        super(config);
    }

    public CareerPage goToCareer() {
        openPage(PATH);
        return this;
    }

    public CareerPage fightForAll() {
        if (isTournamentOpen()) {
            while (!$$(ATTACK_BTN).isEmpty()) {
                $(ATTACK_BTN).click();
                if (!$$(ATTACK_BTN).isEmpty()) {
                    String place = $(ATTACK_BTN).parent().find("css").getText();
                    System.out.println("Career fight: " + place);
                }
            }
            getReward();
        }
        return this;
    }

    public boolean isTournamentOpen() {
        return findFirstIfExist(ATTACK_BTN) != null;
    }

    public boolean isTournamentEnd() {
        return !isTournamentOpen();
//        boolean isTournamentEnd = findFirstIfExist(END_TOURNAMENT_H2, END_TOURNAMENT) != null;
//        isTournamentEnd = isTournamentEnd ||
    }

    public void getReward() {
        SelenideElement el = findFirstIfExist(GET_REWARD_SPAN, GET_REWARD);
        if (el != null && !el.getText().equalsIgnoreCase(BUY_REWARD)) {
            el.click();
        }
    }

}
