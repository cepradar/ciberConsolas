package com.company.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import com.company.utilidades.*;
import com.company.vo.EstacionVo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static com.company.utilidades.Utilidades.EMPEZAR;
import static com.company.utilidades.Utilidades.INICIO;

public class VentanaTabla extends JFrame implements MouseListener {

    private JPanel contentPane;
    private JScrollPane scrollPaneTabla;
    public static JTable tablaPersonas;
    public static int fila, minutos;
    public int columna, nuHora, nuMin, nuSeg;
    public static ArrayList<EstacionVo> listaEstaciones;//lista que simula la información de la BD

    ModeloTabla modelo;//modelo definido en la clase ModeloTabla
    private int filasTabla;
    private int columnasTabla;

    /**
     * Create the frame.
     */
    public VentanaTabla() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1121, 453);

        iniciarComponentes();
        setLocationRelativeTo(null);
        construirTabla();
    }

    private void iniciarComponentes() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 10));

        JLabel lblTablaPersonas = new JLabel("Tabla Estaciones");
        lblTablaPersonas.setFont(new Font("Rockwell", Font.BOLD, 25));
        contentPane.add(lblTablaPersonas, BorderLayout.NORTH);

        scrollPaneTabla = new JScrollPane();
        contentPane.add(scrollPaneTabla);

        tablaPersonas = new JTable();
        tablaPersonas.setBackground(Color.WHITE);
        tablaPersonas.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        tablaPersonas.addMouseListener(this);
        //tablaSeguimiento.addKeyListener(this);
        tablaPersonas.setOpaque(false);
        scrollPaneTabla.setViewportView(tablaPersonas);

    }

    /**
     * Metodo que permite construir la tabla de personas
     * se crean primero las columnas y luego se asigna la información
     */
    private void construirTabla() {

        listaEstaciones = consultarListaPersonas();

        ArrayList<String> titulosList = new ArrayList<>();

        titulosList.add("Ord.");
        titulosList.add("Estado");
        titulosList.add("EMPEZAR");
        titulosList.add("Inicio");
        titulosList.add("Cronometro");
        titulosList.add("Temporizador");
        titulosList.add("Nota1");
        titulosList.add("Nota2");
        titulosList.add("Nota3");
        titulosList.add("Promedio");
        titulosList.add(" ");
        titulosList.add(" ");

        //se asignan las columnas al arreglo para enviarse al momento de construir la tabla
        String titulos[] = new String[titulosList.size()];
        for (int i = 0; i < titulos.length; i++) {
            titulos[i] = titulosList.get(i);
        }
        /*obtenemos los datos de la lista y los guardamos en la matriz
         * que luego se manda a construir la tabla
         */
        Object[][] data = obtenerMatrizDatos(titulosList);
        construirTabla(titulos, data);

    }

    /**
     * Permite simular el llenado de personas en una lista
     * que posteriormente alimentará la tabla
     *
     * @return
     */
    private ArrayList<EstacionVo> consultarListaPersonas() {
        ArrayList<EstacionVo> lista = new ArrayList<>();

        lista.add(new EstacionVo("1234", false, 23, 2.5, 4.3, 3.0, (2.5 + 4.3 + 3) / 33));
        lista.add(new EstacionVo("3455", false, 0, 0, 0, 0, 0));
        lista.add(new EstacionVo("3214", true, 0, 0, 0, 0, 0));
        lista.add(new EstacionVo("7886", false, 0, 0, 0, 0, 0));
        lista.add(new EstacionVo("4331", false, 0, 0, 0, 0, 0));
        lista.add(new EstacionVo("98675", false, 0, 0, 0, 0, 0));
        lista.add(new EstacionVo("1221", false, 0, 0, 0, 0, 0));

        return lista;
    }

    /**
     * Llena la información de la tabla usando la lista de personas trabajada
     * anteriormente, guardandola en una matriz que se retorna con toda
     * la información para luego ser asignada al modelo
     *
     * @param titulosList
     * @return
     */
    private Object[][] obtenerMatrizDatos(ArrayList<String> titulosList) {

        /*se crea la matriz donde las filas son dinamicas pues corresponde
         * a todos los usuarios, mientras que las columnas son estaticas
         * correspondiendo a las columnas definidas por defecto
         */
        String informacion[][] = new String[listaEstaciones.size()][titulosList.size()];

        for (int x = 0; x < informacion.length; x++) {

            informacion[x][Utilidades.ORD] = listaEstaciones.get(x).getOrd() + "";
            informacion[x][Utilidades.ESTADO] = listaEstaciones.get(x).getEstado() + "";
            informacion[x][EMPEZAR] = "EMPEZAR";
            informacion[x][Utilidades.INICIO] = "00:00:00";
            informacion[x][Utilidades.PROFESION] = "00:00:00";
            // listaPersonas.get(x).cronometro(15) +
            informacion[x][Utilidades.EDAD] = "00:00:00";
            informacion[x][Utilidades.NOTA1] = listaEstaciones.get(x).getNota1() + "";
            informacion[x][Utilidades.NOTA2] = listaEstaciones.get(x).getNota2() + "";
            informacion[x][Utilidades.NOTA3] = listaEstaciones.get(x).getNota3() + "";
            informacion[x][Utilidades.PROMEDIO] = listaEstaciones.get(x).getPromedio() + "";
            //se asignan las plabras clave para que en la clase GestionCeldas se use para asignar el icono correspondiente
            informacion[x][Utilidades.PERFIL] = "PERFIL";
            informacion[x][Utilidades.EVENTO] = "EVENTO";
        }

        return informacion;
    }

    /**
     * Con los titulos y la información a mostrar se crea el modelo para
     * poder personalizar la tabla, asignando tamaño de celdas tanto en ancho como en alto
     * así como los tipos de datos que va a poder soportar.
     *
     * @param titulos
     * @param data
     */
    private void construirTabla(String[] titulos, Object[][] data) {
        modelo = new ModeloTabla(data, titulos);
        //se asigna el modelo a la tabla
        tablaPersonas.setModel(modelo);

        filasTabla = tablaPersonas.getRowCount();
        columnasTabla = tablaPersonas.getColumnCount();

        //se asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
        tablaPersonas.getColumnModel().getColumn(Utilidades.EDAD).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA1).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA2).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA3).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.PROMEDIO).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.PERFIL).setCellRenderer(new GestionCeldas("icono"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.EVENTO).setCellRenderer(new GestionCeldas("icono"));
        tablaPersonas.getColumnModel().getColumn(EMPEZAR).setCellRenderer(new GestionCeldas("icono"));
        //se recorre y asigna el resto de celdas que serian las que almacenen datos de tipo texto
        for (int i = 0; i < titulos.length - 7; i++) {//se resta 7 porque las ultimas 7 columnas se definen arriba
            if (i != 2) {
                System.out.println(i);
                tablaPersonas.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldas("texto"));
            }
        }

        tablaPersonas.getTableHeader().setReorderingAllowed(false);
        tablaPersonas.setRowHeight(25);//tamaño de las celdas
        tablaPersonas.setGridColor(new java.awt.Color(0, 0, 0));
        //Se define el tamaño de largo para cada columna y su contenido
        tablaPersonas.getColumnModel().getColumn(Utilidades.ORD).setPreferredWidth(100);//ordenador
        tablaPersonas.getColumnModel().getColumn(Utilidades.ESTADO).setPreferredWidth(150);//estado
        tablaPersonas.getColumnModel().getColumn(EMPEZAR).setPreferredWidth(100);//empezar
        tablaPersonas.getColumnModel().getColumn(Utilidades.INICIO).setPreferredWidth(130);//telefono
        tablaPersonas.getColumnModel().getColumn(Utilidades.PROFESION).setPreferredWidth(130);//profesion
        tablaPersonas.getColumnModel().getColumn(Utilidades.EDAD).setPreferredWidth(130);//edad
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA1).setPreferredWidth(100);//nota1
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA2).setPreferredWidth(100);//nota2
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA3).setPreferredWidth(100);//nota3
        tablaPersonas.getColumnModel().getColumn(Utilidades.PROMEDIO).setPreferredWidth(130);//promedio
        tablaPersonas.getColumnModel().getColumn(Utilidades.PERFIL).setPreferredWidth(30);//accion perfil
        tablaPersonas.getColumnModel().getColumn(Utilidades.EVENTO).setPreferredWidth(30);//accion evento

        //personaliza el encabezado
        JTableHeader jtableHeader = tablaPersonas.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        tablaPersonas.setTableHeader(jtableHeader);

        //se asigna la tabla al scrollPane
        scrollPaneTabla.setViewportView(tablaPersonas);


    }

    public static int getFila() {
        return fila;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //capturo fila o columna dependiendo de mi necesidad
        fila = tablaPersonas.rowAtPoint(e.getPoint());
        columna = tablaPersonas.columnAtPoint(e.getPoint());

        /*uso la columna para valiar si corresponde a la columna de perfil garantizando
         * que solo se produzca algo si selecciono una fila de esa columna
         */
        if (columna == Utilidades.PERFIL) {
            //sabiendo que corresponde a la columna de perfil, envio la posicion de la fila seleccionada
            validarSeleccionMouse(fila);
        } else if (columna == Utilidades.EVENTO) {//se valida que sea la columna del otro evento
            JOptionPane.showMessageDialog(null, "Evento del otro icono");
        } else if (columna == EMPEZAR) {
            JOptionPane.showMessageDialog(null, "holi");
            //informacion[fila][Utilidades.PROFESION] = listaPersonas.get(fila).cronometro(15);
            //listaPersonas.get(fila).cronometro(15, tablaPersonas,fila,columna+2);
            iniciarEstacion();
            //tablaPersonas.setValueAt(#Cronometro,fila,columna+3);


        }


    }

    private void iniciarEstacion() {
        EstacionVo estacionSelec = listaEstaciones.get(fila);
        if (!listaEstaciones.get(fila).getEstado()) {//si la estacion esta in/activa
            minutos = Integer.parseInt(JOptionPane.showInputDialog("Cuanto tiempo?"));
            listaEstaciones.get(fila).setEstado(true);

            estacionSelec.start();
            tablaPersonas.setValueAt(listaEstaciones.get(fila).getInicio(), fila, INICIO);

        } else {
            int aux = JOptionPane.showConfirmDialog(null, "seguro que desea cancelar");
            if (aux == 0) {
                estacionSelec.setEstado(false);
            }
        }
    }

    /**
     * Este metodo simularia el proceso o la acción que se quiere realizar si
     * se presiona alguno de los botones o iconos de la tabla
     *
     * @param fila
     */
    private void validarSeleccionMouse(int fila) {
        Utilidades.filaSeleccionada = fila;


        //teniendo la fila entonces se obtiene el objeto correspondiente para enviarse como parammetro o imprimir la información
        EstacionVo miEstacion = new EstacionVo();
        //se obtiene el estado de la estacion seleccionada
        miEstacion.setOrd(tablaPersonas.getValueAt(fila, Utilidades.ORD).toString());
        //si el estado es verdadero entonces desactiva la estacion (false)
        if (tablaPersonas.getValueAt(fila, Utilidades.ESTADO).toString().compareToIgnoreCase("true") == 0) {
            miEstacion.setEstado(true);
        } else miEstacion.setEstado(false);

        //miEstacion.setEstado(tablaPersonas.getValueAt(fila, Utilidades.ESTADO));
        //System.out.println(tablaPersonas.getValueAt(fila, Utilidades.ESTADO).getClass());
        String info = "INFO PERSONA\n";
        info += "Ordenador: " + miEstacion.getOrd() + "\n";
        info += "Estado: " + Boolean.toString(miEstacion.getEstado()) + "\n";

        JOptionPane.showMessageDialog(null, info);
    }


    //estos metododos pueden ser usados dependiendo de nuestra necesidad, por ejemplo para cambiar el tamaño del icono al ser presionado
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }


}