package ru.tiwar.bot.page.clan;

import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.helper.TimeParser;
import ru.tiwar.bot.page.BasePage;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class DungeonPage extends BasePage {
    private static String PATH = "/clandungeon/";
    private static By ATTACK_MONSTER_BTN = By.cssSelector("a.btn[href^='/clandungeon/attack/'");
    private static By DIV_ZERO_CENTER = BasePage.DIV_ZERO_CENTER;
    private static String HITS_LEFT = "Осталось ударов:";
    private static String NO_HITS_LEFT = "Удары закончились";
    private static String ATTACK_MONSTER = "Атаковать монстра:";

    public DungeonPage(Config config) {
        super(config);
    }

    public DungeonPage goToClanDungeon() {
        openPage(PATH);
        return this;
    }

    public int getAttacksCount() {
        SelenideElement attackMonsterButton = findFirstIfExist(DIV_ZERO_CENTER, HITS_LEFT);
        if (attackMonsterButton != null) {
            String hits = attackMonsterButton.text()
                    .replaceAll(ATTACK_MONSTER, "")
                    .replaceAll(HITS_LEFT, "")
                    .replaceAll("\\D+", "");
            return Integer.parseInt(hits);
        } else {
            return 0;
        }
    }

    public DungeonPage attackMonster() {
        SelenideElement attackBtn = findFirstIfExist(ATTACK_MONSTER_BTN);
        if (attackBtn != null) {
            attackBtn.click();
        }
        return this;
    }

    public Boolean checkNoHitsLeft() {
        return findFirstIfExist(DIV_ZERO_CENTER, NO_HITS_LEFT) != null;
    }

    public Long getNextHitsDelaySeconds() {
        SelenideElement delaySpan = findFirstIfExist(BasePage.TIME_SPAN);
        if (delaySpan != null) {
            return TimeParser.extractSecondsFromElementIdValue(delaySpan);
        }
        return 0L;
    }
}
