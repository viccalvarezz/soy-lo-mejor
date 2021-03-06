package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Lienzo extends JPanel
{
	
	private static final long serialVersionUID = 1L;
	
	private NodeList listaFiguras;
	
	public Lienzo(NodeList lista, JFrame f)
	{
		this.listaFiguras = lista;
		setBounds(f.getBounds());
    	JButton colorearCirculos = new JButton("colorear_circulo");
    	colorearCirculos.setBounds(200, 100, 100, 25);
    	JButton colorearRectangulos = new JButton("colorear_rectangulo");
    	final JPanel p = this;
    	colorearRectangulos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < listaFiguras.getLength(); i++) {
		        	Node nodo = listaFiguras.item(i);
		        	 if (nodo.getNodeType() == Node.ELEMENT_NODE) {
		                 Element element = (Element) nodo;
		                 if(element.getAttribute("tipo").equals("rectangulo")) {
		                	 element.setAttribute("color", String.valueOf(Color.PINK.getRGB()));
		                	 p.repaint();
		                	 App.generarXML();
		                 }
		        	 }
				}
				
			}
    		
    	});
    	colorearCirculos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < listaFiguras.getLength(); i++) {
		        	Node nodo = listaFiguras.item(i);
		        	 if (nodo.getNodeType() == Node.ELEMENT_NODE) {
		                 Element element = (Element) nodo;
		                 if(element.getAttribute("tipo").equals("circulo")) {
		                	 element.setAttribute("color", String.valueOf(Color.DARK_GRAY.getRGB()));
		                	 p.repaint();
		                	 App.generarXML();
		                 }
		        	 }
				}
				
			}
    		
    	});
    	
    	this.add(colorearCirculos);
    	this.add(colorearRectangulos);
    
	}
	
	@Override 
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
	        for (int i = 0; i < listaFiguras.getLength(); i++) {
	        	g.setColor(Color.BLACK);
	        	Node nodo = listaFiguras.item(i);
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) nodo;
	  
	                int x = Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent());
	                int y = Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent());
	                if(element.getAttribute("tipo").equals("rectangulo")) {
	                	int w = Integer.parseInt(element.getElementsByTagName("ancho").item(0).getTextContent());
	                	int h = Integer.parseInt(element.getElementsByTagName("alto").item(0).getTextContent());
	                	if(element.getAttribute("color")=="")
	                		g.drawRect(x, y, w, h);
	                	else
	                	{
	                		g.setColor(new Color(Integer.parseInt(element.getAttribute("color"))));
	                		g.fillRect(x, y, w, h);
	                	}
	                	
	                
	                } else if(element.getAttribute("tipo").equals("circulo")) {
	                	int r = Integer.parseInt(element.getElementsByTagName("radio").item(0).getTextContent());
	                	
	                	if(element.getAttribute("color")=="")
	                		g.drawArc(x, y, r, r, 0, 360);
	                	else
	                	{
	                		g.setColor(new Color(Integer.parseInt(element.getAttribute("color"))));
	                		g.fillArc(x, y, r, r, 0, 360);
	                	}
	                }	
	              
	            }
	        }
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
   }


}
