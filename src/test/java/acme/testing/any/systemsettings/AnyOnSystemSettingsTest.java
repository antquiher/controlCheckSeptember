package acme.testing.any.systemsettings;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyOnSystemSettingsTest extends TestHarness {
	
	@Test
	@Order(10)
	public void positiveAdministratorTest() {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System settings");
		super.checkFormExists();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-in/rol.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveAuthenticatedTest(final String username, final String password) {
		super.signIn(username, password);
		super.clickOnMenu("Authenticated", "System Configuration");
		super.checkFormExists();
	}

}
