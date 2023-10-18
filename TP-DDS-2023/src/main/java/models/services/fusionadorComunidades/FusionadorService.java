package models.services.fusionadorComunidades;

import models.services.fusionadorComunidades.moldes.ListaFusionesComunidades;
import models.services.fusionadorComunidades.moldes.ListaPropuestasFusion;
import models.services.fusionadorComunidades.request.RequestPropuestasComunidad;
import models.services.fusionadorComunidades.request.RequestSugerenciasFusion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface FusionadorService {
	//@GET("fusion_comunidades/")
	@HTTP(method = "POST", path = "fusion_comunidades", hasBody = true)
	Call<ListaFusionesComunidades> fusiones(@Body RequestPropuestasComunidad request);

	@POST("sugerencias_fusiones/")
	Call<ListaPropuestasFusion> propuestas(@Body RequestSugerenciasFusion request);
}
