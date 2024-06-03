package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
/*
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.iugventanas.utilidades.Dialogos;
*/

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ControladorAnadirReserva {
/*

	private IControlador controladorMVC;
	private ObservableList<Aula> aulas = FXCollections.observableArrayList();
	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();

	@FXML private ListView<Aula> lvAula;
    @FXML private ListView<Profesor> lvProfesor;
    @FXML private DatePicker dpDia;
    @FXML private RadioButton rbTramo;
    @FXML private RadioButton rbHora;
    @FXML private ComboBox<Tramo> cbTramo;
    @FXML private TextField tfHora;
    @FXML Button btAceptar;
    @FXML Button btCancelar;

    private ToggleGroup tgHoraTramo = new ToggleGroup();

    private class CeldaAula extends ListCell<Aula> {
        @Override
        public void updateItem(Aula aula, boolean vacia) {
            super.updateItem(aula, vacia);
            if (vacia) {
            	setText("");
            } else {
            	setText(aula.getNombre());
            }
        }
    }

    private class CeldaProfesor extends ListCell<Profesor> {
        @Override
        public void updateItem(Profesor profesor, boolean vacio) {
            super.updateItem(profesor, vacio);
            if (vacio) {
            	setText("");
            } else {
            	setText(profesor.getNombre());
            }
        }
    }

    @FXML
    private void initialize() {
    	cbTramo.setItems(FXCollections.observableArrayList(Tramo.values()));
    	rbTramo.setToggleGroup(tgHoraTramo);
    	rbHora.setToggleGroup(tgHoraTramo);
		tgHoraTramo.selectedToggleProperty().addListener((observable, oldValue, newValue) -> habilitaSeleccion());
		tfHora.setDisable(true);
		cbTramo.setDisable(true);
		lvProfesor.setItems(profesores);
		lvProfesor.setCellFactory(l -> new CeldaProfesor());
		lvAula.setItems(aulas);
		lvAula.setCellFactory(l -> new CeldaAula());
    }

    public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

    public void inicializa() {
    	aulas.setAll(FXCollections.observableArrayList(controladorMVC.getAulas()));
    	profesores.setAll(FXCollections.observableArrayList(controladorMVC.getProfesores()));
    	dpDia.setValue(null);
    	rbHora.setSelected(false);
    	rbTramo.setSelected(false);
    	cbTramo.getSelectionModel().clearSelection();
    	tfHora.setText("");
    }

    private void habilitaSeleccion() {
		RadioButton seleccionado = (RadioButton)tgHoraTramo.getSelectedToggle();
		if (seleccionado == rbHora) {
			tfHora.setDisable(false);
			cbTramo.setDisable(true);
		} else {
			tfHora.setDisable(true);
			cbTramo.setDisable(false);
		}
    }

    @FXML
    void realizarReserva(ActionEvent event) {
    	Aula aula = lvAula.getSelectionModel().getSelectedItem();
    	Profesor profesor = lvProfesor.getSelectionModel().getSelectedItem();
    	LocalDate dia = dpDia.getValue();
    	RadioButton seleccionado = (RadioButton)tgHoraTramo.getSelectedToggle();
		try {
	    	Permanencia permanencia;
			if (seleccionado == rbHora) {
				permanencia = new PermanenciaPorHora(dia, LocalTime.parse(tfHora.getText(), DateTimeFormatter.ofPattern("HH:mm")));
			} else {
				permanencia = new PermanenciaPorTramo(dia, cbTramo.getValue());
			}
			Reserva reserva = new Reserva(profesor, aula, permanencia);
			controladorMVC.insertar(reserva);
			Stage propietario = ((Stage) btAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Realizar Reserva", "Reserva realizada satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Realizar Reserva", e.getMessage());
		}
    }

    @FXML
    void cancelar(ActionEvent event) {
		((Stage) btCancelar.getScene().getWindow()).close();
    }
*/

}