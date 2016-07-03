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
    private JButton btEliminar;
    private JComboListar comboListar;


    private Operations operations;

    public JFrame frame;

    public RellenarTrabajador(){
        operations = new Operations();
        operations.conectar();

        listarTrabajador();

        bt_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((tf_nombreTrab.getText().equalsIgnoreCase("")) && (tf_correo.getText().equalsIgnoreCase(""))){
                    JOptionPane.showMessageDialog(null, "No dejes los campos en blanco");
                    return;
                }
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

                    tf_nombreTrab.setText("");
                    tf_correo.setText("");

                    listarTrabajador();

            }
        });

        btEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Estas apunto de eliminar un trabajador",
                        "",
                        JOptionPane.YES_NO_OPTION);

                if(n == JOptionPane.YES_OPTION) {
                    Trabajador filaSeleccionada = (Trabajador) list1.getSelectedValue();
                    if (filaSeleccionada!=null){
                        operations.eliminarTrabajador(filaSeleccionada);
                        listarTrabajador();
                    } else {
                        JOptionPane.showMessageDialog(null, "No tienes ningun dato seleccionado");
                    }

                }
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
        frame.setResizable(false);
    }

    public void visible(){
        frame.setVisible(true);
    }

    private void listarTrabajador(){
        DefaultListModel dtmLista = new DefaultListModel();
        list1.setModel(dtmLista);

        for(Trabajador trabajador :operations.getTrabajador()){
            dtmLista.addElement(trabajador);
        }
    }

}
