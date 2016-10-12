package com.softserveinc.trainee.service;

import com.softserveinc.trainee.generic.GenericTableRow;
import org.springframework.stereotype.Service;
import javax.ws.rs.*;
import java.util.List;

@Service
@Path("/custom")
@Consumes("application/json")
@Produces("application/json")
public interface CustomTableService {

    @GET
    @Path("/{id : .+}/{from}/{amount}")
    public List<GenericTableRow> getAllObjects(@PathParam("id") String id, @PathParam("from") Integer from,  @PathParam("amount") Integer amount);

    @GET
    @Path("/count/{id : .+}")
    public int getRowCount(@PathParam("id") String id);
}
