package org.fasttrackit.workshop.login;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fasttrackit.util.TestBaseNative;
import org.fasttrackit.workshop.pagefactory.login.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginSteps extends TestBaseNative {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);

    LoginPage loginPage = new LoginPage();

    public LoginSteps() {
        initPage();
    }


    public void initPage() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }


    @Given("^I access the login page$")
    public void I_access_the_login_page() throws Throwable {
        driver.get("https://dl.dropboxusercontent.com/u/16174618/FastTrackIT/app-demo/login.html");
    }

    @Given("^I insert valid credentials$")
    public void I_insert_valid_credentials() throws Throwable {

        I_enter_credentials("eu@fast.com", "eu.pass");

    }

    @When("^I click on login button$")
    public void I_click_on_login_button() throws Throwable {
        loginPage.clickOnLoginButton();

    }


    @Then("^I check if user was logged in$")
    public void I_check_if_user_was_logged_in() throws Throwable {
        boolean successLoggedIn = false;
        try {
            WebElement logout = driver.findElement(By.linkText("Logout"));
            successLoggedIn= logout.isDisplayed();
        } catch (Exception e) {}

      assertThat("Could not find the logout button", successLoggedIn, is(true));

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
