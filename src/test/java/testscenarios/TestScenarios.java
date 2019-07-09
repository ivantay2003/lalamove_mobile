/*	
 * 	Copyright Ivan Tay © 2019
 * 	All Rights Reserved.
 * 
 */

package testscenarios;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;


import browserdriver.BrowserType;
import control.Configuration;
import pageobject.PageObjectManager;
import report.ExtentTestManager;

public class TestScenarios extends PageObjectManager{
	
	private Faker faker = new Faker();
	
	private Configuration config;


	@BeforeTest 
	public void setup (){
		config = new Configuration();
		

		  String androidfile = "lala_noninstall.json";
		  String jsonFile = media_path + androidfile;
		  
		  config.setBrowser(BrowserType.ANDROID,jsonFile);//Use Android driver
		  setInitializePageObjects (config); //Initialize all Page Objects Models with project configurations / web driver / user data
		  
		  ExtentTestManager.getInstance("TestReport");
		  
		  
	}
	
	@AfterTest 
	public void closeTest (){
		
		//Comment this if you do NOT want it to close the browser at the end of script 
		 ExtentTestManager.endReport(); //Write report
		 //closeApp();
	}
	
  @Test
  public void test() {
	  ExtentTestManager.createSection("Order scenario 1");
	  System.out.println("Scenario 1");
	  String sourceString = "Clementi";
	  String destinationString = "Changi Airport";
	  int sourceSearch = 2;
	  int destinationSearch = 2;
	  String instructionNotes = "Testing ABC";
	  
	  Assert.assertEquals(main.detectPage(), true,"Main page did not load up");
	  ExtentTestManager.logTest("Main page", Status.PASS);
	  
	  //Set pickup and destination
	  main.setPickupLocation(sourceString, sourceSearch);
	  main.setDestinationLocation(destinationString, destinationSearch);
	  
	//Set date and time advance (we will not customize this)
	  main.advancedSchedule();
	  
	  
	  //Verify the order is correct
	  Assert.assertEquals(order.detectPage(), true,"Order page did not load up");
	  ExtentTestManager.logTest("Order page", Status.PASS);
	  
	  order.addNotes(instructionNotes);
	  Assert.assertEquals(order.getName(), "Mary", "Name is incorrect");
	  ExtentTestManager.logTest("User name", Status.PASS);
	  
	  order.checkedFavouriteDriver(true);
	  
	  
	  
	  //ExtentTestManager.endReport();
	  
  }
}
