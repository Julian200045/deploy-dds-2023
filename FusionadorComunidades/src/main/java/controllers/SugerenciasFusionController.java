package controllers;

import analizadorComunidades.AnalizadorComunidades;
import com.fasterxml.jackson.databind.ObjectMapper;
import containers.Comunidad;
import containers.ListaComunidades;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SugerenciasFusionController implements Handler {

  AnalizadorComunidades analizadorComunidades;
  public SugerenciasFusionController(AnalizadorComunidades analizadorComunidades){
    this.analizadorComunidades = analizadorComunidades;
  }

  @Override
  public void handle(@NotNull Context context) throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    String body = context.body();

    ListaComunidades listaComunidades = mapper.readValue(body,ListaComunidades.class);

    List<List<Comunidad>> propuestasFusion = analizadorComunidades.generarPropuestasFusion(listaComunidades.getComunidades());

    context.json(propuestasFusion);
  }
}
