package ru.tiwar.bot.helper;

import java.util.stream.Stream;

public class TimeParser {
    private static String HOUR_REGEXP="\\s+ч";
    private static String MINUTES_REGEXP=".*ч\\s+";
    private static String MINUTES="мин";

    public static Long parseTimeIntoSeconds(String text){
        Long nextStartInSec=0L;
        String[] hours=text.split(HOUR_REGEXP);
        nextStartInSec = hours.length>1 ? Long.parseLong(hours[0])*3600 : nextStartInSec;
        if (text.contains("мин")){
            String minutes= Stream.of(text.split(MINUTES_REGEXP)).filter(s->s.contains(MINUTES)).findAny().orElse("0");
            nextStartInSec += Long.parseLong(minutes.replaceAll("\\D+",""))*60L;
        }
        return nextStartInSec;
    }
}
