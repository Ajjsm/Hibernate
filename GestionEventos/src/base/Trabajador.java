package base;

import javax.persistence.*;

import java.sql.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
/**
 * Created by JuanAJ on 15/06/2016.
 */
@Entity
@Table(name = "trabajadores")

public class Trabajador {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "correo")
    private String correo;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "trabajador")
    private List<Evento> eventos;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nombre;
    }


}
