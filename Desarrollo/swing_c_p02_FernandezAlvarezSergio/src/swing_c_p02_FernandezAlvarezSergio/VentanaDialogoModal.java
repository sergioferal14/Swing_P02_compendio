/**
 * VentanaDialogoModal.java
 * 16 nov 2024 17:23:56
 * @author Sergio Fernandez Alvarez
 */
package swing_c_p02_FernandezAlvarezSergio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// TODO: Auto-generated Javadoc
/**
 * The Class VentanaDialogoModal.
 */
public class VentanaDialogoModal extends JDialog {

	/** The campo dias estancia. */
	private JTextField campoNombre, campoApellidos, campoExtras, campoImporte, campoFechaEntrada, campoFechaSalida,
			campoDiasEstancia;

	/** The campo telefono. */
	private JFormattedTextField campoDNI, campoTelefono;

	/** The combo habitacion. */
	private JComboBox<String> comboHabitacion;

	/** The spinner edad ninos. */
	private JSpinner spinnerHabitaciones, spinnerEdadNinos;

	/** The check ninos. */
	private JCheckBox checkNinos;

	/** The btn guardar. */
	private JButton btnImprimir, btnNuevo, btnGuardar;

	/** The imagen label. */
	private JLabel imagenLabel;

	/** The imagen actual. */
	private int imagenActual;

	/** The imagenes. */
	private final String[] imagenes = { "/recursos/habitacion1.jpg", "/recursos/habitacion2.jpg",
			"/recursos/habitacion3.jpg" };

	/** The contenedor principal. */
	private JPanel contenedorPrincipal;

	/**
     * Instantiates a new ventana dialogo modal.
     *
     * @param parent the parent
     */
    public VentanaDialogoModal(JFrame parent) {
        super(parent, "Alta Reservas", true);

        // Configuración de la ventana diálogo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(parent);
        setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage());

        // Contenedor principal con BoxLayout (apilado vertical)
        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new BoxLayout(contenedorPrincipal, BoxLayout.Y_AXIS));
        contenedorPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel 1: Encabezado
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setBackground(new Color(44, 62, 80)); // Azul medianoche
        panelEncabezado.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Hotel Señorío del Bierzo",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 18),
            Color.WHITE
        ));
        panelEncabezado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        contenedorPrincipal.add(panelEncabezado);

        // Panel 2: Datos del cliente
        JPanel panelDatosCliente = new JPanel(new GridLayout(4, 6, 5, 5));
        panelDatosCliente.setBackground(new Color(236, 240, 241)); // Gris claro
        panelDatosCliente.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            "Datos del Cliente"
        ));

        campoNombre = new JTextField();
        campoApellidos = new JTextField();

        try {
            MaskFormatter formatoDNI = new MaskFormatter("########U");
            campoDNI = new JFormattedTextField(formatoDNI);
            campoDNI.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    String dni = campoDNI.getText().trim();
                    if (!dni.matches("\\d{8}[A-Za-z]")) {
                        JOptionPane.showMessageDialog(null, "El DNI debe tener 8 números seguidos de una letra.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                        campoDNI.requestFocus(); // Devuelve el foco al campo si está incorrecto
                    }
                }
            });

            MaskFormatter formatoTelefono = new MaskFormatter("#########");
            campoTelefono = new JFormattedTextField(formatoTelefono);
            campoTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    String telefono = campoTelefono.getText().trim();
                    if (!telefono.matches("\\d{9}")) {
                        JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 9 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                        campoTelefono.requestFocus(); // Devuelve el foco al campo si está incorrecto
                    }
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

        campoFechaEntrada = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        campoFechaEntrada.setEditable(false);

        campoFechaSalida = new JTextField(LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        campoFechaSalida.setEditable(false);

        campoDiasEstancia = new JTextField("1");
        campoDiasEstancia.setEditable(false);

        panelDatosCliente.add(new JLabel("Nombre:"));
        panelDatosCliente.add(campoNombre);
        panelDatosCliente.add(new JLabel("Apellidos:"));
        panelDatosCliente.add(campoApellidos);
        panelDatosCliente.add(new JLabel("DNI:"));
        panelDatosCliente.add(campoDNI);
        panelDatosCliente.add(new JLabel("Teléfono:"));
        panelDatosCliente.add(campoTelefono);
        panelDatosCliente.add(new JLabel("Fecha Entrada:"));
        panelDatosCliente.add(campoFechaEntrada);
        panelDatosCliente.add(new JLabel("Fecha Salida:"));
        panelDatosCliente.add(campoFechaSalida);
        panelDatosCliente.add(new JLabel("N° Días Estancia:"));
        panelDatosCliente.add(campoDiasEstancia);

        panelDatosCliente.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelDatosCliente.getPreferredSize().height));
        contenedorPrincipal.add(panelDatosCliente);

        // Panel 3: Datos de la habitación
        JPanel panelDatosHabitacion = new JPanel(new GridLayout(2, 4, 5, 5));
        panelDatosHabitacion.setBackground(new Color(169, 223, 191)); // Verde pálido
        panelDatosHabitacion.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            "Datos de la Habitación"
        ));

        String[] tiposHabitacion = {"Simple", "Doble", "Suite"};
        comboHabitacion = new JComboBox<>(tiposHabitacion);

        spinnerHabitaciones = new JSpinner(new SpinnerNumberModel(1, 0, 50, 1));
        checkNinos = new JCheckBox("¿Niños?");
        JPanel panelExtras = new JPanel(new GridLayout(2, 2));
        panelExtras.setBorder(BorderFactory.createTitledBorder("Extras Niños"));
        panelExtras.setVisible(false);

        spinnerEdadNinos = new JSpinner(new SpinnerNumberModel(0, 0, 14, 1));
        campoExtras = new JTextField();
        campoExtras.setEditable(false);

        checkNinos.addActionListener(e -> panelExtras.setVisible(checkNinos.isSelected()));
        checkNinos.addActionListener(a -> actualizarImporte());

        spinnerEdadNinos.addChangeListener(e -> {
            int edad = (int) spinnerEdadNinos.getValue();
            if (edad >= 0 && edad <= 3) {
                campoExtras.setText("Cuna");
            } else if (edad >= 4 && edad <= 10) {
                campoExtras.setText("Cama Supletoria Pequeña");
            } else if (edad >= 11 && edad <= 14) {
                campoExtras.setText("Cama Supletoria Normal");
            } else {
                campoExtras.setText("");
            }
        });

        campoImporte = new JTextField("0 €");
        campoImporte.setEditable(false);

        panelExtras.add(new JLabel("Edad Niños:"));
        panelExtras.add(spinnerEdadNinos);
        panelExtras.add(new JLabel("Extras:"));
        panelExtras.add(campoExtras);

        panelDatosHabitacion.add(new JLabel("Tipo de Habitación:"));
        panelDatosHabitacion.add(comboHabitacion);
        panelDatosHabitacion.add(new JLabel("N° de Habitaciones:"));
        panelDatosHabitacion.add(spinnerHabitaciones);
        panelDatosHabitacion.add(checkNinos);
        panelDatosHabitacion.add(panelExtras);
        panelDatosHabitacion.add(new JLabel("Importe Habitación:"));
        panelDatosHabitacion.add(campoImporte);
        
        comboHabitacion.addActionListener(e -> actualizarImporte());
        spinnerHabitaciones.addChangeListener(a -> actualizarImporte());
        

        panelDatosHabitacion.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelDatosHabitacion.getPreferredSize().height));
        contenedorPrincipal.add(panelDatosHabitacion);

        // Panel 4: Imágenes
        JPanel panelImagenes = new JPanel(new BorderLayout());
        panelImagenes.setBorder(BorderFactory.createTitledBorder("Imágenes de Habitaciones"));
        imagenLabel = new JLabel(escalarIcono(imagenes[0], 200, 200));  // Redimensiona la imagen
        imagenActual = 0;

        JButton btnCambiarImagen = new JButton("Siguiente Imagen");
        btnCambiarImagen.setToolTipText("Muestra la siguiente imagen de habitaciones");
        btnCambiarImagen.addActionListener(e -> {
            imagenActual = (imagenActual + 1) % imagenes.length;
            imagenLabel.setIcon(escalarIcono(imagenes[imagenActual], 200, 200));  // Redimensiona la imagen
        });

        panelImagenes.add(imagenLabel, BorderLayout.CENTER);
        panelImagenes.add(btnCambiarImagen, BorderLayout.SOUTH);
        contenedorPrincipal.add(panelImagenes);

        // Panel 5: Pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panelDatosClienteTab = new JPanel(new GridLayout(0, 4));
        JPanel panelDatosHabitacionTab = new JPanel(new GridLayout(0, 4));

        tabbedPane.addTab("Datos del Cliente", panelDatosClienteTab);
        tabbedPane.addTab("Datos de la Habitación", panelDatosHabitacionTab);
        contenedorPrincipal.add(tabbedPane);

        // Panel 6: Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnImprimir = new JButton("Imprimir");
        btnImprimir.setIcon(escalarIcono("/recursos/print.png", 30, 30));
        btnImprimir.setPreferredSize(new Dimension(150, 40));
        btnImprimir.setToolTipText("Imprime los datos en las pestañas");
        btnImprimir.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (!validarCampos()) {
			return;
		}

		panelDatosClienteTab.removeAll();
		panelDatosClienteTab.add(new JLabel("Nombre:"));
		panelDatosClienteTab.add(new JLabel(campoNombre.getText()));
		panelDatosClienteTab.add(new JLabel("Apellidos:"));
		panelDatosClienteTab.add(new JLabel(campoApellidos.getText()));
		panelDatosClienteTab.add(new JLabel("DNI:"));
		panelDatosClienteTab.add(new JLabel(campoDNI.getText()));
		panelDatosClienteTab.add(new JLabel("Teléfono:"));
		panelDatosClienteTab.add(new JLabel(campoTelefono.getText()));
		panelDatosClienteTab.add(new JLabel("Fecha Entrada:"));
		panelDatosClienteTab.add(new JLabel(campoFechaEntrada.getText()));
		panelDatosClienteTab.add(new JLabel("Fecha Salida:"));
		panelDatosClienteTab.add(new JLabel(campoFechaSalida.getText()));
		panelDatosClienteTab.revalidate();
		panelDatosClienteTab.repaint();

		panelDatosHabitacionTab.removeAll();
		panelDatosHabitacionTab.add(new JLabel("Tipo de Habitación:"));
		panelDatosHabitacionTab.add(new JLabel(comboHabitacion.getSelectedItem().toString()));
		panelDatosHabitacionTab.add(new JLabel("N° de Habitaciones:"));
		panelDatosHabitacionTab.add(new JLabel(spinnerHabitaciones.getValue().toString()));
		panelDatosHabitacionTab.add(new JLabel("Niños:"));
		panelDatosHabitacionTab.add(new JLabel(checkNinos.isSelected() ? "Sí" : "No"));
		panelDatosHabitacionTab.add(new JLabel("Importe: "));
		panelDatosHabitacionTab.add(new JLabel(campoImporte.getText()));
		if (checkNinos.isSelected()) {
			panelDatosHabitacionTab.add(new JLabel("Edad de los Niños:"));
			panelDatosHabitacionTab.add(new JLabel(spinnerEdadNinos.getValue().toString()));
			panelDatosHabitacionTab.add(new JLabel("Extras:"));
			panelDatosHabitacionTab.add(new JLabel(campoExtras.getText()));
		}
		panelDatosHabitacionTab.revalidate();
		panelDatosHabitacionTab.repaint();
	}

	});

	btnNuevo=new JButton("Nuevo");btnNuevo.setPreferredSize(new Dimension(150,40));btnNuevo.setIcon(escalarIcono("/recursos/new.png",30,30));btnNuevo.setToolTipText("Limpia todos los campos y reinicia la ventana");btnNuevo.addActionListener(e->{limpiarCampos(); // Limpia
																																																																		// todos
																																																																		// los
																																																																		// campos
	campoNombre.requestFocus(); // Establece el foco en el campo Nombre
	});

	btnGuardar=new JButton("Guardar");btnGuardar.setPreferredSize(new Dimension(150,40));btnGuardar.setIcon(escalarIcono("/recursos/save.png",30,30));btnGuardar.setToolTipText("Guarda el registro actual");btnGuardar.addActionListener(e->{if(validarCampos()){JOptionPane.showMessageDialog(this,"Registro Guardado");}else{JOptionPane.showMessageDialog(this,"Por favor, complete todos los campos obligatorios.");}});

	panelBotones.add(btnImprimir);panelBotones.add(btnNuevo);panelBotones.add(btnGuardar);contenedorPrincipal.add(panelBotones);

	add(contenedorPrincipal);

	actualizarImporte();

	setVisible(true);}

	/**
	 * Limpiar campos.
	 */
	private void limpiarCampos() {
		campoNombre.setText("");
		campoApellidos.setText("");
		campoDNI.setText("");
		campoTelefono.setText("");
		campoExtras.setText("");
		campoImporte.setText("0 €");
		comboHabitacion.setSelectedIndex(0);
		spinnerHabitaciones.setValue(1);
		checkNinos.setSelected(false);
		campoFechaEntrada.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		campoFechaSalida.setText(LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		campoDiasEstancia.setText("1");
	}

	/**
     * Validar campos.
     *
     * @return true, if successful
     */
    private boolean validarCampos() {
        String mensajeError = "";
        if (campoNombre.getText().trim().isEmpty()) {
            mensajeError += "El nombre no puede estar vacío.\n";
        }
        if (campoApellidos.getText().trim().isEmpty()) {
            mensajeError += "Los apellidos no pueden estar vacíos.\n";
            
        }
        if (campoDNI.getText().trim().isEmpty()) {
            mensajeError += "El DNI debe estar completamente rellenado.\n";
            
        }
        if (campoTelefono.getText().trim().isEmpty()) {
            mensajeError += "El teléfono debe estar completamente rellenado.\n";
            
        }
        if (!mensajeError.isEmpty()) {
            JOptionPane.showMessageDialog(this, mensajeError, "Error en los datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

	/**
     * Actualizar importe.
     */
    private void actualizarImporte() {
        double precioBase = 0.0;

        // Determinar el precio base según el tipo de habitación
        switch (comboHabitacion.getSelectedItem().toString()) {
            case "Simple":
                precioBase = 50.0;
                break;
            case "Doble":
                precioBase = 75.0;
                break;
            case "Suite":
                precioBase = 120.0;
                break;
        }

        // Multiplicar por el número de habitaciones
        int numeroHabitaciones = (int) spinnerHabitaciones.getValue();
        double importeTotal = precioBase * numeroHabitaciones;

        // Agregar extras si hay niños
        if (checkNinos.isSelected()) {
            int edadNinos = (int) spinnerEdadNinos.getValue();
            if (edadNinos >= 0 && edadNinos <= 3) {
                importeTotal += 10.0; // Precio por cuna
            } else if (edadNinos >= 4 && edadNinos <= 10) {
                importeTotal += 15.0; // Precio por cama supletoria pequeña
            } else if (edadNinos >= 11 && edadNinos <= 14) {
                importeTotal += 20.0; // Precio por cama supletoria normal
            }
        }
        spinnerEdadNinos.addChangeListener(a -> actualizarImporte());

        // Actualizar el campo de importe
        campoImporte.setText(String.format("%.2f €", importeTotal));
    }

    /**
     * Escalar icono.
     *
     * @param ruta the ruta
     * @param ancho the ancho
     * @param alto the alto
     * @return the icon
     */
    private Icon escalarIcono(String ruta, int ancho, int alto) {
        return new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                .getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
    }
    
//    CambiarNombreTitulo
    
//    JFrame ventana = new JFrame("Cursos de Java");
//
//    JButton botonCursoInicial = new JButton("Curso de Java Inicial");
//    JButton botonCursoAvanzado = new JButton("Curso de Java Avanzado");
//    JButton botonCambiarLogo = new JButton("Cambiar logo");
//
//    botonCursoInicial.addActionListener(e -> ventana.setTitle("Curso de Java Inicial"));
//    botonCursoAvanzado.addActionListener(e -> ventana.setTitle("Curso de Java Avanzado"));
//    botonCambiarLogo.addActionListener(e -> {
//        // Cambiar el ícono de la ventana
//        ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/logo.png"));
//        ventana.setIconImage(icono.getImage());
//    });
//    
//    JPanel panelBotones = new JPanel();
//    panelBotones.add(botonCursoInicial);
//    panelBotones.add(botonCursoAvanzado);
//    panelBotones.add(botonCambiarLogo);
//    
//    ventana.add(panelBotones);
    
}
