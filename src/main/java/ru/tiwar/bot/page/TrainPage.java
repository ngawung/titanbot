package ru.tiwar.bot.page;

import ru.tiwar.bot.condition.ChildCondition;
import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.model.Person;
import ru.tiwar.bot.model.Stat;
import ru.tiwar.bot.model.UpdateStat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class TrainPage extends BasePage {
    public static By A_BTN = By.cssSelector("a.btn");
    public static String UPDATE = "Улучшить за";
    private static String STR_ICON = "/images/icon/str.png";
    private static By STR_IMAGE = By.cssSelector("img[src='" + STR_ICON + "']");
    private static By DIV_ZERO = By.cssSelector("div.block_zero");
    private static String STR_TEXT = "Сила";
    private static String MAX_HP_TEXT = "Жизни";
    private static String LUCK_TEXT = "Удача";
    private static String ARMOR_TEXT = "Броня";
    private static String MAX_MP_TEXT = "Энергия";
    private static String DEFAULT_PATTERN = ":\\s+(\\d+)\\s*";
    private static String SECONDARY_VALUE_PATTERN = ":\\s+\\d+\\s*\\((\\d+)\\)";
    private static Pattern STR_PATTERN = Pattern.compile(STR_TEXT + DEFAULT_PATTERN);
    private static Pattern MAX_HP_PATTERN = Pattern.compile(MAX_HP_TEXT + SECONDARY_VALUE_PATTERN);
    private static Pattern LUCK_PATTERN = Pattern.compile(LUCK_TEXT + DEFAULT_PATTERN);
    private static Pattern ARMOR_PATTERN = Pattern.compile(ARMOR_TEXT + DEFAULT_PATTERN);
    private static Pattern MAX_MP_PATTERN = Pattern.compile(MAX_MP_TEXT + DEFAULT_PATTERN);
    private static String PATH = "/train/";

    public TrainPage(Config config) {
        super(config);
    }

    public TrainPage goToTraining() {
        openPage(PATH);
        return this;
    }

    public TrainPage train(Stat stat) {
        findFirstIfExist(DIV_ZERO, stat.getTrainName()).find(A_BTN).click();
        return this;
    }

    public Person refreshPersonStats(Person person) {
        person.setStr(extractStr());
        person.setMaxHp(extractHp());
        person.setMaxMp(extractMp());
        person.setLuck(extractLuck());
        person.setArmor(extractArmor());
        refreshPersonHpMp(person);
        return person;
    }

    public Person refreshPerson(Person person) {
        refreshPersonHpMp(person);
        refreshPersonWealth(person);
        refreshPersonStats(person);
        return person;
    }

//    public Person getPerson() {
//        Person person = new Person(0, 0);
//        refreshPersonHpMp(person);
//        refreshPersonWealth(person);
//        refreshPersonStats(person);
//        return person;
//    }

    public SelenideElement getTrainButton(SelenideElement statTrainElement) {
        return statTrainElement.find(A_BTN);
    }

    public List<UpdateStat> getAvailableUpdatesForSilver() {
        List<UpdateStat> updateStats = new ArrayList<>(5);
        Condition updateAble = Condition.text(UPDATE);
        Condition bySilver = new ChildCondition(SILVER_IMG);
        ElementsCollection collection = $$(DIV_ZERO).filter(Condition.and(("update for silver"), updateAble, bySilver));
        updateStats.addAll(collection.stream().map(UpdateStat::new).collect(Collectors.toList()));
        return updateStats;
    }

    private SelenideElement getStrengthElement() {
        return findFirstIfExist(DIV_ZERO, STR_TEXT);
    }

    private SelenideElement getMaxHpElement() {
        return findFirstIfExist(DIV_ZERO, MAX_HP_TEXT);
    }

    private SelenideElement getMaxMpElement() {
        return findFirstIfExist(DIV_ZERO, MAX_MP_TEXT);
    }

    private SelenideElement getLuckElement() {
        return findFirstIfExist(DIV_ZERO, LUCK_TEXT);
    }

    private SelenideElement getArmorElement() {
        return findFirstIfExist(DIV_ZERO, ARMOR_TEXT);
    }

    private int extractStr() {
        return extractValueFromPattern(getStrengthElement(), STR_PATTERN);
    }

    private int extractHp() {
        return extractValueFromPattern(getMaxHpElement(), MAX_HP_PATTERN);
    }

    private int extractMp() {
        return extractValueFromPattern(getMaxMpElement(), MAX_MP_PATTERN);
    }

    private int extractLuck() {
        return extractValueFromPattern(getLuckElement(), LUCK_PATTERN);
    }

    private int extractArmor() {
        return extractValueFromPattern(getArmorElement(), ARMOR_PATTERN);
    }

    private Integer extractValueFromPattern(SelenideElement element, Pattern pattern) {
        try {
            Matcher m = pattern.matcher(element.text());
            if (m.find() && m.groupCount() > 0) {
                return Integer.parseInt(m.group(1));
            }
        } catch (Exception r) {
            makeScreenShot();
            throw new RuntimeException(r);
        }
        makeScreenShot();
        return null;
    }
}
