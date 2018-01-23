package automationFramework;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scripts {
	
	private WebDriver driver;
	public Scripts() {
		super();
		//String exePath = "C:\\Users\\sku261\\Desktop\\BasisAutomation\\chrome_driver\\chromedriver_win32\\chromedriver.exe";	
		String exePath = "./chrome_driver/chromedriver_win32/chromedriver.exe";		
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
	}

	public void launch(){
		driver.get("https://test-servicedesk.publicisgroupe.net/");
		driver.manage().window().maximize(); 
	}
	
	public void signIn(HashMap<String, String> hMap){
		try{
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys(hMap.get("loginID"));
		driver.findElement(By.id("user_password")).sendKeys(hMap.get("password"));
		driver.findElement(By.id("sysverb_login")).click();
		}catch(NoSuchElementException e){
			System.out.println("Not found");
		}
	}
	
	
	public ArrayList<String> createBasisIncident(ArrayList<Incidents> list) throws InterruptedException{
		
		ArrayList<String> incList = new ArrayList<>();
				
		
		for (Incidents s : list) {
		    System.out.println(s.getCustomerUpdate());
			Thread.sleep(5000);
		    driver.findElement(By.xpath(".//*[@title='Create New']")).click();
			driver.switchTo().frame("gsft_main");
			driver.findElement(By.xpath(".//*[@id='sys_display.incident.caller_id']")).sendKeys(s.getCaller());
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='AC.incident.caller_id']/table/tbody/tr[2]/td[1]")).click();
			driver.findElement(By.xpath(".//*[@id='incident.short_description']")).sendKeys(s.getDescription());
			driver.findElement(By.xpath(".//*[@id='sys_display.incident.u_service_ci']")).sendKeys(s.getServiceCategory());
			
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='AC.incident.u_service_ci']/div/span")).click();;
			driver.findElement(By.xpath(".//*[@id='sys_display.incident.assignment_group']")).sendKeys(s.getAssignmentGroup());
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='AC.incident.assignment_group']/div/span")).click();
			driver.findElement(By.xpath(".//*[@id='incident.u_medium']")).click();
			driver.findElement(By.xpath(".//*[@id='incident.u_medium']/option[@value='Email']")).click();
			
			driver.findElement(By.xpath(".//*[@id='sys_display.incident.assigned_to']")).sendKeys(s.getAssignedTo());
			Thread.sleep(2000);
			
			try{
			driver.findElement(By.xpath(".//*[@id='AC.incident.assigned_to']/table/tbody/tr[2]/td[2]")).click();
		    }catch(NoSuchElementException e){
				System.out.println("Not found");
			}
			
			driver.findElement(By.xpath(".//*[@id='incident.comments']")).sendKeys(s.getCustomerUpdate());

			driver.findElement(By.xpath(".//*[@id='sysverb_insert']")).click();
			
			String inc = driver.findElement(By.xpath(".//*[@id='section-bc33727d0a0a3c0e03f93a4c94cf54bb']/nav/div/div[1]/h2/span/span")).getText();
			driver.switchTo().defaultContent();
			System.out.println(inc);
            incList.add(inc);
		    
            //return incList;
			
		}
		return incList;
	}
	
	public void quite(){
		// Close the driver
        driver.quit();
	}

}
