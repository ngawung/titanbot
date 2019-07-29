package ru.tiwar.bot.model;

import lombok.Getter;
import ru.tiwar.bot.condition.ChildCondition;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

public enum Img {
    HP(By.cssSelector("img[src='/images/icon/health.png']")),
    MP(By.cssSelector("img[src='/images/icon/mana.png']"));

    @Getter
    private By img;
    @Getter
    private Condition childCondition;

    Img(By selector) {
        this.img = selector;
        this.childCondition = new ChildCondition(img);
    }

    public Condition asCondition() {
        return childCondition;
    }
}
