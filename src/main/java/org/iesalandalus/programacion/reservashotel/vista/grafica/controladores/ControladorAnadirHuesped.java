package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;

import java.time.LocalDate;

public class ControladorAnadirHuesped {
	
	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_TELEFONO = "\\d{9}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	//public static final String FORMATO_FECHA = "dd/MM/yyyy";

	
	@FXML private TextField tfNombre;
	@FXML private TextField tfDni;
	@FXML private TextField tfCorreo;
	@FXML private TextField tfTelefono;
	@FXML private DatePicker dpFechaNacimiento;
	@FXML private Button btAnadir;
	@FXML private Button btCancelar;
	
	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfNombre));
		tfDni.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_DNI, tfDni));
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
		tfTelefono.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_TELEFONO, tfTelefono));
		//tfFechaNacimiento.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfFechaNacimiento));

	}

	@FXML
	private void anadirHuesped() {
		Huesped huesped = null;
		try {
			huesped = getHuesped();
			VistaGrafica.getInstancia().getControlador().insertar(huesped);
			Dialogos.mostrarDialogoInformacion("Añadir Huésped",
					"Huésped añadido satisfactoriamente", null);
			((Stage) btAnadir.getScene().getWindow()).close();
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Huésped", e.getMessage());
		}	
	}
	
	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}
	
    public void inicializa() {
    	tfNombre.setText("");
    	compruebaCampoTexto(ER_OBLIGATORIO, tfNombre);
		tfDni.setText("");
		compruebaCampoTexto(ER_DNI, tfDni);
    	tfCorreo.setText("");
    	compruebaCampoTexto(ER_CORREO, tfCorreo);
    	tfTelefono.setText("");
    	compruebaCampoTexto(ER_TELEFONO, tfTelefono);
    }
	
	private void compruebaCampoTexto(String er, TextField campoTexto) {	
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		}
		else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}
	
	private Huesped getHuesped() {
		String nombre = tfNombre.getText();
		String dni = tfDni.getText();
		String correo = tfCorreo.getText();
		String telefono = tfTelefono.getText();
		LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
		return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
	}

}
