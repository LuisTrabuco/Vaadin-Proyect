package com.comedor.comedorfatima;

import com.comedor.view.DefaultView;
import com.comedor.view.frmRegistroCajero;
import com.comedor.view.frmRegistroCamarero;
import com.comedor.view.frmRegistroCliente;
import com.comedor.view.frmRegistroCocinero;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button regNuevoCliente = new Button("Registrar Cliente", e -> getNavigator().navigateTo("CLIENTE"));
        regNuevoCliente.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button regNuevoCamarero = new Button("Registrar Camarero", e -> getNavigator().navigateTo("MOZO"));
        regNuevoCamarero.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button regNuevoCajero = new Button("Registrar Cajero", e -> getNavigator().navigateTo("CAJERO"));
        regNuevoCajero.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button regNuevoCocinero = new Button("Registrar Cocinero", e -> getNavigator().navigateTo("CHEF"));
        regNuevoCocinero.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        CssLayout menu = new CssLayout(title,  regNuevoCajero, regNuevoCamarero, regNuevoCliente,regNuevoCocinero);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        CssLayout viewContainer = new CssLayout();

        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        setContent(mainLayout);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", DefaultView.class);
        navigator.addView("CLIENTE", frmRegistroCliente.class);
        navigator.addView("MOZO", frmRegistroCamarero.class);
        navigator.addView("CAJERO", frmRegistroCajero.class);
        navigator.addView("CHEF", frmRegistroCocinero.class);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
