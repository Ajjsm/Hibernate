package base;

import com.toedter.calendar.JCalendar;
import hibernate.Operations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by JuanAJ on 27/06/2016.
 */
public class RellenarVacaciones {
    private JTextField tf_descripcion;
    private JCalendar jc_vacaciones;
    private JButton bt_aceptar;
    private JButton bt_cancelar;
    private JPanel panel1;

    private Operations operations;

    public RellenarVacaciones(){
        JFrame frame = new JFrame("RellenarVacaciones");
        frame.setContentPane(panel1);
        frame.pack();
        frame.setTitle("Agregar nuevo trabajador");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        operations = new Operations();
        operations.conectar();

        bt_aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fecha = jc_vacaciones.getDate();
                Vacaciones vacaciones = new Vacaciones();
                vacaciones.setDescripcion(tf_descripcion.getText());
                vacaciones.setFecha(fecha);
                operations.guardarVacaciones(vacaciones);
                JOptionPane.showMessageDialog(null, "Vacaciones almacenadas correctamente");
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

}
