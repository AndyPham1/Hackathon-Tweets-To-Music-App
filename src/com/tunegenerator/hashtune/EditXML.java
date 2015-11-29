package com.tunegenerator.hashtune;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EditXML {
public static final String xmlFilePath = "soundhelix-0.8\\generator\\test.xml";
	
	public static void makeChanges(int program, int bpm, int transposition){
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFilePath);
			
			//changing instrument programs
			NodeList instruments = document.getElementsByTagName("map");
			
			/*Node percussion = instruments.item(0);
			Node melody = instruments.item(1);
			Node chords = instruments.item(2);
			Node accomp = instruments.item(3);
			Node bass = instruments.item(4);
			Node string = instruments.item(5);
			Node pad = instruments.item(6);
			Node arpeggio = instruments.item(7);
			Node melody2 = instruments.item(8);*/
			
			for(int i = 1; i <= 8; i++){ //percussion does not have 'program' field
				NamedNodeMap attribute = instruments.item(i).getAttributes();
				Node nodeAttr = attribute.getNamedItem("program");
				nodeAttr.setTextContent(Integer.toString((program * i)/(program * (i + 1)) * 128 ));
			}
			
			//bpm
			Node bpmNode = document.getElementsByTagName("random").item(document.getElementsByTagName("random").getLength()-3);
			NamedNodeMap attribute = bpmNode.getAttributes();
			Node bpmMax = attribute.getNamedItem("max");
			Node bpmMin = attribute.getNamedItem("min");
			bpmMax.setTextContent(Integer.toString((int) (bpm / 3) + 5));
			bpmMin.setTextContent(Integer.toString((int) (bpm / 3) - 5));
			
			//transposition
			Node transpositionNode = document.getElementsByTagName("random").item(document.getElementsByTagName("random").getLength()-2);
			attribute = transpositionNode.getAttributes();
			Node transpositionMax = attribute.getNamedItem("max");
			Node transpositionMin = attribute.getNamedItem("min");
			transpositionMax.setTextContent(Integer.toString(transposition + 5));
			transpositionMin.setTextContent(Integer.toString(transposition - 5));
			
			//write the DOM object to the file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			transformer.transform(domSource, streamResult);
			
		} catch (ParserConfigurationException pce){
			pce.printStackTrace();
		} catch (TransformerException te){
			te.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		} catch (SAXException sae){
			sae.printStackTrace();
		}
	}
}
