package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.w3c.dom.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class Habitaciones implements IHabitaciones {

    // Se crean los atributos con su visibilidad adecuada
    private static final String RUTA_FICHERO = "datos/habitaciones.xml";
    private static final String RAIZ = "Habitaciones";
    private static final String HABITACION = "Habitacion";
    private static final String IDENTIFICADOR = "Identificador";
    private static final String PLANTA = "Planta";
    private static final String PUERTA = "Puerta";
    private static final String PRECIO = "Precio";
    private static final String CAMAS_INDIVIDUALES = "CamasIndividuales";
    private static final String CAMAS_DOBLES = "CamasDobles";
    private static final String BANOS = "Banos";
    private static final String JACUZZI = "Jacuzzi";
    private static final String TIPO = "Tipo";
    private static final String SIMPLE = "Simple";
    private static final String DOBLE = "Doble";
    private static final String TRIPLE = "Triple";
    private static final String SUITE = "Suite";
    private List<Habitacion> coleccionHabitaciones;
    private static Habitaciones instancia;
    private Document DOM;
    private Element documentosHabitaciones;


    //Constructores
    public Habitaciones() {
        DOM = UtilidadesXML.crearDomVacio(RAIZ);
        documentosHabitaciones = DOM.getDocumentElement();
        this.coleccionHabitaciones = new ArrayList<>();
        comenzar();
    }

    public static Habitaciones getInstancia() {
        if (instancia == null) {
            instancia = new Habitaciones();
        }
        return instancia;
    }

    public void comenzar() {
        leerXML();
    }

    public void terminar() {
        escribirXML();
    }

    //Métodos de acceso y modificación
    @Override
    public List<Habitacion> get() {
        List<Habitacion> copiaHabitaciones = new ArrayList<>();
        for (Iterator<Habitacion> it = coleccionHabitaciones.iterator(); it.hasNext(); ) {
            Habitacion habitacion = it.next();
            if(habitacion instanceof Simple){
                copiaHabitaciones.add(new Simple((Simple) habitacion));
            }else if(habitacion instanceof Doble){
                copiaHabitaciones.add(new Doble((Doble) habitacion));
            }else if(habitacion instanceof Triple){
                copiaHabitaciones.add(new Triple((Triple) habitacion));
            }else {
                copiaHabitaciones.add(new Suite((Suite) habitacion));
            }
        }

        return copiaHabitaciones;
    }

    @Override
    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        List<Habitacion> copiaHabitaciones = new ArrayList<>();
        for (Iterator<Habitacion> it = get().iterator();it.hasNext();){
            Habitacion habitacion = it.next();
            if(habitacion instanceof Simple && tipoHabitacion.equals(TipoHabitacion.SIMPLE)){
                copiaHabitaciones.add(new Simple((Simple) habitacion));
            }else if(habitacion instanceof Doble && tipoHabitacion.equals(TipoHabitacion.DOBLE)){
                copiaHabitaciones.add(new Doble((Doble) habitacion));
            }else if(habitacion instanceof Triple  && tipoHabitacion.equals(TipoHabitacion.TRIPLE)){
                copiaHabitaciones.add(new Triple((Triple) habitacion));
            }else if(habitacion instanceof Suite  && tipoHabitacion.equals(TipoHabitacion.SUITE)){
                copiaHabitaciones.add(new Suite((Suite) habitacion));
            }
        }
        return copiaHabitaciones;
    }

    private void leerXML(){
        //Document DOM = UtilidadesXML.crearDomVacio(RAIZ);
        //Element documentoHuespedes = DOM.getDocumentElement();

        //this.DOM = UtilidadesXML.xmlToDom(RUTA_FICHERO);
        //documentosHuespedes = DOM.getDocumentElement();



        if(UtilidadesXML.xmlToDom(RUTA_FICHERO) != null){
            this.DOM = UtilidadesXML.xmlToDom(RUTA_FICHERO);
        }

        documentosHabitaciones = DOM.getDocumentElement();


        //listaClientes = DOM.getDocumentElement();
        NodeList listaNodos=documentosHabitaciones.getChildNodes();

        for (int i=0; i<listaNodos.getLength();i++)
        {
            Node nodo=listaNodos.item(i);

            if(nodo.getNodeType() == Node.ELEMENT_NODE)
            {
                Element clienteDOM = (Element) nodo;

                coleccionHabitaciones.add(elementToHabitaciones(clienteDOM));

            }
        }
    }

    private Habitacion elementToHabitaciones(Element elementDOM){
        Habitacion habitacion;

        String identificador = elementDOM.getAttribute(IDENTIFICADOR);
        String tipo = elementDOM.getAttribute(TIPO);
        Element planta = (Element) elementDOM.getElementsByTagName(PLANTA).item(0);
        Element puerta = (Element) elementDOM.getElementsByTagName(PUERTA).item(0);
        Element precio = (Element) elementDOM.getElementsByTagName(PRECIO).item(0);

        int planta1 = Integer.parseInt(String.valueOf(planta.getTextContent()));
        int puerta1 = Integer.parseInt(String.valueOf(puerta.getTextContent()));
        double precio1 = Double.parseDouble(String.valueOf(precio.getTextContent()));
        if (tipo.equals(SIMPLE)){
            habitacion = new Simple(planta1, puerta1, precio1);
        } else if (tipo.equals(DOBLE)){
            //Element camaIndiv = (Element) elementDOM.getElementsByTagName()
            Element camaIndiv = (Element) elementDOM.getElementsByTagName(CAMAS_INDIVIDUALES).item(0);
            Element camaDoble = (Element) elementDOM.getElementsByTagName(CAMAS_DOBLES).item(0);

            int camaIndiv1 = Integer.parseInt(String.valueOf(camaIndiv.getTextContent()));
            int camaDoble1 = Integer.parseInt(String.valueOf(camaDoble.getTextContent()));
            habitacion = new Doble(planta1, puerta1, precio1, camaIndiv1, camaDoble1);
        } else if (tipo.equals(TRIPLE)){
            Element banos = (Element) elementDOM.getElementsByTagName(BANOS).item(0);
            Element camaIndiv = (Element) elementDOM.getElementsByTagName(CAMAS_INDIVIDUALES).item(0);
            Element camaDoble = (Element) elementDOM.getElementsByTagName(CAMAS_DOBLES).item(0);

            int banos1 = Integer.parseInt(String.valueOf(banos.getTextContent()));
            int camaIndiv1 = Integer.parseInt(String.valueOf(camaIndiv.getTextContent()));
            int camaDoble1 = Integer.parseInt(String.valueOf(camaDoble.getTextContent()));
            habitacion = new Triple(planta1, puerta1, precio1, banos1,
                    camaIndiv1, camaDoble1);
        } else {
            Element banos = (Element) elementDOM.getElementsByTagName(BANOS).item(0);
            String jacuzzi = String.valueOf(elementDOM.getElementsByTagName(JACUZZI).item(0));

            boolean jacuzzi1 = (Objects.equals(jacuzzi, "con Jacuzzi"));
            int banos1 = Integer.parseInt(String.valueOf(banos.getTextContent()));
            habitacion = new Suite(planta1, puerta1, precio1, banos1, jacuzzi1);
        }

        return habitacion;
    }

    private Element habitacionesToElement(Habitacion habitacion){
        //Document DOM;
        Element clienteDOM = DOM.createElement(HABITACION);
        clienteDOM.setAttribute(IDENTIFICADOR, habitacion.getIdentificador());

        Element planta = DOM.createElement(PLANTA);
        planta.setTextContent(Integer.toString(habitacion.getPlanta()));
        clienteDOM.appendChild(planta);

        Element puerta = DOM.createElement(PUERTA);
        puerta.setTextContent(Integer.toString(habitacion.getPuerta()));
        clienteDOM.appendChild(puerta);

        Element precio = DOM.createElement(PRECIO);
        precio.setTextContent(Double.toString(habitacion.getPrecio()));
        clienteDOM.appendChild(precio);

        if(habitacion instanceof Simple){
            clienteDOM.setAttribute(TIPO, SIMPLE);
        }else if(habitacion instanceof Doble){
            clienteDOM.setAttribute(TIPO, DOBLE);
            Element doble = DOM.createElement(DOBLE);
            Element CamasIndividualesPadre = DOM.createElement(CAMAS_INDIVIDUALES);
            Text CamasIndividuales = DOM.createTextNode(String.valueOf(((Doble) habitacion).getNumCamasIndividuales()));
            CamasIndividualesPadre.appendChild(CamasIndividuales);
            doble.appendChild(CamasIndividualesPadre);

            Element CamasDoblePadre = DOM.createElement(CAMAS_DOBLES);
            Text CamasDoble = DOM.createTextNode(String.valueOf(((Doble) habitacion).getNumCamasDobles()));
            CamasDoblePadre.appendChild(CamasDoble);
            doble.appendChild(CamasDoblePadre);
            clienteDOM.appendChild(doble);
        }else if(habitacion instanceof Triple){
            clienteDOM.setAttribute(TIPO, TRIPLE);

            Element banos = DOM.createElement(BANOS);
            banos.setTextContent(Integer.toString(((Triple) habitacion).getNumBanos()));
            clienteDOM.appendChild(banos);

            Element triple = DOM.createElement(TRIPLE);
            Element CamasIndividualesPadre = DOM.createElement(CAMAS_INDIVIDUALES);
            Text CamasIndividuales = DOM.createTextNode(String.valueOf(((Triple) habitacion).getNumCamasIndividuales()));
            CamasIndividualesPadre.appendChild(CamasIndividuales);
            triple.appendChild(CamasIndividualesPadre);

            Element CamasDoblePadre = DOM.createElement(CAMAS_DOBLES);
            Text CamasDoble = DOM.createTextNode(String.valueOf(((Triple) habitacion).getNumCamasDobles()));
            CamasDoblePadre.appendChild(CamasDoble);
            triple.appendChild(CamasDoblePadre);
            clienteDOM.appendChild(triple);
        }else {
            clienteDOM.setAttribute(TIPO, SUITE);

            Element banos = DOM.createElement(BANOS);
            banos.setTextContent(Integer.toString(((Suite) habitacion).getNumBanos()));
            clienteDOM.appendChild(banos);

            Element jacuzzi = DOM.createElement(JACUZZI);
            String jacuzzi1 = (((Suite) habitacion).isTieneJacuzzi()? "con Jacuzzi":
                    "sin Jacuzzi");
            jacuzzi.setTextContent(jacuzzi1);
            clienteDOM.appendChild(jacuzzi);
        }
        return clienteDOM;
        //listaClientes.appendChild(clienteDOM);
    }


    private void escribirXML(){
        DOM = UtilidadesXML.crearDomVacio(RAIZ);
        documentosHabitaciones = DOM.getDocumentElement();

        List<Habitacion> listaHabitaciones = get();
        for (Habitacion habitacion : listaHabitaciones) {
            Element huespedDOM = habitacionesToElement(habitacion);
            documentosHabitaciones.appendChild(huespedDOM);
        }
        UtilidadesXML.domToXml(DOM, RUTA_FICHERO);
    }


    @Override
    public int getTamano() {
        return coleccionHabitaciones.size();
    }

    @Override
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        Objects.requireNonNull(habitacion, "ERROR: No se puede insertar una habitación nula.");
        if (!coleccionHabitaciones.contains(habitacion)) {
            if(habitacion instanceof Simple){
                coleccionHabitaciones.add(new Simple((Simple) habitacion));
            }else if(habitacion instanceof Doble){
                coleccionHabitaciones.add(new Doble((Doble) habitacion));
            }else if(habitacion instanceof Triple){
                coleccionHabitaciones.add(new Triple((Triple) habitacion));
            }else {
                coleccionHabitaciones.add(new Suite((Suite) habitacion));
            }
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }
    }

    @Override
    public Habitacion buscar(Habitacion habitacion) {
        int indice = coleccionHabitaciones.indexOf(habitacion);
        if (indice == -1) {
            return null;
        } else {
            if(coleccionHabitaciones.get(indice) instanceof Simple){
                return new Simple((Simple) coleccionHabitaciones.get(indice));
            }else if(coleccionHabitaciones.get(indice) instanceof Doble){
                return new Doble((Doble) coleccionHabitaciones.get(indice));
            }else if(coleccionHabitaciones.get(indice) instanceof Triple){
                return new Triple((Triple) coleccionHabitaciones.get(indice));
            }else {
                return new Suite((Suite) coleccionHabitaciones.get(indice));
            }
        }
    }

    @Override
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        Objects.requireNonNull(habitacion, "ERROR: No se puede borrar una habitación nula.");
        int indice = coleccionHabitaciones.indexOf(habitacion);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
        } else {
            coleccionHabitaciones.remove(indice);
        }
    }

}
