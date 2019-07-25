package ru.tiwar.bot.strategy.actions;

import ru.tiwar.bot.page.CareerPage;
import ru.tiwar.bot.page.MainPage;

public class CareerAction extends Action {
    private CareerPage careerPage;
    private Long nextStart;

    public CareerAction(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public void doAction() {
        if (nextStart != null && System.currentTimeMillis() < nextStart) {
            return;
        }
        careerPage = mainPage.goToCareer();
        if (careerPage.isTournamentOpen()) {
            careerPage.fightForAll();
        } else {
            Long delayInSeconds = careerPage.getSecondsToStartNext();
            nextStart = delayInSeconds == null ? null : System.currentTimeMillis() + delayInSeconds * 1000L;
        }
    }
}
