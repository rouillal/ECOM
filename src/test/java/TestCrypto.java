import org.junit.Test;

import fr.ecombio.model.MyCryptoConverter;

public class TestCrypto {

	@Test
	public void test() {
		String ELorrie = MyCryptoConverter.encrypt("lorrie");
		String EApo = MyCryptoConverter.encrypt("apo");
		String EFred = MyCryptoConverter.encrypt("xx");
		
		System.out.println("lorrie <"+ELorrie+">");
		System.out.println("apo <"+EApo+">");
		System.out.println("xx <"+EFred+">");
	}
}
