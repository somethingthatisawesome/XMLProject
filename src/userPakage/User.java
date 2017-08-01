package userPakage;

import java.io.File;
import java.util.List;

import ExamXML.ExamXMLHandler;
import examManage.Paragraph;

public class User {
public int ID;
public String name;
public String username;
public String password;
public int userPrivilege;
ExamXMLHandler xmlhandler = new ExamXMLHandler();
public boolean Login(String username,String password)
{
	 UserController userctr = new UserController();
	 User user = null;
	 user = userctr.Login(username, password);
			 
	 if(user!=null)
	 {
	 this.ID = user.ID;
	 this.name = user.name;
	 this.username  = user.username;
	 this.userPrivilege = user.userPrivilege;
	 return true;
	 }
	 return false;
}
public void createExam(int ID,String location)
{
	xmlhandler.exportExamXML(ID, location);
}
public  OnlineTest takeExam(int ID,String location)
{
 String userFileLocation =this.username+"_"+ID+".xml";
 location  += "\\"+userFileLocation;
 System.out.println(location);
 File f = new File(location);
 if(f.exists() && !f.isDirectory()) { 
	 return xmlhandler.importExamXML(ID,location);
 }
 else
 {
	 createExam(ID,location);
	 return xmlhandler.importExamXML(ID,location);
 }

}
public int getID()
{
	return this.ID;
}
public String getUsername()
{
	return this.username;
}
public String getName()
{
	return this.name;
}
}
