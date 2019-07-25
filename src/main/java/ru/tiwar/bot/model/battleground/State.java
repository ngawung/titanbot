package ru.tiwar.bot.model.battleground;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class State {
    private Integer secondsToStart;
    private Integer secondsToEnd;
    private Boolean isSigned;
}
