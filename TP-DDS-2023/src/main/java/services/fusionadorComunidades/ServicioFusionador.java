package services.fusionadorComunidades;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.fusionadorComunidades.moldes.ListaFusionesComunidades;
import services.fusionadorComunidades.moldes.ListaPropuestasFusion;
import services.fusionadorComunidades.request.RequestPropuestasComunidad;
import services.fusionadorComunidades.request.RequestSugerenciasFusion;

public class ServicioFusionador {
	private static ServicioFusionador instancia = null;
	private static final String urlAPI = "http://localhost:8080/";
	private Retrofit retrofit;

	private ServicioFusionador(){
		this.retrofit = new Retrofit.Builder().baseUrl(urlAPI).addConverterFactory(GsonConverterFactory.create()).build();
	}

	public static ServicioFusionador getInstancia(){
		if (instancia == null){
			instancia = new ServicioFusionador();
		}
		return instancia;
	}

	public ListaFusionesComunidades listaFusionesComunidades(RequestPropuestasComunidad request) throws IOException {
		FusionadorService fusionadorService = this.retrofit.create(FusionadorService.class);
		Call<ListaFusionesComunidades> requestFusiones = fusionadorService.fusiones(request);
		Response<ListaFusionesComunidades> responseFusiones = requestFusiones.execute();
		return responseFusiones.body();
	}

	public ListaPropuestasFusion listaPropuestasFusion(RequestSugerenciasFusion request) throws IOException {
		FusionadorService fusionadorService = this.retrofit.create(FusionadorService.class);
		Call<ListaPropuestasFusion> requestPropuestas = fusionadorService.propuestas(request);
		Response<ListaPropuestasFusion> responsePropuestas = requestPropuestas.execute();
		return responsePropuestas.body();
	}
}
