package ru.tiwar.bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Person {
    private int hp;
    private int mp;

    private int str;
    private int maxHp;
    private int luck;
    private int armor;
    private int maxMp;

    private int lvl;
    private int gold;
    private int silver;

    public Person(int hp, int mp) {
        this.hp = hp;
        this.mp = mp;
    }

    public boolean isReadyForArenaFight() {
        return hp * 100 / maxHp > 10 && mp > 50;
    }

    public Person refresh(int hp, int mp) {
        this.hp = hp;
        this.mp = mp;
        return this;
    }

    public Person refreshWealth(int lvl, int gold, int silver) {
        this.lvl = lvl;
        this.gold = gold;
        this.silver = silver;
        return this;
    }
}
