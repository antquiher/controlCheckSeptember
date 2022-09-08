package acme.testing.authenticated.chef;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedUpdateChefTest extends TestHarness{
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/chef/updatePositive.csv",encoding = "utf-8", numLinesToSkip = 1)
	public void updatePositiveTest(final int index,final String organisation, final String assertion, final String url) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Account", "Chef");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Account", "Chef");
		
		super.checkInputBoxHasValue("organisation", organisation);
		super.checkInputBoxHasValue("assertion", assertion);
		super.checkInputBoxHasValue("url", url);
		super.signOut();
		

		
	}
	
	
	@Order(2)
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/chef/updateNegative.csv",encoding = "utf-8", numLinesToSkip = 1)
	public void updateNegativeTest(final int index,final String organisation, final String assertion, final String url) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Account", "Chef");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();		
		
		super.signOut();
	

	}
	

}
