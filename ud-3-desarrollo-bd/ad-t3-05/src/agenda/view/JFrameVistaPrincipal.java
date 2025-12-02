
package agenda.view;

import agenda.dao.DAO;
import agenda.model.Categoria;
import agenda.model.Contacto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Collection;

public class JFrameVistaPrincipal extends JFrameTemplate {

    public JFrameVistaPrincipal(String titulo) {
        this(titulo, null);
    }

    public JFrameVistaPrincipal(String titulo, Component parent) {
        super(titulo, parent);
        initComponents();
        cargarIconos();
        ocultarColumnaID();
        actualizarEstado(false);
    }

    private void cargarIconos() {
        cargarIcono(jLabelCategoria, "tag-icon.png");
        cargarIcono(jButtonConectar, "broken-link.png");
        cargarIcono(jButtonVaciar, "trash-icon.png");
        cargarIcono(jButtonAgregar, "add-user-icon.png");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCabecera = new JPanel();
        jPanelFiltro = new JPanel();
        jLabelCategoria = new JLabel();
        jComboBoxFiltroCategoria = new JComboBox<>();
        jLabelLeyenda = new JLabel();
        jLabelEstado = new JLabel();
        jPanelBotonera = new JPanel();
        jButtonConectar = new JButton();
        jButtonVaciar = new JButton();
        jButtonAgregar = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(217, 200, 184));
        setResizable(false);
        getContentPane().setLayout(new BorderLayout(10, 10));

        jPanelCabecera.setPreferredSize(new Dimension(850, 80));
        jPanelCabecera.setLayout(new BorderLayout());

        jPanelFiltro.setPreferredSize(new Dimension(400, 50));
        jPanelFiltro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        jLabelCategoria.setFont(new Font("Lato", 0, 18)); // NOI18N
        jLabelCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelCategoria.setText("Categoría:");
        jLabelCategoria.setIconTextGap(10);
        jPanelFiltro.add(jLabelCategoria);

        jComboBoxFiltroCategoria.setFont(new Font("Lato", 0, 18)); // NOI18N
        jComboBoxFiltroCategoria.setToolTipText("");
        jComboBoxFiltroCategoria.setEnabled(false);
        jComboBoxFiltroCategoria.setPreferredSize(new Dimension(200, 30));
        jPanelFiltro.add(jComboBoxFiltroCategoria);

        jLabelLeyenda.setFont(new Font("Lato", 0, 14)); // NOI18N
        jLabelLeyenda.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelLeyenda.setText("DOBLE CLICK  en un contacto para más datos.");
        jLabelLeyenda.setPreferredSize(new Dimension(400, 20));
        jLabelLeyenda.setVerticalTextPosition(SwingConstants.BOTTOM);
        jPanelFiltro.add(jLabelLeyenda);

        jPanelCabecera.add(jPanelFiltro, BorderLayout.WEST);

        jLabelEstado.setFont(new Font("Lato Black", 1, 18)); // NOI18N
        jLabelEstado.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelEstado.setText("status");
        jPanelCabecera.add(jLabelEstado, BorderLayout.CENTER);

        jPanelBotonera.setPreferredSize(new Dimension(265, 40));
        jPanelBotonera.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 18));

        jButtonConectar.setBackground(new Color(238, 245, 247));
        jButtonConectar.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jButtonConectar.setForeground(new Color(255, 250, 240));
        jButtonConectar.setHorizontalTextPosition(SwingConstants.RIGHT);
        jButtonConectar.setIconTextGap(20);
        jButtonConectar.setMaximumSize(new Dimension(60, 40));
        jButtonConectar.setMinimumSize(new Dimension(40, 40));
        jButtonConectar.setPreferredSize(new Dimension(60, 40));
        jPanelBotonera.add(jButtonConectar);

        jButtonVaciar.setBackground(new Color(238, 245, 247));
        jButtonVaciar.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jButtonVaciar.setForeground(new Color(255, 250, 240));
        jButtonVaciar.setEnabled(false);
        jButtonVaciar.setHorizontalTextPosition(SwingConstants.RIGHT);
        jButtonVaciar.setIconTextGap(20);
        jButtonVaciar.setMaximumSize(new Dimension(60, 40));
        jButtonVaciar.setMinimumSize(new Dimension(40, 40));
        jButtonVaciar.setPreferredSize(new Dimension(60, 40));
        jPanelBotonera.add(jButtonVaciar);

        jButtonAgregar.setBackground(new Color(238, 245, 247));
        jButtonAgregar.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jButtonAgregar.setForeground(new Color(255, 250, 240));
        jButtonAgregar.setEnabled(false);
        jButtonAgregar.setHorizontalTextPosition(SwingConstants.RIGHT);
        jButtonAgregar.setIconTextGap(20);
        jButtonAgregar.setMaximumSize(new Dimension(60, 40));
        jButtonAgregar.setMinimumSize(new Dimension(40, 40));
        jButtonAgregar.setPreferredSize(new Dimension(60, 40));
        jPanelBotonera.add(jButtonAgregar);

        jPanelCabecera.add(jPanelBotonera, BorderLayout.EAST);

        getContentPane().add(jPanelCabecera, BorderLayout.NORTH);

        jTable1.setBackground(new Color(238, 245, 247));
        jTable1.setFont(new Font("Lato", 0, 18)); // NOI18N
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido", "Mail"
            }
        ) {
            Class[] types = new Class [] {
                Integer.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setDragEnabled(true);
        jTable1.setGridColor(new Color(36, 123, 160));
        jTable1.setIntercellSpacing(new Dimension(0, 3));
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new Color(75, 147, 177));
        jTable1.setSelectionForeground(new Color(238, 245, 247));
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        }

        getContentPane().add(jScrollPane1, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public boolean confirmar(String mensaje) {
        int res = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar...", JOptionPane.YES_NO_OPTION);
        return res == 0;
    }

    public void listarContactos(Collection<Contacto> contactos) {
        limpiarFilas();
        for (Contacto c : contactos) {
            agregarFila(c.getId(), c.getNombre(), c.getApellido(), c.getMail());
        }
    }

    private void agregarFila(int id, String nombre, String apellido, String mail) {
        DefaultTableModel dtf = (DefaultTableModel) this.jTable1.getModel();
        dtf.addRow(new Object[]{id, nombre, apellido, mail});
    }

    private void limpiarFilas() {
        DefaultTableModel dtf = (DefaultTableModel) this.jTable1.getModel();
        while (dtf.getRowCount() > 0) {
            dtf.removeRow(0);
        }
    }

    private void cargarListaDeFiltros() {
        this.jComboBoxFiltroCategoria.setModel(new DefaultComboBoxModel(Categoria.values()));
    }

    private void ocultarColumnaID() {
        this.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        this.jTable1.getColumnModel().getColumn(0).setMinWidth(0);
    }

    public int obtenerIDSeleccionado() {
        int nroFila = this.jTable1.getSelectedRow();
        return (int) this.jTable1.getValueAt(nroFila, 0);
    }

    public final void actualizarEstado(boolean estaConectado) {
        this.jLabelLeyenda.setVisible(estaConectado);
        activarControles(estaConectado);
        this.jButtonConectar.setEnabled(!estaConectado);
        if (estaConectado) {
            cargarListaDeFiltros();
            this.jLabelEstado.setText("CONECTADO");
            this.jLabelEstado.setForeground(new Color(55, 184, 88));
        } else {
            this.jLabelEstado.setText("NO CONECTADO");
            this.jLabelEstado.setForeground(new Color(255, 87, 71));
        }
    }

    private void activarControles(boolean flag) {
        for (int i = 1; i < this.jPanelBotonera.getComponents().length; i++) {
            this.jPanelBotonera.getComponent(i).setEnabled(flag);
        }
        this.jComboBoxFiltroCategoria.setEnabled(flag);
    }

    public Categoria obtenerCategoriaSeleccionada() {
        return (Categoria) this.jComboBoxFiltroCategoria.getSelectedItem();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////// MANEJADORES PARA CADA ACCION //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////    
    public void manejarAccionConectar(ActionListener al) {
        this.jButtonConectar.addActionListener(al);
    }

    public void manejarAccionAgregar(ActionListener al) {
        this.jButtonAgregar.addActionListener(al);
    }

    public void manejarAccionVaciar(ActionListener al) {
        this.jButtonVaciar.addActionListener(al);
    }

    public void manejarCambioItemFiltrar(ItemListener il) {
        this.jComboBoxFiltroCategoria.addItemListener(il);
    }

    public void manejarClickEnTabla(MouseListener ml) {
        this.jTable1.addMouseListener(ml);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButtonAgregar;
    private JButton jButtonConectar;
    private JButton jButtonVaciar;
    private JComboBox<Categoria> jComboBoxFiltroCategoria;
    private JLabel jLabelCategoria;
    private JLabel jLabelEstado;
    private JLabel jLabelLeyenda;
    private JPanel jPanelBotonera;
    private JPanel jPanelCabecera;
    private JPanel jPanelFiltro;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
