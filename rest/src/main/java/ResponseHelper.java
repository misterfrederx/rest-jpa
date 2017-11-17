import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public final class ResponseHelper {

    public static Response response(Response.Status status) {
        return Response.status(status).build();
    }

    public static Response response(Response.Status status, Object object) {
        if (object == null) {
            return response(status);
        }
        return Response.status(status).entity(object).type(MediaType.APPLICATION_JSON).build();
    }

    public static Response responseError(Object object) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(object).build();
    }

}
