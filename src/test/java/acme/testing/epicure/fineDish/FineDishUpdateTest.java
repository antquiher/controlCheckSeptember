package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class FineDishUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/finedish/update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void update(final int recordIndex, final String code, final String request, final String budget,
		final String initialDate, final String finishDate, final String url) {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "Fine Dish");
		super.checkListingExists();

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("initialDate", initialDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Update");

		super.signOut();
	}
	
	

}
