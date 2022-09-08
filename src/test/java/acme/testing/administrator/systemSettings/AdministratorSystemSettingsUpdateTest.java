package acme.testing.administrator.systemSettings;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class AdministratorSystemSettingsUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-settings/update-positive.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final String spamThreshold, final String defaultCurrency, final String acceptedCurrencies, final String spamTuples) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System settings");
		super.checkFormExists();
		
		super.fillInputBoxIn("defaultCurrency", defaultCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("spamTuples", spamTuples);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Administrator", "System settings");
		super.checkFormExists();
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("spamTuples", spamTuples);
		super.checkInputBoxHasValue("spamThreshold", spamThreshold);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-settings/update-negative.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(20)
	public void negativeTest(final String spamThreshold, final String defaultCurrency, final String acceptedCurrencies, final String spamTuples) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System settings");
		super.checkFormExists();
		
		super.fillInputBoxIn("defaultCurrency", defaultCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("spamTuples", spamTuples);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		super.checkNotPanicExists();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/system-settings/show");
		super.checkPanicExists();
		
		super.signIn("chef1", "chef1");
		super.navigate("/administrator/system-settings/show");
		super.checkPanicExists();
		
		super.signOut();
		
	}

}
