package utils;


import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import pages.common.CommonHelper;
import pages.delivery.DeliveryHelper;
import pages.login.LoginPageHelper;


import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class BaseUtils extends AutomationUtilsByPlaywright {
public static Map<String, String> ucUsersPasswords;


    public static String user;

    private String browserName = "chrome";
    public DeliveryHelper deliveryPageHelper = new DeliveryHelper(this);
    public CommonHelper commonHelper = new CommonHelper(this);
    public LoginPageHelper loginPageHelper = new LoginPageHelper(this);



    @BeforeClass(alwaysRun = true)
    public void setUp() {
        prepareAndLoadPage();
    }


    public String extractStrFromText(String text, String regexPattern) {
        Matcher m = Pattern.compile(regexPattern).matcher(text);
        if (m.find())
            return m.group(0).trim();
        return "";
    }


    public String getValueFromInfoText(String text, String keyToSearch) {
        return extractStrFromText(text, keyToSearch + ":.*")
                .replace(keyToSearch + ":", "")
                .trim();
    }


    @AfterClass(alwaysRun = true)
    public void assertDown(ITestContext result) {
        stopContextAndBrowser(result);
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (playwright != null)
            playwright.close();
    }

}
