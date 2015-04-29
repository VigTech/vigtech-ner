
package edu.univalle.vigtech_ner.api;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.univalle.vigtech_ner.NER;
import edu.univalle.vigtech_ner.utils.Entity;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/")
public class NERApi {

	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("ner")
    public ArrayList<Entity> getIndexList(@QueryParam("text") String text) {
      NER ner = new NER();
      return ner.getEntities("", text);
    }

    
}

