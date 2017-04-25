package wk.easyonboard.grizzlylauncher;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import wk.easyonboard.adminservice.AdminService;
import wk.easyonboard.common.contracts.Launchable;
import wk.easyonboard.gateway.Gateway;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Luca Welker on 4/25/17.
 */
public class GrizzlyLauncher {
    public static void main(String[] args) {
        try {
            startServices(getServiceConfiguration());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startServices(Set<Class<? extends Launchable>> services) throws InstantiationException, IllegalAccessException, IOException {
        for (Class<? extends Launchable> cls : services) {
            HttpServer endpoint = createNewEndpoint(cls);
            endpoint.start();
        }
    }

    private static Set<Class<? extends Launchable>> getServiceConfiguration() {
        Set<Class<? extends Launchable>> services = new HashSet<Class<? extends Launchable>>();
        services.add(Gateway.class);
        services.add(AdminService.class);
        return services;
    }

    private static HttpServer createNewEndpoint(Class<? extends Launchable> service) throws IllegalAccessException, InstantiationException {
        Launchable serviceInstance = service.newInstance();


        URI baseUri = UriBuilder.fromUri("http://localhost")
                .port(serviceInstance.getPortNr())
                .build();


        ResourceConfig configuration = new ResourceConfig(serviceInstance.getControllers())
                .register(GensonJsonConverter.class);

        return GrizzlyHttpServerFactory.createHttpServer(baseUri, configuration);
    }
}
