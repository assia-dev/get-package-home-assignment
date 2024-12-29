package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.delivery.DeliveryConstants;
import utils.BaseUtils;

public class EilatDeliveryTest extends BaseUtils {


    @BeforeClass
    public void beforeClass() {
        commonHelper.navigateDirectlyToDeliveryPage();
        deliveryPageHelper.setDeliverySize(DeliveryConstants.DELIVERY_SIZE_LARGE);
        deliveryPageHelper.setPickupAddress("הצדפים, אילת, ישראל", "200");
        deliveryPageHelper.setDropOffAddress("יצחק שדה, תל אביב-יפו, ישראל", "200");
    }


    @Test
    public void testExpressDeliverySelected() {
        deliveryPageHelper.validateExpressCheckboxSelected();
    }


}
