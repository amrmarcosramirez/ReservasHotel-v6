package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.iesalandalus.programacion.reservashotel.utilidades.Entrada;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades.MongoDB.*;

public class Reservas implements IReservas {

    // Se crean los atributos con su visibilidad adecuada
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
    private static final String RUTA_FICHERO = "datos/reservas.xml";
    private static final String RAIZ = "Reservas";
    private static final String RESERVA = "Reserva";
    private static final String DNI_HUESPED = "Dni";
    private static final String PLANTA_HABITACION = "Planta";
    private static final String PUERTA_HABITACION = "Puerta";
    private static final String FECHA_INICIO_RESERVA = "FechaInicioReserva";
    private static final String FECHA_FIN_RESERVA = "FechaFinReserva";
    private static final String REGIMEN = "Regimen";
    private static final String NUMERO_PERSONAS = "Personas";
    private static final String CHECKIN = "FechaCheckin";
    private static final String CHECKOUT = "FechaCheckout";
    private static final String PRECIO = "Precio";

    private List<Reserva> coleccionReservas;
    private static Reservas instancia;

    private Document DOM;
    private Element documentosReservas;

    public  Reservas() {
        DOM = UtilidadesXML.crearDomVacio(RAIZ);
        documentosReservas = DOM.getDocumentElement();
        this.coleccionReservas = new ArrayList<>();
        comenzar();
    }

    public static Reservas getInstancia() {
        if (instancia == null) {
            instancia = new Reservas();
        }
        return instancia;
    }

    public void comenzar() {
        leerXML();
    }

    public void terminar() {
        escribirXML();
    }

    @Override
    public List<Reserva> get() {
        List<Reserva> copiaReservas = new ArrayList<>();
        for (Reserva coleccionReserva : coleccionReservas) {
            copiaReservas.add(new Reserva(coleccionReserva));
        }
        return copiaReservas;
    }

    private void leerXML(){

        if(UtilidadesXML.xmlToDom(RUTA_FICHERO) != null){
            this.DOM = UtilidadesXML.xmlToDom(RUTA_FICHERO);
        }
        documentosReservas = DOM.getDocumentElement();

        //listaClientes = DOM.getDocumentElement();
        NodeList listaNodos=documentosReservas.getChildNodes();

        for (int i=0; i<listaNodos.getLength();i++)
        {
            Node nodo=listaNodos.item(i);

            if(nodo.getNodeType() == Node.ELEMENT_NODE)
            {
                Element clienteDOM = (Element) nodo;

                coleccionReservas.add(elementToReserva(clienteDOM));

            }
        }
    }

    private Reserva elementToReserva(Element elementDOM){

        String dni = elementDOM.getAttribute(DNI);
        String planta = elementDOM.getAttribute(PLANTA_HABITACION);
        String puerta = elementDOM.getAttribute(PUERTA_HABITACION);

        Element regimen = (Element) elementDOM.getElementsByTagName(REGIMEN).item(0);
        Element fechaInicioReserva = (Element) elementDOM.getElementsByTagName(FECHA_INICIO_RESERVA).item(0);
        Element fechaFinReservaReserva = (Element) elementDOM.getElementsByTagName(FECHA_FIN_RESERVA).item(0);
        Element numpersonas = (Element) elementDOM.getElementsByTagName(NUMERO_PERSONAS).item(0);
        Element precio = (Element) elementDOM.getElementsByTagName(PRECIO).item(0);
        Element chechin = (Element) elementDOM.getElementsByTagName(CHECKIN).item(0);
        Element checkout = (Element) elementDOM.getElementsByTagName(CHECKOUT).item(0);

        // Creamos un huesped ficticio para buscar el huesped correcto
        String nombre = "Huésped Ficticio";
        String telefono = "666666666";
        String correo = "HuspedFicticio@gmail.com";
        LocalDate fechaNacimiento = LocalDate.now();
        Huesped huesped = new Huesped(nombre, dni, correo, telefono,
                fechaNacimiento);
        Huesped huesped1 = Huespedes.getInstancia().buscar(huesped);
        huesped1 = new Huesped(huesped1);

        // Creamos una habitacion ficticia para buscar la habitacion correcta
        double precioHab = 70.0;
        Simple habitacion = new Simple(Integer.parseInt(planta),
                Integer.parseInt(puerta), precioHab);
        Habitacion habitacion1 = Habitaciones.getInstancia().buscar(habitacion);

        Regimen regimen1 = null;
        if(Regimen.SOLO_ALOJAMIENTO.equals(regimen.getTextContent())){
            regimen1 = Regimen.SOLO_ALOJAMIENTO;
        } else if(Regimen.ALOJAMIENTO_DESAYUNO.equals(regimen.getTextContent())){
            regimen1 = Regimen.ALOJAMIENTO_DESAYUNO;
        } else if(Regimen.MEDIA_PENSION.equals(regimen.getTextContent())){
            regimen1 = Regimen.MEDIA_PENSION;
        } else {
            regimen1 = Regimen.PENSION_COMPLETA;
        }

        Reserva reserva = new Reserva(huesped1, habitacion1,regimen1,
                LocalDate.parse(fechaInicioReserva.getTextContent(),FORMATO_FECHA),
                LocalDate.parse(fechaFinReservaReserva.getTextContent(),FORMATO_FECHA),
                Integer.parseInt(numpersonas.getTextContent()));

        if (!Objects.equals(chechin.getTextContent(), "")) {
            reserva.setCheckIn(LocalDateTime.parse(chechin.getTextContent(),
                    FORMATO_FECHA_HORA));
        }

        if (!Objects.equals(checkout.getTextContent(), "")) {
            reserva.setCheckOut(LocalDateTime.parse(checkout.getTextContent(),
                    FORMATO_FECHA_HORA));
        }

        return new Reserva(reserva);

    }

    private Element reservaToElement(Reserva reserva){
        //Document DOM;
        Element clienteDOM = DOM.createElement(RESERVA);
        clienteDOM.setAttribute(DNI, reserva.getHuesped().getDni());
        clienteDOM.setAttribute(PLANTA_HABITACION, Integer.toString(reserva.getHabitacion().getPlanta()));
        clienteDOM.setAttribute(PUERTA_HABITACION, Integer.toString(reserva.getHabitacion().getPuerta()));


        Element regimenE = DOM.createElement(REGIMEN);
        regimenE.setTextContent(reserva.getRegimen().getCadenaAMostrar());
        //nombreE.setAttribute("tipo", "String");
        clienteDOM.appendChild(regimenE);

        Element fechaInicioE = DOM.createElement(FECHA_INICIO_RESERVA);
        fechaInicioE.setTextContent(reserva.getFechaInicioReserva().format(FORMATO_FECHA));
        //dniE.setAttribute("tipo", "String");
        clienteDOM.appendChild(fechaInicioE);

        Element fechaFinE = DOM.createElement(FECHA_FIN_RESERVA);
        fechaFinE.setTextContent(reserva.getFechaFinReserva().format(FORMATO_FECHA));
        //dniE.setAttribute("tipo", "String");
        clienteDOM.appendChild(fechaFinE);

        Element personasE = DOM.createElement(NUMERO_PERSONAS);
        personasE.setTextContent(Integer.toString(reserva.getNumeroPersonas()));
        //dniE.setAttribute("tipo", "String");
        clienteDOM.appendChild(personasE);

        Element fechaInE = DOM.createElement(CHECKIN);
        fechaInE.setTextContent((reserva.getCheckIn()==null)?
                "":reserva.getCheckIn().format(FORMATO_FECHA_HORA));
        //dniE.setAttribute("tipo", "String");
        clienteDOM.appendChild(fechaInE);

        Element fechaOutE = DOM.createElement(CHECKOUT);
        fechaOutE.setTextContent((reserva.getCheckOut()==null)?
                "":reserva.getCheckOut().format(FORMATO_FECHA_HORA));
        //dniE.setAttribute("tipo", "String");
        clienteDOM.appendChild(fechaOutE);

        Element precio = DOM.createElement(PRECIO);
        precio.setTextContent(Double.toString(reserva.getPrecio()));
        clienteDOM.appendChild(precio);

        return clienteDOM;
        //listaClientes.appendChild(clienteDOM);
    }

    private void escribirXML(){
        DOM = UtilidadesXML.crearDomVacio(RAIZ);
        documentosReservas = DOM.getDocumentElement();
        System.out.println("NADA");
        List<Reserva> listaReservas = get();
        for (Reserva reserva : listaReservas) {
            Element huespedDOM = reservaToElement(reserva);
            documentosReservas.appendChild(huespedDOM);
        }
        UtilidadesXML.domToXml(DOM, RUTA_FICHERO);
    }

    @Override
    public int getTamano() {
        return coleccionReservas.size();
    }

    @Override
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
       Objects.requireNonNull(reserva, "ERROR: No se puede insertar una reserva nula.");
        if (!coleccionReservas.contains(reserva)) {
            coleccionReservas.add(new Reserva(reserva));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }

    }

    @Override
    public Reserva buscar(Reserva reserva) {
        Objects.requireNonNull(reserva, "ERROR: No se puede buscar una reserva nula.");
        int indice = coleccionReservas.indexOf(reserva);
        if (indice == -1) {
            return null;
        } else {
            return new Reserva(coleccionReservas.get(indice));
        }
    }

    @Override
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        Objects.requireNonNull(reserva, "ERROR: No se puede borrar una reserva nula.");
        int indice = coleccionReservas.indexOf(reserva);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        } else {
            coleccionReservas.remove(indice);
        }
    }

    @Override
    public List<Reserva> getReservas(Huesped huesped) {
        Objects.requireNonNull(huesped, "ERROR: No se pueden buscar reservas de un huésped nulo.");
        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Iterator<Reserva> it = get().iterator(); it.hasNext();) {
            Reserva reserva = it.next();
            if (reserva != null && reserva.getHuesped().equals(huesped)) {
                reservasHuesped.add(new Reserva(reserva));
            }
        }
        return reservasHuesped;
    }

    @Override
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        Objects.requireNonNull(tipoHabitacion, "ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        List<Reserva> reservasHabitacion = new ArrayList<>();
        for (Iterator<Reserva> it = get().iterator(); it.hasNext();) {
            Reserva reserva = it.next();
            if (reserva.getHabitacion() instanceof Simple && tipoHabitacion == TipoHabitacion.SIMPLE) {
                reservasHabitacion.add(new Reserva(reserva));
            } else if (reserva.getHabitacion() instanceof Doble && tipoHabitacion == TipoHabitacion.DOBLE) {
                reservasHabitacion.add(new Reserva(reserva));
            } else if (reserva.getHabitacion() instanceof Triple && tipoHabitacion == TipoHabitacion.TRIPLE) {
                reservasHabitacion.add(new Reserva(reserva));
            } else if (reserva.getHabitacion() instanceof Suite && tipoHabitacion == TipoHabitacion.SUITE) {
                reservasHabitacion.add(new Reserva(reserva));
            }
        }
        return reservasHabitacion;
    }

    public List<Reserva> getReservas(Habitacion habitacion) {
        Objects.requireNonNull(habitacion, "ERROR: No se pueden buscar reservas de una habitación nula.");
        List<Reserva> reservasHabitacion = new ArrayList<>();
        for (Iterator<Reserva> it = get().iterator(); it.hasNext();) {
            Reserva reserva = it.next();
            if (reserva != null && reserva.getHabitacion().equals(habitacion)) {
                reservasHabitacion.add(new Reserva(reserva));
            }
        }
        return reservasHabitacion;
    }

    @Override
    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        Objects.requireNonNull(habitacion, "ERROR: No se pueden buscar reservas de una habitación nula.");
        List<Reserva> reservasHabitacion = new ArrayList<>();
        for (Iterator<Reserva> it = get().iterator(); it.hasNext();) {
            Reserva reserva = it.next();
            if (reserva != null && reserva.getHabitacion().equals(habitacion) &&
                    reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasHabitacion.add(new Reserva(reserva));
            }
        }
        return reservasHabitacion;
    }

    @Override
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha){
        reserva.setCheckIn(fecha);
    }

    @Override
    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha){
        reserva.setCheckOut(fecha);
    }

}
