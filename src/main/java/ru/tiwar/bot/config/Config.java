package ru.tiwar.bot.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Config {
    public static Config DEFAULT=new Config("https://tiwar.ru","чортеняномерз","525625");
    private String url;
    private String login;
    private String pass;
}
