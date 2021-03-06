import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
public class TeamMainPageTest {
    private static final String LINKTEXT_JCB = "TEAM WORK";
    private static final String JCB_LINK_TITLE= "José Carlos Bregieiro Ribeiro | LinkedIn";
    private static final String LINKTEXT_RM = "shortbio";

    //PAGES
    private static final String URL_MAINPAGE = "/index.html";

    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        Logger.getLogger("").setLevel(Level.OFF);
        driver = new HtmlUnitDriver();
        baseUrl = String.valueOf(System.getProperty("baseURL"));
    }


    @Test
    public void testJCBTeamMemberLink() throws Exception {
        driver.get(baseUrl+URL_MAINPAGE);
        WebElement linkJCB =  (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(By.linkText(LINKTEXT_JCB)));
        linkJCB.click();

        assertTrue("expected: " + JCB_LINK_TITLE + ", found: " + driver.getTitle(), driver.getTitle().equals(JCB_LINK_TITLE));

    }

    @Test
    public void testRMTeamMemberLink() throws Exception {
        driver.get(baseUrl);
        WebElement linkRM =  (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(By.linkText(LINKTEXT_RM)));
        linkRM.click();

        assertNotNull("Image not found...", driver.findElement(By.xpath("//img[contains(@src,'/uploadsShortbio/qrcodes/qrcode-u02e74f10e0327ad868d138f2b4fdd6f0.png')]")));

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
