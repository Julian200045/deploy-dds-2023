package models.services.calculadorConfianza.moldes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import models.services.calculadorConfianza.requests.RequestCalculadorConfianza;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface CalculadorConfianzaGrupo2 {

  @POST("comunidad/usuarios/")
  Call<InformeConfianza> calcularConfianza(@Body RequestCalculadorConfianza request);

}
