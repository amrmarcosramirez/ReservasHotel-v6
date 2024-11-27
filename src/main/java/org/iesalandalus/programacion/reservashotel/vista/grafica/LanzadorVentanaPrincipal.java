package org.iesalandalus.programacion.reservashotel.vista.grafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.reservashotel.MainApp;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;

import java.net.URL;
import java.nio.file.Paths;

public class LanzadorVentanaPrincipal extends Application {

    public static void comenzar() {
        launch(LanzadorVentanaPrincipal.class);
        //MainApp.main();
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("/vistas/VentanaPrincipal.fxml"));
            //FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("/org.iesalandalus.programacion.reservashotel.vista.grafica.recursos/vistas/VentanaPrincipal.fxml"));
            //Parent raiz = fxmlLoader.load();

            URL url = Paths.get("src/main/resources/org.iesalandalus.programacion.reservashotel.vista.grafica.recursos/vistas/VentanaPrincipal.fxml").toUri().toURL();
            Parent raiz = FXMLLoader.load(url);
            Scene escena = new Scene(raiz);
            escenarioPrincipal.setTitle("Gestión de Hoteles IES Al-Ándalus");
            escenarioPrincipal.setOnCloseRequest(e -> confirmarSalida(escenarioPrincipal, e));
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
        if (Dialogos.mostrarDialogoConfirmacion("Salir",
                "¿Estás seguro de que quieres salir de la aplicación?",
                escenarioPrincipal)) {
            escenarioPrincipal.close();
        }
        else {
            e.consume();
        }
    }

}
