package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class VistaPrincipal {
    private BorderPane root;
    private TableView table;
    private Label lblSaldo;
    private ComboBox<String> tipoMovimiento;
    private TextField txtCantidad;
    private ComboBox<String> comboCategoria;
    private TextField txtDescripcion;
    private DatePicker datePicker;
    private Button btnAgregar;

    public VistaPrincipal() {
        root = new BorderPane();
        construirInterfaz();
    }

    public BorderPane getRoot() {
        return root;
    }

    private void construirInterfaz() {
        // Parte superior: formulario
        VBox formulario = new VBox(10);
        formulario.setPadding(new Insets(10));

        tipoMovimiento = new ComboBox<>();
        tipoMovimiento.getItems().addAll("Ingreso", "Egreso");
        tipoMovimiento.setPromptText("Tipo");

        txtCantidad = new TextField();
        txtCantidad.setPromptText("Cantidad");

        comboCategoria = new ComboBox<>();
        comboCategoria.setPromptText("Categoría");

        txtDescripcion = new TextField();
        txtDescripcion.setPromptText("Descripción");

        datePicker = new DatePicker();

        btnAgregar = new Button("Agregar Movimiento");

        formulario.getChildren().addAll(new Text("Registrar Movimiento"),
                tipoMovimiento, txtCantidad, comboCategoria, txtDescripcion, datePicker, btnAgregar);

        // Centro: tabla de movimientos
        table = new TableView<>();
        table.setPlaceholder(new Label("Aún no hay movimientos registrados"));

        // Inferior: resumen y gráfica
        VBox resumen = new VBox(10);
        resumen.setPadding(new Insets(10));
        lblSaldo = new Label("Saldo actual: $0.00");

        PieChart chart = new PieChart(FXCollections.observableArrayList(
                new PieChart.Data("Ejemplo", 100)
        ));

        resumen.getChildren().addAll(lblSaldo, chart);

        // BorderPane: colocamos las secciones
        root.setTop(formulario);
        root.setCenter(table);
        root.setBottom(resumen);
    }
}
