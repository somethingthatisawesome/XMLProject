package examManage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WordDocument {
public List<Paragraph> paragraphs= new ArrayList<Paragraph>();

public WordDocument()
{
	
}
public WordDocument(String location)
{
	ReadDocumentXML(loadDocumentXML(location));
	markQuestion();
}
@SuppressWarnings("resource")
private InputStream loadDocumentXML(String location)
{
	System.out.print("Loading Document...");
	ZipFile zipFile;
		try {
			zipFile = new ZipFile(location);
			System.out.println("Done!");
			return zipFile.getInputStream(zipFile.getEntry("word/document.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return null;
}
private void ReadDocumentXML(InputStream input)
{
	System.out.print("Reading...");
	DocumentBuilderFactory factory =
			DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(input);
				NodeList listP = doc.getElementsByTagName("w:p");
				
				for(int i=0;i<listP.getLength();i++)
				{	
					Paragraph pr = new Paragraph();
					Element paragraph =(Element) listP.item(i);
					Element pPr = (Element) paragraph.getFirstChild();
					Element numbering = (Element) pPr.getElementsByTagName("w:pStyle").item(0);
					Element rPr = (Element) pPr.getElementsByTagName("w:rPr").item(0);
					Element color = null;
					if(rPr!=null)
					color = (Element) rPr.getElementsByTagName("w:color").item(0);
					//System.out.println(paragraph.getTextContent());
					pr.TextContent = paragraph.getTextContent();
					if(numbering!=null)
					//System.out.println(numbering.getAttribute("w:val"));
						pr.Numbering = numbering.getAttribute("w:val");
					if(color!=null)
					//System.out.println(color.getAttribute("w:val"));
						pr.Color = color.getAttribute("w:val");
					paragraphs.add(pr);
				}
				System.out.println("Done!");
				System.out.println("Paragraph(s) Found:"+listP.getLength());
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
}
private void markQuestion()
{
	System.out.print("Marking Questions...");
	List<Paragraph> paragraphs = this.paragraphs;
	String baseQuestionNumbering = paragraphs.get(0).Numbering;
	for(Paragraph pr : paragraphs)
	{
		if(pr.Numbering.equals(baseQuestionNumbering))
		{
			pr.isQuestion = true;
		}
		else
		{
			pr.isQuestion = false;
		}
	}
	System.out.println("Done!");
}
}
