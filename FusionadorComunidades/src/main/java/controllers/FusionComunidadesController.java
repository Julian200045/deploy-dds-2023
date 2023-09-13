package controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import containers.Comunidad;
import containers.RequestPropuestasComunidad;
import dtos.RespuestaFusionComunidades;
import io.javalin.http.HttpStatus;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiRequestBody;
import io.javalin.openapi.OpenApiResponse;
import java.util.Map;
import servicios.fusionadorcomunidades.FusionadorComunidades;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class FusionComunidadesController implements Handler {
  private final FusionadorComunidades fusionadorComunidades;
  Map<String, String> mensajesDeError;
  public FusionComunidadesController(FusionadorComunidades fusionadorComunidades, Map<String, String> mensajesDeError) {
    this.fusionadorComunidades = fusionadorComunidades;
    this.mensajesDeError = mensajesDeError;
  }

  @OpenApi(
      summary = "Toma todas las fusiones de comunidades",
      path = "/fusion_comunidades",
      methods = HttpMethod.GET,
      tags = {"FusionComunidades"},
      requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = RequestPropuestasComunidad.class)}),
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = RespuestaFusionComunidades.class)}),
          @OpenApiResponse(status = "400", content = {@OpenApiContent(from = String.class)})
      }
  )
  @Override
  public void handle(@NotNull Context context) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String body = context.body();
    List<Comunidad> fusiones = new ArrayList<>();

    try {
      RequestPropuestasComunidad listaPropuestas = mapper.readValue(body, RequestPropuestasComunidad.class);
      listaPropuestas.getPropuestas().forEach(propuesta -> fusiones.add(fusionadorComunidades.fusionarComunidades(propuesta.getComunidades())));

      RespuestaFusionComunidades respuestaFusionComunidades = new RespuestaFusionComunidades();
      respuestaFusionComunidades.setFusiones(fusiones);

      context.json(respuestaFusionComunidades);
    } catch (JsonMappingException e) {
      context.status(HttpStatus.BAD_REQUEST);
      context.result(mensajesDeError.get("mensaje-error-mappeo"));
    } catch (NullPointerException nullPointerException) {
      context.status(HttpStatus.BAD_REQUEST);
      context.result(mensajesDeError.get("mensaje-error-body"));
    }
  }
}