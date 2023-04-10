package domain.estaciones;

public class Ubicacion {
    private Double latitud;
    private Double longitud;
    private String calle;
    private Integer altura;

    public Ubicacion(Double latitud, Double longitud, String calle, Integer altura) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.calle = calle;
        this.altura = altura;
    }
}
