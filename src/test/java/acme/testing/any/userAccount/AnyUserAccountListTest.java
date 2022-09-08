package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountListTest extends TestHarness{

	@Test
	@Order(10)
	public void positiveAnonymousTest() {
		super.clickOnMenu("Anonymous", "User Accounts");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/any/user-account/positiveList.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(10)
	public void positiveParameterizedTest(final Integer index,final String username,final String name,final String surname,
		final String rol) {
		super.clickOnMenu("Anonymous","User Accounts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(index, 0, username);
		super.checkColumnHasValue(index, 1, name);
		super.checkColumnHasValue(index, 2, surname);
		super.checkColumnHasValue(index, 3, rol);
	}
	
	
}
