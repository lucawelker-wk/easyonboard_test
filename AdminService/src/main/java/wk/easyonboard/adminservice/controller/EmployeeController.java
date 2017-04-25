package wk.easyonboard.adminservice.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luca Welker on 4/25/17.
 */
@Path("/employees")
public class EmployeeController {
    @GET
    public List<String> getEmployees() {
        List<String> result = new ArrayList<String>();
        for(int i = 1; i <= 10; ++i)
            result.add(String.format("Employee %s", i));

        return result;
    }
}
