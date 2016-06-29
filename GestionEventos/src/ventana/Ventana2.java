package ventana;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JuanAJ on 28/06/2016.
 */
public class Ventana2 {
    private JPanel panel1;
    private JComboBox comboBox1;

   public Ventana2(){
        JFrame frame = new JFrame("Ventana2");
        panel1.setLayout(new GridLayout(0, 1));
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
