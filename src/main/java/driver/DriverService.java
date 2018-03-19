package driver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

public class DriverService {

    private static AppiumDriverLocalService service;
    private AppiumDriver<MobileElement> driver;

    public AppiumDriver<MobileElement> getDriver() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
        }

        if (driver == null) {
            createDriver();
        }

        return driver;
    }

    private void createDriver() throws Exception {
        if (System.getenv("platform").equalsIgnoreCase("android")) {
            driver = createAndroidDriver();
        }
        if (System.getenv("platform").equalsIgnoreCase("ios")) {
            driver = createIOSDriver();
        }
    }

    private AndroidDriver createAndroidDriver() throws IOException {
        File app = new File("app-release.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", System.getenv("deviceName"));
        capabilities.setCapability("app", app.getAbsolutePath());
        return new AndroidDriver<>(service.getUrl(), capabilities);
    }

    private IOSDriver createIOSDriver() throws Exception {
        File app = new File("app-release.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", System.getenv("platformVersion"));
        capabilities.setCapability("deviceName", System.getenv("deviceName"));
        capabilities.setCapability("app", app.getAbsolutePath());
        return new IOSDriver<>(service.getUrl(), capabilities);
    }

    public void killDriver() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}
