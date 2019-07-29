package ru.tiwar.bot.model;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

import com.codeborne.selenide.SelenideElement;

@AllArgsConstructor
public enum Stat {
    STRENGTH("Сила"),
    HP("Жизни"),
    MP("Энергия"),
    LUCK("Удача"),
    ARMOR("Броня");

    private String trainName;

    public String getTrainName(){
        return trainName;
    }

    public static Stat getByTrainDiv(SelenideElement trainingDiv) {
        String text = trainingDiv.text();
        return Stream.of(Stat.values()).filter(s -> text.startsWith(s.trainName)).findAny().orElseThrow(() -> new RuntimeException("Can not check stat by string: " + text));
    }
}