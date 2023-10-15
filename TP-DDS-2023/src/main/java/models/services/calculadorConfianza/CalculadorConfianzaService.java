package models.services.calculadorConfianza;

import models.services.calculadorConfianza.moldes.InformeConfianza;
import models.services.calculadorConfianza.requests.RequestCalculadorConfianza;

import java.io.IOException;

public interface CalculadorConfianzaService {

  InformeConfianza calcularConfianza(RequestCalculadorConfianza request) throws IOException;

}
