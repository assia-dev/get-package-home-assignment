package pages.login;

import utils.BaseUtils;

public class LoginPageHelper {

    private BaseUtils BU;


    public LoginPageHelper(BaseUtils baseUtils) {
        this.BU = baseUtils;
    }


    public void clickOnEmailAndPasswordTab() {
        if (BU.isElementDisplayed(LoginLocators.INPUT_USER_NAME))
            System.out.println("Click on email and password tab");
        BU.clickOnElement(LoginLocators.BTN_EMAIL_AND_PASSWORD_TAB);
        BU.waitForLoadingAnimate();
        BU.validateStepByDisplayed(LoginLocators.INPUT_PASSWORD,
                "The email & password section isn't displayed");
    }


    public void setUserEmail(String userEmail) {
        System.out.println("Set user email: " + userEmail);
        BU.sendKeysToElement(LoginLocators.INPUT_USER_NAME, userEmail);
        BU.waitForLoadingAnimate();
//        BU.assertEquals(BU.getAttrOfElement(LoginLocators.INPUT_USER_NAME, "value"), userEmail,
//                "The entered user email is incorrect");
        BU.captureScreenShot("User Email");
    }


    public void setPassword(String password) {
        System.out.println("Set password");
        BU.sendKeysToElement(LoginLocators.INPUT_PASSWORD, password);
        BU.waitForLoadingAnimate();
    //    BU.assertEquals(BU.getAttrOfElement(LoginLocators.INPUT_PASSWORD, "value"), password,
    //            "The entered password is incorrect");
        BU.captureScreenShot("User Email");
    }


    public void clickOnLogin() {
        System.out.println("Click on login");
        BU.clickOnElement(LoginLocators.BTN_SUBMIT);
        BU.waitForLoadingAnimate();
    }


    public void validateWrongUserDetailsError() {
        System.out.println("Validate wrong email user error displayed");
        BU.waitForLoadingAnimate();
        BU.validateStepByDisplayed(LoginLocators.IMG_ERROR,
                "The wrong email error isn't displayed");
        closeErrorPopup();
    }


    public void closeErrorPopup() {
        System.out.println("Close error popup");
        BU.waitForLoadingAnimate();
        BU.clickOnElement(LoginLocators.BTN_CLOSE_ERROR_POPUP);
        BU.waitForLoadingAnimate();
        BU.validateStepByNotDisplayed(LoginLocators.IMG_ERROR,
                "The wrong email error is displayed");
    }

}



