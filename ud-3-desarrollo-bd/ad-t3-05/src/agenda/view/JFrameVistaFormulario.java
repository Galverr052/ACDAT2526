package agenda.view;

import agenda.model.Categoria;
import agenda.model.Contacto;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class JFrameVistaFormulario extends JFrameTemplate {

    public static final Locale LOCALE_ES = new Locale("es", "ES");

    public JFrameVistaFormulario(String titulo, Component parent) {
        super(titulo, parent);
        initComponents();
        cargarIconos();
        prepararSelectorDeFecha();
        prepararListaDeCategorias();
        manejarAccionCerrar();
    }

    public JFrameVistaFormulario(String titulo) {
        this(titulo, null);
    }
    
    private void cargarIconos() {
        cargarIcono(jLabelFoto, "silueta-icon.png");
        cargarIcono(jButtonCerrar, "close-icon.png");
        cargarIcono(jLabelIconoCredencial, "user-id-icon.png");
        cargarIcono(jLabelIconoMail, "mail-icon.png");
        cargarIcono(jLabelIconoTelefono, "phone-icon.png");
        cargarIcono(jLabelIconoMapa, "map-icon.png");
        cargarIcono(jLabelIconoCalendario, "calendar-icon.png");
        cargarIcono(jLabelIconoEtiqueta, "tag-icon.png");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelContenido = new JPanel();
        jLabelFoto = new JLabel();
        jPanelBotonera = new JPanel();
        jButtonCerrar = new JButton();
        jPanelEdicion = new JPanel();
        jPanelIconos = new JPanel();
        jLabelIconoCredencial = new JLabel();
        jLabelIconoMail = new JLabel();
        jLabelIconoTelefono = new JLabel();
        jLabelIconoMapa = new JLabel();
        jLabelIconoCalendario = new JLabel();
        jLabelIconoEtiqueta = new JLabel();
        jPanelCampos = new JPanel();
        jPanelNombreApellido = new JPanel();
        jTextFieldNombre = new JTextField();
        jTextFieldApellido = new JTextField();
        jTextFieldMail = new JTextField();
        jTextFieldTelefono = new JTextField();
        jTextFieldDireccion = new JTextField();
        jXDatePickerNacimiento = new JXDatePicker();
        jComboBoxCategoria = new JComboBox<>();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabelFoto.setBackground(new Color(238, 245, 247));
        jLabelFoto.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelFoto.setIcon(new ImageIcon(getClass().getResource("/agenda/view/icons/silueta-icon.png"))); // NOI18N
        jLabelFoto.setBorder(new javax.swing.border.LineBorder(new Color(36, 123, 160), 4, true));

        jPanelBotonera.setLayout(new GridLayout(3, 1, 0, 10));

        jButtonCerrar.setBackground(new Color(238, 245, 247));
        jButtonCerrar.setFont(new Font("Lato", 0, 16)); // NOI18N
        jButtonCerrar.setForeground(new Color(24, 19, 9));
        jButtonCerrar.setIcon(new ImageIcon(getClass().getResource("/agenda/view/icons/close-icon.png"))); // NOI18N
        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.setHorizontalAlignment(SwingConstants.LEFT);
        jButtonCerrar.setHorizontalTextPosition(SwingConstants.RIGHT);
        jButtonCerrar.setIconTextGap(20);
        jButtonCerrar.setPreferredSize(new Dimension(400, 53));
        jPanelBotonera.add(jButtonCerrar);

        jPanelEdicion.setPreferredSize(new Dimension(100, 800));
        jPanelEdicion.setLayout(new BoxLayout(jPanelEdicion, BoxLayout.LINE_AXIS));

        jPanelIconos.setMinimumSize(new Dimension(70, 192));
        jPanelIconos.setPreferredSize(new Dimension(0, 800));
        jPanelIconos.setLayout(new GridLayout(6, 1));

        jLabelIconoCredencial.setFont(new Font("Lato", 0, 24)); // NOI18N
        jLabelIconoCredencial.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelIconoCredencial.setIcon(new ImageIcon(getClass().getResource("/agenda/view/icons/user-id-icon.png"))); // NOI18N
        jLabelIconoCredencial.setIconTextGap(15);
        jPanelIconos.add(jLabelIconoCredencial);

        jLabelIconoMail.setFont(new Font("Lato", 0, 24)); // NOI18N
        jLabelIconoMail.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelIconoMail.setIcon(new ImageIcon(getClass().getResource("/agenda/view/icons/mail-icon.png"))); // NOI18N
        jLabelIconoMail.setIconTextGap(15);
        jPanelIconos.add(jLabelIconoMail);

        jLabelIconoTelefono.setFont(new Font("Lato", 0, 24)); // NOI18N
        jLabelIconoTelefono.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelIconoTelefono.setIcon(new ImageIcon(getClass().getResource("/agenda/view/icons/phone-icon.png"))); // NOI18N
        jLabelIconoTelefono.setIconTextGap(15);
        jPanelIconos.add(jLabelIconoTelefono);

        jLabelIconoMapa.setFont(new Font("Lato", 0, 24)); // NOI18N
        jLabelIconoMapa.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelIconoMapa.setIcon(new ImageIcon(getClass().getResource("/agenda/view/icons/map-icon.png"))); // NOI18N
        jLabelIconoMapa.setIconTextGap(15);
        jPanelIconos.add(jLabelIconoMapa);

        jLabelIconoCalendario.setFont(new Font("Lato", 0, 14)); // NOI18N
        jLabelIconoCalendario.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelIconoCalendario.setIcon(new ImageIcon(getClass().getResource("/agenda/view/icons/calendar-icon.png"))); // NOI18N
        jLabelIconoCalendario.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabelIconoCalendario.setIconTextGap(5);
        jPanelIconos.add(jLabelIconoCalendario);

        jLabelIconoEtiqueta.setFont(new Font("Lato", 0, 24)); // NOI18N
        jLabelIconoEtiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelIconoEtiqueta.setIcon(new ImageIcon(getClass().getResource("/agenda/view/icons/tag-icon.png"))); // NOI18N
        jLabelIconoEtiqueta.setIconTextGap(15);
        jPanelIconos.add(jLabelIconoEtiqueta);

        jPanelEdicion.add(jPanelIconos);

        jPanelCampos.setLayout(new GridLayout(6, 1));

        jPanelNombreApellido.setLayout(new GridLayout(1, 2));

        jTextFieldNombre.setFont(new Font("Lato", 0, 20)); // NOI18N
        jTextFieldNombre.setForeground(new Color(24, 19, 9));
        jTextFieldNombre.setMargin(new Insets(5, 5, 5, 5));
        jTextFieldNombre.setName("Nombre"); // NOI18N
        jTextFieldNombre.setSelectionColor(new Color(255, 206, 91));
        jPanelNombreApellido.add(jTextFieldNombre);

        jTextFieldApellido.setFont(new Font("Lato", 0, 20)); // NOI18N
        jTextFieldApellido.setForeground(new Color(24, 19, 9));
        jTextFieldApellido.setMargin(new Insets(5, 5, 5, 5));
        jTextFieldApellido.setName("Apellido"); // NOI18N
        jTextFieldApellido.setSelectionColor(new Color(255, 206, 91));
        jPanelNombreApellido.add(jTextFieldApellido);

        jPanelCampos.add(jPanelNombreApellido);

        jTextFieldMail.setFont(new Font("Lato", 0, 20)); // NOI18N
        jTextFieldMail.setForeground(new Color(24, 19, 9));
        jTextFieldMail.setMargin(new Insets(5, 5, 5, 5));
        jTextFieldMail.setName("Mail"); // NOI18N
        jTextFieldMail.setSelectionColor(new Color(255, 206, 91));
        jPanelCampos.add(jTextFieldMail);

        jTextFieldTelefono.setFont(new Font("Lato", 0, 20)); // NOI18N
        jTextFieldTelefono.setForeground(new Color(24, 19, 9));
        jTextFieldTelefono.setMargin(new Insets(5, 5, 5, 5));
        jTextFieldTelefono.setName("Teléfono"); // NOI18N
        jTextFieldTelefono.setSelectionColor(new Color(255, 206, 91));
        jPanelCampos.add(jTextFieldTelefono);

        jTextFieldDireccion.setFont(new Font("Lato", 0, 20)); // NOI18N
        jTextFieldDireccion.setForeground(new Color(24, 19, 9));
        jTextFieldDireccion.setMargin(new Insets(5, 5, 5, 5));
        jTextFieldDireccion.setName("Dirección"); // NOI18N
        jTextFieldDireccion.setSelectionColor(new Color(255, 206, 91));
        jPanelCampos.add(jTextFieldDireccion);

        jXDatePickerNacimiento.setForeground(new Color(24, 19, 9));
        jXDatePickerNacimiento.setFont(new Font("Lato", 0, 20)); // NOI18N
        jXDatePickerNacimiento.setName("Fecha de nacimiento"); // NOI18N
        jPanelCampos.add(jXDatePickerNacimiento);

        jComboBoxCategoria.setEditable(true);
        jComboBoxCategoria.setFont(new Font("Lato", 0, 18)); // NOI18N
        jComboBoxCategoria.setToolTipText("");
        jComboBoxCategoria.setName("Categoria"); // NOI18N
        jComboBoxCategoria.setPreferredSize(new Dimension(200, 30));
        jPanelCampos.add(jComboBoxCategoria);

        jPanelEdicion.add(jPanelCampos);

        GroupLayout jPanelContenidoLayout = new GroupLayout(jPanelContenido);
        jPanelContenido.setLayout(jPanelContenidoLayout);
        jPanelContenidoLayout.setHorizontalGroup(
            jPanelContenidoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContenidoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotonera, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFoto, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(394, Short.MAX_VALUE))
            .addGroup(jPanelContenidoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, jPanelContenidoLayout.createSequentialGroup()
                    .addContainerGap(158, Short.MAX_VALUE)
                    .addComponent(jPanelEdicion, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanelContenidoLayout.setVerticalGroup(
            jPanelContenidoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanelContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFoto)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanelBotonera, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelContenidoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, jPanelContenidoLayout.createSequentialGroup()
                    .addContainerGap(12, Short.MAX_VALUE)
                    .addComponent(jPanelEdicion, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        getContentPane().add(jPanelContenido, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prepararSelectorDeFecha() {
        this.jXDatePickerNacimiento.setFormats(new SimpleDateFormat("d 'de' MMMM 'de' yyyy", LOCALE_ES));
    }

    private void prepararListaDeCategorias() {
        Categoria[] categs = new Categoria[Categoria.values().length - 1];
        for (int i = 1; i < Categoria.values().length; i++) {
            categs[i - 1] = Categoria.values()[i];
        }
        this.jComboBoxCategoria.setModel(new DefaultComboBoxModel(categs));
        this.jComboBoxCategoria.setEditable(false);
    }

    public Contacto getContacto() {
        validarDatos();
        String nom = this.jTextFieldNombre.getText();
        String ape = this.jTextFieldApellido.getText();
        String mail = this.jTextFieldMail.getText();
        String dir = this.jTextFieldDireccion.getText();
        String tel = this.jTextFieldTelefono.getText();
        Date nac = new Date(this.jXDatePickerNacimiento.getDate().getTime());
        Categoria cat = (Categoria) this.jComboBoxCategoria.getSelectedItem();
        return new Contacto(0, nom, ape, mail, tel, dir, nac, cat);
    }

    private void validarDatos() {
        Component[] campos = this.jPanelCampos.getComponents();
        Component[] nomApe = ((JPanel) campos[0]).getComponents();
        for (int i = 0; i < this.jPanelNombreApellido.getComponents().length; i++) {
            JTextField tf = (JTextField) this.jPanelNombreApellido.getComponent(i);
            if (tf.getText().isEmpty()) {
                throw new IllegalStateException("El campo \"" + tf.getName() + "\" está vacío");
            }
        }
        for (int i = 1; i < this.jPanelCampos.getComponents().length-2; i++) {
            JTextField tf = (JTextField) campos[i];
            if (tf.getText().isEmpty()) {
                throw new IllegalStateException("El campo \"" + tf.getName() + "\" está vacío");
            }
        }
        if (!esNumeroEntero(this.jTextFieldTelefono.getText())) {
            throw new IllegalStateException("El valor del campo \"" + this.jTextFieldTelefono.getName() + "\" no representa un número de teléfono");
        }
        if (!mailValido(this.jTextFieldMail.getText())) {
            throw new IllegalStateException("El valor del campo \"" + this.jTextFieldMail.getName() + "\" no representa un mail");
        }
        if (this.jXDatePickerNacimiento.getDate() == null) {
            throw new IllegalStateException("El campo \"" + this.jXDatePickerNacimiento.getName() + "\" está vacío");
        }
    }
    
    public final void habilitarCampos (boolean flag) {
        for (int i = 0; i < this.jPanelNombreApellido.getComponents().length; i++) {
            JTextField campo = (JTextField) this.jPanelNombreApellido.getComponent(i);
            campo.setEditable(flag);
        }
        for (int i = 1; i < this.jPanelCampos.getComponents().length-2; i++) {
            JTextField campo = (JTextField) this.jPanelCampos.getComponent(i);
            campo.setEditable(flag);
        }
        this.jXDatePickerNacimiento.setEditable(flag);
        this.jComboBoxCategoria.setEnabled(flag);
    }
    
    private boolean mailValido(String cad) {
        return cad.contains("@") && cad.contains("."); // Habría que validarlo con una Regex (Expresión regular)
    }

    private boolean esNumeroEntero(String cad) {
        boolean esEntero = true;
        try {
            Integer.parseInt(cad);
        } catch (RuntimeException e) {
            esEntero = false;
        }
        return esEntero;
    }

    protected JComboBox<Categoria> getjComboBoxCategoria() {
        return jComboBoxCategoria;
    }

    protected JTextField getjTextFieldApellido() {
        return jTextFieldApellido;
    }

    protected JTextField getjTextFieldDireccion() {
        return jTextFieldDireccion;
    }

    protected JTextField getjTextFieldMail() {
        return jTextFieldMail;
    }

    protected JTextField getjTextFieldNombre() {
        return jTextFieldNombre;
    }

    protected JTextField getjTextFieldTelefono() {
        return jTextFieldTelefono;
    }

    protected JXDatePicker getjXDatePickerNacimiento() {
        return jXDatePickerNacimiento;
    }

    protected JLabel getjLabelIconoCalendario() {
        return jLabelIconoCalendario;
    }   

    protected JPanel getJPanelBotonera() {
        return jPanelBotonera;
    }

    private void manejarAccionCerrar() {
        this.jButtonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButtonCerrar;
    private JComboBox<Categoria> jComboBoxCategoria;
    private JLabel jLabelFoto;
    private JLabel jLabelIconoCalendario;
    private JLabel jLabelIconoCredencial;
    private JLabel jLabelIconoEtiqueta;
    private JLabel jLabelIconoMail;
    private JLabel jLabelIconoMapa;
    private JLabel jLabelIconoTelefono;
    private JPanel jPanelBotonera;
    private JPanel jPanelCampos;
    private JPanel jPanelContenido;
    private JPanel jPanelEdicion;
    private JPanel jPanelIconos;
    private JPanel jPanelNombreApellido;
    private JTextField jTextFieldApellido;
    private JTextField jTextFieldDireccion;
    private JTextField jTextFieldMail;
    private JTextField jTextFieldNombre;
    private JTextField jTextFieldTelefono;
    private JXDatePicker jXDatePickerNacimiento;
    // End of variables declaration//GEN-END:variables
}
