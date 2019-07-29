package ru.tiwar.bot.strategy.actions;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.tiwar.bot.model.Currency;
import ru.tiwar.bot.model.Person;
import ru.tiwar.bot.model.UpdateStat;
import ru.tiwar.bot.page.MainPage;
import ru.tiwar.bot.page.TrainPage;

import java.util.List;

@Data
@Accessors(chain = true)
public class TrainAction extends Action {

    public TrainAction(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    @Override
    public void doAction() {
        TrainPage tp = mainPage.goToTraining();
        Person person = tp.refreshPerson(mainPage.getPerson());
        boolean isTrainAvailable = true;
        while (isTrainAvailable) {
            List<UpdateStat> updateStats = tp.getAvailableUpdatesForSilver();
            isTrainAvailable = !updateStats.isEmpty();
            if (!isTrainAvailable) {
                System.out.println("There is no available stats for train by silver");
                return;
            }
            UpdateStat us = updateStats.stream()
                    .filter(updateStat -> updateStat.getCurrency() == Currency.SILVER)
                    .filter(updateStat -> updateStat.getValue() < person.getSilver())
                    .findAny()
                    .orElse(null);
            if (us == null) {
                return;
            }
            System.out.println("Train: " + us.getStat() + " for " + us.getValue() + " " + us.getCurrency().name());
            tp.train(us.getStat());
            tp.refreshPerson(person);
        }
    }
}
