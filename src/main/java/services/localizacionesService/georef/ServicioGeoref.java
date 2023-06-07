package services.localizacionesService.georef;

import domain.localizaciones.Departamento;
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
import services.localizacionesService.georef.moldes.DepartamentoMolde;
import services.localizacionesService.georef.moldes.GeorefService;
import services.localizacionesService.georef.moldes.ListaDepartamentosMolde;
import services.localizacionesService.georef.moldes.ListaMunicipiosMolde;
import services.localizacionesService.georef.moldes.ListaProvinciasMolde;
import services.localizacionesService.georef.moldes.MunicipioMolde;
import services.localizacionesService.georef.moldes.ProvinciaMolde;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServicioGeoref implements LocalizacionesService {
  private static Integer maximaCantidadRegistrosProvincias;
  private static Integer maximaCantidadRegistrosMunicipios;
  private static Integer maximaCantidadRegistrosDepartamentos;
  private static String urlGeorefApi;

  private Retrofit retrofit;

  private static List<ProvinciaMolde> _provincias;
  private static List<MunicipioMolde> _municipios;
  private static List<DepartamentoMolde> _departamentos;


  public ServicioGeoref(String pathPropiedades) throws IOException {

    LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);

    if(!georefInstanciado()) {
      urlGeorefApi = lectorPropiedades.getPropiedad("url-georef-api");
      maximaCantidadRegistrosProvincias = lectorPropiedades.getPropiedadInt("maximaCantidadRegistrosProvincias");
      maximaCantidadRegistrosMunicipios = lectorPropiedades.getPropiedadInt("maximaCantidadRegistrosMunicipios");
      maximaCantidadRegistrosDepartamentos = lectorPropiedades.getPropiedadInt("maximaCantidadRegistrosDepartamentos");
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

  public Departamento departamento(Integer id) throws IOException {

    if (primeraPeticion()) cargarMoldes();

    DepartamentoMolde departamentoMolde = _departamentos.stream().filter(departamento -> Objects.equals(departamento.id, id)).findFirst().get();
    return moldeADepartamento(departamentoMolde);

  }

  private Municipio moldeAMunicipio(MunicipioMolde molde) {
    return new Municipio(molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(),
            molde.centroide.getLon()));
  }

  private Departamento moldeADepartamento(DepartamentoMolde molde) {
    return new Departamento(molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(),
            molde.centroide.getLon()));
  }

  private Provincia moldeAProvincia(ProvinciaMolde molde) {
    return new Provincia(
        molde.id,
        molde.nombre,
        new Ubicacion(molde.centroide.getLat(), molde.centroide.getLon()),
        municipiosDeLaProvinciaMolde(molde),
        departamentosDeLaProvinciaMolde(molde));
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

  private List<DepartamentoMolde> listaDepartamentosMolde() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListaDepartamentosMolde> requestDepartamentosArgentinos = georefService.departamentosMax(maximaCantidadRegistrosDepartamentos);
    Response<ListaDepartamentosMolde> responseDepartamentosArgentinos = requestDepartamentosArgentinos.execute();

    return responseDepartamentosArgentinos.body().departamentos;
  }

  private List<Municipio> municipiosDeLaProvinciaMolde(ProvinciaMolde provinciaMolde) {
    List<MunicipioMolde> municipiosMolde = _municipios.stream().filter(municipio -> municipio.provincia.id == provinciaMolde.id).collect(Collectors.toList());
    return municipiosMolde.stream().map(municipio -> moldeAMunicipio(municipio)).collect(Collectors.toList());
  }

  private List<Departamento> departamentosDeLaProvinciaMolde(ProvinciaMolde provinciaMolde) {
    List<DepartamentoMolde> departamentosMolde = _departamentos.stream().filter(departamento -> departamento.provincia.id == provinciaMolde.id).collect(Collectors.toList());
    return departamentosMolde.stream().map(departamento -> moldeADepartamento(departamento)).collect(Collectors.toList());
  }

  private Boolean georefInstanciado() {
    return urlGeorefApi != null
        && maximaCantidadRegistrosProvincias != null
        && maximaCantidadRegistrosMunicipios != null
        && maximaCantidadRegistrosDepartamentos != null;
  }

  private Boolean primeraPeticion() {
    return
    (_provincias == null || _provincias.isEmpty())
    && (_municipios == null || _municipios.isEmpty())
    && (_departamentos == null || _departamentos.isEmpty());
  }

  private void cargarMoldes() throws IOException {
    _provincias = listaProvinciasMolde();
    _municipios = listaMunicipiosMolde();
    _departamentos = listaDepartamentosMolde();
  }
}
