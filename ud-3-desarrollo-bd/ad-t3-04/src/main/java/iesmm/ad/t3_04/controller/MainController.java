package iesmm.ad.t3_04.controller;

import iesmm.ad.t3_04.dao.DAO;
import iesmm.ad.t3_04.dao.DAOImpl;
import iesmm.ad.t3_04.model.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private DAO dao;

    @FXML
    private TextField idField;

    @FXML
    private TextField tituloField;

    @FXML
    private TextField autorField;

    @FXML
    private TextField anioField;

    @FXML
    private TextField paginasField;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Libro> TableView;

    @FXML
    private TableColumn<Libro, Integer> idColumn;

    @FXML
    private TableColumn<Libro, String> tituloColumn;

    @FXML
    private TableColumn<Libro, String> autorColumn;

    @FXML
    private TableColumn<Libro, String> anioColumn;

    @FXML
    private TableColumn<Libro, Integer> paginasColumn;

    @FXML
    private void insertButton() {
        try {
            // Obtención del valor de campos del formulario
            int id = Integer.valueOf(idField.getText());
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            int anio = Integer.valueOf(anioField.getText());
            int paginas = Integer.valueOf(paginasField.getText());

            // Ejecución operación de actualización
            if (dao.insertar(new Libro(id, titulo, autor, anio, paginas)))
                showBooks(); // Mostrar lista actualizada
            else
                new Alert(Alert.AlertType.WARNING, "NO INSERTADO LIBRO").showAndWait();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "FORMATO INCORRECTO").showAndWait();
        }
    }


    @FXML
    private void updateButton() {
        try {
            // Obtención del valor de campos del formulario
            int id = Integer.valueOf(idField.getText());
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            int anio = Integer.valueOf(anioField.getText());
            int paginas = Integer.valueOf(paginasField.getText());

            // Ejecución operación de actualización
            if (dao.actualizar(new Libro(id, titulo, autor, anio, paginas)))
                showBooks(); // Mostrar lista actualizada
            else
                new Alert(Alert.AlertType.WARNING, "NO ACTUALIZADO LIBRO").showAndWait();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "FORMATO INCORRECTO").showAndWait();
        }
    }

    @FXML
    private void deleteButton() {
        String id = idField.getText().trim(); // Eliminar espacios iniciales y finales

        if (!id.isEmpty() && id.matches("\\d+")) { // Comprobar entero
            if (dao.eliminar(Integer.valueOf(id)))
                showBooks(); // Mostrar lista actualizada
            else
                new Alert(Alert.AlertType.WARNING, "NO EXISTE LIBRO").showAndWait();
        } else
            new Alert(Alert.AlertType.ERROR, "FORMATO INCORRECTO").showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dao = new DAOImpl();
        showBooks();
    }

    public void showBooks() {
        // Obtener lista de items
        ObservableList<Libro> list = FXCollections.observableArrayList(dao.getAll());

        // Vincular definición de propiedades del objeto a la celda del grid
        idColumn.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("id"));
        tituloColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
        anioColumn.setCellValueFactory(new PropertyValueFactory<Libro, String>("anio"));
        paginasColumn.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("paginas"));

        // Vincular lista de items del objeto
        TableView.setItems(list);
    }
}
