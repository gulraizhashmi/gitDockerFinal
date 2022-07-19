package Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

import static ReportsConfiguration.ExtentManager.getReportPath;

public class GetScreenShot {
    public static String capture(WebDriver driver, String screenShotName) throws IOException
    {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        getReportPath(System.getProperty("user.dir")+"/TestReport/screenshots/");
        String destinationPath=System.getProperty("user.dir")+"/TestReport/screenshots/"+screenShotName+".png";
        File destination=new File(destinationPath);
        FileHandler.copy(source, destination);
        return destinationPath;
    }
}
