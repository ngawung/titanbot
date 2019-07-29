package ru.tiwar.bot.strategy.actions;

import ru.tiwar.bot.model.Person;
import ru.tiwar.bot.page.MainPage;

public class RefreshPersonStatsAction extends Action {

    public RefreshPersonStatsAction(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public void doAction() {
        mainPage.goToTraining().refreshPerson(mainPage.getPerson());
    }
}
