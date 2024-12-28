package utils;

import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static utils.AutomationUtilsByPlaywright.loggerInfo;

public class BrowserManager {


    public Playwright playwright;
    public Browser browser;
    public BrowserContext context;
    public Page page;
    public String activeHost;

    public static Path DOWNLOADS_FOLDER_PATH = Paths.get(System.getProperty("user.dir") + "/src/main/java/downloads");


    public void initializeBrowserInstance() {

        Path downloadPath;

        playwright = Playwright.create();
        String browserName = "chrome";

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel(browserName.toLowerCase())
                .setArgs(Arrays.asList(
                        "--start-maximized",
                        "--test-type",
                        "--enable-automation",
                        "--js-flags=--expose-gc",
                        "--enable-precise-memory-info",
                        "--disable-default-apps",
                        "--disable-extensions",
                        "--disable-infobars",
                        "--disable-popup-blocking",
                        "--ignore-certificate-errors",
                        "--safebrowsing-disable-download-protection",
                        "--disable-notifications",
                        "--disable-features=WebRtcHideLocalIpsWithMdns"
                ));


        if (browserName.equalsIgnoreCase("chrome")) {
            browser = playwright.chromium().launch(launchOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            browser = playwright.firefox().launch(launchOptions);
        } else if (browserName.equalsIgnoreCase("webkit")) {
            browser = playwright.webkit().launch(launchOptions);
        }
    }


    private void setUpBrowserContext() {
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        loggerInfo("Browser opened");
        page = context.newPage();

        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }


    protected void prepareAndLoadPage() {
        initializeBrowserInstance();
        setUpBrowserContext();
    }
}
