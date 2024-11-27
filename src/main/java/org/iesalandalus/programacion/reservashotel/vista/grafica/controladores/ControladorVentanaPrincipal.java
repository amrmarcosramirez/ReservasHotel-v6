package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.vista.grafica.LanzadorVentanaPrincipal;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class ControladorVentanaPrincipal {

    //private VistaGrafica vistaGrafica;


    /*
    private IControlador controladorMVC;

    public void setControladorMVC(IControlador controladorMVC) {
        this.controladorMVC = controladorMVC;
    }
*/
    @FXML Button btAnadirHuesped;
    @FXML Button btListarHuespedes;
    @FXML Button btBuscarHuesped;
    @FXML Button btAnadirHabitacion;
    @FXML Button btListarHabitaciones;
    @FXML Button btBuscarHabitacion;
    @FXML Button btAnadirReserva;
    @FXML Button btListarReservas;
    @FXML Button btBuscarReserva;
    @FXML MenuItem btSalir;
    private Stage anadirHuesped;
    private ControladorAnadirHuesped cAnadirHuesped;
    private Stage listarHuespedes;
    private ControladorListarHuespedes cListarHuespedes;
    private Stage mostrarHuesped;
    private ControladorMostrarHuesped cMostrarHuesped;
    private Stage anadirHabitacion;
    private ControladorAnadirHabitacion cAnadirHabitacion;
    private Stage listarHabitaciones;
    private ControladorListarHabitaciones cListarHabitaciones;
    private Stage mostrarHabitacion;
    private ControladorMostrarHabitacion cMostrarHabitacion;
    private Stage anadirReserva;
    private ControladorAnadirReserva cAnadirReserva;
    private Stage listarReservas;
    private ControladorListarReservas cListarReservas;
    private Stage buscarReserva;
    private ControladorBuscarReserva cBuscarReserva;
    private Stage mostrarReserva;
    private ControladorMostrarReserva cMostrarReserva;

    @FXML
    private void salir() {
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", null)) {

            VistaGrafica.getInstancia().terminar();
                    System.exit(0);
        }
    }

   /* @FXML
    private void acercaDe() throws IOException {
        VBox contenido = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/AcercaDe.fxml"));
        Dialogos.mostrarDialogoInformacionPersonalizado("Reservas Aulas", contenido);
    }*/

    @FXML
    private void anadirHuesped() throws IOException {
        crearAnadirHuesped();
        anadirHuesped.showAndWait();
    }

    @FXML
    private void listarHuespedes() throws IOException {
        crearListarHuespedes();
        listarHuespedes.showAndWait();
    }

    @FXML
    private void buscarHuesped() throws IOException {
        crearbuscarHuesped();
        mostrarHuesped.showAndWait();;
    }


    private void crearbuscarHuesped() throws IOException {

        String dni = Dialogos.mostrarDialogoTexto("Buscar Huésped", "Dni:");
        if (dni != null) {
            try {
            String nombre = "Huésped Ficticio";
            String telefono = "666666666";
            String correo = "HuspedFicticio@gmail.com";
            LocalDate fechaNacimiento = LocalDate.now();

                Huesped huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
                huesped = VistaGrafica.getInstancia().getControlador().buscar(huesped);
                //huesped = controladorMVC.buscar(huesped.getProfesorFicticio(correo));
                if (huesped != null) {
                    crearMostrarHuesped(huesped);
                    //mostrarHuesped.showAndWait();
                } else {
                    Dialogos.mostrarDialogoError("Huésped no encontrado", "No existe ningún huésped con ese dni");
                }
            } catch (Exception e) {
                Dialogos.mostrarDialogoError("Dni no válido", e.getMessage());
            }
        }

    }

 /*   @FXML
    private void anadirHabitacion() throws IOException {
        crearAnadirHabitacion();
        anadirHabitacion.showAndWait();
    }

*/
    @FXML
    private void listarHabitaciones() throws IOException {
        crearListarHabitaciones();
        listarHabitaciones.showAndWait();
    }

    /*
    @FXML
    private void buscarHabitacion() {

        Simple habitacion;
        int planta;
        int puerta;
        double precio = 70.0;

        do {
            planta = Integer.parseInt(Objects.requireNonNull(Dialogos.mostrarDialogoTexto("Buscar habitación,",
                    "Introduce la planta:")));
            //System.out.print("Introduce la planta de la habitación: ");
            //planta = Entrada.entero();
        } while (planta < Habitacion.MIN_NUMERO_PLANTA ||
                planta > Habitacion.MAX_NUMERO_PLANTA);

        do {
            puerta = Integer.parseInt(Objects.requireNonNull(Dialogos.mostrarDialogoTexto("Buscar habitación",
                    "Introduce la puerta:")));
            //System.out.print("Introduce la puerta de la habitación: ");
            //puerta = Entrada.entero();
        }  while (puerta < Habitacion.MIN_NUMERO_PUERTA ||
                puerta > Habitacion.MAX_NUMERO_PUERTA);

        habitacion = new Simple(planta, puerta, precio);

        //if (nombre != null) {
        //    Habitacion habitacion = null;
            try {
                Habitacion habitacion1 = VistaGrafica.getInstancia().getControlador().buscar(habitacion);
                //aula = controladorMVC.buscar(Aula.getAulaFicticia(nombre));
                if (habitacion1 != null) {
                    crearMostrarHabitacion(habitacion1);
                    mostrarHabitacion.showAndWait();
                } else {
                    Dialogos.mostrarDialogoError("Habitación no encontrada", "No existe ninguna habitación con ese identificador");
                }
            } catch (Exception e) {
                Dialogos.mostrarDialogoError("Identificador no válido", e.getMessage());
            }
        //}
    }

    @FXML
    private void anadirReserva() throws IOException {
        crearAnadirReserva();
        anadirReserva.showAndWait();
    }

    @FXML
    private void listarReservas() throws IOException {
        crearListarReservas();
        listarReservas.showAndWait();
    }

    @FXML
    private void buscarReserva() throws IOException {
        crearBuscarReserva();
        buscarReserva.showAndWait();
        Reserva reserva = cBuscarReserva.getReserva();
        if (reserva != null) {
            crearMostrarReserva(reserva);
            mostrarReserva.showAndWait();
        } else {
            Dialogos.mostrarDialogoError("Reserva no encontrada", "No existe ninguna reserva para ese aula y esa permanencia");
        }
    }
*/
    private void crearAnadirHuesped() throws IOException {
        if (anadirHuesped == null) {

            //FXMLLoader cargadorAnadirHuesped = new FXMLLoader(LocalizadorRecursos.class.getResource("/vistas/AnadirHuesped.fxml"));
            FXMLLoader cargadorAnadirHuesped = new FXMLLoader(LocalizadorRecursos.class.getResource("/org.iesalandalus.programacion.reservashotel.vista.grafica.recursos/vistas/AnadirHuesped.fxml"));
            Parent raizAnadirHuesped = cargadorAnadirHuesped.load();
            Scene escenaAnadirHuesped = new Scene(raizAnadirHuesped);

            cAnadirHuesped = cargadorAnadirHuesped.getController();
            //cAnadirHuesped.setControladorMVC(controladorMVC);
            cAnadirHuesped.inicializa();


            anadirHuesped = new Stage();
            anadirHuesped.setTitle("Añadir Huésped");
            anadirHuesped.initModality(Modality.APPLICATION_MODAL);
            anadirHuesped.setScene(escenaAnadirHuesped);
        } else {
            cAnadirHuesped.inicializa();
        }
    }

    private void crearListarHuespedes() throws IOException {
        if (listarHuespedes == null) {
            listarHuespedes = new Stage();
           //FXMLLoader cargadorListarHuespedes = new FXMLLoader(LocalizadorRecursos.class.getResource("/vistas/ListarHuespedes.fxml"));
            FXMLLoader cargadorListarHuespedes = new FXMLLoader(LocalizadorRecursos.class.getResource("/org.iesalandalus.programacion.reservashotel.vista.grafica.recursos/vistas/ListarHuespedes.fxml"));
            Parent raizListarHuespedes = cargadorListarHuespedes.load();
            cListarHuespedes = cargadorListarHuespedes.getController();
            //cListarHuespedes.setControladorMVC(controladorMVC);
            cListarHuespedes.initialize();
            Scene escenaListarHuespedes = new Scene(raizListarHuespedes);
            listarHuespedes.setTitle("Listar Huéspedes");
            listarHuespedes.initModality(Modality.APPLICATION_MODAL);
            listarHuespedes.setScene(escenaListarHuespedes);
            listarHuespedes.show();
        } else {
            cListarHuespedes.inicializa();
        }
    }

    private void crearMostrarHuesped(Huesped huesped) throws IOException {
        if (mostrarHuesped == null) {
            mostrarHuesped = new Stage();
            //FXMLLoader cargadorMostrarHuesped = new FXMLLoader(LocalizadorRecursos.class.getResource("/vistas/MostrarHuesped.fxml"));
            FXMLLoader cargadorMostrarHuesped = new FXMLLoader(LocalizadorRecursos.class.getResource("/org.iesalandalus.programacion.reservashotel.vista.grafica.recursos/vistas/MostrarHuesped.fxml"));
            VBox raizMostrarHuesped = cargadorMostrarHuesped.load();
            cMostrarHuesped = cargadorMostrarHuesped.getController();
            cMostrarHuesped.setHuesped(huesped);
            Scene escenaMostrarHuesped = new Scene(raizMostrarHuesped);
            mostrarHuesped.setTitle("Mostrar Huésped");
            mostrarHuesped.initModality(Modality.APPLICATION_MODAL);
            mostrarHuesped.setScene(escenaMostrarHuesped);
        } else {
            cMostrarHuesped.setHuesped(huesped);
        }
    }

/*
    private void crearAnadirHabitacion() throws IOException {
        if (anadirHabitacion == null) {
            anadirHabitacion = new Stage();
            FXMLLoader cargadorAnadirHabitacion = new FXMLLoader(
                    LocalizadorRecursos.class.getResource("vistas/AnadirHabitacion.fxml"));
            VBox raizAnadirAula = cargadorAnadirHabitacion.load();
            cAnadirHabitacion = cargadorAnadirHabitacion.getController();
            //cAnadirHabitacion.setControladorMVC(controladorMVC);
            cAnadirHabitacion.inicializa();
            Scene escenaAnadirHabitacion = new Scene(raizAnadirAula);
            anadirHabitacion.setTitle("Añadir Habitación");
            anadirHabitacion.initModality(Modality.APPLICATION_MODAL);
            anadirHabitacion.setScene(escenaAnadirHabitacion);
        } else {
            cAnadirHabitacion.inicializa();
        }
    }
*/
    private void crearListarHabitaciones() throws IOException {
        if (listarHabitaciones == null) {
            listarHabitaciones = new Stage();
            //FXMLLoader cargadorListarHabitaciones = new FXMLLoader(LocalizadorRecursos.class.getResource("/vistas/ListarHabitaciones.fxml"));
            FXMLLoader cargadorListarHabitaciones = new FXMLLoader(LocalizadorRecursos.class.getResource("/org.iesalandalus.programacion.reservashotel.vista.grafica.recursos/vistas/ListarHabitaciones.fxml"));
            VBox raizListarHabitaciones = cargadorListarHabitaciones.load();
            cListarHabitaciones = cargadorListarHabitaciones.getController();
            //cListarAulas.setControladorMVC(controladorMVC);
            cListarHabitaciones.inicializa();
            Scene escenaListarHabitaciones = new Scene(raizListarHabitaciones);
            listarHabitaciones.setTitle("Listar Habitaciones");
            listarHabitaciones.initModality(Modality.APPLICATION_MODAL);
            listarHabitaciones.setScene(escenaListarHabitaciones);
        } else {
            cListarHabitaciones.inicializa();
        }
    }
/*
    private void crearMostrarHabitacion(Habitacion habitacion) throws IOException {
        if (mostrarHabitacion == null) {
            mostrarHabitacion = new Stage();
            FXMLLoader cargadorMostrarHabitacion = new FXMLLoader(
                    LocalizadorRecursos.class.getResource("vistas/MostrarHabitacion.fxml"));
            VBox raizMostrarAula = cargadorMostrarHabitacion.load();
            cMostrarHabitacion = cargadorMostrarHabitacion.getController();
            //cMostrarHabitacion.setControladorMVC(controladorMVC);
            cMostrarHabitacion.setAula(habitacion);
            Scene escenaMostrarAula = new Scene(raizMostrarAula);
            mostrarHabitacion.setTitle("Mostrar habitación");
            mostrarHabitacion.initModality(Modality.APPLICATION_MODAL);
            mostrarHabitacion.setScene(escenaMostrarAula);
        } else {
            cMostrarHabitacion.setAula(habitacion);
        }
    }

    private void crearAnadirReserva() throws IOException {
        if (anadirReserva == null) {
            anadirReserva = new Stage();
            FXMLLoader cargadorAnadirReserva = new FXMLLoader(
                    LocalizadorRecursos.class.getResource("vistas/AnadirReserva.fxml"));
            VBox raizAnadirReserva = cargadorAnadirReserva.load();
            cAnadirReserva = cargadorAnadirReserva.getController();
            //cAnadirReserva.setControladorMVC(controladorMVC);
            cAnadirReserva.inicializa();
            Scene escenaAnadirAula = new Scene(raizAnadirReserva);
            anadirReserva.setTitle("Añadir Reserva");
            anadirReserva.initModality(Modality.APPLICATION_MODAL);
            anadirReserva.setScene(escenaAnadirAula);
        } else {
            cAnadirReserva.inicializa();
        }
    }

    private void crearListarReservas() throws IOException {
        if (listarReservas == null) {
            listarReservas = new Stage();
            FXMLLoader cargadorListarReservas = new FXMLLoader(
                    LocalizadorRecursos.class.getResource("vistas/ListarReservas.fxml"));
            VBox raizListarReservas = cargadorListarReservas.load();
            cListarReservas = cargadorListarReservas.getController();
            //cListarReservas.setControladorMVC(controladorMVC);
            cListarReservas.inicializa();
            Scene escenaListarReservas = new Scene(raizListarReservas);
            listarReservas.setTitle("Listar Reservas");
            listarReservas.initModality(Modality.APPLICATION_MODAL);
            listarReservas.setScene(escenaListarReservas);
        } else {
            cListarReservas.inicializa();
        }
    }

    private void crearBuscarReserva() throws IOException {
        if (buscarReserva == null) {
            buscarReserva = new Stage();
            FXMLLoader cargadorBuscarReserva = new FXMLLoader(
                    LocalizadorRecursos.class.getResource("vistas/BuscarReserva.fxml"));
            VBox raizBuscarReserva = cargadorBuscarReserva.load();
            cBuscarReserva = cargadorBuscarReserva.getController();
            //cBuscarReserva.setControladorMVC(controladorMVC);
            cBuscarReserva.inicializa();
            Scene escenaBuscarReserva = new Scene(raizBuscarReserva);
            buscarReserva.setTitle("Buscar Reserva");
            buscarReserva.initModality(Modality.APPLICATION_MODAL);
            buscarReserva.setScene(escenaBuscarReserva);
        } else {
            cBuscarReserva.inicializa();
        }
    }

    private void crearMostrarReserva(Reserva reserva) throws IOException {
        if (mostrarReserva == null) {
            mostrarReserva = new Stage();
            FXMLLoader cargadorMostrarReserva = new FXMLLoader(
                    LocalizadorRecursos.class.getResource("vistas/MostrarReserva.fxml"));
            VBox raizMostrarReserva = cargadorMostrarReserva.load();
            cMostrarReserva = cargadorMostrarReserva.getController();
            //cMostrarReserva.setControladorMVC(controladorMVC);
            cMostrarReserva.setReserva(reserva);
            Scene escenaMostrarReserva = new Scene(raizMostrarReserva);
            mostrarReserva.setTitle("Mostrar Reserva");
            mostrarReserva.initModality(Modality.APPLICATION_MODAL);
            mostrarReserva.setScene(escenaMostrarReserva);
        } else {
            cMostrarReserva.setReserva(reserva);
        }
    }
*/

}
