package pages.common;

import pages.delivery.DeliveryHelper;
import pages.delivery.DeliveryLocators;
import pages.login.LoginLocators;
import utils.BaseUtils;

public class CommonHelper {


    private BaseUtils BU;


    public CommonHelper(BaseUtils baseUtils) {
        this.BU = baseUtils;
    }


    public DeliveryHelper getDeliveryHelper() {
        return BU.deliveryPageHelper;
    }


    public void navigateToLoginPage() {
        BU.loggerInfo("Navigating to login page");
        BU.page.navigate("https://sender.getpackage.com/login");
        BU.waitForLoadingAnimate();
        validateAppOpened();
        BU.captureScreenShot("Login Page");
    }


    public void navigateDirectlyToDeliveryPage() {
        BU.loggerInfo("Navigating directly to delivery page");
        BU.page.navigate("https://sender.getpackage.com/delivery");
        BU.waitForLoadingAnimate();
        getDeliveryHelper().validateDeliveryPageOpened();
        BU.captureScreenShot("Delivery Page");
    }


    public void validateAppOpened() {
        BU.loggerInfo("Validate 'Get Package' app is opened");
        BU.waitForLoadingAnimate();
        BU.validateStepByDisplayed(LoginLocators.BTN_EMAIL_AND_PASSWORD_TAB
                , "Get Package App Isn't Opened");
        BU.captureScreenShot("Login Page");
    }

}
