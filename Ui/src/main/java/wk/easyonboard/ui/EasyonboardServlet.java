package wk.easyonboard.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "EasyonboardServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = EasyonboardUi.class, productionMode = false)
public class EasyonboardServlet extends VaadinServlet {
}
