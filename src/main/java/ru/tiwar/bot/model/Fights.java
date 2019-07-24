package ru.tiwar.bot.model;

public class Fights {
    private int wins = 0;
    private int loses = 0;

    public Fights win() {
        wins++;
        return this;
    }

    public Fights lose() {
        loses++;
        return this;
    }

    public Fights isWin(boolean winCondition) {
        return winCondition ? win() : lose();
    }

    @Override
    public String toString() {
        return "Fights: " + (wins + loses) + "\tWins: " + wins + "\tLoses: " + loses;
    }
}
