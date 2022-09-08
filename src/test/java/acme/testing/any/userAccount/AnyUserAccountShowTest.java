package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources="/any/user-account/positiveShow.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(10)
	public void anonymousPositiveTest(final Integer index,final String username,final String name,final String surname,final String email,
		final String rol) {
		
		super.clickOnMenu("Anonymous", "User Accounts");
		super.clickOnListingRecord(index);
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		super.checkInputBoxHasValue("roleList", rol);
	}

}
