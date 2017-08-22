package ExamXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import examDatabase.Answer;
import examDatabase.Exam;
import examDatabase.ExamDB;
import examDatabase.Question;
import examManage.Paragraph;
import userPakage.OnlineTest;

public class ExamXMLHandler {
	ExamDB examDB;
	public ExamXMLHandler()
	{
		examDB= new ExamDB();
	}
	public double getScore(String location)
	{
		double score = 1;
		int len = 0;
		int correct=0;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		Document doc;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			 doc = docBuilder.parse(location);
			 Element exam =(Element) doc.getFirstChild();
			 NodeList questions = exam.getElementsByTagName("Question");
			 len = questions.getLength();
			 for(int i=0;i<len;i++)
			 {
				 Element q = (Element)questions.item(i);
				 NodeList answers = q.getElementsByTagName("Answer");
				 int alen = answers.getLength();
				 int count=1;
				 for(int j=0;j<alen;j++)
				 {
					 Node a = answers.item(j);
					 NamedNodeMap att = a.getAttributes();
					 String isCorrect = att.getNamedItem("isCorrect").getNodeValue();
					 String isChoose = att.getNamedItem("Choose").getNodeValue();
					 System.out.println(isChoose+" "+isCorrect);
					 if(!(isCorrect.equals(isChoose)))
					 {
						count=0; 
					 }
				 }
				 correct+=count;
			 }
			System.out.println("Done");
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
		System.out.println(len+" "+correct);
		score = (double) correct/len;
		return score;
	}
	public void exportResult(String location,String[] Answers)
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		Document doc;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			 doc = docBuilder.parse(location);
			 Element exam =(Element) doc.getFirstChild();
			 XPathFactory xPathfactory = XPathFactory.newInstance();
			 XPath xpath = xPathfactory.newXPath();
			 NodeList answers = exam.getElementsByTagName("Answer");
			 int alen = answers.getLength();
			 for(int i=0;i<alen;i++)
			 {
				 Element a =(Element) answers.item(i);
				 a.setAttribute("Choose", "false");
			 }
			 if(Answers!=null)
			 for(String st:Answers)
			 {
			 XPathExpression expr = xpath.compile("/Exam/Question/Answer[@ID='"+st+"']");
			 NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			 Element n =(Element) nl.item(0);
			 n.setAttribute("Choose", "true");
			 }
		 	TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(location));
			transformer.transform(source, result);
			
			System.out.println("Done");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String exportExamXML(int ID,String location,int number)
	{
		try {
			Exam exam = new Exam(ID);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Attr attr = null;
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Exam");
			rootElement.appendChild(doc.createTextNode(exam.Title));
			doc.appendChild(rootElement);
			List<Question> qList;
			if(number==-1)
			{
			qList= exam.Questions();
			}
			else
			{
			qList= exam.getRandomizedExam(number);
			}
			for(Question q:qList)
			{
			Element question = doc.createElement("Question");
			rootElement.appendChild(question);
			
			question.appendChild(doc.createTextNode(q.Value));
			attr = doc.createAttribute("ID");
			attr.setValue(String.valueOf(q.ID));
			question.setAttributeNode(attr);
			attr = doc.createAttribute("isStatic");
			attr.setValue(String.valueOf(q.isStatic));
			question.setAttributeNode(attr);
			for(Answer a:q.Answers())
			{
			Element answer = doc.createElement("Answer");
			answer.appendChild(doc.createTextNode(a.Value));
			attr = doc.createAttribute("ID");
			attr.setValue(String.valueOf(a.ID));
			answer.setAttributeNode(attr);
			attr = doc.createAttribute("isCorrect");
			attr.setValue(String.valueOf(a.isCorrect));
			answer.setAttributeNode(attr);
			question.appendChild(answer);
			}
			
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(location));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);


		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		return "";
	}
	public OnlineTest importExamXML(int ExamID,String location)
	{
		OnlineTest ol = new OnlineTest();
		File fXmlFile = new File(location);
		DocumentBuilderFactory factory =
		DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fXmlFile);
			NodeList nodes = doc.getElementsByTagName("Question");
			NodeList anodes=null;
			int len = nodes.getLength();
			for(int i=0;i<len;i++)
			{
				Element node =(Element) nodes.item(i);
				userPakage.Question q = new userPakage.Question();
				Paragraph pr = new Paragraph();
				pr.TextContent = node.getFirstChild().getTextContent();
				pr.ID = node.getAttributes().getNamedItem("ID").getTextContent();
				anodes = node.getElementsByTagName("Answer");
				q.pr = pr;
				int alen = anodes.getLength();
				for(int j=0;j<alen;j++)
				{
					Node anode = anodes.item(j);
					Paragraph apr = new Paragraph();
					apr.isQuestion = false;
					apr.TextContent = anode.getTextContent();
					apr.ID = anode.getAttributes().getNamedItem("ID").getTextContent();
					userPakage.Answer a = new userPakage.Answer();
					a.pr = apr;
					q.answer.add(a);
				}
				ol.questions.add(q);
			}
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
		return ol;
	}
}
