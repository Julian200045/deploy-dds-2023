package services.calculadorConfianza.moldes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import services.calculadorConfianza.requests.RequestCalculadorConfianza;


public interface CalculadorConfianzaGrupo2 {
  @GET("comunidad/usuarios")
  Call<InformeConfianza> calcularConfianza(@Body RequestCalculadorConfianza request);

}
