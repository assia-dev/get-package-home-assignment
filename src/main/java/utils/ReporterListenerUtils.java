package utils;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.Arrays;


public class ReporterListenerUtils {


    private boolean isBeforeClassMethod(ITestContext context) {

        ITestNGMethod[] beforeMethods = context.getAllTestMethods()[0].getTestClass().getBeforeClassMethods();
        return Arrays.stream(beforeMethods).anyMatch(beforeMethod -> beforeMethod.getMethodName().equalsIgnoreCase("beforeClass"));
    }


    public synchronized void setTestName(ITestContext context) {
        if (isTestsMachine()) {
            //Set retry only for one test in class or for first because it not rerun the whole class
            ITestNGMethod[] allTestMethods = context.getAllTestMethods();

            if (allTestMethods.length == 1) {
            } else {
                Arrays.stream(allTestMethods)
                        .filter(t -> t.getPriority() == 0)
                        .findFirst();
            }
        }

        AutomationUtilsByPlaywright au = (AutomationUtilsByPlaywright) context.getAllTestMethods()[0].getInstance();
            au.testClassName = context.getAllTestMethods()[0].getTestClass().getName();
            au.testName = context.getAllTestMethods()[0].getMethodName();


    }


    private boolean isTestsMachine() {
        return Boolean.valueOf(System.getProperty("rp.enable", "false").toLowerCase());
    }


    public synchronized void onTestStart(ITestResult result) {
        AutomationUtilsByPlaywright au = (AutomationUtilsByPlaywright) result.getInstance();
            au.testName = updateTestName(result, au);
            au.testClassName = updateTestClassName(result, au);

    }


    private String updateTestName(ITestResult result, AutomationUtilsByPlaywright au) {
        String currentTest = result.getMethod().getMethodName();
        if (au.testName == null || !au.testName.equalsIgnoreCase(currentTest)) {
            return currentTest;
        }
        return au.testName;
    }


    public void onStartSuite(ISuite arg0) {
        int threadCount = Integer.parseInt(System.getProperty("threadCount"));
        arg0.getXmlSuite().setThreadCount(threadCount);
    }


    private String updateTestClassName(ITestResult result, AutomationUtilsByPlaywright au) {
        String currentClass = result.getTestClass().getName();
        if (au.testClassName == null || !au.testClassName.equalsIgnoreCase(currentClass)) {
            return currentClass;
        }
        return au.testClassName;
    }
}
