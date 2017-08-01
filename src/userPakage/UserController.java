package userPakage;

import java.sql.ResultSet;
import java.sql.SQLException;

import examDatabase.ExamDB;

public class UserController {
ExamDB db = new ExamDB();
public User Login(String username,String password)
{
	
	User user = findUserbyUserName(username);
	if(user==null)
		{
		System.out.println("Can't find User");
		return null;
		}
	System.out.println(user.password+password);
	if(user.password.equals(password))
	{	
		user.password = "";
		return  user;
	}
	return null;
}
public User findUserbyUserName(String username)
{
	String query ="SELECT * FROM Users WHERE UserName='"+username+"';";
	System.out.println(query);
	ResultSet rs = ExamDB.find(query);
	User user = new User();
	try {
		if(rs.next())
		{
		user.ID= rs.getInt("ID");
		user.userPrivilege = rs.getInt("Privilege");
		user.name = rs.getString("Name");
		user.password = rs.getString("Password").replaceAll("\\s", "");
		user.username = rs.getString("UserName").replaceAll("\\s", "");
		return user;
		
		}
		else
		{
			return null;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public User findUserbyID(int ID)
{
	String query ="SELECT * FROM User WHERE ID="+ID+";";
	System.out.println(query);
	ResultSet rs = ExamDB.find(query);
	User user = new User();
	try {
		if(rs.next())
		{
		user.ID= rs.getInt("ID");
		user.userPrivilege = rs.getInt("Privilege");
		user.name = rs.getString("Name");
		user.password = rs.getString("Password");
		user.username = rs.getString("UserName");
		return user;
		}
		else
		{
			return null;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
}
