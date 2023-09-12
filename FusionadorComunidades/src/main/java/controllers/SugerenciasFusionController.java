package controllers;

import servicios.analizadorcomunidades.AnalizadorComunidades;
import com.fasterxml.jackson.databind.ObjectMapper;
import containers.Comunidad;
import containers.ListaComunidades;
import dtos.RespuestaPropuestaFusion;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class SugerenciasFusionController implements Handler {

  AnalizadorComunidades analizadorComunidades;

  public SugerenciasFusionController(AnalizadorComunidades analizadorComunidades) {
    this.analizadorComunidades = analizadorComunidades;
  }

  @Override
  public void handle(@NotNull Context context) throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    String body = context.body();

    ListaComunidades listaComunidades = mapper.readValue(body, ListaComunidades.class);

    List<List<Comunidad>> propuestasFusion = new ArrayList<>(analizadorComunidades.generarPropuestasFusion(listaComunidades.getComunidadesAFusionar()));
    propuestasFusion.removeAll(listaComunidades.getPropuestasAnteriores());

    RespuestaPropuestaFusion respuestaPropuestaFusion = new RespuestaPropuestaFusion();
    respuestaPropuestaFusion.setPropuestas(propuestasFusion);
    if(propuestasFusion.isEmpty()) {
      respuestaPropuestaFusion.setMensaje("No hubo coincidencias válidas entre las comunidades. No se logró generar ninguna propuesta de fusión.");
      // quizás configurar un mensaje en las properties.
    }
    else {
      respuestaPropuestaFusion.setMensaje("Propuestas de fusión generadas correctamente.");
    }
    context.json(respuestaPropuestaFusion);
  }
}
