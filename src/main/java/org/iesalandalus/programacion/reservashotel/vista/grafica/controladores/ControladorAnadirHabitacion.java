package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;

public class ControladorAnadirHabitacion {
/*
	private static final String ANADIR_AULA = "Añadir Aula";
	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_PUESTOS = "0?\\d{1,2}|100";
	
	private IControlador controladorMVC;
	
	@FXML private TextField tfNombre;
	@FXML private TextField tfPuestos;
	@FXML private Button btAnadir;
	@FXML private Button btCancelar;
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfNombre));
		tfPuestos.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_PUESTOS, tfPuestos));
	}
	
	@FXML
	private void anadirAula() {
		Aula aula = null;
		try {
			aula = getAula();
			controladorMVC.insertar(aula);
			Stage propietario = ((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion(ANADIR_AULA, "Aula añadida satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(ANADIR_AULA, e.getMessage());
		}	
	}
	
	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}
	
    public void inicializa() {
    	tfNombre.setText("");
    	compruebaCampoTexto(ER_OBLIGATORIO, tfNombre);
    	tfPuestos.setText("");
    	compruebaCampoTexto(ER_PUESTOS, tfPuestos);
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
	
	private Aula getAula() {
		Aula aula = null;
		try {
			String nombre = tfNombre.getText();
			int puestos = Integer.parseInt(tfPuestos.getText());
			aula = new Aula(nombre, puestos);
		} catch (NumberFormatException e) {
			Dialogos.mostrarDialogoError(ANADIR_AULA, e.getMessage());
		}
		return aula;
	}*/
}
