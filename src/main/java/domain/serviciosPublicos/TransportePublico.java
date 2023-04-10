package domain.serviciosPublicos;

import domain.estaciones.Estacion;

public interface TransportePublico {
    Estacion estacionOrigen();
    Estacion estacionDestino();
}