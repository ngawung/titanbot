package ru.tiwar.bot;

import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.page.CavePage;
import ru.tiwar.bot.page.LoginPage;
import ru.tiwar.bot.page.MainPage;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Debug {
    public static void main(String[] args) {
        //4 ч 37 мин
        String text="Сила: 803 (урон 130 - 185)\n" +
                "+3 к силе\n" +
                "Чем больше сила, тем больше урона нанесёшь врагу!\n" +
                "Улучшить за gold 35";
        Pattern p=Pattern.compile(":\\s+(\\d+)\\s*");
        Matcher m=p.matcher(text);
        System.out.println(m.find());
        System.out.println(m.groupCount());
        System.out.println(m.group(0));
        System.out.println(m.group(1));
    }
}
