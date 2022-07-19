package Listeners;

import ReportsConfiguration.ExtentManager;
import ReportsConfiguration.ExtentTestManager;
import Utility.GetScreenShot;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static testscripts.firstTest.driver;
import static testscripts.firstTest.sendMail;

public class TestListener implements ITestListener {
    public String screentshotpath="";
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName()+ " started ***");
    }
    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
        //sendMail();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
        //StepsListener.stepsReporting(result);
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();

    }

    public void onTestFailure(ITestResult result) {
        //StepsListener.stepsReporting(result);
        try {
            screentshotpath= GetScreenShot.capture(driver, result.getMethod().getMethodName());
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        try {
            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed "+ExtentTestManager.getTest().addScreenCaptureFromPath(screentshotpath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }
}
