package PrestaShop.PrestaShop.page;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class LoginPage1 extends PrestaShop.PrestaShop.util.BasePage{
	
	//WebDriver driver ;
	String userName = "";
	File prjDir = new File(System.getProperty("user.dir"));
	String psw="";
	String total_Price = "";
	boolean status = false;
	
	//Login
	//By tbCreateEmail = By.id("email_create");
	By tbCreateEmail = By.xpath("//*[contains(text(),'Email address')]/following-sibling::input[@id='email_create']");
	
	By submitCreateEmail = By.cssSelector("#SubmitCreate > span");
	By tbUserName = By.id("email");
	By tbPassword = By.id("passwd");
	By btnLogin = By.cssSelector("#SubmitLogin > span");
	By logOut = By.xpath("//a[@class='logout']");
	
	//Create Account
	By signIn_link = By.xpath("//a[@class='login']"); //div.header_user_info
	//By signIn_link = By.cssSelector("div.header_user_info");
	By gender = By.id("id_gender1");
	By customer_firstname = By.id("customer_firstname");
	By customer_lastname = By.id("customer_lastname");
	By passwd = By.id("passwd");
	By days = By.id("days");
	By months = By.id("months");
	By years = By.id("years");
	By address1 = By.id("address1");
	By address2 = By.id("address2");
	By city = By.id("city");
	By state = By.id("id_state");
	By postcode = By.id("postcode");
	By phone_mobile = By.id("phone_mobile");
	By alias = By.id("alias");
	By register_btn = By.id("#submitAccount > span");
	By register_btn2 = By.xpath("//span[normalize-space()='Register']"); 
	
	//AddingProductInCart
	By Women_link = By.xpath("//a[@class='sf-with-ul'][contains(text(),'Women')]");
	By tShirt_txt = By.cssSelector(".sfHover > .sf-with-ul");
	By imageTshirt = By.xpath("//a[@class='product_img_link']//img[@class='replace-2x img-responsive']");
	By addToCart_btn = By.cssSelector(".ajax_add_to_cart_button > span");
	By continueShoping = By.cssSelector(".continue > span");
	By proceedToCheckout = By.xpath("//span[normalize-space()='Proceed to checkout']");
	By proceedToCheckout2 = By.xpath("(//span[normalize-space()='Proceed to checkout'])[2]");
	
	// Complete Payment Process
	By processCarrier_proceedToCheckout = By.xpath("//button[@name='processCarrier']");
	By termCondition_chkb = By.id("cgv");
	By totalPrice = By.id("total_price");
	By bankWire_link = By.cssSelector(".bankwire");
	By payment_amount = By.id("amount");
	By confirmOrder_btn = By.xpath("//span[normalize-space()='I confirm my order']");
	By completePyment (String amount) { return By.xpath("//strong[contains(text(),'"+amount+"')]"); }
	
	// Order History 
	By myOrder_link = By.xpath("//a[contains(text(),'My orders')]");
	By historyAmount (String amount) {
		return By.xpath("//tr[contains(@class,'first_item')]//span[@class='price'][contains(text(),'"+amount+"')]");
	}

	
	public boolean waitForJStoLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
       
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jsLoad);
    }
	
	@Test(priority=0)
    public void CreateLoginAccount() throws InterruptedException {
        try {
        	DesiredCapabilities cap = DesiredCapabilities.chrome();
            // cap.setCapability("chrome.binary",file.getAbsolutePath());
            ChromeOptions objOptions = new ChromeOptions();
            //objOptions.setExperimentalOption("prefs", prefs);
            objOptions.addArguments("--disable-notifications");
        	System.setProperty("webdriver.chrome.driver",prjDir+"\\src\\test\\java\\driver\\chromedriver.exe");
        	//FirefoxProfile firefoxProfile = new FirefoxProfile();
            driver = new ChromeDriver(objOptions);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30000, TimeUnit.SECONDS);
            driver.get("http://automationpractice.com/index.php");
            waitForJStoLoad();
        	String firstName = getRandomString();
        	String lastName = getRandomString();
        	psw = "Sk123@10";
        	userName = "skumar"+getRandomValue()+"@gmail.com";
        	javaScriptClickOn(signIn_link);
        	Thread.sleep(2000);
        	waitForJStoLoad();
        	clickOn(tbCreateEmail);
        	enterText(tbCreateEmail ,userName );
        	keyBoard(tbCreateEmail, Keys.TAB);
        	clickOn(submitCreateEmail);
        	waitForJStoLoad();
        	Thread.sleep(2000);
        	clickOn(gender);     	
        	enterText(customer_firstname, firstName);
        	keyBoard(customer_firstname, Keys.TAB);
        	enterText(customer_lastname, lastName);
        	keyBoard(customer_lastname, Keys.TAB);
        	enterText(passwd, psw);
        	keyBoard(passwd, Keys.TAB);
        	selectDropDownListValue(days, "2"); //normalize-space(text())='Next'
        	selectDropDownListValue(months, "2");
        	selectDropDownListValue(years, "1990");
        	enterText(address1, getRandomValue()+"");
        	enterText(address2, getRandomString());
        	enterText(city, getRandomString());
        	selectDropDownListValue(state, "46");
        	enterText(postcode, getRandomValue()+"12");
        	enterText(phone_mobile, "9323422"+getRandomValue());
        	keyBoard(alias, Keys.TAB);
        	Thread.sleep(1000);
        	javaScriptClickOn(register_btn2);
        	waitForJStoLoad();
        	Thread.sleep(2000);
        	if(getWebElement(logOut).isEnabled() && getWebElement(logOut).isDisplayed()) {
    			clickOn(logOut);
    			waitForJStoLoad();
            }
            else {
                System.out.println("Logout Element not found");
            }
        	driver.navigate().refresh();
        }catch (Exception e) {
        	e.printStackTrace();
        	driver.close();
        	}
        }


	@Test(priority=1)
    public void Login() throws InterruptedException {
        try {
        Thread.sleep(2000);
        enterText(tbUserName, userName);
        enterText(tbPassword, psw);
        clickOn(btnLogin);
        Thread.sleep(2000);
        waitForJStoLoad();
        driver.navigate().refresh();
  
    }catch (Exception e) {
		e.printStackTrace();
		driver.close();
		
	}
        
  }
	
	@Test(priority=2)
    public void AddingProductInCart() throws InterruptedException {
        try {
        	
        Thread.sleep(2000);
        MouseHover(Women_link);
        javaScriptClickOn(tShirt_txt);
        Thread.sleep(2000);
        MouseHover(imageTshirt);
        clickOn(addToCart_btn);
        Thread.sleep(2000);
        waitForElemenet(continueShoping);
        clickOn(continueShoping);
        Thread.sleep(2000);
        waitForElemenet(imageTshirt);
        MouseHover(imageTshirt);
        clickOn(addToCart_btn);
        Thread.sleep(1000);
        waitForElemenet(proceedToCheckout);
        clickOn(proceedToCheckout);
        waitForJStoLoad();
        Thread.sleep(1000);
        
      
    }catch (Exception e) {
		e.printStackTrace();
		driver.close();
		
	}
        
  }
	
	By orderStep = By.cssSelector("#order_step");
	
	@Test(priority=3)
    public void completeCheckoutProcessByCompletingPaymentFlow() throws InterruptedException {
        try {
        if(isDisplayed(totalPrice, 1)) {
        total_Price = getText(totalPrice);
        }
        //List<WebElement> stepList = getWebElements(orderStep);
        bringElementIntoView(proceedToCheckout2);
        /*for(int i = 0; i< 4; i++) {
        	javaScriptClickOn(proceedToCheckout2);
        	Thread.sleep(2000);
        	//waitForJStoLoad();
        	if(isDisplayed(processCarrier_proceedToCheckout, 5)) {
        		javaScriptClickOn(termCondition_chkb);
        		Thread.sleep(1000);
        		javaScriptClickOn(proceedToCheckout2);
        		Thread.sleep(2000);
            	//waitForJStoLoad();
        	}
        	if(isDisplayed(bankWire_link, 5)) {
        		total_Price = getText(totalPrice);
        		javaScriptClickOn(bankWire_link);
        		Thread.sleep(2000);
            	waitForJStoLoad();
        	}
        }*/
        bringElementIntoView(proceedToCheckout2);
        while (isDisplayed(proceedToCheckout2, 5)) {
        	javaScriptClickOn(proceedToCheckout2);
        	Thread.sleep(2000);
        	waitForJStoLoad();
        	bringElementIntoView(proceedToCheckout2);
        	if(getWebElement(termCondition_chkb).isDisplayed() && getWebElement(termCondition_chkb).isEnabled()){
        		javaScriptClickOn(termCondition_chkb);
        		Thread.sleep(1000);
        		javaScriptClickOn(proceedToCheckout2);
        		Thread.sleep(2000);
            	waitForJStoLoad();
        	}
        	if(getWebElement(bankWire_link).isDisplayed() && getWebElement(bankWire_link).isEnabled()){
        		total_Price = getText(totalPrice);
        		javaScriptClickOn(bankWire_link);
        		Thread.sleep(2000);
            	waitForJStoLoad();
        	}
        }
        
        String actual_amount = getText(payment_amount);
        if(total_Price.equals(actual_amount)) {
        	status = true;
        	System.out.println("the total cost of product is correct" + total_Price + "  and payment value is : "+ actual_amount);
        }else {
        	System.out.println("the total cost of product is incorrect" + total_Price + "  and payment value is : "+ actual_amount);
        }
        
        if (status) {
        	clickOn(confirmOrder_btn);
        	Thread.sleep(2000);
        	waitForJStoLoad();
        	System.out.println("the total cost of product is correct" + total_Price + "  and payment value is : "+ actual_amount);
        }
      
        String complete_Pyment = getText(completePyment(total_Price));
        if(total_Price.equals(complete_Pyment)) {
        	status = true;
        	System.out.println("the total cost of product is correct" + total_Price + "  and payment value is : "+ complete_Pyment);
        }else {
        	System.out.println("the total cost of product is incorrect" + total_Price + "  and payment value is : "+ complete_Pyment);
        	status = false;
        }
        
        if(getWebElement(confirmOrder_btn).isDisplayed() && getWebElement(confirmOrder_btn).isEnabled()){
	        javaScriptClickOn(confirmOrder_btn);
	        Thread.sleep(2000);
	        waitForJStoLoad();
	        
        }
        
    }catch (Exception e) {
		e.printStackTrace();
		driver.close();
		
	}
        
  }
	
	@Test(priority=4)
    public void VerifyOrderAmountUnderOrderHistory() throws InterruptedException {
        try {
        Thread.sleep(1000);
        bringElementIntoView(myOrder_link);
        javaScriptClickOn(myOrder_link);
        Thread.sleep(2000);
        waitForJStoLoad();
        String history_Payment = getText(historyAmount(total_Price));
        if(isDisplayed(historyAmount(total_Price), 5)) {
        	status = true;
        	System.out.println("the total cost of product is correct" + total_Price + "  and payment value is : "+ history_Payment);
        }else {
        	System.out.println("the total cost of product is incorrect" + total_Price + "  and payment value is : "+ history_Payment);
        	status = false;
        }
      
    }catch (Exception e) {
		e.printStackTrace();
		driver.close();
		
	}
        
  }
	
	@AfterTest
	   public void tearDown() {
		if(getWebElement(logOut).isEnabled() && getWebElement(logOut).isDisplayed()) {
			clickOn(logOut);
			waitForJStoLoad();
        }
        else {
            System.out.println("Logout Element not found");
        }
			
			driver.close();
	   }
	

}

