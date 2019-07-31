package ru.tiwar.bot.page;

import lombok.NoArgsConstructor;
import ru.tiwar.bot.condition.ChildCondition;
import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.model.Img;
import ru.tiwar.bot.model.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

@NoArgsConstructor
public class BasePage {
    protected static By LVL_IMG = By.cssSelector("img[src='/images/icon/level.png']");
    protected static By GOLD_IMG = By.cssSelector("img[src='/images/icon/gold.png']");
    protected static By SILVER_IMG = By.cssSelector("img[src='/images/icon/silver.png']");
    protected static By DIV_CENTER = By.cssSelector("div.center");
    protected static By DIV_ZERO_CENTER = By.cssSelector("div[class='block_zero center']");
    protected static By TIME_SPAN = By.cssSelector("span[id^='time_']");
    protected static String NOT_DIGITAL_REGEXP = "\\D+";
    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private static By HP = By.cssSelector("img[alt='hp']");
    private static By ENERGY = By.cssSelector("img[alt='mp']");
    private static By ARENA_FLOOR = By.cssSelector("span[class='bl rght nwr'");
    protected Config config = Config.DEFAULT;

    public BasePage(Config config) {
        this.config = config;
    }

    public static SelenideElement findFirstIfExist(By by, String text) {
        ElementsCollection collection = $$(by).filter(Condition.text(text));
        if (!collection.isEmpty()) {
            return collection.first();
        }
        return null;
    }

    public static SelenideElement findFirstIfExist(By by) {
        ElementsCollection collection = $$(by);
        if (!collection.isEmpty()) {
            return collection.first();
        }
        return null;
    }

    public static SelenideElement findFirstIfExist(By... bys) {
        List<SelenideElement> elements = new ArrayList<>();
        for (By by : bys) {
            elements.addAll($$(by));
        }
        if (!elements.isEmpty()) {
            return elements.get(0);
        }
        return null;
    }

    protected void openPage(String path) {
        open(config.getUrl() + path);
    }

    public Person refreshPersonHpMp(Person person) {
        String[] status = $$(By.cssSelector("span"))
                .filter(Condition.and("contains hp and mp images", Img.HP.asCondition(), Img.MP.asCondition()))
                .first()
                .getText()
                .split("\\|");
        try {
            person.refresh(Integer.parseInt(status[status.length - 2].trim()), Integer.parseInt(status[status.length - 1].trim()));
        } catch (Exception e) {
            System.err.println("Status: " + Arrays.toString(status));
            makeScreenShot();
            throw new RuntimeException(e);
        }
        return person;
    }

    public Person refreshPersonWealth(Person person) {
        ChildCondition lvlChildCondition = new ChildCondition(LVL_IMG);
        ChildCondition goldChildCondition = new ChildCondition(GOLD_IMG);
        ChildCondition silverChildCondition = new ChildCondition(SILVER_IMG);
        Condition complexCondition = Condition.and("person wealth condition",
                lvlChildCondition,
                goldChildCondition,
                silverChildCondition);
        String wealth = $$(DIV_CENTER).filterBy(complexCondition).first().text();
        String[] values = wealth.split("\\s*\\|\\s*");
        int lvl = Integer.parseInt(values[0].replaceAll(NOT_DIGITAL_REGEXP, ""));
        int gold = Integer.parseInt(values[1].replaceAll(NOT_DIGITAL_REGEXP, ""));
        int silver = Integer.parseInt(values[2].trim().replaceAll(NOT_DIGITAL_REGEXP, ""));
        return person.refreshWealth(lvl, gold, silver);
    }

    public void makeScreenShot() {
        String filename = "screen_" + SDF.format(new Date());
        String saved = screenshot(filename);
        System.err.println("ScreenShot saved to: " + saved);
    }

    protected void sleepFor(TimeUnit tu, Long value) {
        try {
            tu.sleep(value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void sleepFor(Long seconds) {
        sleepFor(TimeUnit.SECONDS, seconds);
    }
}
