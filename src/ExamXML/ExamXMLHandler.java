package ExamXML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import examDatabase.Answer;
import examDatabase.Exam;
import examDatabase.ExamDB;
import examDatabase.Question;

public class ExamXMLHandler {
	ExamDB examDB;
	public ExamXMLHandler()
	{
		examDB= new ExamDB();
	}
	public String exportExamXML(int ID)
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
			for(Question q:exam.Questions())
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
			StreamResult result = new StreamResult(new File("D:\\XMLProject\\file.xml"));

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
}
