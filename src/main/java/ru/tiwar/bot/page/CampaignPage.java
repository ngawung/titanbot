package ru.tiwar.bot.page;

import ru.tiwar.bot.config.Config;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class CampaignPage extends BasePage {
    private static String PATH = "/campaign/";
    private static String RAIDS_COUNT_TEXT = "Осталось походов:";
    private static By RAIDS_COUNT_SELECTOR = By.cssSelector("div[class='block_zero center']");
    private static By RAID_BTN = By.cssSelector("span.label");
    private static String RAID_BTN_TEXT = "Отправиться в поход";
    private static By START_FIGHT_BTN = RAID_BTN;
    private static String START_FIGHT_TEXT = "Начать бой";
    private static By ATTACK_MONSTER_BTN = RAID_BTN;
    private static String ATTACK_MONSTER_TEXT = "Атаковать монстра";
    private static By RECEIVE_REWARD_SPAN = By.cssSelector("span.dgreen");
    private static String RECEIVE_REWARD = "Получить награду";
    private static By YOU_DIED_SPAN = By.cssSelector("span.grey");
    private static String YOU_DIED = "Вы были убиты во время боя";
    private static By END_BATTLE_BTN = RAID_BTN;
    private static String END_BATTLE = "Закончить бой";
    private static By H2 = By.tagName("h2");
    private static String WIN = "Победа";
    private static String LOSE = "Поражение";
    private static By RETURN_TO_CAMPAIGN_BTN = RAID_BTN;
    private static String RETURN_TO_CAMPAIGN = "Вернуться в поход";

    public CampaignPage(Config config) {
        super(config);
    }

    public CampaignPage goToCampaign() {
        openPage(PATH);
        return this;
    }

    public CampaignPage raid() {
        if (getRaidsCount() > 0) {
            findFirstIfExist(RAID_BTN, RAID_BTN_TEXT).click();
            findFirstIfExist(START_FIGHT_BTN, START_FIGHT_TEXT).click();
            while (isMonsterAttackable()) {
                getAttackMonsterButton().click();
            }
            if (isMonsterDied()) {
                recieveReward();
            } else {
                getEndBattleButton().click();
            }
            getReturnToCampaignButton().click();
        }
        return this;
    }


    private SelenideElement getReturnToCampaignButton() {
        return findFirstIfExist(RETURN_TO_CAMPAIGN_BTN, RETURN_TO_CAMPAIGN);
    }

    private boolean isMonsterDied() {
        return findFirstIfExist(YOU_DIED_SPAN, YOU_DIED) == null;
    }

    private boolean isPlayerDied() {
        return findFirstIfExist(YOU_DIED_SPAN, YOU_DIED) != null;
    }

    private SelenideElement getEndBattleButton() {
        return findFirstIfExist(END_BATTLE_BTN, END_BATTLE);
    }

    private boolean isMonsterAttackable() {
        return getAttackMonsterButton() != null;
    }

    private SelenideElement getAttackMonsterButton() {
        return findFirstIfExist(ATTACK_MONSTER_BTN, ATTACK_MONSTER_TEXT);
    }

    private void recieveReward() {
        SelenideElement receiveRewardButton = getReceiveRewardButton();
        if (receiveRewardButton != null) {
            receiveRewardButton.click();
        }
    }

    private SelenideElement getReceiveRewardButton() {
        return findFirstIfExist(RECEIVE_REWARD_SPAN, RECEIVE_REWARD);
    }

    public int getRaidsCount() {
        SelenideElement el = findFirstIfExist(RAIDS_COUNT_SELECTOR, RAIDS_COUNT_TEXT);
        if (el != null) {
            String raidsCount = el.getText().trim().replaceAll(RAIDS_COUNT_TEXT, "").replaceAll("\\D+", "");
            return Integer.parseInt(raidsCount);
        }
        return 0;
    }

}
