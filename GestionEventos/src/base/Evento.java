package base;

import util.Fecha;

import javax.persistence.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by JuanAJ on 23/06/2016.
 */

@Entity
@Table(name = "evento")

public class Evento {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_tarea")
    private Tarea tareas;

    @ManyToOne
    @JoinColumn(name = "id_trabajador")
    private Trabajador trabajador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tarea getTareas() {
        return tareas;
    }

    public void setTareas(Tarea tareas) {
        this.tareas = tareas;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    @Override
    public String toString() {

            return Fecha.formatFecha(fecha) + " - " + trabajador.getNombre() + " - " + tareas.getNombre();

    }
}
