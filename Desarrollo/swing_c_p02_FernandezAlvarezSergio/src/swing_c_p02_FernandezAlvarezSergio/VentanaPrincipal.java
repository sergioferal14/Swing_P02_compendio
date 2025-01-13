/**
 * VentanaPrincipal.java
 * 13 nov 2024 9:32:53
 * @author Sergio Fernandez Alvarez
 */
package swing_c_p02_FernandezAlvarezSergio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 * 
 */
public class VentanaPrincipal extends JFrame {

	public VentanaPrincipal() {
        // Tamaño, título y posición de la ventana principal
        setTitle("Gestión Hotel Señorío del Bierzo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setSize(width / 2, height / 2);
        setLocationRelativeTo(null);

        // Poner ícono de la ventana
        Image icon = new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage();
        setIconImage(icon);

        // Elaborar la barra de menú
        JMenuBar miBarra = new JMenuBar();

        // Menú Archivo
        JMenu archivo = new JMenu("Archivo");
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(e -> System.exit(0)); // Cierra la aplicación
        archivo.add(salir);
        miBarra.add(archivo);

        // Menú Registro
        JMenu registro = new JMenu("Registro");
        registro.setMnemonic(KeyEvent.VK_R); // Atajo Alt + R
        

        JMenuItem altasReservas = new JMenuItem("Alta Reservas");
        
        altasReservas.setMnemonic(KeyEvent.VK_A); // Atajo Alt + A
        altasReservas.addActionListener(e -> mostrarDialogo());

        JMenuItem bajaReservas = new JMenuItem("Baja Reservas");
        bajaReservas.setMnemonic(KeyEvent.VK_B); // Atajo Alt + B
        bajaReservas.addActionListener(e -> mostrarMensaje("Opción no desarrollada"));

        registro.add(altasReservas);
        registro.add(bajaReservas);
        miBarra.add(registro);

        // Menú Ayuda
        JMenu ayuda = new JMenu("Ayuda");
        JMenuItem acercaDe = new JMenuItem("Acerca de...");
        acercaDe.addActionListener(e -> mostrarMensaje("Gestión Hotel Señorio del Bierzo - Creado por Sergio Fernandez Alvarez"));
        ayuda.add(acercaDe);
        miBarra.add(ayuda);

        // Añadir barra de menú a la ventana
        this.setJMenuBar(miBarra);

        // Crear botones principales
        JPanel panelBotones = new JPanel();
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelBotones.setLayout(new FlowLayout());
        JLabel imagenLabel = new JLabel(escalarIcono("/recursos/logo.png", 300, 300));
        JButton btnAltaReservas = new JButton("Alta Reservas");
        btnAltaReservas.setIcon(escalarIcono("/recursos/icono_alta.png", 20, 20)); // Escala la imagen
        btnAltaReservas.setPreferredSize(new Dimension(150, 40)); // Tamaño del botón
        btnAltaReservas.addActionListener(e -> mostrarDialogo());
        
        //Boton que habre el dialogo accionado por Alt + C
        btnAltaReservas.setMnemonic(KeyEvent.VK_C); 

        JButton btnBajaReservas = new JButton("Baja Reservas");
        btnBajaReservas.setIcon(escalarIcono("/recursos/icono_baja.png", 20, 20)); // Escala la imagen
        btnBajaReservas.setPreferredSize(new Dimension(150, 40)); // Tamaño del botón
        btnBajaReservas.addActionListener(e -> mostrarMensaje("Opción no desarrollada"));

        altasReservas.setMnemonic(KeyEvent.VK_A); 
        
        panelImagen.add(imagenLabel, BorderLayout.CENTER);
        panelBotones.add(btnAltaReservas);
        panelBotones.add(btnBajaReservas);

        // Agregar el panel de botones al frame
        add(panelImagen, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para mostrar la ventana de Alta Reservas
    private void mostrarDialogo() {
        new VentanaDialogoModal(this); // Llama al constructor de VentanaDialogoModal
    }

    // Método para mostrar un mensaje emergente
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private ImageIcon escalarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(ruta));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
