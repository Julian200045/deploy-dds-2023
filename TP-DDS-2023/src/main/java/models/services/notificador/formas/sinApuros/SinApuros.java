package models.services.notificador.formas.sinApuros;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioNotificaciones;
import models.services.notificador.EstadoEnvio;
import models.services.notificador.Notificacion;
import models.services.notificador.formas.FormasNotificar;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class SinApuros extends FormasNotificar {

  private RepositorioNotificaciones repoNotificaciones;

  public SinApuros(RepositorioNotificaciones repoNotificaciones) {
    this.repoNotificaciones = repoNotificaciones;
  }

  public void notificar(Notificacion notificacion) throws SchedulerException, IOException {
    Usuario usuario = notificacion.getUsuario();
    if (usuario.estaDisponible(LocalDateTime.now())) {
      enviarNotificacion(notificacion);
    }
  }

  public void iniciarEnvioNotificaciones() throws SchedulerException {
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler = schedulerFactory.getScheduler();

    scheduler.start();

    JobDetail job = newJob(Notificar.class)
        .withIdentity("notificar")
        .build();

    job.getJobDataMap().put("servicioNotificador", this);

    Trigger trigger = newTrigger()
        .withIdentity("everyMinute")
        .startNow()
        .withSchedule(simpleSchedule()
            .withIntervalInMinutes(1)
            .repeatForever())
        .forJob("notificar")
        .build();

    scheduler.scheduleJob(trigger);
  }

  public void notificarPendientes() {
    List<Notificacion> notificacionesPendientes = (List<Notificacion>) repoNotificaciones.buscarPorEstado(EstadoEnvio.PENDIENTE);
    notificacionesPendientes.forEach(notificacion ->
        {
          try {
            enviarNotificacion(notificacion);
          } catch (IOException e) {
            e.printStackTrace();
          }

        }
    );
  }
}
