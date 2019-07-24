package ru.tiwar.bot.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person {
    private int hp;
    private int mp;

    public boolean isReadyForArenaFight() {
        return hp > 100 && mp > 50;
    }

    public Person refresh(int hp, int mp) {
        this.hp = hp;
        this.mp = mp;
        return this;
    }
}
