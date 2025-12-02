package agenda.controller;

import agenda.dao.DAO;
import agenda.dao.DAOImpl;
import agenda.model.Contacto;
import agenda.view.JFrameVistaFormularioAgregar;
import agenda.view.JFrameVistaFormularioVer;
import agenda.view.JFrameVistaPrincipal;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.Collection;

public class MainController {

    private JFrameVistaPrincipal vPrincipal;
    private JFrameVistaFormularioAgregar vFormAgregar;
    private JFrameVistaFormularioVer vFormVer;
    private DAO dao;

    public MainController() {
        try {
            this.vPrincipal = new JFrameVistaPrincipal("Agenda SQLite");

            // Vincular escuchadores de acción en componentes gráficos
            this.vPrincipal.manejarAccionAgregar(new HandlerAgregarContactoVP());
            this.vPrincipal.manejarAccionConectar(new HandlerConectarVP());
            this.vPrincipal.manejarAccionVaciar(new HandlerVaciarVP());
            this.vPrincipal.manejarClickEnTabla(new HandlerClickTablaVP());
            this.vPrincipal.manejarCambioItemFiltrar(new HandlerFiltrarVP());

            // Control del cierre de conexión a BD
            this.vPrincipal.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        if (dao != null)
                            ((DAOImpl) dao).close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.exit(0);
                }
            });

            // Visible ventana
            this.vPrincipal.setVisible(true);
        } catch (Exception e) {
            tratarExcepcion(e);
        }
    }

    private void tratarExcepcion(Exception ex) {
        String causeMessage = ex.getCause() == null ? "" : "\n" + ex.getCause().getMessage();
        vPrincipal.mostrarCartelDeError(ex.getMessage() + "\n" + causeMessage);
    }

    private void listarContactosEnVista() {
        try {
            Collection<Contacto> contactos = dao.obtenerContactos(vPrincipal.obtenerCategoriaSeleccionada());
            vPrincipal.listarContactos(contactos);
        } catch (Exception ex) {
            tratarExcepcion(ex);
        }
    }

    private void agregarContacto() {
        try {
            Contacto co = vFormAgregar.getContacto();
            dao.agregarContacto(co);
            listarContactosEnVista();
            vFormAgregar.cerrarVentana();
            vPrincipal.mostrarCartelDeInfo("Se agregó a " + co.nombreCompleto() + " a la agenda.");
        } catch (Exception ex) {
            tratarExcepcion(ex);
        }
    }

    private void actualizarContacto() {
        try {
            Contacto co = vFormVer.getContacto();
            dao.actualizarContacto(co);
        } catch (Exception ex) {
            tratarExcepcion(ex);
        }
    }

    private void borrarContacto() {
        Contacto co = vFormVer.getContacto();
        boolean confirma = vPrincipal.confirmar("¿Estás segur@ de borrar a " + co.nombreCompleto() + "?");
        if (confirma) {
            try {
                dao.borrarContacto(co.getId());
                listarContactosEnVista();
                vFormVer.cerrarVentana();
                vPrincipal.mostrarCartelDeInfo("Se borró a " + co.nombreCompleto() + " de la agenda.");
            } catch (Exception ex) {
                tratarExcepcion(ex);
            }
        }
    }

    private void vaciarAgenda() {
        boolean confirma = vPrincipal.confirmar("¿Estás segur@ de vaciar la agenda?");
        if (confirma) {
            try {
                dao.vaciarAgenda();
                listarContactosEnVista();
                vPrincipal.mostrarCartelDeInfo("Agenda vacía.");
            } catch (Exception ex) {
                tratarExcepcion(ex);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////// HANDLERS PARA CADA ACCION /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// VISTA PRINCIPAL ///////////////////////////////////////
    private class HandlerConectarVP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                dao = new DAOImpl(); // Crea objeto DAO de nueva conexión a la BD
                vPrincipal.actualizarEstado(true);
                listarContactosEnVista();
            } catch (Exception ex) {
                vPrincipal.mostrarCartelDeError("NO CONECTADO");
                vPrincipal.actualizarEstado(false);
            }
        }
    }

    private class HandlerAgregarContactoVP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            vFormAgregar = new JFrameVistaFormularioAgregar("Agregar contacto", vPrincipal);
            vFormAgregar.manejarAccionAgregar(new HandlerAgregarContactoVC());
            vFormAgregar.setVisible(true);
        }
    }

    private class HandlerVaciarVP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            vaciarAgenda();
        }
    }

    private class HandlerFiltrarVP implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                listarContactosEnVista();
            }
        }
    }

    private class HandlerClickTablaVP implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                try {
                    Contacto co = dao.obtenerContacto(vPrincipal.obtenerIDSeleccionado());
                    vFormVer = new JFrameVistaFormularioVer("Contacto " + co.nombreCompleto(), vPrincipal, co);
                    vFormVer.manejarAccionEditar(new HandlerEditarContactoVC());
                    vFormVer.manejarAccionBorrar(new HandlerBorrarContactoVC());
                    vFormVer.setVisible(true);
                } catch (Exception ex) {
                    tratarExcepcion(ex);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    //////////////////// VISTA DE LOGIN ////////////////////////////////////////
    private class HandlerConectarVA implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                vPrincipal.actualizarEstado(true);
                listarContactosEnVista();
            } catch (Exception ex) {
                tratarExcepcion(ex);
            }
        }
    }

    //////////////////// VISTA DE CONTACTO /////////////////////////////////////
    private class HandlerEditarContactoVC implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                vFormVer.habilitarCampos(true);
            } else {
                vFormVer.habilitarCampos(false);
                actualizarContacto();
                listarContactosEnVista();
            }
        }
    }

    private class HandlerBorrarContactoVC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            borrarContacto();
            listarContactosEnVista();

        }
    }

    private class HandlerAgregarContactoVC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            agregarContacto();
        }
    }

}
