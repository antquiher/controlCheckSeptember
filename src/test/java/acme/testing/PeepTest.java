package acme.testing;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class PeepTest extends TestPeepHardness{
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-in/rol.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePeep(final String username, final String password) {
		super.PeepAnonimous();
		super.PeepChef(username, password);
		
		
	}
}
