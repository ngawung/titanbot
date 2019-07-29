package ru.tiwar.bot.strategy;

import lombok.Getter;
import ru.tiwar.bot.page.MainPage;
import ru.tiwar.bot.strategy.actions.Action;
import ru.tiwar.bot.strategy.actions.CampaignAction;
import ru.tiwar.bot.strategy.actions.CareerAction;
import ru.tiwar.bot.strategy.actions.CaveAction;
import ru.tiwar.bot.strategy.actions.FightForAllAction;
import ru.tiwar.bot.strategy.actions.RefreshPersonStatsAction;
import ru.tiwar.bot.strategy.actions.TrainAction;
import ru.tiwar.bot.strategy.actions.WaitAction;
import ru.tiwar.bot.strategy.defaultStrategy.CorrectWaitActionForCaveSearchPostAction;

import java.util.LinkedList;
import java.util.List;

public class Default extends Strategy {
    private MainPage mainPage;
    @Getter
    private WaitAction waitAction;
    @Getter
    private CaveAction caveAction;

    public Default(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    @Override
    protected List<Action> init() {
        List<Action> actions = new LinkedList<>();
        waitAction = new WaitAction(WaitAction.WAIT_5_TO_10MINUTES);
        caveAction = new CaveAction(mainPage);
        caveAction.addPostAction(new CorrectWaitActionForCaveSearchPostAction(waitAction));
        actions.add(new RefreshPersonStatsAction(mainPage));
        actions.add(new FightForAllAction(mainPage));
        actions.add(new CareerAction(mainPage));
        actions.add(new CampaignAction(mainPage));
        actions.add(new TrainAction(mainPage));
        actions.add(caveAction);
        actions.add(waitAction);
        return actions;
    }
}
