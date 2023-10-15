package models.services.calculadorConfianza;

import models.services.LectorPropiedades;
import models.services.calculadorConfianza.moldes.CalculadorConfianzaGrupo2;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import models.services.calculadorConfianza.moldes.InformeConfianza;
import models.services.calculadorConfianza.requests.RequestCalculadorConfianza;

import java.io.IOException;

public class CalculadorConfianza implements CalculadorConfianzaService{
  private static String url;

  private final Retrofit retrofit;

  public CalculadorConfianza(String pathPropiedades) throws IOException {
    LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);
    url = lectorPropiedades.getPropiedad("url-calculador-confianza");
    this.retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public InformeConfianza calcularConfianza(RequestCalculadorConfianza request) throws IOException {
    CalculadorConfianzaGrupo2 calculadorConfianza = this.retrofit.create(CalculadorConfianzaGrupo2.class);
    Call<InformeConfianza> requestInformeConfianza = calculadorConfianza.calcularConfianza(request);
    Response<InformeConfianza> responseInformeConfianza = requestInformeConfianza.execute();

    return responseInformeConfianza.body();
  }
}
