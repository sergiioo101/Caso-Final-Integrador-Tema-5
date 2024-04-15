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
        // Lógica para abrir la interfaz de gestión de documentos
        System.out.println("Gestión de Documentos activada...");
    }

    private void gestionarFechas() {
        // Lógica para abrir la interfaz de gestión de fechas
        System.out.println("Gestión de Fechas activada...");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

