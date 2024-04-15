package interfaz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AplicacionPrincipal extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema Interactivo de Análisis Genómico y Organización de Datos");

        // Creación de botones para las distintas funcionalidades
        Button btnAnalisisGenomico = new Button("Análisis Genómico");
        btnAnalisisGenomico.setOnAction(e -> realizarAnalisisGenomico());

        Button btnGestionDocumentos = new Button("Gestión de Documentos");
        btnGestionDocumentos.setOnAction(e -> gestionarDocumentos());

        Button btnGestionFechas = new Button("Gestión de Fechas");
        btnGestionFechas.setOnAction(e -> gestionarFechas());

        // Organización de los botones en la interfaz
        VBox layout = new VBox(10);
        layout.getChildren().addAll(btnAnalisisGenomico, btnGestionDocumentos, btnGestionFechas);

        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void realizarAnalisisGenomico() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.getChildren().add(new Label("Análisis Genómico"));
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("Análisis Genómico");
        stage.setScene(scene);
        stage.show();
    }

    private void gestionarDocumentos() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.getChildren().add(new Label("Gestión de Documentos"));
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("Gestión de Documentos");
        stage.setScene(scene);
        stage.show();
    }

    private void gestionarFechas() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        DatePicker datePicker = new DatePicker();
        Button addDate = new Button("Agregar Fecha");
        Label feedback = new Label();

        addDate.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            if (date != null) {
                feedback.setText("Fecha " + date.toString() + " agregada.");
                // Aquí se agregaría la fecha al sistema de gestión de fechas
            } else {
                feedback.setText("Seleccione una fecha válida.");
            }
        });

        layout.getChildren().addAll(new Label("Gestión de Fechas"), datePicker, addDate, feedback);
        Scene scene = new Scene(layout, 300, 250);
        stage.setTitle("Gestión de Fechas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

