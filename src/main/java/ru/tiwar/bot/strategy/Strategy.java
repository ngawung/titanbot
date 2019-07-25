package ru.tiwar.bot.strategy;


import ru.tiwar.bot.strategy.actions.Action;
import ru.tiwar.bot.strategy.actions.ActionState;

import java.util.List;
import java.util.function.Predicate;

public abstract class Strategy {
    protected List<Action> actionList;

    protected abstract List<Action> init();

    public void start() {
        actionList = init();
        Action action = getNextAction();
        while (iterateCondition().test(this)) {
            ActionState state = ActionState.REMOVE;
            while (!state.equals(ActionState.NOW)) {
                state = action.beforeAction(this);
            }
            action.doAction();
            action.postActions(this);
            actionList.remove(0);
            actionList.add(action);
            action = getNextAction();
        }
    }

    protected Predicate<Strategy> iterateCondition() {
        return (p) -> true;
    }

    public Action moveAction(Action action, ActionState state) {
        actionList.remove(0);
        Action nextAction = getNextAction();
        switch (state) {
            case NOW:
                return action;
            case REMOVE:
                break;
            case POSTPONE:
                actionList.add(action);
                break;
            case DOWN_TO_ONE:
                actionList.add(1, action);
                break;
            default:
                System.out.println("Not expected action state: " + state);
        }
        return nextAction;
    }

    public Action getNextAction() {
        if (actionList == null && actionList.isEmpty()) {
            actionList = init();
        }
        return actionList.get(0);
    }
}
