package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;

import javax.naming.OperationNotSupportedException;
import java.time.format.DateTimeFormatter;

import static org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped.FORMATO_FECHA;

public class ControladorMostrarHuesped {

	private Huesped huesped;
	
	@FXML Label lbNombre;
	@FXML Label lbDni;
	@FXML Label lbCorreo;
	@FXML Label lbTelefono;
	@FXML Label lbFechaNacimiento;
	@FXML Button btCancelar;
	@FXML Button btBorrar;

	
	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
		lbNombre.setText(huesped.getNombre());
		lbDni.setText(huesped.getDni());
		lbCorreo.setText(huesped.getCorreo());
		lbTelefono.setText(huesped.getTelefono());
		lbFechaNacimiento.setText(huesped.getFechaNacimiento().
				format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
	}
	
	@FXML
	private void cancelar() {
		Stage escena = (Stage) btCancelar.getScene().getWindow();
	    escena.close();
	}
	
	@FXML
	private void borrar() {
		Stage propietario = (Stage) btBorrar.getScene().getWindow();
		try {
			VistaGrafica.getInstancia().getControlador().borrar(huesped);
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Borrar Huésped", e.getMessage(), propietario);
		}
		Dialogos.mostrarDialogoInformacion("Borrar Huésped", "Huésped borrado satisfactoriamente", propietario);
	}

}
