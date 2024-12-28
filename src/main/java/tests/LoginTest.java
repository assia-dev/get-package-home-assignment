package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseUtils;

public class LoginTest extends  BaseUtils{


    @BeforeClass
    public void testNavigateToLogin() {
        commonHelper.navigateToLoginPage();
    }


    @Test()
    public void testOpenEmailAndPasswordSection() {
        loginPageHelper.clickOnEmailAndPasswordTab();
    }


    @Test(priority = 1)
    public void testWrongUserEmail() {
        loginPageHelper.setUserEmail("wrong@email.com");
        loginPageHelper.setPassword("Asi309690725");
        loginPageHelper.clickOnLogin();
        loginPageHelper.validateWrongUserDetailsError();
    }


    @Test(priority = 2)
    public void testLogin() {
        loginPageHelper.setUserEmail("andeloasnako@gmail.com");
        loginPageHelper.clickOnLogin();
        deliveryPageHelper.validateDeliveryPageOpened();
    }

}
