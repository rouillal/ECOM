package fr.ecombio.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {

    @Produces
    @PersistenceContext(unitName = "primary")
    private EntityManager em;
    
   /* @Produces
    @PersistenceContext(unitName = "read-unit")
    private EntityManager em2;*/

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
