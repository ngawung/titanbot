package ru.tiwar.bot.page;


import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.model.Fights;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ArenaPage extends BasePage {
    private static String ARENA_PATH = "/arena";
    private static By ARENA_CENTER = By.cssSelector("div[class='arena_bg center']");
    private static By ARENA_FIGHT_BTN = By.cssSelector("a.btn");
    private static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static String ATTACK = "Атакoвать";
    private static By FIGTH_RESULT_DIV = By.cssSelector("div[class='block_light']");
    private static String WIN = " Победа! ";

    private Fights fights=new Fights();


    public ArenaPage(Config config) {
        super(config);
    }

    public ArenaPage gotoArena() {
        openPage(ARENA_PATH);
        return this;
    }

    public ArenaPage fightForAll() {
        int count = 0;
        gotoArena();
        boolean flag = true;

        while (flag) {
            if (refreshPersonState().isReadyForArenaFight()) {
                fight();
                boolean isWin = $$(FIGTH_RESULT_DIV).filter(Condition.text(WIN)).size() > 0;
                fights.isWin(isWin);
            } else {
                long sleepTime = 300L + RANDOM.nextLong(500L);
                System.out.println(fights + "\tSleep: " + sleepTime + "sec.");
                sleepFor(sleepTime);
                gotoArena();
            }
        }
        return this;
    }

    private void fight() {
        if (RANDOM.nextBoolean()) {
            $(ARENA_CENTER).click();
        } else {
            $$(ARENA_FIGHT_BTN).find(Condition.text(ATTACK)).click();
        }
    }
}
