package services.calculadorConfianza;

import services.calculadorConfianza.moldes.InformeConfianza;
import services.calculadorConfianza.requests.RequestCalculadorConfianza;

import java.io.IOException;

public interface CalculadorConfianzaService {

  InformeConfianza calcularConfianza(RequestCalculadorConfianza request) throws IOException;

}
