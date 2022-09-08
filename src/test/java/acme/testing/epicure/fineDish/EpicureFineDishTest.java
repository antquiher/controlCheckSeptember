package acme.testing.epicure.fineDish;



import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import acme.testing.TestHarness;

public class EpicureFineDishTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/finedish/create-finedish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String code, final String request, final String budget,
		final String initialDate, final String finishDate, final String url) {
		super.signIn("epicure3", "epicure3");
		super.clickOnMenu("Epicure", "Fine Dish");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("initialDate", initialDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Create");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/finedish/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String request, final String budget,
			final String initialDate, final String finishDate, final String url) {

		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "Fine Dish");
		super.checkListingExists();
		
		super.clickOnButton("Create");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("initialDate", initialDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("url", url);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
	
	

}
