package Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static ReportsConfiguration.ExtentManager.getReportPath;

public class GetScreenShot {
    public static String capture(RemoteWebDriver remoteDriver, String screenShotName) throws IOException
    {
        WebDriver tempDriver;
        tempDriver = new Augmenter().augment(remoteDriver);
        TakesScreenshot ts=((TakesScreenshot)tempDriver);
        File source = ts.getScreenshotAs(OutputType.FILE);
        getReportPath(System.getProperty("user.dir")+"/TestReport/screenshots/");
        String destinationPath=System.getProperty("user.dir")+"/TestReport/screenshots/"+screenShotName+".png";
        File destination=new File(destinationPath);
        FileHandler.copy(source, destination);
        return destinationPath;
    }
}
