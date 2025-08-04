package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import model.Categoria;
import model.Movimiento;

import java.time.LocalDate;

public class Controller {

    // Campos de FXML
    @FXML private ComboBox<String> comboTipo;
    @FXML private ComboBox<String> comboCategoria;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtDescripcion;
    @FXML private DatePicker datePicker;
    @FXML private Button btnAgregar;

    @FXML private TableView<Movimiento> tablaMovimientos;
    @FXML private TableColumn<Movimiento, String> colTipo;
    @FXML private TableColumn<Movimiento, Double> colCantidad;
    @FXML private TableColumn<Movimiento, String> colCategoria;
    @FXML private TableColumn<Movimiento, String> colDescripcion;
    @FXML private TableColumn<Movimiento, LocalDate> colFecha;

    @FXML private Label lblSaldo;
    @FXML private PieChart graficaCategorias;

    private final ObservableList<Movimiento> listaMovimientos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializar ComboBox tipo
        comboTipo.setItems(FXCollections.observableArrayList("Ingreso", "Egreso"));

        // Inicializar ComboBox categorías
        comboCategoria.setItems(FXCollections.observableArrayList("Comida", "Transporte", "Salud", "Entretenimiento", "Educacion", "Otros"));

        // Inicializar columnas de tabla
        colTipo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTipo()));
        colCantidad.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getCantidad()).asObject());
        colCategoria.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategoria().getNombre()));
        colDescripcion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFecha()));

        tablaMovimientos.setItems(listaMovimientos);

        // Botón agregar
        btnAgregar.setOnAction(e -> agregarMovimiento());
    }

    private void agregarMovimiento() {
        try {
            String tipo = comboTipo.getValue();
            String categoriaNombre = comboCategoria.getValue();
            String descripcion = txtDescripcion.getText();
            double cantidad = Double.parseDouble(txtCantidad.getText());
            LocalDate fecha = datePicker.getValue();

            // Validaciones básicas
            if (tipo == null || categoriaNombre == null || descripcion.isEmpty() || fecha == null) {
                mostrarAlerta("Por favor completa todos los campos.");
                return;
            }

            if (cantidad <= 0) {
                mostrarAlerta("La cantidad debe ser mayor a cero.");
                return;
            }

            Categoria categoria = new Categoria(categoriaNombre);
            Movimiento nuevo = new Movimiento(tipo, cantidad, categoria, descripcion, fecha);
            listaMovimientos.add(nuevo);

            actualizarSaldo();
            limpiarFormulario();

        } catch (NumberFormatException e) {
            mostrarAlerta("El campo 'Cantidad' debe ser un número válido.");
        }
    }

    private void actualizarSaldo() {
        double saldo = 0.0;
        for (Movimiento m : listaMovimientos) {
            saldo += m.getTipo().equals("Ingreso") ? m.getCantidad() : -m.getCantidad();
        }
        lblSaldo.setText("Saldo actual: $" + String.format("%.2f", saldo));
    }

    private void limpiarFormulario() {
        comboTipo.setValue(null);
        comboCategoria.setValue(null);
        txtCantidad.clear();
        txtDescripcion.clear();
        datePicker.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
