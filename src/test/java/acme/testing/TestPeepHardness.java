package acme.testing;



import org.hibernate.internal.util.StringHelper;

import acme.framework.testing.AbstractTest;

public class TestPeepHardness extends AbstractTest{
	
	protected void PeepAnonimous() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Peeps");
	}
	
	protected void PeepChef(final String username, final String password) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);
		super.navigateHome();
		super.clickOnMenu("Sign in");
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.clickOnSubmit("Sign in");
		super.checkCurrentPath("/master/welcome");
		super.clickOnMenu("Authenticated", "Peeps");
	
	}

	
}
