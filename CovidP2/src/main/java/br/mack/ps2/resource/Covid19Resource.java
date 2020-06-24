package br.mack.ps2.resource;

import br.mack.ps2.api.Trends;
import br.mack.ps2.dao.Covid19DAOMySQL;
import br.mack.ps2.dao.TrendsDao;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("trends")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class Covid19Resource {

        Covid19DAOMySQL dao = new Covid19DAOMySQL();
        TrendsDao covid19 = new TrendsDao(dao);

        @GET
        public Trends getCovid19() {
                return covid19.getAllTrends();
        }

}

