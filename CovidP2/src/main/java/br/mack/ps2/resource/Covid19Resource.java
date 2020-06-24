package br.mack.ps2.resource;



import br.mack.ps2.api.Covid19;
//import br.mack.ps2.api.CoronavirusString;
//import br.mack.ps2.api.Trends;
import br.mack.ps2.dao.Covid19DAOMySQL;
//import br.mack.ps2.dao.TrendsDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("trends")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class Covid19Resource {

        Covid19DAOMySQL dao1 = new Covid19DAOMySQL();
        InterfaceUsuarioCovid19 coronavirus = new InterfaceUsuarioCovid19(dao1);


        @GET
        public List<Covid19> getCoronavirus () {

            return this.coronavirus.read();
        }

}

