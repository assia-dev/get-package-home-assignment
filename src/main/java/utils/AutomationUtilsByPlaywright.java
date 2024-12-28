package utils;


import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;

import com.microsoft.playwright.options.SelectOption;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.ITestContext;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.WaitForSelectorState.*;

public class AutomationUtilsByPlaywright extends BrowserManager {


    //need to set the Project Name from main utils of used project
    public static String projectName;
    //need to set the Test Name & Class name from the Extend Report Listener of used project
    public String testName;
    public String testClassName;

    private static final String LOG4J_CONF_FILE_PATH = new File(System.getProperty("user.dir")).getParent() +
            "//get-package-home-assignment//src//main//resources//log4j2.xml";

    static {
        System.setProperty("log4j2.configurationFile", LOG4J_CONF_FILE_PATH);
    }


    protected static final Logger LOGGER = LogManager.getLogger(AutomationUtilsByPlaywright.class);
    protected static final Logger DATA_LOGGER = LogManager.getLogger("binary_data_logger");
    public final int DEFAULT_TIMEOUT = 30000;


    public void waitForElementToBeDisplayed(String selector, int timeOutInSeconds) {
        try {
            //by default state is visible
            page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                    .setTimeout(timeOutInSeconds * 1000));
        } catch (Exception e) {
            captureScreenShot(e.toString());
            e.printStackTrace();
        }
    }


    public void waitForElementNotBeDisplayed(String selector) {
        waitForElementNotBeDisplayed(selector, 50);
    }


    public void waitForElementNotBeDisplayed(String selector, int timeOutInSeconds) {
        try {
            page.locator(selector).all().parallelStream().forEach(l -> l.waitFor(
                    new Locator.WaitForOptions()
                            .setState(HIDDEN)
                            .setTimeout(timeOutInSeconds * 1000)));

        } catch (Exception e) {
            captureScreenShot(e.toString());
            e.printStackTrace();
        }
    }


    public void waitForElementNotBeDisplayed(Locator locator, int timeOutInSeconds) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(HIDDEN)
                    .setTimeout(timeOutInSeconds * 1000));
        } catch (Exception e) {
            captureScreenShot(e.toString());
            e.printStackTrace();
        }
    }


    public void waitForElementNotBeDisplayed(String selector, String text) {
        waitForElementNotBeDisplayed(selector, text, 50);
    }


    public void waitForElementNotBeDisplayed(String selector, String text, int timeOutInSeconds) {
        try {
            page.locator(selector).locator("visible=true")
                    .first()
                    .filter(new Locator.FilterOptions().setHasText(text))
                    .waitFor(new Locator.WaitForOptions()
                            .setState(HIDDEN)
                            .setTimeout(timeOutInSeconds * 1000));
        } catch (Exception e) {
            captureScreenShot(e.toString());
            e.printStackTrace();
        }
    }


    public void waitForElementToBePresence(Locator locator, int timeOutInSeconds) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(ATTACHED)
                    .setTimeout(timeOutInSeconds * 1000));
        } catch (Exception e) {
            captureScreenShot(e.toString());
            e.printStackTrace();
        }
    }


    public void waitForLoad() {
        //by default wait for load state
        page.waitForLoadState();
    }


    public void waitForLoadingAnimate() {
        waitForLoad();
        try {
            page.waitForFunction(
                    """
                            () => {
                            const elements = [
                            document.querySelector("img[src='https://sc1.checkpoint.com/uc-stg/images/srs/ajax-loader.gif']"),
                            document.querySelector('.loading_text'),
                            document.evaluate("//div[@id='customized-dialog-title']/h6[text()='Loading...']", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue,
                            document.evaluate("//button[@id='atm-button-existingCustomer']/parent::div/following-sibling::div[1]/span/span[7]", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue,
                            document.querySelector('.slds-spinner_container'),
                            document.querySelector("[mode='indeterminate'] svg circle"),
                            document.querySelector("[class^='Loading-local__Loader']"),
                            document.querySelector('.loader [mode="indeterminate"]'),
                            document.querySelector("[class^='ServerTableDropdown__LoaderWrapper']"),
                            document.querySelector('.MuiCircularProgress-root'),
                            document.querySelector("g[aria-label='Check Point']"),
                            document.querySelector('.sk-spinner'),
                            document.querySelector('.Spinner'),
                            document.querySelector('.MuiCircularProgress-circle'),
                            document.querySelector('.MuiCircularProgress-svg'),
                            document.querySelector("[class^='indicatorSeparator']"),
                            document.querySelector("[class*='atm-loader']"),
                            document.querySelector(".Loader [mode='indeterminate'] svg"),
                            document.querySelector("[id^='__lottie_element_']"),
                            document.querySelector('.atm-reset-button[disabled]')
                            ];
                            return elements.every(el => el === null || window.getComputedStyle(el).display === 'none');
                            }
                            """
                    , null, new Page.WaitForFunctionOptions().setTimeout(60000));
            waitForElementNotBeDisplayed(".Toastify");
            waitForElementNotBeDisplayed("[mode='indeterminate']");
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


//########################## Find Element ##########################


    public Locator findElement(String selector) {
        try {
            waitForLoad();
            Locator locator = page.locator(selector).locator("visible=true").first();
            //By default state is visible
            locator.waitFor();
            return locator;
        } catch (Exception e) {
            captureScreenShot("The element: " + selector + " is not visible");
            throw e;
        }
    }


    public Locator findElement(String selector, int timeOutInSeconds) {
        try {
            waitForLoad();
            Locator locator = page.locator(selector).locator("visible=true").first();
            //By default state is visible
            locator.waitFor(new Locator.WaitForOptions().setTimeout(timeOutInSeconds * 1000));
            return locator;
        } catch (Exception e) {
            captureScreenShot("The element: " + selector + " is not visible");
            throw e;
        }
    }


    public Locator findHiddenElement(String selector) {
        try {
            waitForLoad();
            Locator locator = page.locator(selector).first();
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(ATTACHED));
            return locator;
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


    public List<Locator> findElements(String selector) {
        return findElements(selector, 30);
    }


    public List<Locator> findElements(String selector, int secondToWait) {
        Locator locator = page.locator(selector).locator("visible=true");
        waitForLoad();
        try {
            locator.first().waitFor(new Locator.WaitForOptions()
                    .setTimeout(secondToWait * 1000));
            return locator.all();
        } catch (PlaywrightException e) {
            captureScreenShot(e.toString());
            return page.locator(selector).all();
        }
    }


//########################## Send Keys ##########################


    public void sendKeysToElement(String selector, String value) {
        sendKeysToElement(findElement(selector), value);
    }


    public void clearElement(String selector) {
        try {
            findElement(selector).clear();
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


    public void sendKeysToElement(Locator locator, String value) {
        try {
            //include clear before
            locator.fill(value);
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


    public void sendKeysToHiddenElement(String selector, String value) {
        sendKeysToHiddenElement(findHiddenElement(selector), value);
    }


    public void sendKeysToHiddenElement(Locator locator, String value) {
        try {
            //TODO setForce not throw error but not fill the value.
            locator.fill(value, new Locator.FillOptions().setForce(true));
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


//########################## Is element ... ##########################


    public Boolean isElementDisplayed(String selector) {
        return isElementDisplayed(selector, 10);
    }


    public Boolean isElementDisplayed(String selector, int timeOutInSeconds) {
        try {
            assertThat(findElement(selector, timeOutInSeconds))
                    .isVisible();
            return true;
        } catch (Throwable t) {
            captureScreenShot("The element: " + selector + " is not displayed");
            return false;
        }
    }


    public Boolean isElementPresence(String selector) {
        return isElementPresence(selector, 10);
    }


    public Boolean isElementPresence(String selector, int timeOutInSeconds) {
        boolean isPresence = false;
        try {
            page.setDefaultTimeout(timeOutInSeconds * 1000);
            //findHiddenElement check if element is attached to the DOM
            isPresence = findHiddenElement(selector) != null;
        } catch (Exception e) {
            captureScreenShot("The element: " + selector + " is not present");
        }
        page.setDefaultTimeout(DEFAULT_TIMEOUT);
        return isPresence;
    }


    public Boolean isElementDisabled(String selector) {
        page.setDefaultTimeout(5000);
        boolean isDisabled = page.isDisabled(selector);
        page.setDefaultTimeout(DEFAULT_TIMEOUT);
        return isDisabled;
    }


    public Boolean isSelected(String selector) {
        page.setDefaultTimeout(5000);
        boolean isSelected = page.isChecked(selector);
        page.setDefaultTimeout(DEFAULT_TIMEOUT);
        return isSelected;
    }


    //########################## Focus ##########################
    public void focusOnElement(String selector) {
        try {
            findElement(selector).focus();
        } catch (Exception e) {
            captureScreenShot(e.toString());
        }
    }


    public void unFocusOnElement(String selector) {
        try {
            findElement(selector).blur();
            waitForLoad();
        } catch (Exception e) {
            captureScreenShot(e.toString());
        }
    }


    //########################## Click ##########################
    public void clickOnElement(String selector) {
        clickOnElement(findElement(selector));
    }


    public void clickOnElement(Locator locator) {
        try {
            locator.click();
        } catch (Exception e) {
            captureScreenShot("Element &nbsp; : &nbsp; " + locator + " Is Not Clickable");
            throw e;
        }
    }


    public void clickOnElementWithJS(String selector) {
        clickOnElementWithJS(findElement(selector));
    }


    public void clickOnElementWithJS(Locator locator) {
        try {
            locator.evaluate("element => element.click()");
        } catch (Exception e) {
            captureScreenShot("Element &nbsp; : &nbsp; " + locator + " Is Not Clickable");
            throw e;
        }
    }


    public void clickOnElementWithActions(String selector) {
        clickOnElementWithActions(findElement(selector));
    }


    public void clickOnElementWithActions(Locator locator) {
        try {
            locator.hover();
            locator.click(new Locator.ClickOptions().setForce(true));
        } catch (Exception e) {
            captureScreenShot("Element &nbsp; : &nbsp; " + locator + " Is Not Clickable");
            throw e;
        }
    }


    public void clickOnHiddenElement(String selector) {
        clickOnHiddenElement(findHiddenElement(selector));
    }


    public void clickOnHiddenElement(Locator locator) {
        try {
            locator.click(new Locator.ClickOptions()
                    .setForce(true));
        } catch (Exception e) {
            captureScreenShot("Element &nbsp; : &nbsp; " + locator + " Is Not Clickable");
            throw e;
        }
    }


    public void selectElementByVisibleText(String selector, String stringToSelect) {
        try {
            findElement(selector)
                    .selectOption(new SelectOption().setLabel(stringToSelect));
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


    public void selectElementByValue(String selector, String valueToSelect) {
        try {
            findElement(selector)
                    .selectOption(new SelectOption().setValue(valueToSelect));
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


    public void selectElementByIndex(String selector, int index) {
        try {
            findElement(selector)
                    .selectOption(new SelectOption().setIndex(index));
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


//########################## Get Text ##########################


    public String getTextByElement(String selector) {
        return getTextByElement(selector, 15);
    }


    public String getTextByElement(String selector, int timeOutInSeconds) {
        try {
            Locator locator = findElement(selector, timeOutInSeconds);
            assertThat(locator)
                    .containsText(Pattern.compile(".*"),
                            new LocatorAssertions.ContainsTextOptions()
                                    .setTimeout(timeOutInSeconds * 1000));
            return locator.innerText();
        } catch (Exception e) {
            captureScreenShot("Element : " + selector + ", Not Contains Any Text");
            throw e;
        }
    }


    public String getTextByElement(String selector, String defaultValue) {
        try {
            return getTextByElement(selector);
        } catch (Exception e) {
            return defaultValue;
        }
    }


    public List<String> getTextFromElements(String selector) {
        Locator locator = page.locator(selector).locator("visible=true");
        try {
            waitForLoad();
            locator.first().waitFor(new Locator.WaitForOptions());
            return locator.allInnerTexts();
        } catch (Exception e) {
            captureScreenShot(e.toString());
            return new ArrayList<>();
        }
    }


    public String getTextFromHiddenElement(String selector, int timeOutInSeconds) {
        try {
            Locator locator = findHiddenElement(selector);
            assertThat(locator)
                    .containsText(Pattern.compile(".*"),
                            new LocatorAssertions.ContainsTextOptions()
                                    .setTimeout(timeOutInSeconds * 1000));
            return locator.innerText();
        } catch (Exception e) {
            captureScreenShot("Element : " + selector + ", Not Contains Any Text");
            throw e;
        }
    }


    public String getTextFromHiddenElement(String selector) {
        return getTextFromHiddenElement(selector, 15);
    }


//########################## Validate ##########################


    public void validateStepByDisplayed(String selector, String errorMessage) {
        validateStepByDisplayed(isElementDisplayed(selector, 50), errorMessage);
    }


    public void validateStepByDisplayed(Boolean conditionIsDisplay, String errorMessage) {
        if (!conditionIsDisplay) {
            captureScreenShot(errorMessage);
            Assert.fail(errorMessage);
        }
    }


    public void validateStepByNotDisplayed(Boolean conditionIsDisplay, String errorMessage) {
        if (conditionIsDisplay) {
            captureScreenShot(errorMessage);
            Assert.fail(errorMessage);
        }
    }


    public void selectElementByText(String inputEle, CharSequence charSequence) {
        try {
            sendKeysToHiddenElement(inputEle, String.valueOf(charSequence));
            waitForElementToBeDisplayed(".pac-container .pac-item",10);
            clickOnElement(".pac-container .pac-item");
        } catch (Exception e) {
            captureScreenShot(e.toString());
            throw e;
        }
    }


    public void pressEnterOnElement(String selector) {
        try {
            findElement(selector).press("Enter");
        } catch (Exception e) {
            captureScreenShot("Keys");
            throw e;
        }
    }

    public String getAttrOfElement(String l, String attr, String defaultValue) {
        Locator locator = findElement(l);
        if ((boolean) locator.first().evaluate("element => element.hasAttribute('" + attr + "')")) {
            return locator.first().getAttribute(attr);
        } else {
            captureScreenShot("The element: " + locator + " does not have the attribute: " + attr);
            return defaultValue;
        }
    }

    public void validateStepByNotDisplayed(String selector, String errorMessage) {
        validateStepByNotDisplayed(isElementDisplayed(selector), errorMessage);
    }


    public void assertFalse(Boolean condition, String errorMessage) {
        if (condition) {
            assertFail(errorMessage);
        } else {
        }
    }


    public void assertTrue(Boolean condition, String errorMessage) {
        if (!condition) {
            assertFail(errorMessage);
        } else {
        }
    }


    public void assertTrue(Boolean condition, String errorMessage, Boolean isWarning) {
        if (!condition) {
            assertFail(errorMessage, isWarning);
        }
    }


    public void assertFail(String errorMessage, Boolean isWarning) {
        if (isWarning) {
            loggerWarning(errorMessage);
        } else {
            try {
                Assert.fail(errorMessage);
            } catch (Error e) {
                loggerFail(e);
            }
        }
    }


    public void assertFail(String errorMessage) {
        assertFail(errorMessage, false);
    }


    //########################## Logger ##########################
    public static void loggerInfo(String details) {
        LOGGER.info(details);
    }


    public static void loggerError(String details) {
        LOGGER.error(details);
    }


    public static void loggerFail(String details) {
        LOGGER.fatal(details);
    }


    public static void loggerFail(Throwable throwable) {
        throwable.printStackTrace();
        LOGGER.fatal(throwable);
    }


    public static void loggerWarning(String details) {
        LOGGER.warn(details);
    }


    protected void logFileAsBase64(String base64, String message) {
        DATA_LOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
    }


    public static void logFile(Path path, String message) {
        DATA_LOGGER.info("RP_MESSAGE#FILE#{}#{}", path, message);
    }


    //########################## Capture ScreenShot ##########################
    public synchronized void captureScreenShot(String logMessage) {
        captureScreenShot(logMessage, true);
    }


    public synchronized void captureScreenShot(String logMessage, Boolean isScrollable) {
        try {
            String imageAsBase64 = getScreenshot(isScrollable);
            logFileAsBase64(imageAsBase64, logMessage);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }


    private Boolean isPageScrollable() {
        Boolean isScrollable = true, isJQuery, isInnerWidthNum;
        try {
            isJQuery = Boolean.valueOf((Boolean) page.evaluate("window.jQuery != undefined"));
            if (isJQuery)
                return Boolean.valueOf((Boolean) page.evaluate("$(document).height() > $(window).height()"));
            else {
                isInnerWidthNum = Boolean.valueOf((Boolean) page.evaluate("typeof window.innerWidth === 'number'"));
                if (isInnerWidthNum) {
                    return Boolean.valueOf((Boolean) page.evaluate(
                            "window.innerWidth > document.documentElement.clientWidth"));
                }
            }
        } catch (Exception ignore) {
        }
        return isScrollable;
    }


    public String getScreenshot(Boolean isScrollable) {
        byte[] screenshot;
        if (isScrollable && isPageScrollable()) {
            screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setFullPage(true));
        } else {
            screenshot = page.screenshot(new Page.ScreenshotOptions());
        }
        return Base64.getEncoder().encodeToString(screenshot);

    }


    public void stopContextAndBrowser(ITestContext result) {
        loggerInfo("Stop context and browser");

        if (context != null) {
            //result.getSkippedTests().size() > 0 to check if before class or before method failed
            if (result.getFailedTests().size() > 0 || result.getSkippedTests().size() > 0) {
                stopAndUploadTrace(result);
            }
            loggerInfo("Closed Context");
            context.close();
            context = null;
        }
        if (browser != null) {
            loggerInfo("Closed Browser");
            browser.close();
            browser = null;
        }
    }


    private void stopAndUploadTrace(ITestContext result) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String fileName = result.getName().replaceAll("\\s+", "") + timeStamp + ".zip";
        Path traceResultPath = Paths.get(System.getProperty("user.dir") + "/test-output/" + fileName);
        try {
            context.tracing().stop(new Tracing.StopOptions().setPath(traceResultPath));
        } catch (Exception e) {
            e.printStackTrace();
            loggerFail(e.toString());
        }
        logFile(traceResultPath, "In order to open trace file navigate to url: https://trace.playwright.dev");
        removeAllFilesFromDirByFileName(traceResultPath.getParent().toString(), fileName);
    }


    public static void removeAllFilesFromDirByFileName(String folderPath, String fileNameContain) {
        File folder = new File(folderPath);
        folder.mkdirs();
        try {
            Arrays.stream(Objects.requireNonNull(folder.listFiles(
                    (f, p) -> StringUtils.containsIgnoreCase(p, fileNameContain)))).forEach(File::delete);
        } catch (NullPointerException e) {
            loggerFail(e);
        }
    }

    public void assertEquals(String string1, String string2, String errorMessage) {
        try {
            Assert.assertEquals(string1, string2, errorMessage);
        } catch (Error e) {
            assertFail(e.toString());

        }
    }


    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
