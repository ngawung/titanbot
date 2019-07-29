package ru.tiwar.bot.condition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;

public class ChildCondition extends Condition {
    private By by;

    public ChildCondition(By by) {
        super("Have child");
        this.by = by;
    }

    @Override
    public boolean apply(Driver driver, WebElement element) {
        return !element.findElements(by).isEmpty();
    }
}
