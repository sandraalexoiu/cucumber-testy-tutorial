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
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginSteps extends TestBaseNative {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);

    LoginPage loginPage;



    @Given("^I access the login page$")
    public void I_access_the_login_page() throws Throwable {
        driver.get("https://dl.dropboxusercontent.com/u/16174618/FastTrackIT/app-demo/login.html");
    }

    @Given("^I insert valid credentials$")
    public void I_insert_valid_credentials() throws Throwable {
       WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("eu@fast.com");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("eu.pass");

    }

    @When("^I click on login button$")
    public void I_click_on_login_button() throws Throwable {
       WebElement clicklog= driver.findElement(By.id("loginButton"));
        clicklog.click();
    }

    @Then("^I check if user was logged in$")
    public void I_check_if_user_was_logged_in() throws Throwable {
        boolean successLoggedIn = false;
        try {
            WebElement logout = driver.findElement(By.linkText("Logout"));
            successLoggedIn= logout.isDisplayed();
        } catch (Exception e) {}
        //Assert.assertEquals("eroare", true, driver.findElement(By.linkText("Logout")));

      assertThat("Could not find the logout button",successLoggedIn, is(true));

    }

    @Given("^I insert invalid credentials$")
    public void I_insert_invalid_credentials() throws Throwable {
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("aa@fast.com");


        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("eu.pass");

    }

    @Then("^I expect invalid credentials message$")
    public void I_expect_invalid_credentials_message() throws Throwable {
       WebElement error= driver.findElement(By.className("error-msg"));
        assertThat(error.getText(), is("Invalid user or password!"));


    }

   // @Given("^I open this url \"([^\"]*)\"$")
   // public void I_open_this_url(String url) throws Throwable {
    //    driver.get(url);
    //}

   // @Then("^I send (\\d+) into search field$")
   // public void I_send_into_search_field(int arg1) throws Throwable {
   //    System.out.println("numarul meu este" +arg1);
   // }
}
