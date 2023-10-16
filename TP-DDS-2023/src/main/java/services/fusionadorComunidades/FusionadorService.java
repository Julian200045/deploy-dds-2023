package services.fusionadorComunidades;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import services.fusionadorComunidades.moldes.ListaFusionesComunidades;
import services.fusionadorComunidades.moldes.ListaPropuestasFusion;
import services.fusionadorComunidades.request.RequestPropuestasComunidad;
import services.fusionadorComunidades.request.RequestSugerenciasFusion;

public interface FusionadorService {
	//@GET("fusion_comunidades/")
	@HTTP(method = "POST", path = "fusion_comunidades", hasBody = true)
	Call<ListaFusionesComunidades> fusiones(@Body RequestPropuestasComunidad request);

	@GET("sugerencias_fusiones/")
	Call<ListaPropuestasFusion> propuestas(@Body RequestSugerenciasFusion request);
}
