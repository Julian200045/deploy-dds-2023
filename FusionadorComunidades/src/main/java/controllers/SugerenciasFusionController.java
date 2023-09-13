package controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import containers.Comunidad;
import containers.ListaComunidades;
import dtos.RespuestaPropuestaFusion;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiRequestBody;
import io.javalin.openapi.OpenApiResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import servicios.analizadorcomunidades.AnalizadorComunidades;

public class SugerenciasFusionController implements Handler {

  AnalizadorComunidades analizadorComunidades;
  Map<String, String> mensajesDeError;

  public SugerenciasFusionController(AnalizadorComunidades analizadorComunidades, Map<String, String> mensajesDeError) {
    this.analizadorComunidades = analizadorComunidades;
    this.mensajesDeError = mensajesDeError;
  }

  @OpenApi(
      summary = "Toma todas las sugerencias de fusión",
      path = "/sugerencias_fusiones",
      methods = HttpMethod.GET,
      tags = {"SugerenciasFusion"},
      requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = ListaComunidades.class)}),
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = RespuestaPropuestaFusion.class)}),
          @OpenApiResponse(status = "400", content = {@OpenApiContent(from = String.class)})
      }
  )
  @Override
  public void handle(@NotNull Context context) throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    String body = context.body();

    try {
      ListaComunidades listaComunidades = mapper.readValue(body, ListaComunidades.class);
      List<List<Comunidad>> propuestasFusion = new ArrayList<>(analizadorComunidades.generarPropuestasFusion(listaComunidades.getComunidadesAFusionar()));

      propuestasFusion.removeAll(listaComunidades.getPropuestasAExcluir());

      RespuestaPropuestaFusion respuestaPropuestaFusion = new RespuestaPropuestaFusion();
      respuestaPropuestaFusion.setPropuestas(propuestasFusion);

      if (propuestasFusion.isEmpty()) {
        respuestaPropuestaFusion.setMensaje("No hubo coincidencias válidas entre las comunidades. No se logró generar ninguna propuesta de fusión.");
        // quizás configurar un mensaje en las properties.
      } else {
        respuestaPropuestaFusion.setMensaje("Propuestas de fusión generadas correctamente.");
      }
      context.json(respuestaPropuestaFusion);
    } catch (JsonMappingException e) {
      context.status(HttpStatus.BAD_REQUEST);
      context.result(mensajesDeError.get("mensaje-error-mappeo"));
    } catch (NullPointerException nullPointerException) {
      context.status(HttpStatus.BAD_REQUEST);
      context.result(mensajesDeError.get("mensaje-error-body"));
    }
  }
}
