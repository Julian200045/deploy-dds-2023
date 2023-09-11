package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import containers.Comunidad;
import containers.ListaPropuestas;
import servicios.fusionadorcomunidades.FusionadorComunidades;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class FusionComunidadesController implements Handler {
  private final FusionadorComunidades fusionadorComunidades;

  public FusionComunidadesController(FusionadorComunidades fusionadorComunidades) {
    this.fusionadorComunidades = fusionadorComunidades;
  }

  @Override
  public void handle(@NotNull Context context) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String body = context.body();
    List<Comunidad> fusiones = new ArrayList<>();

    ListaPropuestas listaPropuestas = mapper.readValue(body, ListaPropuestas.class);
    listaPropuestas.getPropuestas().forEach(propuesta -> fusiones.add(fusionadorComunidades.fusionarComunidades(propuesta)));

    context.json(fusiones);
  }
}
