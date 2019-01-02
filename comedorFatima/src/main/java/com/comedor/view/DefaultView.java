package com.comedor.view;

import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Alejandro Duarte
 */
public class DefaultView extends Composite implements View {

    public DefaultView() {

        VerticalLayout layout = new VerticalLayout();
        final Label Titulo = new Label();
        Titulo.setValue("SISTEMA DE RESTAURANTE COMEDOR TIA FATIMA");
        Titulo.addStyleName(ValoTheme.MENU_SUBTITLE);

        // Display an image stored in theme
        Image image = new Image(null,
                new ThemeResource("icons/Logotipo.jpg"));

// To enable scrollbars, the size of the panel content
// must not be relative to the panel size
        image.setSizeUndefined(); // Actually the default

// The panel will give it scrollbars.
        Panel panel = new Panel("Logotipo");
        panel.setWidth("630px");
        panel.setHeight("420px");
        panel.setContent(image);

        layout.addComponents(Titulo, panel);
        layout.setIcon(new ThemeResource("icons/Logotipo.ico"));
        setCompositionRoot(layout);
    }
}
