package ru.tiwar.bot.strategy.actions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.tiwar.bot.page.MainPage;
import ru.tiwar.bot.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public abstract class Action {
    protected List<PostAction> postActions;
    protected MainPage mainPage;

    public Action(MainPage mainPage) {
        this.mainPage = mainPage;
        postActions = new ArrayList<>();
    }

    public Action addPostAction(PostAction postAction) {
        postActions.add(postAction);
        return this;
    }

    public abstract void doAction();


    public ActionState beforeAction(Strategy strategy) {
        return ActionState.NOW;
    }

    public void postActions(Strategy strategy) {
        if (postActions != null && !postActions.isEmpty()) {
            postActions.forEach(p -> p.doPostAction(strategy));
        }
    }
}
