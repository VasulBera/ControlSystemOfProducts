package com.softserveinc.trainee.service;

import com.softserveinc.trainee.customObject.GenericTableRow;
import org.springframework.stereotype.Service;
import javax.ws.rs.*;
import java.util.List;

@Service
@Path("/custom")
@Consumes("application/json")
@Produces("application/json")
public interface CustomTableService {

    @GET
    @Path("/{id : .+}")
    public List<GenericTableRow> getAllObjects(@PathParam("id") String id);
}
