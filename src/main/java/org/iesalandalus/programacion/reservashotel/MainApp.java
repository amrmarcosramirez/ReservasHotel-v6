package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservashotel.modelo.IModelo;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.vista.FactoriaVista;
import org.iesalandalus.programacion.reservashotel.vista.Vista;
import org.iesalandalus.programacion.reservashotel.vista.texto.VistaTexto;

import javax.naming.OperationNotSupportedException;

public class MainApp {


    public static void main(String[] args) {
        System.out.println("Programa para la Gestión de Hoteles IES Al-Ándalus");

        //try {
            IModelo modelo = new Modelo(procesarArgumentosFuenteDatos(args));
            Vista vista = procesarArgumentosVista(args);
            Controlador controlador = new Controlador(modelo, vista);
            controlador.comenzar();
       /* } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }*/
    }

   //
    /* */

    private static FactoriaFuenteDatos procesarArgumentosFuenteDatos(String[] args) {
        //FactoriaFuenteDatos fuenteDatos = FactoriaFuenteDatos.MONGODB;
        FactoriaFuenteDatos fuenteDatos = FactoriaFuenteDatos.MEMORIA;
        for (String argumento : args) {
            if (argumento.equalsIgnoreCase("-fdmemoria")) {
                fuenteDatos = FactoriaFuenteDatos.MEMORIA;
            } else if (argumento.equalsIgnoreCase("-fdmongodb")) {
                fuenteDatos = FactoriaFuenteDatos.MONGODB;
            } else if (argumento.equalsIgnoreCase("-fdfichero")) {
                fuenteDatos = FactoriaFuenteDatos.FICHERO;
            }
        }
        return fuenteDatos;
    }

    private static Vista procesarArgumentosVista(String[] args) {
        //Vista fuenteVista = FactoriaVista.GRAFICA.crear();
        Vista fuenteVista = FactoriaVista.TEXTO.crear();
        for (String argumento : args) {
            if (argumento.equalsIgnoreCase("-vTexto")) {
                fuenteVista = FactoriaVista.TEXTO.crear();
            } else if (argumento.equalsIgnoreCase("-vGrafica")) {
                fuenteVista = FactoriaVista.GRAFICA.crear();;
            }
        }
        return fuenteVista;
    }
}
