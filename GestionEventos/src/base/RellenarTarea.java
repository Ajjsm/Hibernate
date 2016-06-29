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
    private Operations operations;
    private JFrame frame;


    public RellenarTarea() {
          operations = new Operations();
        operations.conectar();

        bt_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarea tarea = new Tarea();
                tarea.setNombre(tf_nombre.getText());
                //FIXME Comprobar id automatico, va de 3 en 3
                operations.guardarTarea(tarea);
                JOptionPane.showMessageDialog(null, "Tarea insertado correctamente");
                frame.setVisible(false);
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
        frame.setTitle("Agregar nuevo trabajador");
        frame.setLocationRelativeTo(null);
    }

    public void visible(){
        frame.setVisible(true);
    }


}
