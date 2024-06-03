package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped.FORMATO_FECHA;

public class ControladorListarHuespedes {
	
	//private IControlador controladorMVC;
	
	@FXML TableColumn<Huesped, String> tcNombre;
	@FXML TableColumn<Huesped, String> tcDni;
	@FXML TableColumn<Huesped, String> tcCorreo;
	@FXML TableColumn<Huesped, String> tcTelefono;
	@FXML TableColumn<Huesped, String> tcFechaNacimiento;
	@FXML TableView<Huesped> tvHuespedes;
	@FXML Button btAceptar;
	List<Huesped> listaPersonas;
	private ObservableList<Huesped> listaPersonasVisible;
	private Huesped registro;

	private String filtro;

	public void inicializa() {
		this.tvHuespedes.setItems(FXCollections.observableArrayList(
				VistaGrafica.getInstancia().getControlador().getHuespedes()));
	}

	@FXML
	public void initialize() {
		tcNombre.setCellValueFactory(huesped -> new SimpleStringProperty(huesped.getValue().getNombre()));
		tcDni.setCellValueFactory(huesped -> new SimpleStringProperty(huesped.getValue().getDni()));
		tcCorreo.setCellValueFactory(huesped -> new SimpleStringProperty(huesped.getValue().getCorreo()));
		tcTelefono.setCellValueFactory(huesped -> new SimpleStringProperty(huesped.getValue().getTelefono()));
		tcFechaNacimiento.setCellValueFactory(huesped -> new SimpleStringProperty(huesped.getValue().
				getFechaNacimiento().format(DateTimeFormatter.ofPattern(FORMATO_FECHA))));

		/*tvHuespedes.setItems(FXCollections.observableArrayList(
				VistaGrafica.getInstancia().getControlador().getHuespedes()));*/

		this.filtro = "";
		listaPersonasVisible = FXCollections.observableArrayList();
		this.tvHuespedes.setItems(listaPersonasVisible);
		this.listaPersonas = VistaGrafica.getInstancia().getControlador().getHuespedes();
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
		this.registro = this.tvHuespedes.getSelectionModel().getSelectedItem();
	}

	private void refrescarTabla()
	{
		this.registro = null;
		this.tvHuespedes.getSelectionModel().select(null);

		this.listaPersonasVisible.clear();
		this.listaPersonas = VistaGrafica.getInstancia().getControlador().getHuespedes();

		for(Huesped p:this.listaPersonas)
		{
			if(this.filtro.isEmpty() || p.getNombre().toLowerCase().contains(filtro.toLowerCase()))
			{
				this.listaPersonasVisible.add(p);
			}
			//this.listaPersonasVisible.add(p);
		}

		this.tvHuespedes.refresh();
	}
}
