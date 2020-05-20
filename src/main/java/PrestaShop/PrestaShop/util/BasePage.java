package PrestaShop.PrestaShop.util;

import com.google.common.io.BaseEncoding;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {


	public WebDriver driver;
    //public WebDriverWait wait;
    final int DefaultTimeOut = 30;
    public static String projectPath = System.getProperty("user.dir");

    public void BasePage(WebDriver driver) {
        this.driver = driver;
        /*if(driver!=null) {
            wait = new WebDriverWait(driver, DefaultTimeOut);
        }*/
    }
    
    By loader = By.xpath("//div[@class='loading-binding']");
    

    public int getRandomValue()
    {
    	Random random = new Random();
    	int x = random.nextInt(900) + 100;
		return x;
    }
    
    public String getAlphanumericRandomString()
    {
    	Random rand = new Random();
        String words = null;
        //while(words.length() < 10000) 
        words = Long.toString(Math.abs(rand.nextLong() % 3656158440062976L), 36);
        return words;
    }
    
    public String getRandomString() {
    	  
        int leftLimit = 97;
        int rightLimit = 122; 
        int targetStringLength = 7;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
     
        return generatedString;
    }
    

    public boolean isBlank(String obj) {

   if(obj!=null)
            return obj.isEmpty();
        else

    {
        return false;
    }

}
    public boolean isNotBlank(String obj)

    {
        if(obj!=null)
            return  !obj.isEmpty();
        else
        {return false;}
    }




    public void clickOn(By locator) {

        try {
        	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (StaleElementReferenceException e) {
        	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        }
    }

    public void selectCheckbox(By locator)
    {

            driver.findElement(locator).click();

    }


    public void javaScriptClickOn(By locator) {
    		WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
          WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    }



    public void keyBoard(By Locator, Keys keys) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.presenceOfElementLocated(Locator)).sendKeys(keys);
    }


    public void SwitchToFrame(By Locator) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Locator));
    }

    protected String switchWindow() {
        String mainWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> Windows = driver.getWindowHandles();
        List<String> Windowlist = new ArrayList<String>(Windows);
        driver.switchTo().window(Windowlist.get(1));

        if(driver instanceof InternetExplorerDriver)
        {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenResolution = new Dimension((int)
                    toolkit.getScreenSize().getWidth(), (int)
                    toolkit.getScreenSize().getHeight());

            driver.manage().window().setSize(screenResolution);
        }
        return mainWindow;
    }

    public BasePage SwitchBackFromFrame() {
        driver.switchTo().defaultContent();
        return this;
    }



    public void logPerformance() throws URISyntaxException {
       /* List<LogEntry> logs = driver.manage().logs().get("performance").getAll();
        getStatus(logs);*/
    }

    
                        //       }


    public void waitUntilDisappear(By Locator, int timeout)
    {

        WebElement test=null;

        try {
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            test = wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
        }catch(Exception e){

        }
        if(test!=null) {
            WebDriverWait wait2 = new WebDriverWait(driver, DefaultTimeOut);
            wait2.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
        }
    }



    public void rightClick(By locator) {

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        WebElement elementLocator =  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        actions.contextClick(elementLocator).perform();
        
    }
    public void waitUntilDisappear(By Locator, int timeoutforappear,int timeoutfordisappear)
    {

        WebElement test=null;

        try {
            WebDriverWait wait = new WebDriverWait(driver,timeoutforappear);
            test = wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
        }catch(Exception e){

        }
        if(test!=null) {
                WebDriverWait wait2 = new WebDriverWait(driver, timeoutfordisappear);
                wait2.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
        }
    }
    public void bringElementIntoView(By Locator) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public void bringElementIntoView(By Locator,String trueOrfalse) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView("+trueOrfalse+");", element);
    }


    public void scrollUp() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-500)", "");

    }
    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-1000)");

    }

    public void uploadFile(By locator, String FilePath) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(FilePath);
    }

    public boolean isAlertPresent(int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public boolean isSelected(By Locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(Locator)).isSelected();
    }

    public void enterText(By locator, String inputText)  {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
       wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(inputText);

    }

    public void enterTextWithoutClear(By locator, String inputText) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(inputText);
    }

    public boolean isDisplayed(By Locator, int timeOutinSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutinSec);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).isDisplayed();
        } catch (NoSuchElementException e) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }


    }

    public boolean isClickable(By Locator, int timeOutinSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutinSec);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(Locator)).isDisplayed();
        } catch (NoSuchElementException e) {
            return wait.until(ExpectedConditions.elementToBeClickable(Locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public void SelectFromDropDown(By Locator, String ValueTobeSelect) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)));
        List<WebElement> Options = select.getOptions();
        for (WebElement option : Options) {
            if (option.getText().equalsIgnoreCase(ValueTobeSelect)) {
                wait.until(ExpectedConditions.elementToBeSelected(option));
                break;
            }
        }
    }
    
    public void SelectFromDropDown2(By Locator, String ValueTobeSelect) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)));
        List<WebElement> Options = select.getOptions();
        for (WebElement option : Options) {
            if (option.getText().equalsIgnoreCase(ValueTobeSelect)) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                break;
            }
        }
    }
        
        public void selectDropDownListValue(By locator, String value) {
			// System.out.println("Test 1 : ");
			new Select(driver.findElement(locator)).selectByValue(value);
		
	
    }

    public String getSelectedValueFromDropDown(By Locator) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)));
        WebElement option = select.getFirstSelectedOption();
        return option.getText();
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        this.driver.switchTo().defaultContent();
    }

    public boolean waitForJStoLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        // wait for jQuery to load
       /* ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((JavascriptExecutor) driver).executeScript("return jQuery.active").toString().equals("0");
                } catch (Exception e) {
                    return true;
                }
            }
        };*/

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jsLoad);
    }



    public void selectFromAutoSuggest(By Locator, By AutosuggestTextLocator, By AutosuggestArea, String valueToBeSelect) throws InterruptedException {
        isDisplayed(Locator, 10);
        clickOn(Locator);
        clearText(AutosuggestTextLocator);
        String[] newValueToBeSelect = valueToBeSelect.split("(?!^)");
        for (String value : newValueToBeSelect) {
            enterTextWithoutClear(AutosuggestTextLocator, value);
            if (isClickable(AutosuggestArea, 1)) {
                break;
            }
        }

        try {
            Thread.sleep(2000);
            clickOn(AutosuggestArea);
        }catch(ElementClickInterceptedException e)
        {

            bringElementIntoView(AutosuggestArea);
            javaScriptClickOn(AutosuggestArea);
        }
    }






    public List<WebElement> getWebElements(By Locator) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));
    }

    public List<WebElement> getWebElements(By Locator,int timeout) {
           WebDriverWait wait = new WebDriverWait(driver,timeout);
           try {
               return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));
           }catch (Exception e)
           {
               return new ArrayList<WebElement>();
           }
    }

    public Boolean isVisibleInViewport(By Locator,int timeout) {
        WebDriverWait wbwait = new WebDriverWait(driver,timeout);
      WebElement webElement =  wbwait.until(ExpectedConditions.visibilityOfElementLocated(Locator));

        return (Boolean)((JavascriptExecutor)driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , webElement);
    }

    public void clearText(By Locator) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.elementToBeClickable(Locator)).clear();

    }


    public String getAttributeValue(By Locator, String attribute) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        return wait.until(ExpectedConditions.presenceOfElementLocated(Locator)).getAttribute(attribute);
    }

    public Boolean isAttributeValuePresent(By Locator, String Attribute, String value) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        return wait.until(ExpectedConditions.attributeContains(Locator, Attribute, value));
    }

    public Boolean waitUntilTextValuePresent(By Locator, String Text, int timeout)

    {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.textToBe(Locator, Text));
    }


    public String getText(By Locator) {
        waitForJStoLoad();
        isDisplayed(Locator, DefaultTimeOut);
        try {
        	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
            return wait.until(ExpectedConditions.presenceOfElementLocated(Locator)).getText();
        }catch (StaleElementReferenceException e)
        {
        	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
            return wait.until(ExpectedConditions.presenceOfElementLocated(Locator)).getText();
        }
    }

    public void MouseHover(By locator) {
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public boolean waitForElemenet(By locator){
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return isDisplayed(locator, 60);
    }

    /**
     * This method is used to select value from auto suggest dropdown
     * @param locator
     * @param autoSuggestedInputLocator
     * @param strValue
     * @param lstDropDown
     */
    public void selectFromAutoSuggest(By locator, By autoSuggestedInputLocator, String strValue, By lstDropDown){
        isDisplayed(locator, 10);
        clickOn(locator);
        String[] newValueToBeSelect = strValue.split("(?!^)");
        System.out.println(newValueToBeSelect);
        for (String value : newValueToBeSelect) {
            enterTextWithoutClear(autoSuggestedInputLocator, value);
            if (isDisplayed(lstDropDown, 2)){
                break;
            }
        }
        clickOn(lstDropDown);
    }

    protected void tabAndEnter(By locator){
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(Keys.TAB);

    }

    /**
     * This method is used to find expected element using text from list of webelement
     * @param lstElement
     * @param strTitle
     * @return
     */
    public WebElement getWebElementWithMatchingText(List<WebElement> lstElement, String strTitle){
        WebElement expectedElement = null;
        WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.visibilityOfAllElements(lstElement));
        for(WebElement element : lstElement){
            if(element.getText().equalsIgnoreCase(strTitle)){
                expectedElement = element;
                break;
            }
        }
        return expectedElement;
    }

    public boolean getWebElementsAndMatchContainsText(List<WebElement> lstElement, String strTitle){
        boolean expectedElement = false;
        WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.visibilityOfAllElements(lstElement));
        for(WebElement element : lstElement){
            if(element.getText().contains(strTitle)){
                expectedElement = true;
                break;
            }
        }
        return expectedElement;
    }


    /**
     * This method is used to convert by object to webelement
     * @return string
     */
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * This method is used to convert by object to webelement
     * @param locator
     * @return
     */
    public WebElement getWebElement(By locator){
    	WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

   public boolean isSelected(By Locator)
   {
	   WebDriverWait wait = new WebDriverWait(driver, DefaultTimeOut);
       return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).isSelected();
   }

    
    protected String getStringOFxml(File file, String Xpath) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        Document ExpectedXmlDocument = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(file.getAbsolutePath());
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression xPathExpression = xPath.compile(Xpath);
        NodeList nodes = (NodeList) xPathExpression.
                evaluate(ExpectedXmlDocument, XPathConstants.NODESET);
        Document newXmlDocument = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().newDocument();
        Element root = newXmlDocument.createElement("root");
        if (nodes.getLength() == 0) {
            root = null;
        }

        newXmlDocument.appendChild(root);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Node copyNode = newXmlDocument.importNode(node, true);
            root.appendChild(copyNode);
        }

        Writer outExpected = new StringWriter();
        OutputFormat format2 = new OutputFormat(newXmlDocument);
        XMLSerializer serializer2 = new XMLSerializer(outExpected, format2);
        serializer2.serialize(newXmlDocument);
        System.out.println(outExpected.toString());
        return outExpected.toString();

    }

    public void switchToWindow(String strWindow){
        driver.close();
        driver.switchTo().window(strWindow);

    }

    public String getTodaysDate(String format){
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("MM/dd/yyyy").format(date);
        return modifiedDate;
    }

    /**
     * @author Sunil_Kumar </br>
     *         capture and store screen shots
     *//*

    public String captureScreenshot(String screens) {
        try {
            //String basedir = System.getProperty("user.dir");
            screenName = screens.replaceAll("\\s\\s", screens);
            System.out.println(screenName);
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(screenShotFolderPath + screenName + ".png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return screenName;
    }
*/
    public BasePage selectFromList(By locator, String textToBeSelected)
    {
        List<WebElement> elements = getWebElements(locator);
        for(WebElement ele: elements)
        {
            if(ele.getText().toLowerCase().trim().contains(textToBeSelected.toLowerCase()))
            {
                ele.click();
                break;
            }
        }
        return this;
    }

    protected boolean switchToLatestWindow() {
        Set<String> windows = driver.getWindowHandles();
        List<String> windowList = new ArrayList<String>(windows);
        driver.switchTo().window(windowList.get(windowList.size()-1));
        return true;
    }


}
