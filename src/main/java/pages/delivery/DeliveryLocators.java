package pages.delivery;

public class DeliveryLocators {

    public static final String CNT_DELIVERY_DETAILS = ".delivery-details";

    public static final String TXT_DELIVERY_PRICE = ".price-value";

    public static final String BTN_SIZE_ENVELOPE = "//img[contains(@src, 'envelope')]";
    public static final String BTN_SIZE_SMALL = "//img[contains(@src, 'small')]";
    public static final String BTN_SIZE_MEDIUM = "//img[contains(@src, 'medium')]";
    public static final String BTN_SIZE_LARGE = "//img[contains(@src, 'large')]";
    public static final String BTN_TIME_PICKER = "div.timeselector";
    public static final String BTN_SUBMIT = ".submit";
    public static final String BTN_TIME_PICKER_DISABLED = "div.timeselector + .timepicker.disable";

    public static final String INPUT_PICKUP_ADDRESS = "[formcontrolname='pickUpAddress'] input";
    public static final String INPUT_DROP_OFF_ADDRESS = "[formcontrolname='dropOffAddress'] input";

    public static final String TXTBX_PICKUP_ADDRESS_DETAILS = "[formcontrolname='pickUpAddressDetail']";
    public static final String TXTBX_DROP_OFF_ADDRESS_DETAILS = "[formcontrolname='dropOffAddressDetail']";

    public static final String CHBOX_SELECT_EXPRESS_SERVICE_TYPE = ".type .mat-checkbox-inner-container";
    public static final String CHBOX_SELECTED_EXPRESS_SERVICE = ".mat-checkbox-inner-container .mat-checkbox-checkmark";

    public static final String CNT_DELIVERY_SIZE_BLOCKED = "[formcontrolname='size'] .block";
    public static final String CNT_PICKUP_BLOCKED = ".pickup + * > .block";
    public static final String CNT_DROP_OFF_BLOCKED = ".dropoff + * > .block";
    public static final String CNT_SENDER_BLOCKED = ".sender + * > .block";
    public static final String CNT_RECIPIENT_BLOCKED = ".recipient + * > .block";
}
