package ru.tiwar.bot.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import ru.tiwar.bot.config.Config;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@NoArgsConstructor
public class CavePage extends BasePage {
    private static final String PATH = "/cave/";
    private static String NEW_SEARCH = "Новый поиск";
    private static String DOWN_TO_CAVE = "Спуститься в пещеру";
    private static String SEARCH_RESOURCES = "Вы осматриваете пещеру";
    private static String START_MINING = "Начать добычу";
    private static String KILL = "Напасть";
    private static String MINING = "Вы занимаетесь добычей ресурсов";
    private static By NEW_SEARCH_BTN = By.xpath("//span[@class='label' and contains(text(),'" + NEW_SEARCH + "')]");
    private static By DOWN_TO_CAVE_BTN = By.xpath("//span[@class='label' and contains(text(),'" + DOWN_TO_CAVE + "')]");
    private static By SEARCH_RESOURCES_SPAN = By.xpath("//span[@class='blue' and contains(text(),'" + SEARCH_RESOURCES + "')]");
    private static By START_MINING_BTN = By.xpath("//span[@class='label' and contains(text(),'" + START_MINING + "')]");
    private static By MINING_SPAN = By.xpath("//span[@class='blue' and contains(text(),'" + MINING + "')]");
    private static By KILL_SPAN = By.xpath("//span[@class='label' and contains(text(),'" + KILL + "')]");
    private static By H2_KILL_STATUS = By.tagName("h2");
    private static String DEFEAT = "Поражение";
    private CaveProcess caveProcess;

    public CavePage(Config config) {
        super(config);
        caveProcess = new CaveProcess();
    }

    public CavePage goToCave() {
        openPage(PATH);
        checkCaveProcess();
        return this;
    }

    public CaveProcess getCaveProcess() {
        return caveProcess;
    }

    public CavePage startSearchAndMining() {
        switch (caveProcess.status) {
            case DONE:
                SelenideElement searchButton = findFirstIfExist(NEW_SEARCH_BTN, DOWN_TO_CAVE_BTN);
                searchButton.click();
                break;
            case READY_FOR_MINE:
                $(START_MINING_BTN).click();
                break;
            case FIGHT:
                $(KILL_SPAN).click();
                if (findFirstIfExist(H2_KILL_STATUS, DEFEAT) == null) {
                    System.out.println("Fight in mine: defeat");
                } else {
                    System.out.printf("Fight in mine: win");
                }
                goToCave();
                startSearchAndMining();
                break;
        }
        sleepFor(2L);
        if (checkCaveProcess().status==CaveStatus.FIGHT){
            return startSearchAndMining();
        }
        return this;
    }

    public CaveProcess checkCaveProcess() {
        if (findFirstIfExist(NEW_SEARCH_BTN, DOWN_TO_CAVE_BTN) != null) {
            return caveProcess.setStatus(CaveStatus.DONE);
        }
        if (!$$(SEARCH_RESOURCES_SPAN).isEmpty()) {
            String text = $(SEARCH_RESOURCES_SPAN).parent().getText();
            return caveProcess.setStatus(CaveStatus.MINING, text);
        }
        if (!$$(START_MINING_BTN).isEmpty()) {
            return caveProcess.setStatus(CaveStatus.READY_FOR_MINE);
        }
        if (!$$(MINING_SPAN).isEmpty()) {
            String text = $(MINING_SPAN).parent().getText();
            return caveProcess.setStatus(CaveStatus.MINING, text);
        }
        if (findFirstIfExist(KILL_SPAN) != null) {
            return caveProcess.setStatus(CaveStatus.FIGHT);
        }
        caveProcess.setStatus(CaveStatus.UNDEFINED);
        System.out.println("WARNING CAVE STATUS IS UNDEFINED!!!");
        makeScreenShot();
        return caveProcess;
    }

    enum CaveStatus {
        SEARCH,
        READY_FOR_MINE,
        MINING,
        DONE,
        FIGHT,
        UNDEFINED;
    }

    @Getter
    public static class CaveProcess {
        private long timeoutSec = 0L;
        private CaveStatus status = CaveStatus.UNDEFINED;

        public boolean isReadyForAction() {
            return status == CaveStatus.DONE ||
                    status == CaveStatus.READY_FOR_MINE ||
                    status.equals(CaveStatus.FIGHT)||
                    status == CaveStatus.UNDEFINED;
        }

        protected CaveProcess setStatus(CaveStatus newStatus, String textWithTimeout) {
            status = newStatus;
            String[] minutesAndSeconds = textWithTimeout.substring(textWithTimeout.lastIndexOf(' ')).trim().split(":");
            timeoutSec = Long.parseLong(minutesAndSeconds[0]) * 60L + Long.parseLong(minutesAndSeconds[1]);
            return this;
        }

        protected CaveProcess setStatus(CaveStatus newStatus) {
            status = newStatus;
            timeoutSec = 0;
            return this;
        }

        protected CaveProcess search(String[] minutesAndSeconds) {
            status = CaveStatus.SEARCH;
            timeoutSec = Integer.parseInt(minutesAndSeconds[0]) * 60 + Integer.parseInt(minutesAndSeconds[1]);
            return this;
        }

        @Override
        public String toString() {
            return "Cave status: " + status + "\t " + timeoutSec + "sec.";
        }
    }


}
