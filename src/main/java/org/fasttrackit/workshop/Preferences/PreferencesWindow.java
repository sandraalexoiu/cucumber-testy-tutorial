package org.fasttrackit.workshop.Preferences;


import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.form.TextField;
import com.sdl.selenium.web.utils.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PreferencesWindow {

    private WebLocator window = new WebLocator().setId("preferences-win");

    private Button preferencesButton = new Button().setText("Preferences");

    private TextField currentPassword = new TextField(window).setLabel("Current Password");
    private TextField newPassword = new TextField().setLabel("New Password").setContainer(window);
    private TextField confirmNewPassword = new TextField(window).setLabel("Repeat Password");
    private Button saveButton = new Button().setText("Save").setContainer(window);
    private Button closeButton = new Button().setText("Close").setContainer(window);
    private WebLocator statusMessageEl = new WebLocator(window).setClasses("status-msg");

    public static void main(String[] args) {
        PreferencesWindow preferencesWindow = new PreferencesWindow();

        //System.out.println(preferencesWindow.window.getPath());
        //System.out.println(preferencesWindow.saveButton.getPath());
        //System.out.println(preferencesWindow.closeButton.getPath());
        System.out.println(preferencesWindow.statusMessageEl.getPath());
    }


    public void open() {
        Utils.sleep(2000);
        preferencesButton.click();

    }

    public void typeCurrentPassword(String password) {
        Utils.sleep(2000);
        currentPassword.setValue(password);

    }

    public void typeNewPassword(String password) {
        newPassword.setValue(password);

    }

    public void typeConfirmPassword(String password) {
        confirmNewPassword.setValue(password);

    }

    public void save() {
        Utils.sleep(2000);
        saveButton.assertClick();
    }

    public void statusMessageShouldBePresent(String expectedMessage) {
        assertThat(statusMessageEl.getHtmlText(), is(expectedMessage));
    }

    public void close() {
        Utils.sleep(2000);
        closeButton.assertClick();
    }
}