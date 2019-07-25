package ru.tiwar.bot.strategy.actions;

import ru.tiwar.bot.page.CareerPage;
import ru.tiwar.bot.page.MainPage;

public class CareerAction extends Action {
    private CareerPage careerPage;

    public CareerAction(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public void doAction() {
        careerPage = mainPage.goToCareer();
        if (careerPage.isTournamentOpen()) {
            careerPage.fightForAll();
        }
    }
}
