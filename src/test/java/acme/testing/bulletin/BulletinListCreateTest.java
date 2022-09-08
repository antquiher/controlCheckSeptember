/*
 * SignUpTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class BulletinListCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/bulletin/create-positive.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String heading, final String text, final String link) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "Bulletin");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.checkListingExists();

		super.clickOnButton("Create Bulletin");
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create Bulletin");
		
		super.clickOnMenu("Authenticated", "Bulletin");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 1, text);
		super.checkColumnHasValue(recordIndex, 2, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("link", link);
				
		super.signOut();
		
	}
	 
	@ParameterizedTest
	@CsvFileSource(resources = "/bulletin/create-positive.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String heading, final String text, final String link) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "Bulletin");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.checkListingExists();

		super.clickOnButton("Create Bulletin");
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "false");
		super.clickOnSubmit("Create Bulletin");
		
		super.checkErrorsExist();
				
		super.signOut();
		
	}
	// Ancillary methods ------------------------------------------------------

}
