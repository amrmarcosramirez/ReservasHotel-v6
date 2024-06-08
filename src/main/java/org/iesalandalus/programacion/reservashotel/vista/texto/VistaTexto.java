package org.iesalandalus.programacion.reservashotel.vista.texto;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.vista.Vista;
import org.iesalandalus.programacion.reservashotel.utilidades.Entrada;

//import org.iesalandalus.programacion.utilidades.Entrada;
//import org.iesalandalus.programacion.utilidades.Entrada;
//import org.iesalandalus.programacion.reservashotel.utilidades.Entrada;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion.*;

public class VistaTexto extends Vista {

    // Se crean los atributos con su visibilidad adecuada
    //private Controlador controlador;

    // Se crean el constructor
    public VistaTexto() {
        super();
        Opcion.setVista(this);
    }

    //Se crean los m�todos
    /*
    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "ERROR: El controlador no puede ser nulo.");
        this.controlador = controlador;
    }
    */
    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            //ejecutarOpcion(opcion);
            opcion.ejecutar();
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        //System.out.println("Hasta luego.!!!");
        getControlador().terminar();
    }

    public void insertarHuesped() {
        String mensaje = "Insertar hu�sped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Huesped huesped = Consola.leerHuesped();
            getControlador().insertar(huesped);
            System.out.println("Hu�sped insertado correctamente.");
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarHuesped() {
        String mensaje = "Buscar hu�sped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            if (getControlador().getHuespedes().isEmpty()) {
                System.out.println("No hay hu�spedes dados de alta en el sistema.");
            } else {
                Huesped huesped = Consola.leerClientePorDni();
                Huesped huesped1 = getControlador().buscar(huesped);
                if (huesped1 != null) {
                    System.out.println("El hu�sped buscado es: " + huesped1);
                } else {
                    System.out.println("No existe ning�n hu�sped con dicho DNI.");
                }
            }
        } catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void borrarHuesped() {
        String mensaje = "Borrar hu�sped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            if (getControlador().getHuespedes().isEmpty()) {
                System.out.println("No hay hu�spedes dados de alta en el sistema.");
            } else {
                Huesped huesped = Consola.leerClientePorDni();
                Huesped huesped1 = getControlador().buscar(huesped);
                if (huesped1 == null) {
                    System.out.println("No existe ning�n hu�sped con dicho DNI.");
                } else {
                    getControlador().borrar(huesped1);
                    System.out.println("Hu�sped borrado correctamente.");
                }
            }
        } catch (OperationNotSupportedException | IllegalArgumentException |
                 NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarHuespedes(){
        String mensaje = "Mostrar hu�spedes";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            List<Huesped> listaHuespedes = getControlador().getHuespedes();
            if (!listaHuespedes.isEmpty()) {
                listaHuespedes.sort(Comparator.comparing(Huesped::getNombre));
                listaHuespedes.forEach(huesped1 -> System.out.println(huesped1.toString()));
                /*for (Huesped huesped : listaHuespedes) {
                    System.out.println(huesped);
                }*/
            } else {
                System.out.println("No hay hu�spedes que mostrar.\n");
            }
        } catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void insertarHabitacion() {
        String mensaje = "Insertar habitaci�n";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            Habitacion habitacion = Consola.leerHabitacion();
            getControlador().insertar(habitacion);
            System.out.println("Habitaci�n insertada correctamente.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscarHabitacion(){
        String mensaje = "Buscar habitaci�n";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            if (getControlador().getHabitaciones().isEmpty()) {
                System.out.println("No hay habitaciones dadas de alta en el sistema.");
            } else {
                Habitacion habitacion = Consola.leerHabitacionPorIdentificador();
                Habitacion habitacion1 = getControlador().buscar(habitacion);
                if (habitacion1 != null) {
                    System.out.println("La habitaci�n buscada es: " + habitacion1);
                } else {
                    System.out.println("La habitaci�n buscada no existe.");
                }
            }
        }catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void borrarHabitacion() {
        String mensaje = "Borrar habitacci�n";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            if (getControlador().getHabitaciones().isEmpty()) {
                System.out.println("No hay habitaciones dadas de alta en el sistema.");
            } else {
                Habitacion habitacion = Consola.leerHabitacionPorIdentificador();
                Habitacion habitacion1 = getControlador().buscar(habitacion);
                if (habitacion1 == null) {
                    System.out.println("No existe la habitaci�n indicada.");
                } else {
                    getControlador().borrar(habitacion1);
                    System.out.println("Habitaci�n borrada correctamente.");
                }
            }
        } catch (OperationNotSupportedException | IllegalArgumentException |
                 NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarHabitaciones(){
        String mensaje = "Mostrar habitaciones";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            List<Habitacion> listaHabitacion = getControlador().getHabitaciones();
            if (!listaHabitacion.isEmpty()) {
                listaHabitacion.sort(Comparator.comparing(Habitacion::getIdentificador));
                listaHabitacion.forEach(habitacion1 -> System.out.println(habitacion1.toString()));
                /*for (Habitacion habitacion : listaHabitacion) {
                    System.out.println(habitacion);
                }*/
            } else {
                System.out.println("No hay habitaciones que mostrar.");
            }
        } catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void insertarReserva() {
        Reserva reserva;
        Huesped huesped;
        //Habitacion habitacion;
        Regimen regimen;
        int numeroPersonas;

        String mensaje = "Insertar reserva";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            if (getControlador().getHabitaciones().isEmpty()) {
                System.out.println("No hay habitaciones dadas de alta en el sistema para poder realizar la reserva.");
            } else {
                //comprobarDisponibilidad();

                String mensaje1 = "Consultar disponibilidad";
                System.out.printf("%n%s%n", mensaje1);
                String cadena1 = "%0" + mensaje1.length() + "d%n";
                System.out.println(String.format(cadena1, 0).replace("0", "-"));

                TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
                LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio de reserva (%s): ");
                LocalDate fechaFin = Consola.leerFecha("Introduce la fecha de fin de reserva (%s): ");
                Habitacion habitacionDisponible = consultarDisponibilidad(tipoHabitacion, fechaInicio,
                        fechaFin);
                if (habitacionDisponible == null) {
                    System.out.println("El tipo de habitaci�n NO est� disponible.");
                } else {
                    System.out.println("La habitaci�n: " + habitacionDisponible + ", est� disponible.");
                    //Reserva reserva = leerReserva();
                    huesped = Consola.leerHuesped();
                    //habitacion = leerHabitacion();
                    regimen = Consola.leerRegimen();

                    System.out.print("Introduce el n�mero de personas: ");
                    numeroPersonas = Entrada.entero();
                    reserva = new Reserva(huesped, habitacionDisponible, regimen, fechaInicio,
                                          fechaFin, numeroPersonas);

                    reserva = new Reserva(reserva);
                    reserva.setHabitacion(habitacionDisponible);

                    getControlador().insertar(reserva);
                    if(getControlador().buscar(reserva.getHuesped()) == null) {
                        getControlador().insertar(reserva.getHuesped());
                    } else {
                        System.out.println("El hu�sped de la reserva est� dado de alta e el sistema.\n");
                        System.out.println("Informaci�n del sistema:\n");
                        System.out.println(getControlador().buscar(reserva.getHuesped()).toString());
                        /*System.out.println("\nInformaci�n del la reserva:\n");
                        System.out.println(reserva.getHuesped().toString());
                        System.out.println("\nDesea actualizar los datos del hu�sped en el sistema (S/N)?\n");
                        char confCambio = Entrada.caracter();
                        if (confCambio == 'S') {
                            MongoCollection<Document> huespedes = (MongoCollection<Document>) new Huespedes();
                            Bson c2 = eq(HUESPED_DNI, reserva.getHuesped().getDni());
                            Bson campo = set(CORREO, reserva.getHuesped().getCorreo());
                            Bson campo2 = set(TELEFONO, reserva.getHuesped().getTelefono());
                            huespedes.updateOne(c2, campo);
                            huespedes.updateOne(c2, campo2);
                            //controlador.insertar(reserva.getHuesped());
                            System.out.println("Informaci�n actualizada correctamente.");
                        }*/
                    }
                    //controlador.insertar(reserva.getHuesped());
                    System.out.println("Reserva insertada correctamente.");
                }
            }
        } catch (OperationNotSupportedException|IllegalArgumentException|NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarReservasHuesped(){
        String mensaje = "Listar reservas hu�sped";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            List<Reserva> listaReservas = getControlador().getReservas();
            if (!listaReservas.isEmpty()) {
                try {
                    Huesped huesped = Consola.leerClientePorDni();
                    listarReservas(huesped);
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("No hay reservas dadas de alta en el sistema.");
            }
        } catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void listarReservas(Huesped huesped){

        try {
            List<Reserva> listaReservas = getControlador().getReservas(huesped);
            if (!listaReservas.isEmpty()){
                listaReservas.sort(Comparator.comparing(
                        Reserva::getFechaInicioReserva).reversed().thenComparing(
                        (Reserva reserva1) -> reserva1.getHabitacion().getIdentificador()));
                int i = 0;
                for (Reserva reserva2 : listaReservas) {
                    System.out.println(i + ".- " + reserva2);
                    i++;
                }
            } else {
                System.out.println("No hay reservas que listar.");
            }
        } catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarReservasTipoHabitacion(){
        String mensaje = "Listar reservas tipo habitaci�n";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        List<Reserva> listaReservas = getControlador().getReservas();
        if (!listaReservas.isEmpty()) {
            try {
                TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
                listarReservas(tipoHabitacion);
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No hay reservas dadas de alta en el sistema.");
        }

    }

    public void comprobarDisponibilidad() {
        String mensaje1 = "Consultar disponibilidad";
        System.out.printf("%n%s%n", mensaje1);
        String cadena1 = "%0" + mensaje1.length() + "d";
        System.out.println(String.format(cadena1, 0).replace("0", "-"));

        try {
            if (getControlador().getHabitaciones().isEmpty()) {
                System.out.println("No hay habitaciones dadas de alta en el sistema para poder realizar la reserva.");
            } else {
                TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
                LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio (%s): ");
                LocalDate fechaFin = Consola.leerFecha("Introduce la fecha de fin (%s): ");

                Habitacion habitacionDisponible = consultarDisponibilidad(tipoHabitacion, fechaInicio,
                        fechaFin);
                if (habitacionDisponible == null) {
                    System.out.println("El tipo de habitaci�n solicitado NO est� disponible.");
                } else {
                    System.out.println("La habitaci�n: " + habitacionDisponible + ", est� disponible.");

                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarReservas(TipoHabitacion tipoHabitacion){

        try {
            List<Reserva> listaReservas  = getControlador().getReservas(tipoHabitacion);
            if (!listaReservas.isEmpty()){
                listaReservas.sort(Comparator.comparing(
                        Reserva::getFechaInicioReserva).reversed().thenComparing(
                        (Reserva reserva1) -> reserva1.getHuesped().getNombre()));
                int i = 0;
                for (Reserva reserva2: listaReservas) {
                    System.out.println(i + ".- " + reserva2);
                    i++;
                }
            } else {
                System.out.println("No hay reservas que listar.");
            }
        } catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public List<Reserva> getReservasAnulables(List<Reserva> reservasAAnular){
        List<Reserva> reservasAnulables = new ArrayList<>();
        try {
            for (Iterator<Reserva> it = reservasAAnular.iterator();
                 it.hasNext();) {
                Reserva reserva = it.next();

                if(reserva.getFechaInicioReserva().isAfter(LocalDate.now())){
                    reservasAnulables.add(new Reserva(reserva));
                }
            }
        } catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }

        return reservasAnulables;
    }

    public void anularReserva() {
        String mensaje = "Anular reservas";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        char confReserva = 'S';
        try {
            if (getControlador().getReservas().isEmpty()) {
                System.out.println("No hay reservas dadas de alta en el sistema.");
            } else {
                Huesped huesped = Consola.leerClientePorDni();
                Huesped huesped1 = getControlador().buscar(huesped);
                if (huesped1 != null) {
                    List<Reserva> reservas = getControlador().getReservas(huesped1);
                    if (!reservas.isEmpty()) {
                        List<Reserva> reservas1 = getReservasAnulables(reservas);

                        int i = 0;
                        for (Reserva reserva2 : reservas1) {
                            System.out.println(i + ".- " + reserva2);
                            i++;
                        }
                        System.out.println("Elija la reserva que desea anular.");
                        int numReserva = Entrada.entero();

                        if (reservas.size() == 1) {
                            System.out.println("Confirma que desea anular la reserva (S/N)?");
                            confReserva = Entrada.caracter();
                        }
                        if (confReserva == 'S') {
                            getControlador().borrar(reservas.get(numReserva));
                            System.out.println("Reserva anulada correctamente.");
                        } else {
                            System.out.println("La reserva no ha sido anulada.");
                        }
                    } else {
                        System.out.println("No hay reservas que se puedan anular para dicho hu�sped.");
                    }
                } else {
                    System.out.println("No existe ning�n hu�sped con dicho DNI.");
                }
            }
        } catch(IllegalArgumentException | NullPointerException | OperationNotSupportedException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarReservas(){
        String mensaje = "Mostrar reservas";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            List<Reserva> listaReservas = getControlador().getReservas();
            if (!listaReservas.isEmpty()) {
                listaReservas.sort(Comparator.comparing(
                        Reserva::getFechaInicioReserva).reversed().thenComparing(
                        (Reserva reserva1) -> reserva1.getHabitacion().getIdentificador()));
                listaReservas.forEach(reserva2 -> System.out.println(reserva2.toString()));

                /*for (Reserva reserva : listaReserva) {
                    if (!(reserva == null))
                        System.out.println(reserva);
                }*/
            } else {
                System.out.println("No hay reservas que mostrar.");
            }
        } catch (IllegalArgumentException|NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion,
                                               LocalDate fechaInicioReserva,
                                               LocalDate fechaFinReserva){
        try {
            if (tipoHabitacion == null) {
                throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un tipo de habitaci�n nulo.");
            }
            if (fechaInicioReserva.isBefore(LocalDate.now())) {
                throw new NullPointerException("ERROR: La fecha de inicio no debe ser anterior al d�a de hoy.");
            }
            if (!fechaFinReserva.isAfter(fechaInicioReserva)) {
                throw new NullPointerException("ERROR: La fecha de fin de reserva debe ser posterior a la fecha de inicio de reserva.");
            }

            List<Habitacion> habitacionesTipoSolicitado = getControlador().getHabitaciones(tipoHabitacion);

            if (habitacionesTipoSolicitado.isEmpty()) {
                return null;
            } else {
                //for (Iterator<Habitacion> iterator = habitacionesTipoSolicitado.iterator();
                //iterator.hasNext();) {
                for (Habitacion habitacion : habitacionesTipoSolicitado) {
                    //System.out.println(habitacion);
                    List<Reserva> reservasFuturas = getControlador().getReservaFuturas(habitacion);

                    //int numElementosNoNulos = getNumElementosNoNulos(reservasFuturas);
                    if (reservasFuturas.isEmpty()) {
                        return habitacion;
                    } else {
                        /*Arrays.sort(reservasFuturas, 0, numElementosNoNulos,
                                Comparator.comparing(Reserva::getFechaFinReserva).reversed());
                        */
                        reservasFuturas.sort(Comparator.comparing(Reserva::getFechaFinReserva).reversed());
                        if (fechaInicioReserva.isAfter(reservasFuturas.get(0).getFechaFinReserva())) {
                            return habitacion;
                        } else {
                            /*Arrays.sort(reservasFuturas, 0, numElementosNoNulos,
                                    Comparator.comparing(Reserva::getFechaInicioReserva));*/
                            reservasFuturas.sort(Comparator.comparing(Reserva::getFechaInicioReserva));
                            if (fechaFinReserva.isBefore(reservasFuturas.get(0).getFechaInicioReserva())) {
                                return habitacion;
                            } else {
                                Habitacion habitacionDisponible = null;
                                boolean tipoHabitacionEncontrada = false;
                                for (int j = 1; j < reservasFuturas.size() && !tipoHabitacionEncontrada; j++) {
                                    if (reservasFuturas.get(j) != null && reservasFuturas.get(j) != null) {
                                        if (fechaInicioReserva.isAfter(reservasFuturas.get(j).getFechaFinReserva()) &&
                                                fechaFinReserva.isBefore(reservasFuturas.get(j).getFechaInicioReserva())) {
                                            if (tipoHabitacion == SIMPLE) {
                                                habitacionDisponible = new Simple((Simple) habitacionesTipoSolicitado.get(j));
                                            } else if (tipoHabitacion == DOBLE) {
                                                habitacionDisponible = new Doble((Doble) habitacionesTipoSolicitado.get(j));
                                            } else if (tipoHabitacion == TRIPLE) {
                                                habitacionDisponible = new Triple((Triple) habitacionesTipoSolicitado.get(j));
                                            } else {
                                                habitacionDisponible = new Suite((Suite) habitacionesTipoSolicitado.get(j));
                                            }
                                            tipoHabitacionEncontrada = true;
                                        }
                                    }
                                }
                                return habitacionDisponible;
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void realizarCheckin() {
        String mensaje = "Realizar checkin";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            if (getControlador().getHabitaciones().isEmpty()) {
                System.out.println("No hay habitaciones dadas de alta en el sistema. " +
                        "El checkin no se puede realizar.");
            } else {
                Huesped huesped = Consola.leerClientePorDni();
                Huesped huesped1 = getControlador().buscar(huesped);
                if (!(huesped1 == null)) {
                    List<Reserva> reservas = getControlador().getReservas(huesped1);
                    if (!reservas.isEmpty()) {
                        listarReservas(huesped1);
                        System.out.println("Elija la reserva a la que desea realizar el checkin.");
                        int numReserva = Entrada.entero();
                        Reserva reserva = reservas.get(numReserva);
                        //listarReservas(reserva.getHabitacion().getTipoHabitacion());
                        String mensaje1 = "Introduce la fecha y hora de checkin de la reserva (%s): ";
                        LocalDateTime fechaHora = Consola.leerFechaHora(mensaje1);
                        getControlador().realizarCheckin(reserva, fechaHora);
                        System.out.println("Checkin realizado correctamente.");
                    } else {
                        System.out.println("No existe ninguna reseva para dicho hu�sped.");
                    }
                } else {
                    System.out.println("No existe ning�n hu�sped con dicho DNI.");
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void realizarCheckOut() {
        String mensaje = "Realizar checkout";
        System.out.printf("%n%s%n", mensaje);
        String cadena = "%0" + mensaje.length() + "d";
        System.out.println(String.format(cadena, 0).replace("0", "-"));

        try {
            if (getControlador().getHabitaciones().isEmpty()) {
                System.out.println("No hay habitaciones dadas de alta en el sistema. " +
                        "El checkout no se puede realizar.");
            } else {
                Huesped huesped = Consola.leerClientePorDni();
                Huesped huesped1 = getControlador().buscar(huesped);
                if (!(huesped1 == null)) {
                    List<Reserva> reservas = getControlador().getReservas(huesped1);
                    if (!reservas.isEmpty()) {
                        listarReservas(huesped1);
                        System.out.println("Elija la reserva a la que desea realizar el checkin.");
                        int numReserva = Entrada.entero();
                        Reserva reserva = reservas.get(numReserva);
                        //listarReservas(reserva.getHabitacion().getTipoHabitacion());
                        String mensaje1 = "Introduce la fecha y hora de checkout de la reserva (%s): ";
                        LocalDateTime fechaHora = Consola.leerFechaHora(mensaje1);
                        getControlador().realizarCheckout(reserva, fechaHora);
                        System.out.println("Checkout realizado correctamente.");
                    } else {
                        System.out.println("No existe ninguna reseva para dicho hu�sped.");
                    }
                } else {
                    System.out.println("No existe ning�n hu�sped con dicho DNI.");
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}
