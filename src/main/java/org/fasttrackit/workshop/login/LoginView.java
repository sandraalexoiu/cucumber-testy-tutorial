package org.fasttrackit.workshop.login;

import com.sdl.selenium.web.WebLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginView {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginView.class);


    private WebLocator loginButton = new WebLocator().setId("loginButton");
    private WebLocator email = new WebLocator().setId("email");
    private WebLocator password = new WebLocator().setId("password");
    private WebLocator error = new WebLocator().setClasses("error-msg");


    public void clickOnLoginButton() {
        loginButton.click();

    }
    public void enterCredentials(String emailValue, String passwordValue) {
        System.out.println("enter email: " + emailValue);
        email.sendKeys(emailValue);
        System.out.println("enter password: " + passwordValue);
        password.sendKeys(passwordValue);
    }

    public void errorMessageShouldBePresent(String expectedMessage) {
        assertThat(error.getHtmlText(), is(expectedMessage));
    }
}
