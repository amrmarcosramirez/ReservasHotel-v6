package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Reservas;

public class FuenteDatosFichero implements IFuenteDatos {

    public IHuespedes crearHuespedes(){
        /*Huespedes huespedes = null;
        List<Huesped> coleccionHuespedes = huespedes.get();
        return (IHuespedes) coleccionHuespedes;*/
        return new Huespedes();
    };

    public IHabitaciones crearHabitaciones(){
        /*Habitaciones habitaciones = null;
        List<Habitacion> coleccionHabitaciones = habitaciones.get();
        return (IHabitaciones) coleccionHabitaciones;*/
        return new Habitaciones();
    };

    public IReservas crearReservas(){
        /*Reservas reservas = null;
        List<Reserva> coleccionReservas = reservas.get();
        return (IReservas) coleccionReservas;*/
        return new Reservas();
    };

}
