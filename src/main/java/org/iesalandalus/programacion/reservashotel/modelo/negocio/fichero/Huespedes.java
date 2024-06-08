package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Huespedes implements IHuespedes {

    // Se crean los atributos con su visibilidad adecuada
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String RUTA_FICHERO = "datos/huespedes.xml";
    private static final String RAIZ = "Huespedes";
    private static final String HUESPED = "Huesped";
    private static final String NOMBRE = "Nombre";
    private static final String DNI = "Dni";
    private static final String CORREO = "Correo";
    private static final String TELEFONO = "Telefono";
    private static final String FECHA_NACIMIENTO = "FechaNacimiento";

    private List<Huesped> coleccionHuespedes;
    private static Huespedes instancia;

    private Document DOM;
    private Element documentosHuespedes;


    //Constructores
    public Huespedes() {
        DOM = UtilidadesXML.crearDomVacio(RAIZ);
        documentosHuespedes = DOM.getDocumentElement();
        this.coleccionHuespedes = new ArrayList<>();
        comenzar();
    }

    public static Huespedes getInstancia() {
        if (instancia == null) {
            instancia = new Huespedes();
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
    public List<Huesped> get() {
        List<Huesped> copiaHuespedes = new ArrayList<>();
        for (Huesped coleccionHuespede : coleccionHuespedes) {
            copiaHuespedes.add(new Huesped(coleccionHuespede));
        }
        return copiaHuespedes;
    }

    private void leerXML(){
        //Document DOM = UtilidadesXML.crearDomVacio(RAIZ);
        //Element documentoHuespedes = DOM.getDocumentElement();

        //this.DOM = UtilidadesXML.xmlToDom(RUTA_FICHERO);
        //documentosHuespedes = DOM.getDocumentElement();



        if(UtilidadesXML.xmlToDom(RUTA_FICHERO) != null){
            this.DOM = UtilidadesXML.xmlToDom(RUTA_FICHERO);
        }

        documentosHuespedes = DOM.getDocumentElement();


        //listaClientes = DOM.getDocumentElement();
        NodeList listaNodos=documentosHuespedes.getChildNodes();

        for (int i=0; i<listaNodos.getLength();i++)
        {
            Node nodo=listaNodos.item(i);

            if(nodo.getNodeType() == Node.ELEMENT_NODE)
            {
                Element clienteDOM = (Element) nodo;

                coleccionHuespedes.add(elementToHuesped(clienteDOM));

            }
        }
    }

    private Huesped elementToHuesped(Element elementDOM){

        String dni = elementDOM.getAttribute(DNI);
        Element nombre = (Element) elementDOM.getElementsByTagName(NOMBRE).item(0);
        Element correo = (Element) elementDOM.getElementsByTagName(CORREO).item(0);
        Element telefono = (Element) elementDOM.getElementsByTagName(TELEFONO).item(0);
        Element fechaNacimiento = (Element) elementDOM.getElementsByTagName(FECHA_NACIMIENTO).item(0);

        Huesped huesped = new Huesped(nombre.getTextContent(), dni,
                correo.getTextContent(), telefono.getTextContent(),
                LocalDate.parse(fechaNacimiento.getTextContent(),
                        DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA)));

        return new Huesped(huesped);

    }

    private Element huespedToElement(Huesped huesped){
        //Document DOM;
        Element clienteDOM = DOM.createElement(HUESPED);
        clienteDOM.setAttribute(DNI, huesped.getDni());


        Element nombreE = DOM.createElement(NOMBRE);
        nombreE.setTextContent(huesped.getNombre());
        //nombreE.setAttribute("tipo", "String");
        clienteDOM.appendChild(nombreE);

        Element correoE = DOM.createElement(CORREO);
        correoE.setTextContent(huesped.getCorreo());
        //dniE.setAttribute("tipo", "String");
        clienteDOM.appendChild(correoE);

        Element telefonoE = DOM.createElement(TELEFONO);
        telefonoE.setTextContent(huesped.getTelefono());
        //dniE.setAttribute("tipo", "String");
        clienteDOM.appendChild(telefonoE);

        Element fechaNacimientoE = DOM.createElement(FECHA_NACIMIENTO);
        fechaNacimientoE.setTextContent(huesped.getFechaNacimiento().format(FORMATO_FECHA));
        //dniE.setAttribute("tipo", "String");
        clienteDOM.appendChild(fechaNacimientoE);


        return clienteDOM;
        //listaClientes.appendChild(clienteDOM);
    }

    private void escribirXML(){
        DOM = UtilidadesXML.crearDomVacio(RAIZ);
        documentosHuespedes = DOM.getDocumentElement();

        List<Huesped> listaHuespedes = get();
        for (Huesped huesped : listaHuespedes) {
            Element huespedDOM = huespedToElement(huesped);
            documentosHuespedes.appendChild(huespedDOM);
        }
        UtilidadesXML.domToXml(DOM, RUTA_FICHERO);
    }

    @Override
    public int getTamano() {
        return coleccionHuespedes.size();
    }

    @Override
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        Objects.requireNonNull(huesped, "ERROR: No se puede insertar un huésped nulo.");
        if (!coleccionHuespedes.contains(huesped)) {
            coleccionHuespedes.add(new Huesped(huesped));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        }
    }

    @Override
    public Huesped buscar(Huesped huesped) {
        Objects.requireNonNull(huesped, "ERROR: No se puede buscar un huésped nulo.");
        int indice = coleccionHuespedes.indexOf(huesped);
        if (indice == -1) {
            return null;
        } else {
            return new Huesped(coleccionHuespedes.get(indice));
        }
    }

    @Override
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        Objects.requireNonNull(huesped, "ERROR: No se puede borrar un huésped nulo.");
        int indice = coleccionHuespedes.indexOf(huesped);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
        } else {
            coleccionHuespedes.remove(indice);
        }
    }

}
