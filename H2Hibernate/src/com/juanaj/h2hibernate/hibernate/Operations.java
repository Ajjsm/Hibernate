package com.juanaj.h2hibernate.hibernate;

import com.juanaj.h2hibernate.entity.Usuarios;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Operations {

    public void conectar(){
        try {
            Util.buildSessionFactory();
            Util.openSession();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public void guardarUsuario(Usuarios usuarios){
        Session sesion = Util.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(usuarios);
        sesion.getTransaction().commit();
        sesion.close();
    }
}
