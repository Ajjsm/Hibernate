package base;

import hibernate.Operations;
import ventana.Ventana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JuanAJ on 27/06/2016.
 */
public class RellenarTrabajador extends JFrame{
    private JPanel panel1;
    public JButton bt_aceptar;
    private JTextField tf_nombre;
    private JTextField tf_correo;
    private JButton bt_cancelar;
    private JComboListar comboListar;


    private Operations operations;

    public JFrame frame;

    public RellenarTrabajador(){
        operations = new Operations();
        operations.conectar();

        bt_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trabajador trabajador = new Trabajador();
                trabajador.setNombre(tf_nombre.getText());
                trabajador.setCorreo(tf_correo.getText());
                operations.guardarTrabajador(trabajador);
                JOptionPane.showMessageDialog(null, "Trabajador insertado correctamente");
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
