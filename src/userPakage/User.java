package userPakage;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

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
public String md5()
{
	String hex="";
	try {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		hex = (new HexBinaryAdapter()).marshal(md5.digest((String.valueOf(System.currentTimeMillis())).getBytes()));
		System.out.println(hex);
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return hex;
}
public String md5(String st)
{
	String hex="";
	try {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		hex = (new HexBinaryAdapter()).marshal(md5.digest((st).getBytes()));
		System.out.println(hex);
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return hex;
}
public void createExam(int ID,String location)
{
	xmlhandler.exportExamXML(ID, location,-1);
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
public String getUSERNAME()
{
	return this.username;
}
public String getNAME()
{
	return this.name;
}
}
