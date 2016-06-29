package hibernate;

import base.Evento;
import base.Tarea;
import base.Trabajador;

import base.Vacaciones;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created by JuanAJ on 15/06/2016.
 */

public class Operations {
    public void conectar(){
        try {
            Util.buildSessionFactory();
            Util.openSession();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public void guardarTrabajador(Trabajador trabajador) {
        Session sesion = Util.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(trabajador);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void guardarEvento(Evento evento) {
        Session sesion = Util.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(evento);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void guardarVacaciones(Vacaciones vacaciones) {
        Session sesion = Util.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(vacaciones);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void modificarEvento(Evento evento){
        Session sesion = Util.getCurrentSession();
        sesion.beginTransaction();
        sesion.update(evento);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void guardarTarea(Tarea tarea) {
        Session sesion = Util.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(tarea);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Trabajador> getTrabajador() {
        Query query = Util.getCurrentSession().
                createQuery("FROM Trabajador ORDER BY nombre");
        ArrayList<Trabajador> trabajador = (ArrayList<Trabajador>) query.list();
        return trabajador;
    }

    public ArrayList<Tarea> getTarea() {
        Query query = Util.getCurrentSession().
                createQuery("FROM Tarea ORDER BY nombre");
        ArrayList<Tarea> tareas = (ArrayList<Tarea>) query.list();
        return tareas;
    }

    public ArrayList<Evento> getEvento() {
        Query query = Util.getCurrentSession().
                createQuery("FROM Evento order by fecha");
        ArrayList<Evento> eventos = (ArrayList<Evento>) query.list();
        return eventos;
    }

    public ArrayList<Vacaciones> getVacaciones() {
        Query query = Util.getCurrentSession().
                createQuery("FROM Vacaciones order by fecha");
        ArrayList<Vacaciones> vacaciones = (ArrayList<Vacaciones>) query.list();
        return vacaciones;
    }


}
