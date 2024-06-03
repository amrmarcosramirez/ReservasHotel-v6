package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero.utilidades;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class UtilidadesXML {


    private UtilidadesXML() {
    }


    public static Document xmlToDom (String archivoXml) {
        Document doc=null;
        try {
            // 1� Creamos una nueva instancia de un f�brica de constructores de documentos.
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 2� A partir de la instancia anterior, fabricamos un constructor de documentos, que procesar� el XML.
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 3� Procesamos el documento (almacenado en un archivo) y lo convertimos en un �rbol DOM.
            doc=db.parse(archivoXml);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return doc;
    }


    public static boolean domToXml (Document doc, String CaminoAlArchivoXML) {
        try {
            // 1� Creamos una instancia de la clase File para acceder al archivo donde guardaremos el XML.
            File f=new File(CaminoAlArchivoXML);
            //2� Creamos una nueva instancia del transformador a trav�s de la f�brica de transformadores.
            TransformerFactory tFactory=TransformerFactory.newInstance();
            //3� Establecemos algunas opciones de salida, como por ejemplo, la codificaci�n de salida.
            tFactory.setAttribute("indent-number", 4);
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //4� Creamos el StreamResult, que intermediar� entre el transformador y el archivo de destino.
            FileOutputStream fos =new FileOutputStream(f);
            StreamResult result = new StreamResult(new OutputStreamWriter(fos,"UTF-8"));
            //5� Creamos el DOMSource, que intermediar� entre el transformador y el �rbol DOM.
            DOMSource source = new DOMSource(doc);
            //6� Realizamos la transformaci�n.
            transformer.transform(source, result);
            return true;
        } catch (TransformerException ex) {
            System.out.println(ex.getMessage());
        } catch (UnsupportedEncodingException uee){
            System.out.println(uee.getMessage());
        } catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        }
        return false;
    }


    public static Document crearDomVacio(String etiquetaRaiz) {
        // 1� Creamos una nueva instancia de un f�brica de constructores de documentos.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document d = null ;
        try {
            // 2� A partir de la instancia anterior, inicializamos el constructor de documentos.
            db = dbf.newDocumentBuilder() ;
            // 3� Inicializamos el documento
            d = db.newDocument() ;
            // 4� Insertamos el elemento a nivel de la raiz.
            d.appendChild(d.createElement(etiquetaRaiz));
            //System.out.println(etiquetaRaiz);
            //System.out.println(d.getDocumentElement());
            return d;
        } catch (ParserConfigurationException ex) {

            System.out.println(ex.getMessage());
        }
        return d ;
    }
}
