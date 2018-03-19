package app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AddNetworkPopup {

    private static final String CREATE_WIFI_NETWORK_BUTTON = "createwifibutton";
    private static final String DISMISS_BUTTON = "closePopup";
    private static final String TYPE_NETWORK_EDITTEXT = "typeNetworkName";
    private static final String ERROR_TEXTVIEW = "errorText";

    private AppiumDriver<MobileElement> driver;

    public AddNetworkPopup(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public AddNetworkPopup clickCreateWiFiNetworkButton() {
        driver.findElementById(CREATE_WIFI_NETWORK_BUTTON).click();
        return this;
    }

    public MainActivity getMainActivity() {
        return new MainActivity(driver);
    }

    public MainActivity clickDismissButton() {
        driver.findElementById(DISMISS_BUTTON).click();
        return new MainActivity(driver);
    }

    public AddNetworkPopup typeNetwork(String name) {
        driver.findElementById(TYPE_NETWORK_EDITTEXT).sendKeys(name);
        driver.hideKeyboard();
        return this;
    }

    public String getErrorText() {
        return driver.findElementById(ERROR_TEXTVIEW).getText();
    }
}
