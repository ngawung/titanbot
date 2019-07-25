package ru.tiwar.bot.page;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.model.Person;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

@AllArgsConstructor
@NoArgsConstructor
public class BasePage {
    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private static By HP = By.cssSelector("img[alt='hp']");
    private static By ENERGY = By.cssSelector("img[alt='mp']");
    private static By ARENA_FLOOR = By.cssSelector("span[class='bl rght nwr'");
    protected Config config = Config.DEFAULT;
    protected Person person = new Person(0, 0);

    public BasePage(Config config) {
        this.config = config;
    }

    protected void openPage(String path) {
        open(config.getUrl() + path);
    }

    public Person refreshPersonState() {
        String[] status = $(ARENA_FLOOR).getText().split("\\|");
        try {
            person.refresh(Integer.parseInt(status[status.length - 2].trim()), Integer.parseInt(status[status.length - 1].trim()));
        } catch (Exception e) {
            System.err.println("Status: " + Arrays.toString(status));
            makeScreenShot();
            throw new RuntimeException(e);
        }
        return person;
    }

    public SelenideElement findFirstIfExist(By by, String text) {
        ElementsCollection collection = $$(by).filter(Condition.text(text));
        if (!collection.isEmpty()) {
            return collection.first();
        }
        return null;
    }

    public SelenideElement findFirstIfExist(By by) {
        ElementsCollection collection = $$(by);
        if (!collection.isEmpty()) {
            return collection.first();
        }
        return null;
    }

    public void makeScreenShot() {
        String filename = "screen_" + SDF.format(new Date()) + ".png";
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
