package services.georef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import services.georef.entities.ListaMunicipiosEntity;
import services.georef.entities.ListaProvinciasEntity;

public interface GeorefService {
  @GET("provincias")
  Call<ListaProvinciasEntity> provincias();

  @GET("provincias")
  Call<ListaProvinciasEntity> provincias(@Query("campos") String campos);

  @GET("municipios")
  Call<ListaMunicipiosEntity> municipios(@Query("provincia") int idProvincia);

  @GET("municipios")
  Call<ListaMunicipiosEntity> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

  @GET("municipios")
  Call<ListaMunicipiosEntity> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max);
}
