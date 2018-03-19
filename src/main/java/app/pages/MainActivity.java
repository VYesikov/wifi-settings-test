package app.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MainActivity {

    private static final String ADD_WIFI_NETWORK_BUTTON = "addWIFIButton";
    private static final String NETWORKS_LISTVIEW = "listOfNetworks";
    private static final String CONNECTED_NETWORK_TEXTVIEW = "selectedNetwork";

    private AppiumDriver<MobileElement> driver;

    public MainActivity(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public AddNetworkPopup clickAddWiFiNetworkButton() {
        driver.findElementById(ADD_WIFI_NETWORK_BUTTON).click();
        return new AddNetworkPopup(driver);
    }

    public List<MobileElement> getNetworksList() {
        return driver.findElements(By.id(NETWORKS_LISTVIEW));
    }

    public MainActivity connectToNetwork(String networkName) {
        driver.findElementById(NETWORKS_LISTVIEW).findElementByXPath(String.format("//*[@text = '%s']", networkName))
                .click();
        return this;
    }

    public String getConnectedNetworkName() {
        return driver.findElementById(CONNECTED_NETWORK_TEXTVIEW).getText();
    }

    public List<String> getListOfNetworks() {
        return driver.findElementById(NETWORKS_LISTVIEW).findElementsByClassName("android.widget.CheckedTextView").stream()
                .map(element -> element.getText()).collect(Collectors.toList());
    }
}