package wk.easyonboard.grizzlylauncher;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import wk.easyonboard.adminservice.AdminService;
import wk.easyonboard.common.contracts.Launchable;
import wk.easyonboard.gateway.Gateway;
import wk.easyonboard.ui.EasyonboardServlet;

import javax.servlet.ServletRegistration;
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
            startVaadinServlet().start();
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

    private static HttpServer startVaadinServlet() {

        URI baseUri = UriBuilder.fromUri("http://localhost")
                .port(8080)
                .build();

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri);

        WebappContext context = new WebappContext("EasyonboardingApp");

        ServletRegistration registration = context.addServlet("EasyonboardServlet", EasyonboardServlet.class);
        registration.addMapping("/*");
        registration.addMapping("./VAADIN");
        context.deploy(server);

        return server;
    }
}
