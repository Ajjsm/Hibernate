package base;

import hibernate.Operations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JuanAJ on 27/06/2016.
 */
public class RellenarTrabajador extends JFrame{
    private JPanel panel1;
    public JButton bt_aceptar;
    private JTextField tf_nombreTrab;
    private JTextField tf_correo;
    private JButton bt_cancelar;
    private JList list1;
    private JComboListar comboListar;


    private Operations operations;

    public JFrame frame;

    public RellenarTrabajador(){
        operations = new Operations();
        operations.conectar();

        bt_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Trabajador trabajadores : operations.getTrabajador()) {
                    if (trabajadores.getNombre().equalsIgnoreCase(tf_nombreTrab.getText())) {
                        JOptionPane.showMessageDialog(null, "Ya existe un trabajador con ese nombre");
                        tf_nombreTrab.setText("");
                        tf_correo.setText("");
                        return;
                    }

                }
                    Trabajador trabajador = new Trabajador();
                    trabajador.setNombre(tf_nombreTrab.getText());
                    trabajador.setCorreo(tf_correo.getText());
                    operations.guardarTrabajador(trabajador);
                    JOptionPane.showMessageDialog(null, "Trabajador insertado correctamente");
                    frame.setVisible(false);

                    tf_nombreTrab.setText("");
                    tf_correo.setText("");

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
