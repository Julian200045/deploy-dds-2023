package server.utils;

import io.javalin.http.Context;
import org.quartz.SchedulerException;

import java.io.IOException;

public interface ICrudViewsHandler {
  void index(Context context); // Devolver una vista que muestre el listado de todos los recursos.
  void show(Context context); // Devolver una vista que muestre el detalle de un recurso.
  void create(Context context) throws IOException; // Devolver una vista que permita crear un recurso
  void save(Context context) throws SchedulerException; // Guardar un recurso.
  void edit(Context context); // Devolver una vista que permita editar un recurso.
  void update(Context context); // Actualizar un recurso.
  void delete(Context context); // Eliminar un recurso.
}