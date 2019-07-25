package ru.tiwar.bot.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.helper.TimeParser;

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
    private static String START_NEXT_TOURNAMENT = "Турнир откроется через:";
    private static By START_NEXT_TOURNAMENT_DIV = By.xpath("//div[contains(text(),'" + START_NEXT_TOURNAMENT + "')]");
    private static By TIME_SPAN = By.cssSelector("span.orange");
    private static By CLOCK_IMAGE = By.cssSelector("img[src='/images/icon/clock.png']");

    public CareerPage(Config config) {
        super(config);
    }

    public CareerPage goToCareer() {
        openPage(PATH);
        return this;
    }

    public Long getSecondsToStartNext() {
        SelenideElement se = findFirstIfExist(START_NEXT_TOURNAMENT_DIV);
        if (se != null) {
            String text = se.find(TIME_SPAN).text();
            return TimeParser.parseTimeIntoSeconds(text);
        }
        return null;
    }

    public CareerPage fightForAll() {
        if (isTournamentOpen()) {
            while (!$$(ATTACK_BTN).isEmpty()) {
                $(ATTACK_BTN).click();
                if (!$$(ATTACK_BTN).isEmpty()) {
                    String place = $(ATTACK_BTN).parent().find("div").getText();
                    System.out.println("Career fight: " + place);
                }
            }
            System.out.println(findFirstIfExist(END_TOURNAMENT_H2,END_TOURNAMENT).getText());
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
