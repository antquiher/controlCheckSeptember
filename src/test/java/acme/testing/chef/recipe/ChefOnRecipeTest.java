package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefOnRecipeTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String code, final String heading, final String description,
		final String preparationNotes, final String link, final boolean isPublished) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "Recipes");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 1, code);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnButton("Find the Artifact used by this recipe");
		super.checkListingExists();
		
		super.signOut();
	}

	@Test
	@Order(20)
	public void negativeTest() {
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/chef/recipe/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/recipe/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/recipe/list");
		super.checkPanicExists();
		super.signOut();
	}
	// Ancillary methods ------------------------------------------------------

}
