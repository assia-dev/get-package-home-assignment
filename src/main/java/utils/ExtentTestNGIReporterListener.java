package utils;

import org.testng.*;


public class ExtentTestNGIReporterListener implements ITestListener, ISuiteListener, IInvokedMethodListener {


    private ReporterListenerUtils reporterListenerUtils = new ReporterListenerUtils();


//    @Override
//    public void afterInvocation(IInvokedMethod method, ITestResult arg0) {
//        reporterListenerUtils.changeStatusToFail(arg0);
//    }


    @Override
    public void onFinish(ISuite arg0) {
    }


    @Override
    public synchronized void onStart(ITestContext context) {
        reporterListenerUtils.setTestName(context);

    }


    @Override
    public synchronized void onFinish(ITestContext context) {
    }


    @Override
    public synchronized void onTestStart(ITestResult result) {
        reporterListenerUtils.onTestStart(result);
    }


    @Override
    public synchronized void onTestSuccess(ITestResult result) {
    }


    @Override
    public synchronized void onTestFailure(ITestResult result) {
    }


    @Override
    public synchronized void onTestSkipped(ITestResult result) {
    }


    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }


}