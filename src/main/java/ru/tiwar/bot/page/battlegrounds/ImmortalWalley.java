package ru.tiwar.bot.page.battlegrounds;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$$;

public class ImmortalWalley {
    private static By BTN=By.cssSelector("a.btn");
    private static String ATTACK="Атаковать";
    private static String sign="Подать заявку";
    private static String REFRESH="Обновить";
    String url="https://tiwar.ru/undying/?from=fights";

    public void getRefreshButton(){
        $$(BTN).find(Condition.text(REFRESH)).click();
    }

    public void attack(){
        $$(BTN).find(Condition.text(ATTACK)).click();
    }

}
