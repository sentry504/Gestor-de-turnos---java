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
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Santos
 */
public class prueba {
    private boolean comprobacion=false;
    
    File archivo= new File(ruta("registros"));
    
    public String ruta(String archivo){
        String ruta= "src/Registros/"+archivo+".xml";
        return ruta;
    }
    
    public boolean Comprobacion() throws SAXException, IOException, ParserConfigurationException, TransformerException{
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
        int n=0;
        int temp=0;
        String ordenPrioridad[]={"P","S","E","N","R"};
        String ListaOrdenada[][]= new String[array.length][3];
        
        for (int i = 0; i < ordenPrioridad.length; i++) {
            for (int j = 0; j <array.length ; j++) {
                if (array[j][0].startsWith(ordenPrioridad[i])){
                    ListaOrdenada[n][0]=array[j][0];
                    ListaOrdenada[n][1]=array[j][1];
                    ListaOrdenada[n][2]=array[j][2];
                    n+=1;
                }
            }
        }
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
        for (int i = 0; i < 10; i++) {
            System.out.println(ListaOrdenada[i][0]);
        }
        System.out.println(temp);
        return false;
    }
    
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        prueba yo= new prueba();
        yo.Comprobacion();
    }
}
