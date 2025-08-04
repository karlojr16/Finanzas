package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.VistaPrincipal;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        VistaPrincipal vista = new VistaPrincipal();
        BorderPane root = vista.getRoot();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Control de Finanzas Personales");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
