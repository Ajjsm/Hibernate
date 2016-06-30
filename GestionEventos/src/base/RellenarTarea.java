package base;

import hibernate.Operations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JuanAJ on 27/06/2016.
 */
public class RellenarTarea extends JFrame{
    private JPanel panel1;
    private JTextField tf_nombre;
    public JButton bt_aceptar;
    private JButton bt_cancelar;
    private JList list1;
    private JTextField tfAbrev;
    private Operations operations;
    private JFrame frame;


    public RellenarTarea() {
        operations = new Operations();
        operations.conectar();

        bt_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Tarea tareas : operations.getTarea()) {
                    if (tareas.getNombre().equalsIgnoreCase(tf_nombre.getText())) {
                        JOptionPane.showMessageDialog(null, "Ya existe una tarea con ese nombre");
                        tf_nombre.setText("");
                        return;
                    }
                }
                        Tarea tarea = new Tarea();
                        tarea.setNombre(tf_nombre.getText());
                        tarea.setAbreviatura(tfAbrev.getText());
                        operations.guardarTarea(tarea);
                        JOptionPane.showMessageDialog(null, "Tarea insertado correctamente");
                        frame.setVisible(false);


                tf_nombre.setText("");

            }
        });

        bt_cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

    public void crearVentana(){
        frame = new JFrame("RellenarTrabajador");
        frame.setContentPane(panel1);
        frame.pack();
        frame.setTitle("Agregar nueva tarea");
        frame.setLocationRelativeTo(null);
    }

    public void visible(){
        frame.setVisible(true);
    }


}
