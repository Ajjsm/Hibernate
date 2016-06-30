package ventana;


import base.*;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import hibernate.Operations;
import net.sf.jasperreports.engine.*;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.data.DateTextValue;
import net.sf.jasperreports.engine.util.*;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import util.Fecha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by JuanAJ on 25/06/2016.
 */
public class Ventana {

    //FIXME Crear boton verde y rojo para saber si estas conectado
    //FIXME Pasar a los combos la seleccion de la lista de eventos
    //FIXME Añadir funciones para eliminar eventos y vacaciones
    //FIXME Añadir lista al agregar trabajadores y tareas para poder eliminar
    //FIXME Si un evento se repite en fecha, tarea y trabajador, se actualiza, no se inserta uno nuevo


    private JPanel panel1;
    private JComboBox cbTrabajador;
    private JComboBox cbTarea;
    private JList listaVacaciones;
    private JButton btNuevaVacaciones;
    private JList listaEventos;
    private JTextField tfBuscarEv;
    private JButton btBuscar;
    private JButton eliminarButton1;
    private JButton btInsertarEv;
    private JTabbedPane tabbedPane1;
    private JCalendar jc_calendario;
    private JButton btActualizarVacas;
    private JButton btVerTodos;
    private JButton ib_trabajador;
    private JButton ib_tarea;
    private JButton btCrearInforme;
    private JComboBox cbInformeCant;
    private JScrollPane jScrollPane;
    private  net.sf.jasperreports.swing.JRViewer jRViewer;

    private JDayChooser jdc;
    private RellenarTrabajador rellenarTrabajador;
    private RellenarTarea rellenarTarea;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu menuAyuda;

    private JMenuItem menuItemAddTrabajador;
    private JMenuItem menuItemAddTarea;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;

    private JMenuItem menuItemInstrucciones;
    private JMenuItem menuItemVersion;

    private Operations operations;

    private Trabajador trabajador;
    private Tarea tarea;
    private Evento evento;

    private JComboListar comboTrabajador;
    private JComboListar comboTarea;

    private DefaultListModel dtm_listaEventos;
    private DefaultListModel dtm_listaVacaciones;

    public Ventana() throws InterruptedException {
        JScrollPane scroll2 = new JScrollPane(panel1);
        JFrame frame = new JFrame("Ventana");
        frame.add(scroll2);
        frame.setContentPane(scroll2);
        frame.setContentPane(scroll2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();

        comboTrabajador = new JComboListar(cbTrabajador);
        comboTarea = new JComboListar(cbTarea);

        rellenarTrabajador = new RellenarTrabajador();
        rellenarTarea = new RellenarTarea();

        evento = null;
        tarea = null;
        trabajador = null;

        operations = new Operations();
        operations.conectar();

        listarTodo();

        frame.setJMenuBar(getMenuBar());
        frame.setVisible(true);


        frame.setVisible(true);

        btCrearInforme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cbInformeCant.getSelectedItem().toString().equalsIgnoreCase("semanal")) {
                    crearInforme("reportGestion.jasper");
                } else if (cbInformeCant.getSelectedItem().toString().equalsIgnoreCase("diario")) {
                    crearInforme("reportGestionDiario2.jasper");
                } else if (cbInformeCant.getSelectedItem().toString().equalsIgnoreCase("mensual")) {
                    crearInforme("reportGestionMensual.jasper");
                }

            }
        });

        btNuevaVacaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RellenarVacaciones rellenarVacaciones = new RellenarVacaciones();
            }
        });
        btInsertarEv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarEvento();
            }
        });

        jdc = new JDayChooser();
        jdc.addPropertyChangeListener("day", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                System.out.println(e.getPropertyName()+ ": " + e.getNewValue());
            }
        });

        jc_calendario.addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println(jc_calendario.getDate());
                listarSegunFecha(jc_calendario.getDate());
            }
        });

        btVerTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTodo();
            }
        });

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEvento(tfBuscarEv.getText());
            }
        });

        ib_trabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboListar jComboListar = new JComboListar(cbTrabajador);
                jComboListar.listarTrabajadores(cbTrabajador);
                jComboListar.listarTarea(cbTarea);
            }
        });

        ib_tarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboListar jComboListar = new JComboListar(cbTrabajador);
                jComboListar.listarTrabajadores(cbTrabajador);
                jComboListar.listarTarea(cbTarea);
            }
        });

    }



    private JMenuBar getMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        menuItemAddTrabajador = new JMenuItem("Agregar trabajador");
        menuItemAddTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rellenarTrabajador = new RellenarTrabajador();
                rellenarTrabajador.crearVentana();
                rellenarTrabajador.visible();

            }
        });
        menuItemAddTarea = new JMenuItem("Agregar tarea");
        menuItemAddTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rellenarTarea = new RellenarTarea();
                rellenarTarea.crearVentana();
                rellenarTarea.visible();

            }
        });
        menuItem3 = new JMenuItem("Configuracion");
        menuItem4 = new JMenuItem("Salir");

        menu.add(menuItemAddTrabajador);
        menu.add(menuItemAddTarea);
        menu.add(menuItem3);
        menu.add(menuItem4);

        menuAyuda = new JMenu("Ayuda");
        menuBar.add(menuAyuda);
        menuItemInstrucciones = new JMenuItem("Instrucciones");
        menuItemVersion = new JMenuItem("Ver version");
        menuAyuda.add(menuItemVersion);
        menuAyuda.add(menuItemInstrucciones);

        return menuBar;
    }


    private void crearInforme(String dato)  {

        tabbedPane1.removeAll();
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiontrabajadores2", "root", "");
            JasperReport report = (JasperReport)
                    JRLoader.loadObject(this.getClass().getClassLoader() .getResourceAsStream(dato));
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(report,
                    map(jc_calendario.getDate()), conexion);

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new
                File("backupInforme.pdf"));
        exporter.exportReport();
           jRViewer = new net.sf.jasperreports.swing.JRViewer(jasperPrint);
            tabbedPane1.addTab("Informe", jRViewer);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }

    }

    private void insertarEvento(){
        evento = new Evento();
        Date fecha = jc_calendario.getDate();

        tarea = (Tarea)cbTarea.getSelectedItem();
        trabajador = (Trabajador) cbTrabajador.getSelectedItem();

        evento.setTareas(tarea);
        evento.setTrabajador(trabajador);
        evento.setFecha(fecha);
        operations.guardarEvento(evento);

        JOptionPane.showMessageDialog(null, "Evento insertado correctamente");

        listarTodo();

    }

    private void listaEventos(){
        dtm_listaEventos = new DefaultListModel();
        listaEventos.setModel(dtm_listaEventos);
        dtm_listaEventos.removeAllElements();
        operations.getEvento().forEach(dtm_listaEventos::addElement);
    }

    private void listaVacaciones(){
        dtm_listaVacaciones = new DefaultListModel();
        listaVacaciones.setModel(dtm_listaVacaciones);
        dtm_listaVacaciones.removeAllElements();
        operations.getVacaciones().forEach(dtm_listaVacaciones::addElement);
    }

    private void listarTodo(){
        JComboListar jComboListar = new JComboListar(cbTrabajador);
        jComboListar.listarTrabajadores(cbTrabajador);
        jComboListar.listarTarea(cbTarea);

        listaEventos();
        listaVacaciones();
    }

    private void listarSegunFecha(Date fecha){
        dtm_listaEventos = new DefaultListModel();
        listaEventos.setModel(dtm_listaEventos);
        operations.getEvento().stream().filter(eventos -> Fecha.formatFecha(eventos.getFecha()).equalsIgnoreCase(Fecha.formatFecha(fecha))).forEach(dtm_listaEventos::addElement);
    }

    private void buscarEvento(String valor){

        dtm_listaEventos = new DefaultListModel();
        listaEventos.setModel(dtm_listaEventos);
        for (Evento eventos : operations.getEvento()){
            if (valor.equalsIgnoreCase(eventos.getTrabajador().getNombre())){
                dtm_listaEventos.addElement(eventos);
            }
            if (valor.equalsIgnoreCase(eventos.getTareas().getNombre())){
                dtm_listaEventos.addElement(eventos);
            }

            if (valor.equalsIgnoreCase("")){
                listarTodo();
            }

        }

    }

    private Map map(Date fecha){
        //Conseguir el primer dia y ultimo de la semana
        Calendar cal = Calendar.getInstance();
        cal.setTime(jc_calendario.getDate());
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date startDate = cal.getTime();
        cal.add(Calendar.DATE, 6);
        Date endDate = cal.getTime();

        //Conseguir una fecha en joda y sumarle los dias que quiera
        org.joda.time.DateTimeZone timeZone = org.joda.time.DateTimeZone.forID( "Europe/Madrid" );
        DateTime now = new DateTime( jc_calendario.getDate(), timeZone );
        DateTime tomorrow = now.plusDays( 1 );
        java.util.Date fechaFinal = tomorrow.toDate();

        DateTime now2 = new DateTime(jc_calendario.getDate(), timeZone);
        DateTime primerDiaMes = now.dayOfMonth().withMinimumValue();
        DateTime ultimoDiaMes = now.dayOfMonth().withMaximumValue();

        java.util.Date diaMesinicio = primerDiaMes.toDate();
        java.util.Date diaMesfinal = ultimoDiaMes.toDate();



        Map<String, Date> parametros = new HashMap<>();

            if (cbInformeCant.getSelectedItem().toString().equalsIgnoreCase("diario")){
                parametros.put("fechaParam", jc_calendario.getDate());
            } else if (cbInformeCant.getSelectedItem().toString().equalsIgnoreCase("semanal")) {
                parametros.put("fechaParam", startDate);
                parametros.put("fechaFinal", endDate);
            } else if (cbInformeCant.getSelectedItem().toString().equalsIgnoreCase("Mensual")) {
                parametros.put("inicioMes", diaMesinicio);
                parametros.put("finMes", diaMesfinal);

                System.out.println(diaMesfinal);
                System.out.println(diaMesinicio);

            }


        return parametros;
    }

}
