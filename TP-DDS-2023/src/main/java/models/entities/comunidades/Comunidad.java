package models.entities.comunidades;

import models.entities.servicios.PrestacionDeServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comunidad")
public class Comunidad {
  @Getter
  @Id
  @GeneratedValue
  private long id;
  @Getter
  @Column(name = "nombre")
  private String nombre;
  @OneToMany(mappedBy = "comunidad", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
  private List<Miembro> miembros = new ArrayList<>();
  @ManyToMany(fetch = FetchType.LAZY)
  private List<PrestacionDeServicio> prestacionDeServicios = new ArrayList<>();

  @Getter
  @Setter
  private Double gradoConfianza = 0D;

  public Comunidad(String nombre) {
    this.nombre = nombre;
  }

  public void agregarPrestacionDeInteres(PrestacionDeServicio prestacionDeServicio){
    prestacionDeServicios.add(prestacionDeServicio);
  }

  public Boolean prestacionEsDeInteres(PrestacionDeServicio prestacionDeServicio){
    return prestacionDeServicios.contains(prestacionDeServicio);
  }

  public Boolean esMiembro(Persona persona){
    return miembros.stream().anyMatch(miembro -> miembro.getPersona() == persona);
  }

  public void agregarMiembro(Miembro miembro){
    miembros.add(miembro);
    miembro.setComunidad(this);
  }
  public List<Miembro> getMiembros(){
    return new ArrayList<>(miembros);
  }

  public Comunidad(){

  }
}