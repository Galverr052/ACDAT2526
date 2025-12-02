package agenda.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JFrameVistaFormularioAgregar extends JFrameVistaFormulario {

    private JButton jButtonAgregar;

    public JFrameVistaFormularioAgregar(String titulo, Component parent) {
        super(titulo, parent);
        initComponents();
    }

    public JFrameVistaFormularioAgregar(String titulo) {
        this(titulo, null);
    }

    public final void initComponents() {
        this.jButtonAgregar = new JButton();
        jButtonAgregar.setBackground(new Color(238, 245, 247));
        jButtonAgregar.setFont(new Font("Lato", 0, 16)); // NOI18N
        jButtonAgregar.setForeground(new Color(24, 19, 9));
        cargarIcono(jButtonAgregar, "add-user-icon.png");
        jButtonAgregar.setText("Agregar");
        jButtonAgregar.setHorizontalAlignment(SwingConstants.LEFT);
        jButtonAgregar.setHorizontalTextPosition(SwingConstants.RIGHT);
        jButtonAgregar.setIconTextGap(20);
        jButtonAgregar.setPreferredSize(new Dimension(400, 53));
        getJPanelBotonera().add(jButtonAgregar);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////// MANEJADORES PARA CADA ACCION //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////  
    public void manejarAccionAgregar(ActionListener al) {
        this.jButtonAgregar.addActionListener(al);
    }

}
