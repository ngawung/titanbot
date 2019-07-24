package ru.tiwar.bot.page.battlegrounds;

public class ImmortalKing extends BaseBattleGround{
    private static String PATH="/king/?from=fights";
    private static String SIGN_IN="Подать заявку";
    private static String BUTTONS="div.fight_buttons";


    @Override
    protected String getPath() {
        return PATH;
    }
}
