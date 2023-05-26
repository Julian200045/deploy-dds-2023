package services.georef;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.georef.entities.ListaMunicipiosEntity;
import services.georef.entities.ListaProvinciasEntity;
import services.georef.entities.ProvinciaEntity;

import java.io.IOException;

public class ServicioGeoref {

  private static ServicioGeoref instancia = null;
  private static int maximaCantidadRegistrosDefault = 200;
  private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
  private Retrofit retrofit;
  private ServicioGeoref() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static ServicioGeoref instancia(){
    if(instancia== null){
      instancia = new ServicioGeoref();
    }
    return instancia;
  }

  public ListaProvinciasEntity listadoDeProvincias() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListaProvinciasEntity> requestProvinciasArgentinas = georefService.provincias();
    Response<ListaProvinciasEntity> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();
  }

  public ListaMunicipiosEntity listadoDeMunicipiosDeProvincia(ProvinciaEntity provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListaMunicipiosEntity> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
    Response<ListaMunicipiosEntity> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }
}
