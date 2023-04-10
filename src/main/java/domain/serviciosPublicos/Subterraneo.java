package domain.serviciosPublicos;

import domain.estaciones.Estacion;

import java.util.ArrayList;
import java.util.List;

public class Subterraneo implements TransportePublico{
    private String nombre;
    private List<Estacion> estaciones = new ArrayList<>();;

    @Override
    public Estacion estacionOrigen() {
        return estaciones.get(0);
    }

    @Override
    public Estacion estacionDestino() {
        return estaciones.get(estaciones.size()-1);
    }
}
