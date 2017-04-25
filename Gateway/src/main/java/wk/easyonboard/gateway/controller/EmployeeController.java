package wk.easyonboard.gateway.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Luca Welker on 4/25/17.
 */
@Path("/api/employees")
public class EmployeeController extends BaseController {
    @GET
    public List<String> getEmployees() {
        return buildAdminClient()
                .path("employees")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .buildGet()
                .invoke(new GenericType<List<String>>() {});
    }
}
