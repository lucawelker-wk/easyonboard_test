package wk.easyonboard.gateway.controller;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by Luca Welker on 4/25/17.
 */
public class BaseController {
    private static final String BASE_HOST = "http://localhost";
    private static final int ADMINSERVICE_PORT = 9081;

    protected WebTarget buildAdminClient() {
        return buildClient(ADMINSERVICE_PORT);
    }

    private WebTarget buildClient(int port) {
        ClientConfig config = new ClientConfig(GensonJsonConverter.class);
        return ClientBuilder.newClient(config)
                .target(UriBuilder.fromUri(BASE_HOST).port(port).build());
    }
}
