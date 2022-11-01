/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*try{
            Date hora1 = new SimpleDateFormat().parse("19/05/2006 05:00:00");
            Date hora2 = new SimpleDateFormat().parse("19/05/2006 08:30:30");
            long lantes = hora1.getTime();
            long lahora = hora2.getTime();
            long diferencia = (lahora - lantes);
            System.out.println(new java.text.SimpleDateFormat( "HH:mm:ss" ).format( new Date(diferencia)));
        }catch(Exception e){

/**
 *
 * @author Santos
 */

public class Turnos{
    //Variable que sirve de guia para saber si los registros actuales en el Registros.xml son de fecha actual
    public boolean comprobacion=false;
    File archivo= new File(ruta("Registros"));
    /**
     *Identificador para conocer el servicio seleccionado por el cliente
     * S= Servicio al cliente
     * P= Preferencial (Tercera edad, embarazadas, discapacitados)
     * R= Caja rapida
     * N= Caja normal
     * E= Empresarial
     */
    String identificador[]={"S","P","R","N","E"};
    String nTicket;
    
    /**
     * @param archivo nombre del archivo a registrarse.
     * @return
     */
    public String ruta(String archivo){
        String ruta= "src/Registros/"+archivo+".xml";
        return ruta;
    }
    
    public boolean Comprobacion(int categoria, String Descripcion) throws SAXException, IOException, ParserConfigurationException, TransformerException{
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();//Preparacion del marco de construccion del documento
        Document doc = newDocumentBuilder.parse(ruta("Registros"));//Guarda la data del archivo existente en la variable doc
        NodeList listaturnos = doc.getElementsByTagName("turno");//Lista todos los nodos del documento Registros.xml
        Element root = doc.getDocumentElement();//Guarda la ruta del archivo Registros.xml
        String array[][]=new String[listaturnos.getLength()][3];

        for (int temp = 0; temp < listaturnos.getLength(); temp++) {
            Node nodo = listaturnos.item(temp);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                array[temp][0]= element.getElementsByTagName("idTicket").item(0).getTextContent();
                array[temp][1]= element.getElementsByTagName("Tiempo_inicial").item(0).getTextContent();
                array[temp][2]= element.getElementsByTagName("Tiempo_final").item(0).getTextContent();
            }
        }

        //En este segmento de codigo se verifica si la fecha registrada corresponde con la fecha actual
        if (array.length<1) {
            // Crear un nuevo nodo de turno
            Element turno = doc.createElement("turno");

            // Crear varios nodos secundarios de turno
            Element idTicket = doc.createElement("idTicket");
            Element  Tiempo_inicial= doc.createElement("Tiempo_inicial");
            Element Tiempo_final = doc.createElement("Tiempo_final");
        
            // Establecer valores para nodos secundarios
            idTicket.setTextContent (identificador[categoria-1]+"1");
            Tiempo_inicial.setTextContent(LocalDateTime.now().toString());
            Tiempo_final.setTextContent("");
            //Tiempo_final.setTextContent(LocalDateTime.now().toString());
            //boolean b= A_.getDayOfMonth()==LocalDateTime.now().getDayOfMonth();

             // Añadir nodo hijo a turno          
            turno.appendChild(idTicket);
            turno.appendChild(Tiempo_inicial);
            turno.appendChild(Tiempo_final);
             // Agregar el nodo turno al nodo raíz
            root.appendChild(turno);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //DOMSource source = new DOMSource(doc);
            Source source = new DOMSource(doc);
            //StreamResult result = new StreamResult(); 
            Result result = new StreamResult(ruta("Registros"));
            transformer.transform(source,result);
            JOptionPane.showMessageDialog(null,Descripcion+"\nId de ticket:"+(identificador[categoria-1]+"1"));
            comprobacion=false;
        }
        if (!(LocalDateTime.parse(array[listaturnos.getLength()-1][1]).getDayOfMonth()==LocalDateTime.now().getDayOfMonth())) {
            //Si el registro es de un dia anterior se borra el archivo Registros.xml
            archivo.delete();
            //Se creea un nuevo documento con los datos de 
            FileOutputStream salidas;
            salidas = new FileOutputStream(archivo);
            String contenido= "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +"<Turnos></Turnos>";
            byte[] bytesTxt = contenido.getBytes();
            salidas.write(bytesTxt);
            comprobacion=true;
        }else{
            //Se asume que los registros son del dia actual y el archivo se mantiene igual
            comprobacion=false;
        }
        return comprobacion;
    }
    
    /**
     * @param categoria_ El numero que indica el identificador que se registrara en el ticket
     * @param Descripcion La cadena de texto que representa el identificador
     * @param A_ Fecha y hora de generacion de ticket
     * @throws Exception
     */
    public void ticketGenerado(int categoria_, String Descripcion, LocalDateTime A_) throws Exception{
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();//Preparacion del marco de construccion del documento
        Document doc = newDocumentBuilder.parse(ruta("Registros"));//Guarda la data del archivo existente en la variable doc
        NodeList listaturnos = doc.getElementsByTagName("turno");//Lista todos los nodos del documento Registros.xml
        Element root = doc.getDocumentElement();//Guarda la ruta del archivo Registros.xml
        String array[][]=new String[listaturnos.getLength()][3];//Creacion de matrix que guardara la data de Registros.xml
        //Este segmento guarda una copia de la data de Registros.xml en array[][]
        for (int i = 0; i < array.length; i++) {
            Node nodo = listaturnos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                array[i][0]= element.getElementsByTagName("idTicket").item(0).getTextContent();
                array[i][1]= element.getElementsByTagName("Tiempo_inicial").item(0).getTextContent();
                array[i][2]= element.getElementsByTagName("Tiempo_final").item(0).getTextContent();
            }
        }
        // Crear un nuevo nodo de turno
        Element turno = doc.createElement("turno");
        
        // Crear varios nodos secundarios de turno
        Element idTicket = doc.createElement("idTicket");
        Element  Tiempo_inicial= doc.createElement("Tiempo_inicial");
        Element Tiempo_final = doc.createElement("Tiempo_final");
        int temp=0;
        //Generar numero de ticket
        // Establecer valores para nodos secundarios
        //En este segmento se filtra la data de array[][] para solo dejar los correspondientes a categoria_, luego guardar la lista filtrada en filtro[][]
        String filtro[][]= new String[array.length][3];
        int n=0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][0].startsWith(identificador[categoria_-1])){
                filtro[n][0]=array[i][0];
                filtro[n][1]=array[i][1];
                filtro[n][2]=array[i][2];
                temp+=1;
                n+=1;
            }
        }
        idTicket.setTextContent (identificador[categoria_-1]+(temp+1));
        Tiempo_inicial.setTextContent(A_.toString());
        Tiempo_final.setTextContent("");
        //Tiempo_final.setTextContent(LocalDateTime.now().toString());
        //boolean b= A_.getDayOfMonth()==LocalDateTime.now().getDayOfMonth();

         // Añadir nodo hijo a turno          
        turno.appendChild(idTicket);
        turno.appendChild(Tiempo_inicial);
        turno.appendChild(Tiempo_final);
         // Agregar el nodo turno al nodo raíz
        root.appendChild(turno);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //DOMSource source = new DOMSource(doc);
        Source source = new DOMSource(doc);
        //StreamResult result = new StreamResult(); 
        Result result = new StreamResult(ruta("Registros"));
        transformer.transform(source,result);
        JOptionPane.showMessageDialog(null,Descripcion+"\nId de ticket:"+identificador[categoria_-1]+(temp+1));
        //Finalizacion de modulo   
        
    }
    
    public void clienteAtendido(String idTicket_) throws SAXException, IOException, ParserConfigurationException, TransformerException{
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();//Preparacion del marco de construccion del documento
        Document doc = newDocumentBuilder.parse(ruta("Registros"));//Guarda la data del archivo existente en la variable doc
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
        
        for (int i=0; i<array.length; i++) {
            Node nodo = listaturnos.item(i);
            if (array[i][0].equals(idTicket_)) {
                Element element = (Element) nodo;
                element.getElementsByTagName("idTicket").item(0).setTextContent(idTicket_);
                element.getElementsByTagName("Tiempo_inicial").item(0).setTextContent(array[i][1]);
                element.getElementsByTagName("Tiempo_final").item(0).setTextContent(LocalDateTime.now().toString());

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                //DOMSource source = new DOMSource(doc);
                Source source = new DOMSource(doc);
                //StreamResult result = new StreamResult(); 
                Result result = new StreamResult(ruta("Registros"));
                transformer.transform(source,result);
            }
        }
        //Finalizacion de modulo   
    }
    
    public boolean ordenamientoPorPrioridad(int categoria_)throws SAXException, IOException, ParserConfigurationException, TransformerException{
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();//Preparacion del marco de construccion del documento
        Document doc = newDocumentBuilder.parse(ruta("Registros"));//Guarda la data del archivo existente en la variable doc
        NodeList listaturnos = doc.getElementsByTagName("turno");//Lista todos los nodos del documento Registros.xml
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
        int n=0;
        
        String ordenPrioridad[]={"P","S","E","N","R"};
        String ListaOrdenada[][]= new String[array.length][3];
        //Se ordena la data por orden de prioridad
        for (int i = 0; i < ordenPrioridad.length; i++) {
            for (int j = 0; j <array.length ; j++) {
                if (array[j][0].startsWith(ordenPrioridad[i])){
                    ListaOrdenada[n][0]=array[j][0];
                    ListaOrdenada[n][1]=array[j][1];
                    ListaOrdenada[n][2]=array[j][2];
                    System.out.print(ListaOrdenada[n][0]+", ");
                    n+=1;
                }
            }
        }
        
        //Se optiene el idTicket que se actualizara como cliente atendido
        boolean encontrado=false;
        if (!(categoria_==0)) {
            n=0;
            for (int i = 0; i < ListaOrdenada.length ; i++) {
                if (ListaOrdenada[i][0].startsWith(ordenPrioridad[(categoria_-1)])){
                    if (ListaOrdenada[i][2].equals("")) {
                        n=i;
                        encontrado=true;
                        break;
                    }
                }
            } 
        }
        System.out.println(n);
        if (encontrado){
            archivo.delete();
            //Se creea un nuevo documento con los datos de 
            FileOutputStream salidas;
            salidas = new FileOutputStream(archivo);
            String contenido= "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +"<Turnos></Turnos>";
            byte[] bytesTxt = contenido.getBytes();
            salidas.write(bytesTxt);
            
            ListaOrdenada[n][2]= LocalDateTime.now().toString();
            //Se actualiza el registro por orden de prioridad
            for (int i = 0; i < array.length; i++) {
                Node nodo = listaturnos.item(i);
                Element element = (Element) nodo;
                element.getElementsByTagName("idTicket").item(0).setTextContent(ListaOrdenada[i][0]);
                element.getElementsByTagName("Tiempo_inicial").item(0).setTextContent(ListaOrdenada[i][1]);
                element.getElementsByTagName("Tiempo_final").item(0).setTextContent(ListaOrdenada[i][2]);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                //DOMSource source = new DOMSource(doc);
                Source source = new DOMSource(doc);
                //StreamResult result = new StreamResult(); 
                Result result = new StreamResult(ruta("Registros"));
                transformer.transform(source,result);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Sin clientes por atender para este modulo");  
        }
        return encontrado;
    } 
}
