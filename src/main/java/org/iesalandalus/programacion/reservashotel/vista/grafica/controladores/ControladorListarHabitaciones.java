package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.vista.Vista;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped.FORMATO_FECHA;
import static org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion.*;
/*
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
*/

public class ControladorListarHabitaciones {


	@FXML TableColumn<Habitacion, String> tcIdentificador;
	@FXML TableColumn<Habitacion, String> tcPrecio;
	@FXML TableColumn<Habitacion, String> tcTipoHabitacion;
	@FXML TableColumn<Habitacion, String> tcCapacidad;
	@FXML TableColumn<Habitacion, String> tcBanios;
	@FXML TableColumn<Habitacion, String> tcCamasIndividuales;
	@FXML TableColumn<Habitacion, String> tcCamasDobles;
	@FXML TableColumn<Habitacion, String> tcJacuzzi;
	@FXML TableView<Habitacion> tvHabitaciones;
	@FXML Button btAceptar;
	@FXML ChoiceBox<String> tipoHabitacion;
	List<Habitacion> listaHabitaciones;
	private ObservableList<Habitacion> listaHabitacionVisible;
	private Habitacion registro;

	private String filtro;


	public void inicializa() {
		this.tvHabitaciones.setItems(FXCollections.observableArrayList(
				VistaGrafica.getInstancia().getControlador().getHabitaciones()));
	}

	@FXML
	private void initialize() {
		tcIdentificador.setCellValueFactory(habitacion -> new SimpleStringProperty(habitacion.getValue().getIdentificador()));
		tcPrecio.setCellValueFactory(habitacion -> new SimpleStringProperty(Double.toString(habitacion.getValue().getPrecio())));
		tcTipoHabitacion.setCellValueFactory(habitacion -> new SimpleStringProperty(getTipoHabitacionString(habitacion.getValue())));
		tcCapacidad.setCellValueFactory(habitacion -> new SimpleStringProperty(Integer.toString(habitacion.getValue().getNumeroMaximoPersonas())));
		tcBanios.setCellValueFactory(habitacion -> new SimpleStringProperty(Integer.toString(getBaniosInt(habitacion.getValue()))));
		tcCamasIndividuales.setCellValueFactory(habitacion -> new SimpleStringProperty(Integer.toString(getCamasIndividualesInt(habitacion.getValue()))));
		tcCamasDobles.setCellValueFactory(habitacion -> new SimpleStringProperty(Integer.toString(getCamasDoblesInt(habitacion.getValue()))));
		tcJacuzzi.setCellValueFactory(habitacion -> new SimpleStringProperty(getCamasJacuzziString(habitacion.getValue())));

		//ChoiceBox<String> tipoHabitacion = new ChoiceBox<>();
		this.tipoHabitacion.setItems(FXCollections.observableArrayList(SIMPLE.name(), DOBLE.name(),
				TRIPLE.name(), SUITE.name()));


		this.filtro = "";
		listaHabitacionVisible = FXCollections.observableArrayList();
		this.tvHabitaciones.setItems(listaHabitacionVisible);
		this.listaHabitaciones = VistaGrafica.getInstancia().getControlador().getHabitaciones();
		this.refrescarTabla();
	}
	
	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
	    escena.close();
	}

	@FXML
	void Seleccionar(MouseEvent event)
	{
		this.registro = this.tvHabitaciones.getSelectionModel().getSelectedItem();
	}


	private void refrescarTabla()
	{
		this.registro = null;
		this.tvHabitaciones.getSelectionModel().select(null);

		this.listaHabitacionVisible.clear();
		this.listaHabitaciones = VistaGrafica.getInstancia().getControlador().getHabitaciones();

		for(Habitacion p:this.listaHabitaciones)
		{
			if(this.filtro.isEmpty() || p.getIdentificador().contains(filtro))
			{
				this.listaHabitacionVisible.add(p);
			}
			//this.listaPersonasVisible.add(p);
		}

		this.tvHabitaciones.refresh();
	}


	private int getBaniosInt(Habitacion habitacion) {
		int numBanios = 0;
		if (habitacion instanceof Triple) {
			numBanios = ((Triple) habitacion).getNumBanos();
		}else if (habitacion instanceof Suite) {
			numBanios = ((Suite) habitacion).getNumBanos();
		}
		return numBanios;
	}

	private int getCamasIndividualesInt(Habitacion habitacion) {
		int numCamas = 0;
		if (habitacion instanceof Doble) {
			numCamas = ((Doble) habitacion).getNumCamasIndividuales();
		}else if (habitacion instanceof Triple) {
			numCamas = ((Triple) habitacion).getNumCamasIndividuales();
		}
		return numCamas;
	}

	private int getCamasDoblesInt(Habitacion habitacion) {
		int numCamas = 0;
		if (habitacion instanceof Doble) {
			numCamas = ((Doble) habitacion).getNumCamasDobles();
		}else if (habitacion instanceof Triple) {
			numCamas = ((Triple) habitacion).getNumCamasDobles();
		}
		return numCamas;
	}

	private String getCamasJacuzziString(Habitacion habitacion) {
		String tieneJacuzzi = "No";
		if (habitacion instanceof Suite && ((Suite) habitacion).isTieneJacuzzi()) {
			tieneJacuzzi = "Si";
		}
		return tieneJacuzzi;
	}

	private String getTipoHabitacionString(Habitacion habitacion) {
		String tipoHab;

		if (habitacion instanceof Simple) {
			tipoHab = "Simple";
		}else if (habitacion instanceof Doble) {
			tipoHab = "Doble";;
		}else if (habitacion instanceof Triple) {
			tipoHab = "Triple";;
		}else {
			tipoHab = "Suite";;
		}
		return tipoHab;
	}
}
