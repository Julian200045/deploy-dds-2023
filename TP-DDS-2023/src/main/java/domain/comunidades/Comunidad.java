package domain.comunidades;

import domain.servicios.PrestacionDeServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;

@Entity
@Table(name = "comunidad")
public class Comunidad {
  @Id
  @GeneratedValue
  long id;
  @Getter
  @Column(name = "nombre")
  private String nombre;
  @ManyToMany
  private final List<Miembro> miembros = new ArrayList<>();

  @ManyToMany
  private final List<Miembro> administradores = new ArrayList<>();
  @ManyToMany
  private final List<PrestacionDeServicio> prestacionDeServicios = new ArrayList<>();



  public Comunidad(String nombre) {
    this.nombre = nombre;
  }

  public void agregarPrestacionDeInteres(PrestacionDeServicio prestacionDeServicio){
    prestacionDeServicios.add(prestacionDeServicio);
  }

  public Boolean prestacionEsDeInteres(PrestacionDeServicio prestacionDeServicio){
    return prestacionDeServicios.contains(prestacionDeServicio);
  }

  public Boolean esMiembro(Miembro miembro){
    return miembros.contains(miembro);
  }

  public void agregarMiembro(Miembro miembro){
    miembros.add(miembro);
  }
  public List<Miembro> getMiembros(){
    return new ArrayList<>(miembros);
  }
}