package domain.establecimientos;

import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import domain.ubicaciones.Ubicacion;

import java.util.List;

public class Establecimiento {
    private String nombre;
    private Ubicacion ubicacion;
    private List<PrestacionDeServicio> prestaciones;
}
