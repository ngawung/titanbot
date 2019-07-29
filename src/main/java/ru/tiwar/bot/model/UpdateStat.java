package ru.tiwar.bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tiwar.bot.page.BasePage;
import ru.tiwar.bot.page.TrainPage;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

@Data
@AllArgsConstructor
public class UpdateStat {
    private static By TRAIN_BUTTON = TrainPage.A_BTN;
    private static String UPDATE = TrainPage.UPDATE;
    private Stat stat;
    private Currency currency;
    private int value;

    public UpdateStat(SelenideElement trainingDivElement) {
        stat = Stat.getByTrainDiv(trainingDivElement);
        SelenideElement trainBtn = trainingDivElement.find(TRAIN_BUTTON);
        currency = Currency.findCurrencyInElement(trainBtn);
        value = Integer.parseInt(trainBtn.text().replaceAll(UPDATE, "").replaceAll("\\D+", ""));
    }

}
