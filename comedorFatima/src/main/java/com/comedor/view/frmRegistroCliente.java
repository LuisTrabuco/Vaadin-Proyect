/**
 *
 * @author Luis Trabuco
 */
package com.comedor.view;

import com.comedor.controlador.DAO.ClienteDao;
import com.comedor.controlador.ConexionBD.PostgresSQL;
import com.comedor.modelo.Cliente;
import com.comedor.modelo.Estado;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class frmRegistroCliente extends Composite implements View {
// The object that will be edited

    private final static Estado Casado = Estado.CASADO;
    private final static Estado Casada = Estado.CASADA;
    private final static Estado Soltero = Estado.SOLTERO;
    private final static Estado Soltera = Estado.SOLTERA;
    private final static Estado Viudo = Estado.VIUDO;
    private final static Estado Viuda = Estado.VIUDA;
    private final static Estado Divorciado = Estado.DIVORCIADO;
    private final static Estado Divorciada = Estado.DIVORCIADA;
    private static Cliente cliente;
    private static ClienteDao clienteBD;
    private static PostgresSQL bd;
    public static Connection conexion;

    public frmRegistroCliente() {
        //Formulario Lauyout
        FormLayout formLayout = new FormLayout();
        final Label respuesta = new Label();

        respuesta.setVisible(false);
        //titulo de cabecera
        final Label Titulo = new Label();
        Titulo.setValue("Registrar Cliente");

        Titulo.addStyleName(ValoTheme.MENU_SUBTITLE);

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

        final ComboBox<Estado> cboEstadoCivil = new ComboBox<>("EstadoCivil");
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

        final TextField celular = new TextField();
        celular.setCaption("Introduzca su celular:");
        celular.setPlaceholder("Ingrese su celular cliente");
        celular.setValueChangeMode(ValueChangeMode.EAGER);

        final RadioButtonGroup<String> groupGenero = new RadioButtonGroup<>();
        groupGenero.setItems("Femenino", "Masculino");
        groupGenero.setCaption("Seleccione el genero del Cliente:");

        final TextField genero = new TextField();
        genero.setCaption("Genero seleccionado es:");
        genero.setEnabled(false);

        groupGenero.addValueChangeListener(event -> {
            if (event.getSource().isEmpty()) {
                genero.setEnabled(true);
                genero.setValue("Genero no fue seleccionado");
                genero.setEnabled(false);
            } else {
                genero.setEnabled(true);
                genero.setValue(event.getValue());
                genero.setEnabled(false);
            }
        });

        Button save = new Button("Registrar");
        save.addClickListener(e -> {
            if (validarDatos(cedula.getValue(), name.getValue(), surname.getValue(), street.getValue(), phone.getValue(), email.getValue(), celular.getValue(), groupGenero.getValue(), cboEstadoCivil.getValue().getPosicion())) {
                clienteBD = obtenerClienteBD();
                try {
                    if (conexion.equals(null) && bd.equals(null) && clienteBD.equals(null)) {
                        try {
                            this.bd = new PostgresSQL();
                            this.conexion = bd.establecer();
                            clienteBD = new ClienteDao(bd);
                        } catch (SQLException ex) {
                            Logger.getLogger(frmRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (conexion.equals(clienteBD.getCon())) {
                        int ci = Integer.parseInt(cedula.getValue());

                        if (ci > 0) {

                            cliente = new Cliente(ci, name.getValue(), surname.getValue(), street.getValue(), phone.getValue(), email.getValue(), groupGenero.getValue(), cboEstadoCivil.getValue(), celular.getValue());
                            respuesta.setVisible(true);
                            respuesta.setValue("Cliente grabado en la Base de Datos");
                            int result = clienteBD.insertarCliente(cliente);
                            if (result > 0) {
                                Notification notification = new Notification("Registro guardado exitosamente", Notification.Type.ASSISTIVE_NOTIFICATION);
                                notification.show(cliente.toString(), Notification.Type.ASSISTIVE_NOTIFICATION);
                                respuesta.setValue("¡Registro cargado exitosamente!");
                                respuesta.setVisible(true);
                            }
                        }
                    }
                } catch (NullPointerException ex) {
                    Notification notification = new Notification("Error al insertar el objeto", Notification.Type.ERROR_MESSAGE);
                    notification.show("Objeto para insertar en la BD fué nulo: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                } catch (InputMismatchException ex) {
                    Notification notification = new Notification("Error al cargar el cedula", Notification.Type.ERROR_MESSAGE);
                    notification.show("Letras en campo numerico: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);

                } catch (NumberFormatException ex) {
                    Notification notification = new Notification("Error al cargar el cedula", Notification.Type.ERROR_MESSAGE);
                    notification.show("Cedula no numerico: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                } finally {
                    try {
                        if (bd.VerificarConexionBD()) {
                            bd.cerrarConeccionJDBC();
                            conexion.close();
                        }
                    } catch (SQLException ex) {
                        //ex.printStackTrace();
                        Notification notification = new Notification("Error al cerrar la base de datos", Notification.Type.ERROR_MESSAGE);
                        notification.show("BD no cerrada: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                    }
                }
            }
        });
        Button reset = new Button("Limpiar");
        reset.addClickListener(e -> {
            String vacio = "";
            cedula.setValue(vacio);
            name.setValue(vacio);
            surname.setValue(vacio);
            street.setValue(vacio);
            phone.setValue(vacio);
            email.setValue(vacio);
            cboEstadoCivil.setSelectedItem(null);
            groupGenero.setSelectedItem(null);
            celular.setValue(vacio);
        });
        // Button bar
        HorizontalLayout actions = new HorizontalLayout();
        respuesta.addStyleName(ValoTheme.LABEL_SUCCESS);
        actions.addComponents(save, reset, respuesta);
        formLayout.addComponents(Titulo, cedula, name, surname, street, phone, email, cboEstadoCivil, estadoCivil, celular, groupGenero, genero, actions);

        setCompositionRoot(formLayout);
    }

    private boolean validarDatos(String cedula, String nombre, String apellido, String direccion, String telefono, String correo, String celular, String genero, int codEstadoCivil) {
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

        if (celular.trim().isEmpty()) {
            Notification notification = new Notification("Error al cargar el celular", Notification.Type.ERROR_MESSAGE);
            notification.show("Celular vacio", Notification.Type.ERROR_MESSAGE);
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

    private ClienteDao obtenerClienteBD() {
        ClienteDao cBD = null;
        try {
            this.bd = new PostgresSQL();
            this.conexion = bd.establecer();
            if (conexion.equals(bd.establecer())) {
                cBD = new ClienteDao(bd);
                Notification notification = new Notification("Abriendo la conexión la base de datos", Notification.Type.ASSISTIVE_NOTIFICATION);
                notification.show("BD PostgreSQL estado: Abierto", Notification.Type.ASSISTIVE_NOTIFICATION);
            } else {
                if (bd == null) {
                    this.bd = new PostgresSQL();
                }
                this.conexion = bd.establecer();
                if (conexion.equals(bd.establecer())) {
                    cBD = new ClienteDao(bd);
                    Notification notification = new Notification("Abriendo la conexión la base de datos", Notification.Type.ASSISTIVE_NOTIFICATION);
                    notification.show("BD PostgreSQL estado: Abierto", Notification.Type.ASSISTIVE_NOTIFICATION);
                }
            }
        } catch (NullPointerException ex) {
            Notification notification = new Notification("Error al crear el objeto", Notification.Type.ERROR_MESSAGE);
            notification.show("Objeto: Para la conexión fue nulo: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);

        } catch (SQLException ex) {
            Notification notification = new Notification("Error al cargar la base de datos", Notification.Type.ERROR_MESSAGE);
            notification.show("BD no establecida: \n" + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        return cBD;
    }

}
