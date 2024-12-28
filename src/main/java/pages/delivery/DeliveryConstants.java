package pages.delivery;

import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryConstants {


    //Delivery sizes
    public static final String DELIVERY_SIZE_ENVELOP = "Envelop";
    public static final String DELIVERY_SIZE_SMALL = "Small";
    public static final String DELIVERY_SIZE_MEDIUM = "Medium";
    public static final String DELIVERY_SIZE_LARGE = "Large";



    public static final Map<String, String> deliverySizes = new HashMap<>() {{
        put(DELIVERY_SIZE_ENVELOP, DeliveryLocators.BTN_SIZE_ENVELOPE);
        put(DELIVERY_SIZE_SMALL, DeliveryLocators.BTN_SIZE_SMALL);
        put(DELIVERY_SIZE_MEDIUM,DeliveryLocators.BTN_SIZE_MEDIUM);
        put(DELIVERY_SIZE_LARGE,DeliveryLocators.BTN_SIZE_LARGE);
    }};


    public static final List<String> requiredFields = new ArrayList<>() {{
        Lists.newArrayList(DeliveryLocators.CNT_DELIVERY_SIZE_BLOCKED, DeliveryLocators.CNT_PICKUP_BLOCKED, DeliveryLocators.CNT_DROP_OFF_BLOCKED, DeliveryLocators.CNT_SENDER_BLOCKED, DeliveryLocators.CNT_RECIPIENT_BLOCKED);
    }};
}
