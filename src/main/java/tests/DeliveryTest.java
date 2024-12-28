package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.delivery.DeliveryConstants;
import utils.BaseUtils;

public class DeliveryTest extends BaseUtils {


    @BeforeClass
    public void beforeClass() {
        commonHelper.navigateDirectlyToDeliveryPage();
    }


    @Test
    public void testDeliveryTypeSectionDisabled() {
        deliveryPageHelper.validateDeliveryTypeSectionDisabled();
    }


    @Test(priority = 1)
    public void testSetDeliverySize() {
        deliveryPageHelper.setDeliverySize(DeliveryConstants.DELIVERY_SIZE_LARGE);
    }


    @Test(priority = 2)
    public void testSetPickupAddress() {
        deliveryPageHelper.setPickupAddress("תל אביב ההגנה, תל אביב-יפו, ישראל","200");
    }


    @Test(priority = 3)
    public void testSetDropOffAddress() {
        deliveryPageHelper.setDropOffAddress("יצחק שדה, תל אביב-יפו, ישראל","200");
    }


    @Test(priority = 4)
    public void testDeliveryTypeSectionEnabled() {
        deliveryPageHelper.validateDeliveryTypeSectionEnabled();
    }


    @Test(priority = 5)
    public void testExpressDeliveryType() {
        deliveryPageHelper.validateExpressOptionDisplayed();
    }


    @Test(priority = 6)
    public void testValidateQuotePrices() {
        deliveryPageHelper.validateQuotePrices("70", "100");
    }



}

