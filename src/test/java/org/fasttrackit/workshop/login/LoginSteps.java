package org.fasttrackit.workshop.login;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fasttrackit.util.TestBase;
import org.fasttrackit.workshop.Menu.MainMenuView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginSteps extends TestBase {

    public static final String VALID_EMAIL = "eu@fast.com";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);
    public static String VALID_PASSWORD = "eu.pass";

    private LoginView loginPage = new LoginView();


    @Given("^I access the login page$")
    public void I_access_the_login_page() throws Throwable {
        driver.get("https://dl.dropboxusercontent.com/u/16174618/FastTrackIT/app-demo/login.html");
        //driver.get("file:///C:/Users/sandraa/Desktop/app-demo/login.html");
    }

    @Given("^I insert valid credentials$")
    public void I_insert_valid_credentials() throws Throwable {

        I_enter_credentials(VALID_EMAIL, VALID_PASSWORD);

    }

    @When("^I click on login button$")
    public void I_click_on_login_button() throws Throwable {
        loginPage.clickOnLoginButton();

    }

    @Then("^I check if user was logged in$")
    public void I_check_if_user_was_logged_in() throws Throwable {
       MainMenuView.logout.assertReady();
    }

    @Given("^I insert invalid credentials$")
    public void I_insert_invalid_credentials() throws Throwable {

        I_enter_credentials("aa@fast.com", "aa.pass");

    }

    @Then("^I expect invalid credentials message$")
    public void I_expect_invalid_credentials_message() throws Throwable {
        loginPage.errorMessageShouldBePresent("Invalid user or password!");

    }


    @When("^I enter \"([^\"]*)\"/\"([^\"]*)\" credentials$")
    public void I_enter_credentials(String emailValue, String passwordValue) throws Throwable {

        loginPage.enterCredentials(emailValue, passwordValue);
    }


    @Then("^I expect \"([^\"]*)\" error message$")
    public void I_expect_error_message(String expectedMessage) throws Throwable {
        loginPage.errorMessageShouldBePresent(expectedMessage);
    }

    @Given("^I successfully login$")
    public void I_successfully_login() throws Throwable {
        I_access_the_login_page();
        I_insert_valid_credentials();
        I_click_on_login_button();
        I_check_if_user_was_logged_in();
    }
}
