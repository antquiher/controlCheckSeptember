package acme.testing.authenticated.epicure;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedCreateEpicureTest extends TestHarness{
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/epicure/createPositive.csv",encoding = "utf-8", numLinesToSkip = 1)
	public void createPositiveTest(final int index,final String organisation, final String assertion, final String url) {
		
		super.signUp("userTest"+index, "testPassword", "test","test","test@gmail.com");
		super.signIn("userTest"+index, "testPassword");
		super.clickOnMenu("Account", "Become Epicure");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Register");
		
		super.clickOnMenu("Account", "Epicure");
		
		super.checkInputBoxHasValue("organisation", organisation);
		super.checkInputBoxHasValue("assertion", assertion);
		super.checkInputBoxHasValue("url", url);
		super.signOut();
		

		
	}
	
	
	@Order(2)
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/epicure/createNegative.csv",encoding = "utf-8", numLinesToSkip = 1)
	public void createNegativeTest(final int index,final String organisation, final String assertion, final String url) {
		
		super.signUp("userTestNeg"+index, "testPassword", "test","test","test@gmail.com");
		super.signIn("userTestNeg"+index, "testPassword");
		super.clickOnMenu("Account", "Become Epicure");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Register");
		
		super.checkErrorsExist();		
		
		super.signOut();
	

	}
}
