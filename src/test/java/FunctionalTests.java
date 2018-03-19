import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.pages.AddNetworkPopup;
import app.pages.MainActivity;
import driver.DriverService;

public class FunctionalTests {

    DriverService driverService;
    MainActivity mainActivity;
    AddNetworkPopup addNetworkPopup;

    @BeforeEach
    public void setUp() throws Exception {
        driverService = new DriverService();
        mainActivity = new MainActivity(driverService.getDriver());
        addNetworkPopup = new AddNetworkPopup(driverService.getDriver());
    }

    @AfterEach
    public void tearDown() throws Exception {
        driverService.killDriver();
    }

    @Test
    @DisplayName("Add new network and connect to it")
    public void addNetworkAndConnectToIt() {
        String networkName = "test";

        mainActivity
            .clickAddWiFiNetworkButton()
            .typeNetwork(networkName)
            .clickCreateWiFiNetworkButton()
            .getMainActivity()
            .connectToNetwork(networkName);

        Assertions.assertEquals(String.format("Connected to the '%s'", networkName),
                mainActivity.getConnectedNetworkName(), "There is no connection to: " + networkName);
        Assertions.assertTrue(mainActivity.getListOfNetworks().contains(networkName),
                "There is no network with name: " + networkName);
    }

    @Test
    @DisplayName("Add network with same name twice")
    public void addNetworkWithSameNameTwice() {
        String networkName = "test";

        AddNetworkPopup addNetworkPopup = mainActivity
            .clickAddWiFiNetworkButton()
            .typeNetwork(networkName)
            .clickCreateWiFiNetworkButton()
            .getMainActivity()
            .clickAddWiFiNetworkButton()
            .typeNetwork(networkName)
            .clickCreateWiFiNetworkButton();

        String errorMessage = addNetworkPopup.getErrorText();
        Assertions.assertTrue(errorMessage.equals("Duplicate network name"), "Wrong error message");

        List<String> networks = addNetworkPopup.clickDismissButton().getListOfNetworks();

        Long countByNetworkName = networks.stream().filter(el -> el.equals(networkName)).count();
        Assertions.assertTrue(countByNetworkName == 1, "There are networks wit duplicate name: " + networkName);
    }
}
