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

  public static List<ProvinciaMolde> _provincias;
  public static List<MunicipioMolde> _municipios;
  public static List<LocalidadMolde> _localidades;


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

    Provincia provinciaDelMunicipio = moldeAProvincia(_provincias.stream().filter(provinciaMolde -> provinciaMolde.id == municipioMolde.provincia.id).findFirst().get());

    return moldeAMunicipio(municipioMolde,provinciaDelMunicipio);
  }

  public Localidad localidad(Integer id) throws IOException {

    if (primeraPeticion()) cargarMoldes();

    LocalidadMolde localidadMolde = _localidades.stream().filter(localidad -> Objects.equals(localidad.id, id)).findFirst().get();

    Provincia provinciaDeLaLocalidad = moldeAProvincia(_provincias.stream().filter(provinciaMolde -> provinciaMolde.id == localidadMolde.provincia.id).findFirst().get());
    Municipio municipioDeLaLocalidad = moldeAMunicipio(_municipios.stream().filter(municipioMolde -> municipioMolde.id == localidadMolde.municipio.id).findFirst().get(),provinciaDeLaLocalidad);

    return moldeALocalidad(localidadMolde,municipioDeLaLocalidad);

  }

  private Localidad moldeALocalidad(LocalidadMolde molde,Municipio municipio) {

    Localidad localidad = new Localidad(molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(),
            molde.centroide.getLon()));

    localidad.setMunicipio(municipio);

    return localidad;
  }

  private Municipio moldeAMunicipio(MunicipioMolde molde,Provincia provincia) {

    Municipio municipio = new Municipio(molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(),
            molde.centroide.getLon()));

    municipio.setProvincia(provincia);
    municipio.setLocalidades(localidadesDelMunicipio(municipio));

    return municipio;
  }

  private Provincia moldeAProvincia(ProvinciaMolde molde) {

    Provincia provincia = new Provincia(
        molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(), molde.centroide.getLon()));

    provincia.setMunicipios(municipiosDeLaProvincia(provincia));

    return provincia;
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

  private List<Municipio> municipiosDeLaProvincia(Provincia provincia) {
    List<MunicipioMolde> municipiosMolde = _municipios.stream().filter(municipio -> Objects.equals(municipio.provincia.id, provincia.id)).toList();
    return municipiosMolde.stream().map(municipio -> moldeAMunicipio(municipio,provincia)).collect(Collectors.toList());
  }

  private List<Localidad> localidadesDelMunicipio(Municipio municipio) {
    List<LocalidadMolde> localidadesMolde = _localidades.stream().filter(localidad -> Objects.equals(localidad.municipio.id, municipio.id)).toList();
    return localidadesMolde.stream().map(localidad -> moldeALocalidad(localidad,municipio)).collect(Collectors.toList());
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
