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
    private JList listTarea;
    private JTextField tfAbrev;
    private JButton btnEliminar;
    private Operations operations;
    private JFrame frame;


    public RellenarTarea() {
        operations = new Operations();
        operations.conectar();

        listarTarea();

        bt_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((tf_nombre.getText().equalsIgnoreCase("")) && (tfAbrev.getText().equalsIgnoreCase(""))){
                    JOptionPane.showMessageDialog(null, "No dejes los campos en blanco");
                    return;
                }
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
                listarTarea();
                tf_nombre.setText("");
                tfAbrev.setText("");

            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Estas apunto de eliminar una tarea",
                        "",
                        JOptionPane.YES_NO_OPTION);

                if(n == JOptionPane.YES_OPTION){
                    Tarea filaSeleccionada = (Tarea)listTarea.getSelectedValue();
                    if (filaSeleccionada!=null){
                        operations.eliminarTarea(filaSeleccionada);
                        listarTarea();
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
        frame.setTitle("Agregar nueva tarea");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    public void visible(){
        frame.setVisible(true);
    }

    private void listarTarea(){
        DefaultListModel dtmLista = new DefaultListModel();
        listTarea.setModel(dtmLista);

        for(Tarea tarea :operations.getTarea()){
            dtmLista.addElement(tarea);
        }
    }

}
