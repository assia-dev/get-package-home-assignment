package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.delivery.DeliveryConstants;
import utils.BaseUtils;

public class RequiredFieldsTest extends BaseUtils {

    @BeforeClass
    public void beforeClass() {
        commonHelper.navigateDirectlyToDeliveryPage();
        deliveryPageHelper.clickOnSubmit();
    }


    @Test
    public void testRequiredFields() {
        deliveryPageHelper.validateRequiredFields();
    }
}
