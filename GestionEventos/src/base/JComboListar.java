package base;

import hibernate.Operations;
import hibernate.Util;

import javax.management.Query;
import javax.swing.*;
import java.util.List;

/**
 * Created by JuanAJ on 28/06/2016.
 */
public class JComboListar {
    private Operations operations;
    private JComboBox comboBox;

    public JComboListar(JComboBox comboBox){
        operations = new Operations();
        this.comboBox = comboBox;

    }

    public void listarTrabajadores(JComboBox jComboBox){
        comboBox.removeAllItems();
        for (int i = 0; i < operations.getTrabajador().size(); i++) {
            comboBox.addItem(operations.getTrabajador().get(i));
        }
    }

    public void listarTarea(JComboBox jComboTarea){
        jComboTarea.removeAllItems();
        for (int i = 0; i < operations.getTarea().size(); i++) {
            jComboTarea.addItem(operations.getTarea().get(i));
        }
    }


}
