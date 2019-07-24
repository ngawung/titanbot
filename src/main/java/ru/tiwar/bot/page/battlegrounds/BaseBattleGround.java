package ru.tiwar.bot.page.battlegrounds;

import ru.tiwar.bot.model.battleground.State;
import ru.tiwar.bot.page.BasePage;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public abstract class BaseBattleGround extends BasePage {
    protected static By CENTER = By.cssSelector("div.block_zero");
    protected static String START_BATTLE_IN = "Битва начнется через:";
    protected static String SIGN_IN = "Подать заявку";
    protected static String REFRESH = "Обновить";
    protected static By SPAN_START_TIME_IN = By.cssSelector("span[id^='time_']");
    protected State state = new State();

    protected abstract String getPath();

    public void openBattleGround() {
        openPage(getPath());
    }

    public State signIn() {
        if (!refreshState().getIsSigned()) {
            $$(CENTER).find(Condition.text(SIGN_IN)).click();
        }
        return state.setIsSigned(true);

    }

    public State refreshState() {
        state.setIsSigned(null);
        if ($$(CENTER).filter(Condition.text(SIGN_IN)).size() > 0) {
            state.setIsSigned(false);
        }
        if ($$(CENTER).filter(Condition.text(REFRESH)).size() > 0) {
            state.setIsSigned(true);
        }
        String timeToStartMsec = $(SPAN_START_TIME_IN).getAttribute("id").replaceAll("\\D+", "").trim();
        state.setSecondsToStart(Integer.parseInt(timeToStartMsec) / 1000);
        return state;
    }
}
