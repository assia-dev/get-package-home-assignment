package pages.delivery;

import org.apache.commons.lang3.StringUtils;
import utils.BaseUtils;

public class DeliveryHelper {


    private BaseUtils BU;

    public DeliveryHelper(BaseUtils baseUtils) {
        this.BU = baseUtils;
    }


    public void setDeliverySize(String deliverySize) {
        System.out.println("Setting delivery size to " + deliverySize);
        BU.clickOnElement(DeliveryConstants.deliverySizes.get(deliverySize));
        BU.waitForLoadingAnimate();
        BU.captureScreenShot("Selected Delivery size");
    }


    public void setPickupAddress(String pickupAddress, String addressDetails) {
        System.out.println("Setting pickup address to " + pickupAddress + ",  and details to: " + addressDetails);
        BU.selectElementByText(DeliveryLocators.INPUT_PICKUP_ADDRESS, pickupAddress);
        BU.waitForLoadingAnimate();
        BU.sendKeysToElement(DeliveryLocators.TXTBX_PICKUP_ADDRESS_DETAILS, addressDetails);
        BU.pressEnterOnElement(".delivery-summary");
        BU.clickOnElement(".delivery-summary");
    }


    public void setDropOffAddress(String dropOffAddress, String addressDetails) {
        System.out.println("Setting dropOffAddress to " + dropOffAddress + ",  and details to: " + addressDetails);
        BU.scrollIntoElementView(DeliveryLocators.INPUT_DROP_OFF_ADDRESS);
        BU.selectElementByText(DeliveryLocators.INPUT_DROP_OFF_ADDRESS, dropOffAddress);
        BU.waitForLoadingAnimate();
        BU.sendKeysToElement(DeliveryLocators.TXTBX_DROP_OFF_ADDRESS_DETAILS, addressDetails);
        BU.pressEnterOnElement(".delivery-summary");
        BU.clickOnElement(".delivery-summary");
    }


    public void validateDeliveryPageOpened() {
        System.out.println("Validate 'Delivery' page opened");
        BU.waitForLoadingAnimate();
        BU.validateStepByDisplayed(DeliveryLocators.CNT_DELIVERY_DETAILS
                , "Delivery Page Isn't Opened");
        BU.captureScreenShot("Delivery Page");
    }


    public void validateDeliveryTypeSectionDisabled() {
        System.out.println("Validate delivery section disabled");
        BU.waitForLoadingAnimate();
        BU.assertTrue(BU.isElementDisplayed(DeliveryLocators.BTN_TIME_PICKER_DISABLED)
                , "The delivery type section isn't disabled");
        BU.captureScreenShot("Delivery Type");
    }


    public void validateDeliveryTypeSectionEnabled() {
        System.out.println("Validate delivery section enabled");
        BU.sleep(500);
        BU.assertFalse(BU.isElementDisplayed(DeliveryLocators.BTN_TIME_PICKER_DISABLED)
                , "The delivery type section is disabled");
        BU.captureScreenShot("Delivery Type");
    }


    public void validateQuotePrices(String listPrice, String expressPrice) {
        System.out.println("Validate quote prices are list price " + listPrice + ", and express price " + expressPrice);
        BU.waitForLoadingAnimate();
        BU.assertEquals(BU.getTextByElement(DeliveryLocators.TXT_DELIVERY_PRICE)
                , listPrice, "The delivery list price is incorrect");
        BU.clickOnElementWithJS(DeliveryLocators.CHBOX_SELECT_EXPRESS_SERVICE_TYPE);
        BU.sleep(2500);
        BU.waitForLoadingAnimate();
        BU.assertEquals(BU.getTextByElement(DeliveryLocators.TXT_DELIVERY_PRICE)
                , expressPrice, "The express delivery price is incorrect");
        BU.captureScreenShot("Delivery Type");
    }


    public void validateExpressCheckboxSelected() {
        System.out.println("Validate express checkbox selected");
        BU.validateStepByDisplayed(DeliveryLocators.CHBOX_SELECTED_EXPRESS_SERVICE,
                "The express delivery checkbox isn't selected");
        BU.captureScreenShot("Delivery Type");
    }


    public void validateExpressOptionDisplayed() {
        System.out.println("Validate express option displayed");
        BU.validateStepByDisplayed(DeliveryLocators.CHBOX_SELECT_EXPRESS_SERVICE_TYPE,
                "The express delivery checkbox isn't displayed");
        BU.captureScreenShot("Delivery Type");
    }


    public void validateRequiredFields() {
        System.out.println("Validate required fields");
        for (String requireField : DeliveryConstants.requiredFields) {
            String eleNane = StringUtils.substringBefore(requireField, " ");
            BU.assertTrue(BU.isElementDisplayed(requireField),
                    "The field " + eleNane + " isn't marked as required");
        }
        BU.captureScreenShot("Required Fields");
    }


    public void clickOnSubmit() {
        System.out.println("Click on submit");
        BU.clickOnElement(DeliveryLocators.BTN_SUBMIT);
        BU.waitForLoadingAnimate();
    }

}




