package ru.tiwar.bot.page;

import lombok.AllArgsConstructor;
import ru.tiwar.bot.config.Config;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@AllArgsConstructor
public class LoginPage extends BasePage{
    private static By BUTTON_SIGN_IN = By.className("label");
    private static String SIGN_IN = "Войти";
    private static By BUTTON_LOGIN_IN = By.cssSelector("input.label[value='" + SIGN_IN + "']");
    private static By INPUT_LOGIN = By.name("login");
    private static By INPUT_PASSWORD = By.name("pass");
    private Config config;

    public LoginPage openPage() {
        open(config.getUrl());
        return this;
    }

    public LoginPage clickSignInButton() {
        $$(BUTTON_SIGN_IN).find(Condition.text(SIGN_IN)).click();
        return this;
    }

    public MainPage doLogin() {
        openPage();
        clickSignInButton();
        $(INPUT_LOGIN).sendKeys(config.getLogin());
        $(INPUT_PASSWORD).sendKeys(config.getPass());
        $(BUTTON_LOGIN_IN).click();
        return new MainPage();
    }
}
