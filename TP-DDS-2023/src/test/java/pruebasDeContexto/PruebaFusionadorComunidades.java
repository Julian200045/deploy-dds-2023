package pruebasDeContexto;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import services.fusionadorComunidades.ServicioFusionador;
import services.fusionadorComunidades.moldes.ComunidadMolde;
import services.fusionadorComunidades.moldes.ListaFusionesComunidades;
import services.fusionadorComunidades.moldes.PrestacionDeServicioMolde;
import services.fusionadorComunidades.moldes.Propuesta;
import services.fusionadorComunidades.request.RequestPropuestasComunidad;

public class PruebaFusionadorComunidades {
	public static void main(String[] args) throws IOException {
		ServicioFusionador servicio = ServicioFusionador.getInstancia();

		PrestacionDeServicioMolde prestacionDeServicioMolde1 = new PrestacionDeServicioMolde(0,0,0);
		ComunidadMolde comunidad1 = new ComunidadMolde(0, List.of(1,2,3),List.of(prestacionDeServicioMolde1),1);
		ComunidadMolde comunidad2 = new ComunidadMolde(0, List.of(1,4,5),List.of(prestacionDeServicioMolde1),2);
		Propuesta propuesta1 = new Propuesta(List.of(comunidad1, comunidad2));

		RequestPropuestasComunidad requestPropuestasComunidad = new RequestPropuestasComunidad(List.of(propuesta1));
		ListaFusionesComunidades lista1 = servicio.listaFusionesComunidades(requestPropuestasComunidad);


		Gson gson = new Gson();
		System.out.println(lista1);
	}
}
