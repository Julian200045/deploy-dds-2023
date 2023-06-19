package services.localizacionesService.georef;

import domain.localizaciones.Localidad;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;
import domain.ubicaciones.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import services.LectorPropiedades;
import services.localizacionesService.LocalizacionesService;
import services.localizacionesService.georef.moldes.ListaLocalidadesMolde;
import services.localizacionesService.georef.moldes.LocalidadMolde;
import services.localizacionesService.georef.moldes.GeorefService;
import services.localizacionesService.georef.moldes.ListaMunicipiosMolde;
import services.localizacionesService.georef.moldes.ListaProvinciasMolde;
import services.localizacionesService.georef.moldes.MunicipioMolde;
import services.localizacionesService.georef.moldes.ProvinciaMolde;

public class ServicioGeoref implements LocalizacionesService {
  private static Integer maximaCantidadRegistrosProvincias;
  private static Integer maximaCantidadRegistrosMunicipios;
  private static Integer maximaCantidadRegistrosLocalidades;
  private static String urlGeorefApi;

  private final Retrofit retrofit;

  private static List<ProvinciaMolde> _provincias;
  private static List<MunicipioMolde> _municipios;
  private static List<LocalidadMolde> _localidades;


  public ServicioGeoref(String pathPropiedades) throws IOException {

    LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);

    if(!georefInstanciado()) {
      urlGeorefApi = lectorPropiedades.getPropiedad("url-georef-api");
      maximaCantidadRegistrosProvincias = lectorPropiedades.getPropiedadInt("maximaCantidadRegistrosProvincias");
      maximaCantidadRegistrosMunicipios = lectorPropiedades.getPropiedadInt("maximaCantidadRegistrosMunicipios");
      maximaCantidadRegistrosLocalidades = lectorPropiedades.getPropiedadInt("maximaCantidadRegistrosLocalidades");
    }

    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlGeorefApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public Provincia provincia(Integer id) throws IOException {

    if (primeraPeticion()) cargarMoldes();

    ProvinciaMolde provinciaMolde = _provincias.stream().filter(provincia -> Objects.equals(provincia.id, id)).findFirst().get();
    return moldeAProvincia(provinciaMolde);
  }

  public Municipio municipio(Integer id) throws IOException {

    if (primeraPeticion()) cargarMoldes();

    MunicipioMolde municipioMolde = _municipios.stream().filter(municipio -> Objects.equals(municipio.id, id)).findFirst().get();
    return moldeAMunicipio(municipioMolde);
  }

  public Localidad localidad(Integer id) throws IOException {

    if (primeraPeticion()) cargarMoldes();

    LocalidadMolde localidadMolde = _localidades.stream().filter(localidad -> Objects.equals(localidad.id, id)).findFirst().get();
    return moldeALocalidad(localidadMolde);

  }

  private Localidad moldeALocalidad(LocalidadMolde molde) {
    return new Localidad(molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(),
            molde.centroide.getLon()),
        moldeAMunicipio(_municipios.stream().filter(municipioMolde -> municipioMolde.id == molde.municipio.id).toList().get(0)),
        moldeAProvincia(_provincias.stream().filter(provinciaMolde -> provinciaMolde.id == molde.provincia.id).toList().get(0)));
  }

  private Municipio moldeAMunicipio(MunicipioMolde molde) {
    return new Municipio(molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(),
            molde.centroide.getLon()),
        moldeAProvincia(_provincias.stream().filter(provinciaMolde -> provinciaMolde.id == molde.provincia.id).toList().get(0)),
        localidadesDelMunicipioMolde(molde));
  }

  private Provincia moldeAProvincia(ProvinciaMolde molde) {
    return new Provincia(
        molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(), molde.centroide.getLon()),
        municipiosDeLaProvinciaMolde(molde),
        localidadesDeLaProvinciaMolde(molde));
  }

  private List<ProvinciaMolde> listaProvinciasMolde() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListaProvinciasMolde> requestProvinciasArgentinas = georefService.provinciasMax(maximaCantidadRegistrosProvincias);
    Response<ListaProvinciasMolde> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();

    return responseProvinciasArgentinas.body().provincias;
  }

  private List<MunicipioMolde> listaMunicipiosMolde() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListaMunicipiosMolde> requestMunicipiosArgentinos = georefService.municipiosMax(maximaCantidadRegistrosMunicipios);
    Response<ListaMunicipiosMolde> responseMunicipiosArgentinos = requestMunicipiosArgentinos.execute();

    return responseMunicipiosArgentinos.body().municipios;
  }

  private List<LocalidadMolde> listaLocalidadesMolde() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListaLocalidadesMolde> requestlocalidadesArgentinos = georefService.localidadesMax(maximaCantidadRegistrosLocalidades);
    Response<ListaLocalidadesMolde> responselocalidadesArgentinos = requestlocalidadesArgentinos.execute();

    return responselocalidadesArgentinos.body().localidades;
  }

  private List<Municipio> municipiosDeLaProvinciaMolde(ProvinciaMolde provinciaMolde) {
    List<MunicipioMolde> municipiosMolde = _municipios.stream().filter(municipio -> municipio.provincia.id == provinciaMolde.id).toList();
    return municipiosMolde.stream().map(municipio -> moldeAMunicipio(municipio)).collect(Collectors.toList());
  }

  private List<Localidad> localidadesDeLaProvinciaMolde(ProvinciaMolde provinciaMolde) {
    List<LocalidadMolde> localidadesMolde = _localidades.stream().filter(localidad -> localidad.provincia.id == provinciaMolde.id).toList();
    return localidadesMolde.stream().map(localidad -> moldeALocalidad(localidad)).collect(Collectors.toList());
  }

  private List<Localidad> localidadesDelMunicipioMolde(MunicipioMolde municipioMolde) {
    List<LocalidadMolde> localidadesMolde = _localidades.stream().filter(localidad -> localidad.municipio.id == municipioMolde.id).toList();
    return localidadesMolde.stream().map(localidad -> moldeALocalidad(localidad)).collect(Collectors.toList());
  }

  private Boolean georefInstanciado() {
    return urlGeorefApi != null
        && maximaCantidadRegistrosProvincias != null
        && maximaCantidadRegistrosMunicipios != null
        && maximaCantidadRegistrosLocalidades != null;
  }

  private Boolean primeraPeticion() {
    return
    (_provincias == null || _provincias.isEmpty())
    && (_municipios == null || _municipios.isEmpty())
    && (_localidades == null || _localidades.isEmpty());
  }

  private void cargarMoldes() throws IOException {
    _provincias = listaProvinciasMolde();
    _municipios = listaMunicipiosMolde();
    _localidades = listaLocalidadesMolde();
  }
}
