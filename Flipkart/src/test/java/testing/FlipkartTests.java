package testing;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import base.BasePage;
import pages.HomePage;
import pages.ProductsListPage;
import pages.SingleProductPage;

public class FlipkartTests extends BasePage
{
	
	HomePage homepage=new HomePage();
	ProductsListPage productslist=new ProductsListPage();
	SingleProductPage singleproduct=new SingleProductPage();
	
	String product="mobiles";
	int productindex=3;
	
	@Test
	public void validateTitleandHeading()
	{
		homepage.openUrl();
		homepage.search(product);
		String title=productslist.getTitle();
		String heading=productslist.getHeading();
		test=report.createTest("validate Title and Heading");
		if(title.contains(product) && heading.contains(product))
		{	
			test.log(Status.PASS,"Title and heading matched as expected");	
			
		}
		else
		{
			test.log(Status.FAIL,"Title and Heading not matched as expected");	
			
		}
		takeScreenshot("title.png");
	}
	@Test
	public void validatePricesSortingOrder()
	{
		
		homepage.openUrl();
		homepage.search(product);
		productslist.clickPriceLowToHigh();
		List<Integer> actprices=productslist.getAllprices();
		List<Integer> expprices=actprices.stream().sorted().collect(Collectors.toList());
		test=report.createTest("Validate Prices sorting order");
		if(actprices.equals(expprices))
		{
			
			test.log(Status.PASS,"All prices are in sorting order");
			
		}
		else
		{
			test.log(Status.FAIL,"All prices are not in sorting order :");
				 
		}
		   test.log(Status.INFO, expprices.toString());
		   test.log(Status.INFO,actprices.toString());
		   takeScreenshot("prices.png"); 
		  	
	}
	@Test
	public void validateProductnameandprice()
	{
		
		homepage.openUrl();
		homepage.search(product);
		List<String> first=productslist.clickOnProduct(productindex);
		singleproduct.addToCart();
		List<String> second=singleproduct.getProductsincart();
		test=report.createTest("Validate productName and Price in cart");
		if(first.equals(second))
		{
			test.log(Status.PASS,"Product Name and Price in Cart are same");
		  
		}
		else
		{
			test.log(Status.FAIL,"Product Name and Price in Cart are NOT same");
		
		}
		takeScreenshot("cart.png");
	}
}

	



 


