package base;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by JuanAJ on 15/06/2016.
 */

@Entity
@Table(name = "tareas")

public class Tarea {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "abreviatura")
    private String abreviatura;


    @OneToMany(mappedBy = "tareas")
    private List<Evento> eventos;

    public Tarea() {
        eventos = new ArrayList<Evento>();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @Override
    public String toString() {
        return nombre;
    }
}


