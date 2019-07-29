package ru.tiwar.bot.model;


import lombok.AllArgsConstructor;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

@AllArgsConstructor
public enum Currency {
    SILVER(By.cssSelector("img[src='/images/icon/silver.png']")),
    GOLD(By.cssSelector("img[src='/images/icon/gold.png']"));

    private By imgSelector;

    public By getImage() {
        return imgSelector;
    }


    public static Currency findCurrencyInElement(SelenideElement se) {
        for (Currency c : Currency.values()) {
            if (!se.findAll(c.imgSelector).isEmpty()) {
                return c;
            }
        }
        throw new RuntimeException("Can nor find currency image in element: " + se.getTagName() + "\n:" + se.innerHtml());
    }
}
