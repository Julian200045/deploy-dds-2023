package services.notificador;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import services.notificador.formas.FormasNotificar;
import services.notificador.formas.SinApuros;

public class Notificar implements Job {

  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    SinApuros formaNotificar = (SinApuros) jobExecutionContext.get("servicioNotificador");
    formaNotificar.notificarPendientes();
  }
}
