package agenda.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class JFrameTemplate extends JFrame {

    protected final static String PATH_ICONS = "/agenda/view/icons/";
    private Component parent;
    private JPanel jPanelContenido;
    private JPanel jPanelFooter;
    private static final int PADDING = 5;

    public JFrameTemplate(String titulo, Component parent) {
        super(titulo); // Invoca al constructor de la superclase con el título de la ventana
        this.parent = parent;
        this.jPanelContenido = new JPanel();
        this.jPanelFooter = new JPanelFooter();
        this.cargar();
    }

    public JFrameTemplate(String titulo) {
        this(titulo, null);
    }

    public void mostrarCartelDeError(String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarCartelDeInfo(String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cargar() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // La aplicación por defecto se cierra al tocar la cruz
        this.cargarImagenes(); // Personaliza el ícono de la ventana
        this.setResizable(false); // Impide que la ventana cambie de dimensiones
        this.establecerLayout();
        this.setLocationRelativeTo(this);
    }

    private void establecerLayout() {
        super.getContentPane().setLayout(new BorderLayout()); // Establece la disposición de la ventana
        super.getContentPane().add(jPanelContenido, BorderLayout.CENTER); // El contenido se inserta en el medio
        super.getContentPane().add(jPanelFooter, BorderLayout.PAGE_END); // El footer (pie) se inserta debajo
        this.jPanelContenido.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.pack();
    }

    private void cargarImagenes() {
        this.setIconImage(getImagen(PATH_ICONS + "user-id-icon.png")); // Establece el ícono de la ventana
    }

    protected void cargarIcono(JLabel label, String nameIcon) {
        label.setIcon(new ImageIcon(getURL(PATH_ICONS + nameIcon)));
    }

    protected void cargarIcono(AbstractButton btn, String nameIcon) {
        btn.setIcon(new ImageIcon(getURL(PATH_ICONS + nameIcon)));
    }

    private Image getImagen(String ruta) {
        Image img = null;
        try {
            img = Toolkit.getDefaultToolkit().getImage(getURL(ruta));
        } catch (Exception e) {
            System.out.println("No se pudo cargar recurso " + ruta + ": " + e.getMessage());
        }
        return img;
    }

    private URL getURL(String ruta) {
        URL u = getClass().getResource(ruta);
        if (u == null) {
            throw new RuntimeException("No se pudo cargar recurso " + ruta);
        }
        return u;
    }

    public void cerrarVentana() {
        this.dispose();
    }

    @Override
    public final JComponent getContentPane() {
        return jPanelContenido;
    }

    private class JPanelFooter extends JPanel {

        private JLabel jLabelLink;

        public JPanelFooter() {
            jLabelLink = new JLabel();
            jLabelLink.setForeground(Color.lightGray);
            subject();
            establecerLayout();
            setearComponentes();
        }

        private void establecerLayout() {
            this.setLayout(new BorderLayout());
            this.add(jLabelLink, BorderLayout.EAST);
            this.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        }

        private void subject() {
            jLabelLink.setText("Acceso a Datos");
        }

        private void setearComponentes() {
            for (Component comp : this.getComponents()) {
                JLabel jl = (JLabel) comp;
                jl.setAlignmentX(Component.CENTER_ALIGNMENT);
                jl.setAlignmentY(Component.CENTER_ALIGNMENT);
                jl.setFont(new Font("Lato", 0, 19));
            }
        }

    }
}
