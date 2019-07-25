package ru.tiwar.bot;

import ru.tiwar.bot.config.Config;
import ru.tiwar.bot.page.CavePage;
import ru.tiwar.bot.page.LoginPage;
import ru.tiwar.bot.page.MainPage;

import java.util.Arrays;
import java.util.stream.Stream;

public class Debug {
    public static void main(String[] args) {
        //4 ч 37 мин
        String text="4 ч 37 мин";
        Long nextStartInSec=0L;
        String[] hours=text.split("\\s+ч");
        nextStartInSec = hours.length>1 ? Long.parseLong(hours[0])*3600 : nextStartInSec;
        if (text.contains("мин")){
            String minutes=Stream.of(text.split(".*ч\\s+")).filter(s->s.contains("мин")).findAny().orElse("0");
            nextStartInSec += Long.parseLong(minutes.replaceAll("\\D+",""))/60L;
        }
    }
}
