package Listeners;

import ReportsConfiguration.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.testng.IConfigurationListener;
import org.testng.ITestResult;

public class ConfigurationListener implements IConfigurationListener {

    @Override
    public void onConfigurationSuccess(ITestResult itr) {
        // TODO Auto-generated method stub
        System.out.println(("*** Configuration test method " + itr.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(itr.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.PASS, "Test passed configuration");
    }

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        // TODO Auto-generated method stub
        System.out.println(("*** Configuration test method " + itr.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(itr.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onConfigurationSkip(ITestResult itr) {
        // TODO Auto-generated method stub
        System.out.println(("*** Configuration test method " + itr.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(itr.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

}
