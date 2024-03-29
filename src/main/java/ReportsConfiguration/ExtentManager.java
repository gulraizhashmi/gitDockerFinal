package ReportsConfiguration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFileName="Test-Automation-Report"+".html";
    private static String fileSeparator=System.getProperty("file.separator");
    public static String reportFilepath=System.getProperty("user.dir")+fileSeparator+"TestReport";
    public static String reportFileLocation=reportFilepath+fileSeparator+reportFileName;

    public static ExtentReports getInstance() {
        if(extent== null)
            createInstance();

        return extent;
    }

    public static ExtentReports createInstance() {
        System.out.println(reportFileLocation);
        String fileName=getReportPath(reportFilepath);
        ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", "MAC");
        extent.setSystemInfo("AUT", "QA");
        return extent;
    }
    public static String getReportPath(String path) {
        File testDirectory = new File(path);
        if(!testDirectory.exists()) {
            if(testDirectory.mkdir()) {
                System.out.println("Directory: "+path+" is created");
                return reportFileLocation;
            }
            else {
                System.out.println("Failed to create directory: "+path);
                return System.getProperty("user.dir");
            }
        }
        else {
            System.out.println("Directory already exists: "+ path);
        }
        return reportFileLocation;
    }
}
