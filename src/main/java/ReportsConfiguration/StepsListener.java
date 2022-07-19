package ReportsConfiguration;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class StepsListener{
    public static ExtentTest childNode;
    public static void stepsReporting(String message) {
        childNode=ExtentTestManager.getTest().createNode("Test Steps Performed");
        childNode.log(Status.INFO, MarkupHelper.createLabel(message, ExtentColor.BLUE));
    }
}