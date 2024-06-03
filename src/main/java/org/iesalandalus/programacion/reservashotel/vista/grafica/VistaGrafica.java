package org.iesalandalus.programacion.reservashotel.vista.grafica;

import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class VistaGrafica extends Vista {

    private static VistaGrafica instancia;

    public VistaGrafica()
    {
        super();
    }

    public static VistaGrafica getInstancia() {
        if (instancia == null) {
            instancia = new VistaGrafica();
        }
        return instancia;
    }

    public void comenzar() {
        LanzadorVentanaPrincipal.comenzar();
    }

    public void terminar() {
        getControlador().terminar();
    }

}
