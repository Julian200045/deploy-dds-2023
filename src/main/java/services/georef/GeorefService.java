package services.georef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import services.georef.entities.ListaDepartamentosMolde;
import services.georef.entities.ListaMunicipiosMolde;
import services.georef.entities.ListaProvinciasMolde;

public interface GeorefService {
  @GET("provincias")
  Call<ListaProvinciasMolde> provincias();

  @GET("provincias")
  Call<ListaProvinciasMolde> provinciasMax(@Query("max") int max);

  @GET("departamentos")
  Call<ListaDepartamentosMolde> departamentos();

  @GET("departamentos")
  Call<ListaDepartamentosMolde> departamentosMax(@Query("max") int max);

  @GET("departamentos")
  Call<ListaDepartamentosMolde> departamentos(@Query("provincia") int idProvincia);

  @GET("municipios")
  Call<ListaMunicipiosMolde> municipios();

  @GET("municipios")
  Call<ListaMunicipiosMolde> municipiosMax(@Query("max") int max);

  @GET("municipios")
  Call<ListaMunicipiosMolde> municipios(@Query("provincia") int idProvincia);

}
