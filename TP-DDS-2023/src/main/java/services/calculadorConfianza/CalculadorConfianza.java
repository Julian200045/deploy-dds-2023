package services.calculadorConfianza;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.LectorPropiedades;
import services.calculadorConfianza.moldes.CalculadorConfianzaGrupo2;
import services.calculadorConfianza.moldes.InformeConfianza;
import services.calculadorConfianza.requests.RequestCalculadorConfianza;
import services.localizacionesService.georef.moldes.GeorefService;
import services.localizacionesService.georef.moldes.ListaProvinciasMolde;

import java.io.IOException;

public class CalculadorConfianza implements CalculadorConfianzaService{
  private static String url;

  private final Retrofit retrofit;

  public CalculadorConfianza(String pathPropiedades){
    LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);

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
