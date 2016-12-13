import static org.junit.Assert.fail;

import org.junit.Test;

import fr.ecombio.model.MyCryptoConverter;

public class TestCrypto {

	@Test
	public void test() {
		String ELorrie = MyCryptoConverter.encrypt("lorrie");
		String EApo = MyCryptoConverter.encrypt("apo");
		String EFred = MyCryptoConverter.encrypt("xx");
		
		/*System.out.println("lorrie <"+ELorrie+">");
		System.out.println("apo <"+EApo+">");
		System.out.println("xx <"+EFred+">");*/
		
		if (!MyCryptoConverter.decrypt(EFred).equals("xx") ||
				!MyCryptoConverter.decrypt(EApo).equals("apo") ||
				!MyCryptoConverter.decrypt(ELorrie).equals("lorrie")) {
			fail("Erreur dans le cryptage");
		}
	}
}
