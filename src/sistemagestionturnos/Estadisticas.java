/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagestionturnos;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Santos
 */
public class Estadisticas extends javax.swing.JInternalFrame {
    DefaultTableModel modeloDatos;
    /**
     * Creates new form Estadisticas
     */
    public Estadisticas() throws SAXException, IOException, ParserConfigurationException, TransformerException {
        initComponents();
        ListadoPantalla();
    }

    @SuppressWarnings("RedundantStringToString")
    public void ListadoPantalla()throws SAXException, IOException, ParserConfigurationException, TransformerException{
        modeloDatos = new DefaultTableModel();
        
        modeloDatos.addColumn("Turno");
        modeloDatos.addColumn("Tiempo en espera");
        
        jTable1.removeAll();
        jTable1.setModel(modeloDatos);
        
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();//Preparacion del marco de construccion del documento
        Document doc = newDocumentBuilder.parse("src/Registros/Registros.xml");//Guarda la data del archivo existente en la variable doc
        NodeList listaturnos = doc.getElementsByTagName("turno");//Lista todos los nodos del documento Registros.xml
        Element root = doc.getDocumentElement();//Guarda la ruta del archivo Registros.xml
        String array[][]=new String[listaturnos.getLength()][3];//Creacion de matrix que guardara la data de Registros.xml
        
        //Este segmento guarda una copia de la data de Registros.xml en array[][]
        for (int i = 0; i < listaturnos.getLength(); i++) {
            Node nodo = listaturnos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                array[i][0]= element.getElementsByTagName("idTicket").item(0).getTextContent();
                array[i][1]= element.getElementsByTagName("Tiempo_inicial").item(0).getTextContent();
                array[i][2]= element.getElementsByTagName("Tiempo_final").item(0).getTextContent();
            }
        }
     
        String [] Datos = new String [2];
        String estadisticas[]= new String[array.length];
        LocalTime TiempoFinal, TiempoInicial;
        int n=0;
        for (int i = 0; i < array.length; i++) {
            if (!(array[i][2].equals(""))) {
                n+=1;
            }
        }
        String filtrado[][]= new String[n][3];
        n=0;
        
        for (int i = 0; i < array.length; i++) {
            if (!(array[i][2].equals(""))) {
                filtrado[n][0]= array[i][0];
                filtrado[n][1]= array[i][1];
                filtrado[n][2]= array[i][2];
                n+=1;
            }
        }
        n=0;
        for (int i = 0; i<filtrado.length; i++) {
            n+=1;
            TiempoFinal= LocalDateTime.parse(filtrado[i][2].toString()).toLocalTime();
            TiempoInicial= LocalDateTime.parse(filtrado[i][1].toString()).toLocalTime();
            TiempoFinal= TiempoFinal.minusHours(TiempoInicial.getHour());
            TiempoFinal= TiempoFinal.minusMinutes(TiempoInicial.getMinute());
            TiempoFinal= TiempoFinal.minusSeconds(TiempoInicial.getSecond());
            
            estadisticas[i]=String.valueOf(TiempoFinal.getHour())+String.valueOf(TiempoFinal.getMinute())+String.valueOf(TiempoFinal.getSecond());
            Datos[0]=filtrado[i][0];
            Datos[1]=TiempoFinal.toString();
            modeloDatos.addRow(Datos);
        }
        
//        String cadena="0124";
//        long tiempos[]=new long[filtrado.length];
//        //Aqui obtenemos el promedio de tiempo de espera
//        long promedio=0;
//        for (int i = 0; i<estadisticas.length; i++) {
//            System.out.println(Long.parseLong((estadisticas[i].replaceFirst("^0*", "").trim())));
//        }
//        for (int i = 0; i < tiempos.length; i++) {
//            //System.out.println(tiempos[i]);
//        }
//        promedio= promedio/estadisticas.length;
//        this.jTextFieldPE.setText(String.valueOf(promedio));
        
        //Obtencion del Maximo
//        int maximo= Integer.valueOf(estadisticas[0]);
//        for (int i = 1; i < estadisticas.length; i++) {
//            if (Integer.valueOf(estadisticas[i])>maximo) {
//                maximo= Integer.valueOf(estadisticas[i]);
//            }
//        }
//        cadena= String.valueOf(maximo);
//        n=0;
//        for (int i = 0; i < cadena.length()+1; i+=2) {
//            tiempo+=cadena.substring(n, i);
//            n=i;
//        }
//        this.jTextFieldEMayor.setText(tiempo);
//        
//        //Obtencion del minimo
//        int minimo= Integer.valueOf(estadisticas[0]);
//        for (int i = 1; i < estadisticas.length; i++) {
//            if (Integer.valueOf(estadisticas[i])<minimo) {
//                minimo= Integer.valueOf(estadisticas[i]);
//            }
//        }
//        cadena= String.valueOf(minimo);
//        n=0;
//        for (int i = 0; i < cadena.length()+1; i+=2) {
//            tiempo+=cadena.substring(n, i);
//            n=i;
//        }
//        this.jTextFieldEMenor.setText(tiempo);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldPE = new javax.swing.JTextField();
        jTextFieldEMenor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldEMayor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTextFieldPE.setEditable(false);
        jTextFieldPE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldPE.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextFieldEMenor.setEditable(false);
        jTextFieldEMenor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldEMenor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Promedio Tiempo en espera");
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Mayor tiempo en espera");
        jLabel2.setOpaque(true);

        jTextFieldEMayor.setEditable(false);
        jTextFieldEMayor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldEMayor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Menor tiempo en espera");
        jLabel3.setOpaque(true);

        setTitle("Estadisticas de tiempos de espera");

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ticket", "Tiempo en espera"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldEMayor;
    private javax.swing.JTextField jTextFieldEMenor;
    private javax.swing.JTextField jTextFieldPE;
    // End of variables declaration//GEN-END:variables
}
