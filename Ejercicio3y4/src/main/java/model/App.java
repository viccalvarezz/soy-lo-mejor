package model;

import java.io.File;


import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import org.w3c.dom.NodeList;

public class App 
{
	private static Document doc;
	
    public static void main( String[] args )
    {
    	JFrame lienzo = new JFrame("Lienzo");
    	lienzo.setResizable(false);

    	
    	try {
            File archivo = new File("archivos/datos.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            doc = documentBuilder.parse(archivo);
            
            doc.getDocumentElement().normalize();
 
            lienzo.setBounds(0, 0, Integer.parseInt(doc.getDocumentElement().getAttribute("ancho")), Integer.parseInt(doc.getDocumentElement().getAttribute("alto")));
            
            
            NodeList listaFiguras = doc.getElementsByTagName("figura");
            lienzo.getContentPane().add(new Lienzo(listaFiguras,lienzo));

            lienzo.setLocationRelativeTo(null);
            lienzo.setVisible(true);
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    }
    
	public static void generarXML()
	{
		try {
	        Source source = new DOMSource(doc);
	        Result result = new StreamResult(new java.io.File("archivos/datosSalida.xml")); //nombre del archivo
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.transform(source, result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
}
