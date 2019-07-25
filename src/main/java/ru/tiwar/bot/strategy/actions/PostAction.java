package ru.tiwar.bot.strategy.actions;

import ru.tiwar.bot.strategy.Strategy;

public abstract class PostAction<S extends Strategy> {
    public abstract void doPostAction(S strategy);
}
