package services.georef;

import domain.localizaciones.Departamento;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;
import domain.ubicaciones.Ubicacion;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.georef.entities.DepartamentoMolde;
import services.georef.entities.ListaDepartamentosMolde;
import services.georef.entities.ListaMunicipiosMolde;
import services.georef.entities.ListaProvinciasMolde;
import services.georef.entities.MunicipioMolde;
import services.georef.entities.ProvinciaMolde;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioGeoref {

  private static ServicioGeoref instancia = null;
  private static int maximaCantidadRegistrosProvincias = 24;
  private static int maximaCantidadRegistrosMunicipios = 1814;
  private static int maximaCantidadRegistrosDepartamentos = 529;
  private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
  private Retrofit retrofit;

  public static List<ProvinciaMolde> _provincias;
  public static List<MunicipioMolde> _municipios;
  private static List<DepartamentoMolde> _departamentos;


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

  private Municipio moldeAMunicipio(MunicipioMolde entity){
    return new Municipio(entity.id,
        entity.nombre,
        new Ubicacion(entity.centroide.getLat(),
            entity.centroide.getLon()));
  }

  private Departamento moldeADepartamento(DepartamentoMolde entity){
    return new Departamento(entity.id,
        entity.nombre,
        new Ubicacion(entity.centroide.getLat(),
            entity.centroide.getLon()));
  }

  private Provincia moldeAProvincia(ProvinciaMolde entity){
    return new Provincia(
        entity.id,
        entity.nombre,
        new Ubicacion(entity.centroide.getLat(),entity.centroide.getLon()),
        municipiosDeLaProvinciaMolde(entity),
        departamentosDeLaProvinciaMolde(entity));
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

  private List<Municipio> municipiosDeLaProvinciaMolde(ProvinciaMolde provinciaMolde){

    List<MunicipioMolde> municipiosMolde =  _municipios.stream().filter(municipio -> municipio.provincia.id == provinciaMolde.id).collect(Collectors.toList());
    return municipiosMolde.stream().map(municipio -> moldeAMunicipio(municipio)).collect(Collectors.toList());
  }

  private List<Departamento> departamentosDeLaProvinciaMolde(ProvinciaMolde provinciaMolde){
    List<DepartamentoMolde> departamentosMolde = _departamentos.stream().filter(departamento -> departamento.provincia.id == provinciaMolde.id).collect(Collectors.toList());
    return departamentosMolde.stream().map(departamento -> moldeADepartamento(departamento)).collect(Collectors.toList());
  }

  public Provincia provincia(Integer id) throws IOException {
    if(_provincias == null || _provincias.isEmpty()){
      _provincias = listaProvinciasMolde();
    }
    if(_municipios == null || _municipios.isEmpty()){
      _municipios = listaMunicipiosMolde();
    }
    if(_departamentos == null || _departamentos.isEmpty()){
      _departamentos = listaDepartamentosMolde();
    }
    ProvinciaMolde provinciaMolde = _provincias.stream().filter(provincia -> provincia.id == id).findFirst().get();
    return moldeAProvincia(provinciaMolde);
  }

  public Municipio municipio(Integer id) throws IOException {
    if(_municipios == null || _municipios.isEmpty()){
      _municipios = listaMunicipiosMolde();
    }
    MunicipioMolde municipioMolde = _municipios.stream().filter(municipio -> municipio.id == id).findFirst().get();
    return moldeAMunicipio(municipioMolde);
  }

  public Departamento departamento(Integer id) throws IOException {
    if(_departamentos == null || _departamentos.isEmpty()){
      _departamentos = listaDepartamentosMolde();
    }
    DepartamentoMolde departamentoMolde = _departamentos.stream().filter(departamento -> departamento.id == id).findFirst().get();
    return moldeADepartamento(departamentoMolde);

  }

}
