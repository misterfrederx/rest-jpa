import entities.PersonEntity;
import services.PersonDAO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/persons")
public class PersonResource {

    @EJB
    private PersonDAO provider;

    @GET
    public Response getAllPersons() {
        try {
            return ResponseHelper.response(Status.OK, provider.getAllPersons());

        } catch (Exception ex) {
            return ResponseHelper.responseError(ex.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") int id) {
        try {
            PersonEntity person = provider.getPersonByID(id);
            return ResponseHelper.response(person != null ? Status.OK : Status.NOT_FOUND, person);

        } catch(Exception ex) {
            return ResponseHelper.responseError(ex.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(PersonEntity person) {
        try {
            return ResponseHelper.response(Status.CREATED, provider.addPerson(person));

        } catch (Exception ex) {
            return ResponseHelper.responseError(ex.getMessage());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(PersonEntity person) {
        try {
            PersonEntity updatedPerson = provider.updatePerson(person);
            Status returnCode = updatedPerson.equals(person) ? Status.NO_CONTENT : Status.CREATED;

            return ResponseHelper.response(returnCode, updatedPerson);

        } catch (Exception ex) {
            return ResponseHelper.responseError(ex.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        try {
            Status returnCode = provider.deletePerson(id) ? Status.NO_CONTENT : Status.NOT_FOUND;
            return ResponseHelper.response(returnCode);

        } catch (Exception ex) {
            return ResponseHelper.responseError(ex.getMessage());
        }
    }
}
