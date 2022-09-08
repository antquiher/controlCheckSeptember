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

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.bulletin.Bulletin;
import acme.features.authenticated.bulletin.AuthenticatedBulletinRepository;
import acme.framework.helpers.FactoryHelper;
import acme.testing.TestHarness;

public class BulletinListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------
	
	@Autowired
	private AuthenticatedBulletinRepository repository;
	
	@BeforeAll
	@Override
	public void beforeAll() {
		super.beforeAll();
	    FactoryHelper.autowire(this);
	    Calendar cal = Calendar.getInstance();
	    cal.set(1900, Calendar.JANUARY, 1, 0, 0, 0); //Year, month, day of month, hours, minutes and seconds
	    Date date = cal.getTime();
		Bulletin bulletin = this.repository.findBulletinToPatch(date);
		bulletin.setInstantiationMoment(new Date());
		this.repository.save(bulletin);
	}

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/bulletin/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String heading, final String text,
		final boolean flag, final String link) {
		super.signIn("chef1", "chef1");
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
		
		super.signIn("epicure1", "epicure1");
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
		
		super.signIn("administrator", "administrator");
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

	// Ancillary methods ------------------------------------------------------

}
