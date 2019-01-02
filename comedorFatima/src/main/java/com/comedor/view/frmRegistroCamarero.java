package com.comedor.view;

import com.comedor.controlador.ConexionBD.PostgresSQL;
import com.comedor.controlador.DAO.CamareroDao;
import com.comedor.modelo.Camarero;
import com.comedor.modelo.Estado;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

/**
 *
 * @author alumno-201
 */
@Theme("mytheme")
public class frmRegistroCamarero extends Composite implements View {

    // The object that will be edited
    private final static Estado Casado = Estado.CASADO;
    private final static Estado Casada = Estado.CASADA;
    private final static Estado Soltero = Estado.SOLTERO;
    private final static Estado Soltera = Estado.SOLTERA;
    private final static Estado Viudo = Estado.VIUDO;
    private final static Estado Viuda = Estado.VIUDA;
    private final static Estado Divorciado = Estado.DIVORCIADO;
    private final static Estado Divorciada = Estado.DIVORCIADA;
    private static Camarero camarero;
    private static CamareroDao camareroBD = null;
    private static PostgresSQL bd = null;
    public static Connection conexion = null;

    public frmRegistroCamarero() {
        try {
            bd = new PostgresSQL();
            if (bd.VerificarConexionBD()) {
                camareroBD = new CamareroDao(bd);
                conexion = bd.establecer();
            } else {
                try {
                    if (bd.VerificarConexionBD() && conexion == null) {
                        bd.cerrarConeccionJDBC();
                        conexion.close();
                    }
                } catch (SQLException ex) {
                    //ex.printStackTrace();
                    Notification notification = new Notification("Error al cerrar la base de datos", Notification.Type.ERROR_MESSAGE);
                    notification.show("BD no cerrada: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
                conexion = bd.establecer();
                Notification notification = new Notification("Abriendo la conexión la base de datos", Notification.Type.WARNING_MESSAGE);
                notification.show("BD PostgreSQL estado: " + conexion.getClientInfo("postgress"), Notification.Type.WARNING_MESSAGE);
            }
        } catch (NullPointerException ex) {
            Notification notification = new Notification("Error al insertar el objeto", Notification.Type.ERROR_MESSAGE);
            notification.show("Objeto: " + bd.toString() + "nulo: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);

        } catch (SQLException ex) {
            // Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
            Notification notification = new Notification("Error al cargar la base de datos", Notification.Type.ERROR_MESSAGE);
            notification.show("BD no establecida: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

        final VerticalLayout layout = new VerticalLayout();

        //titulo de cabecera
        final Label Titulo = new Label();
        Titulo.setValue("Registrar Camarero");

        final TextField cedula = new TextField();
        cedula.setCaption("Introduzca su Nro. CI");
        cedula.setPlaceholder("Ingrese su ci");
        cedula.setValueChangeMode(ValueChangeMode.EAGER);

        //nombre
        final TextField name = new TextField();
        name.setCaption("Introduzca el nombre del Cliente:");
        name.setPlaceholder("Ingrese su nombre");
        name.setValueChangeMode(ValueChangeMode.EAGER);

        //apellido
        final TextField surname = new TextField();
        surname.setCaption("Introduzca el apellido del Cliente:");
        surname.setPlaceholder("Ingrese su apellido");
        surname.setValueChangeMode(ValueChangeMode.EAGER);

        //telefono
        final TextField phone = new TextField();
        phone.setCaption("Introduzca el teléfono del Cliente:");
        phone.setPlaceholder("Ingrese su teléfono");
        phone.setValueChangeMode(ValueChangeMode.EAGER);

        //direccion
        final TextArea street = new TextArea();
        street.setCaption("Introduzca la dirección del Cliente:");
        street.setPlaceholder("Ingrese su dirección");
        street.setWordWrap(true);

        //email
        final TextField email = new TextField();
        email.setCaption("Introduzca el e-mail del Cliente:");
        email.setPlaceholder("Ingrese su correo electrónico");
        email.setValueChangeMode(ValueChangeMode.EAGER);

        ComboBox<Estado> cboEstadoCivil = new ComboBox<>("EstadoCivil");
        cboEstadoCivil.setItems(Casado, Casada, Soltera, Soltero, Divorciado, Divorciada, Viudo, Viuda);
        cboEstadoCivil.setCaption("Seleccione el estado Civil del Cliente:");

        final TextField estadoCivil = new TextField();
        estadoCivil.setCaption("Estado Civil selecccionado fue:");
        estadoCivil.setEnabled(false);

        cboEstadoCivil.addValueChangeListener(event -> {

            if (event.getSource().isEmpty()) {
                estadoCivil.setEnabled(true);
                estadoCivil.setValue("Estado civil no seleccionado");
                estadoCivil.setEnabled(false);
            } else {
                estadoCivil.setEnabled(true);
                estadoCivil.setValue(event.getValue().getEstado());
                estadoCivil.setEnabled(false);
            }

        });

        final RadioButtonGroup<String> groupGenero = new RadioButtonGroup<>();
        groupGenero.setItems("Femenino", "Masculino");
        groupGenero.setCaption("Seleccione el genero del Cliente:");

        final TextField genero = new TextField();
        genero.setCaption("Genero seleccionado es:");
        genero.setEnabled(false);

        groupGenero.addValueChangeListener(event -> {
            if (event.getSource().isEmpty()) {
                genero.setEnabled(true);
                genero.setValue("Estado civil no seleccionado");
                genero.setEnabled(false);
            } else {
                genero.setEnabled(true);
                genero.setValue(event.getValue());
                genero.setEnabled(false);
            }
        });

        Button button = new Button("Registrar");
        button.addClickListener(e -> {
            if (validarDatos(cedula.getValue(), name.getValue(), surname.getValue(), street.getValue(), phone.getValue(), email.getValue(), groupGenero.getValue(), cboEstadoCivil.getValue().getPosicion())) {
                try {
                    int ci = Integer.parseInt(cedula.getValue());

                    if (ci > 0) {
                        camarero = new Camarero(ci, name.getValue(), surname.getValue(), street.getValue(), phone.getValue(), email.getValue(), groupGenero.getValue(), cboEstadoCivil.getValue());
                        layout.addComponent(new Label("Cliente grabado en la Base de Datos"));
                        try {
                            if (conexion != null) {
                                int result = camareroBD.insertarCamarero(camarero);
                                if (result > 0) {
                                    Notification notification = new Notification("Registro guardado exitosamente", Notification.Type.ASSISTIVE_NOTIFICATION);
                                    notification.show(camarero.toString(), Notification.Type.ASSISTIVE_NOTIFICATION);
                                    layout.addComponent(new Label("¡Registro cargado exitosamente!"));
                                }
                            }
                        } catch (NullPointerException ex) {
                            Notification notification = new Notification("Error al insertar el objeto", Notification.Type.ERROR_MESSAGE);
                            notification.show("Objeto desconocido nulo: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);

                        } finally {
                            try {
                                bd.cerrarConeccionJDBC();
                                conexion.close();
                            } catch (SQLException ex) {
                                //ex.printStackTrace();
                                Notification notification = new Notification("Error al cerrar la base de datos", Notification.Type.ERROR_MESSAGE);
                                notification.show("BD no cerrada: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (NullPointerException ex) {
                    Notification notification = new Notification("Error al insertar el objeto", Notification.Type.ERROR_MESSAGE);
                    notification.show("Objeto desconocido nulo: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                } catch (InputMismatchException ex) {
                    Notification notification = new Notification("Error al cargar el cedula", Notification.Type.ERROR_MESSAGE);
                    notification.show("Letras en campo numerico: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);

                } catch (NumberFormatException ex) {
                    Notification notification = new Notification("Error al cargar el cedula", Notification.Type.ERROR_MESSAGE);
                    notification.show("Cedula no numerico: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        layout.addComponents(Titulo, cedula, name, surname, street, phone, email, cboEstadoCivil, estadoCivil, groupGenero, genero, button);
        setCompositionRoot(layout);

    }

    private boolean validarDatos(String cedula, String nombre, String apellido, String direccion, String telefono, String correo, String genero, int codEstadoCivil) {
        boolean registro = true;

        if (cedula.trim().isEmpty()) {

            Notification notification = new Notification("Error al cargar el cedula", Notification.Type.ERROR_MESSAGE);
            notification.show("Cedula vacio", Notification.Type.ERROR_MESSAGE);
            registro = false;
        }

        if (nombre.trim().isEmpty()) {

            Notification notification = new Notification("Error al cargar el nombre", Notification.Type.ERROR_MESSAGE);
            notification.show("Nombre vacio", Notification.Type.ERROR_MESSAGE);
            registro = false;
        }

        if (apellido.trim().isEmpty()) {

            Notification notification = new Notification("Error al cargar el apellido", Notification.Type.ERROR_MESSAGE);
            notification.show("Apellido vacio", Notification.Type.ERROR_MESSAGE);
            registro = false;
        }

        if (direccion.trim().isEmpty()) {

            Notification notification = new Notification("Error al cargar la dirección", Notification.Type.ERROR_MESSAGE);
            notification.show("Dirección vacio", Notification.Type.ERROR_MESSAGE);
            registro = false;
        }
        if (telefono.trim().isEmpty()) {

            Notification notification = new Notification("Error al cargar el teléfono", Notification.Type.ERROR_MESSAGE);
            notification.show("Teléfono vacio", Notification.Type.ERROR_MESSAGE);
            registro = false;
        }

        if (correo.trim().isEmpty()) {

            Notification notification = new Notification("Error al cargar el correo", Notification.Type.ERROR_MESSAGE);
            notification.show("Correo vacio", Notification.Type.ERROR_MESSAGE);
            registro = false;
        }

        if (genero.trim().isEmpty()) {
            Notification notification = new Notification("Error al cargar el genero", Notification.Type.ERROR_MESSAGE);
            notification.show("Genero vacio", Notification.Type.ERROR_MESSAGE);
            registro = false;
        }

        if (codEstadoCivil <= -1) {
            Notification notification = new Notification("Error al cargar el estado civil", Notification.Type.ERROR_MESSAGE);
            notification.show("Estado Civil no contemplado", Notification.Type.ERROR_MESSAGE);
            registro = false;
        }

        return registro;

    }

}
