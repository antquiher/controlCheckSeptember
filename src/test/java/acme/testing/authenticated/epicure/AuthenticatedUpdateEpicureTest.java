package acme.testing.authenticated.epicure;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedUpdateEpicureTest extends TestHarness{
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/epicure/updatePositive.csv",encoding = "utf-8", numLinesToSkip = 1)
	public void updatePositiveTest(final int index,final String organisation, final String assertion, final String url) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Account", "Epicure");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Account", "Epicure");
		
		super.checkInputBoxHasValue("organisation", organisation);
		super.checkInputBoxHasValue("assertion", assertion);
		super.checkInputBoxHasValue("url", url);
		super.signOut();
		

		
	}
	
	
	@Order(2)
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/epicure/updateNegative.csv",encoding = "utf-8", numLinesToSkip = 1)
	public void updateNegativeTest(final int index,final String organisation, final String assertion, final String url) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Account", "Epicure");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();		
		super.signOut();
	

	}
}
