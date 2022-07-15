package testscripts;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class firstTest {
    @Test
    public void testOne() throws MalformedURLException {
        System.out.println(System.getenv("HUB_HOST"));
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        URL url= new URL("http://localhos"+System.getenv("HUB_HOST")+":4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url,dc);
        driver.get("http://www.google.com");
        System.out.println("Title of trehe page: "+driver.getTitle());
        driver.quit();
    }
}
