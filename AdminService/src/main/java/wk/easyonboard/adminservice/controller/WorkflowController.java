package wk.easyonboard.adminservice.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luca Welker on 4/25/17.
 */
@Path("/workflows")
public class WorkflowController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> GetWorkflows() {
        final List<String> result = new ArrayList<String>();
        for (int i = 1; i <= 10; ++i)
            result.add(String.format("Workflow %s", i));

        return result;
    }
}