package fr.ecombio.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
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
    
    public static Image getLogo() throws BadElementException, MalformedURLException, IOException {
    	return Image.getInstance("logo.png");
    }

}
