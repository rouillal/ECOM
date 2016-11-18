import static org.junit.Assert.*;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

import fr.ecombio.data.PanierRepository;
import fr.ecombio.model.Panier;
import fr.ecombio.rest.PanierResourceRESTService;

public class TestPanier {
	@Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Panier.class, PanierRepository.class, PanierResourceRESTService.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }
	
    @Inject
    Logger log;
    
    @Inject
    PanierRepository repository;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
