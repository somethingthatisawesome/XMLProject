package examDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ExamDB {
private static String dbURL = "jdbc:sqlserver://localhost;databaseName=ExamsDatabase;user=sa;password=sa";
public static Connection conn=null;
public ExamDB()
{
	if(conn==null)
	{
	 try {
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(dbURL);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
public static int insert(String query)
{
    PreparedStatement statement;
	try {
		statement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
    statement.executeUpdate();
    ResultSet result = statement.getGeneratedKeys();
    if (result.next()) {
  	    return result.getInt(1);
  	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return -1;
}
public static boolean remove(String query)
{
	PreparedStatement statement;
	try {
		statement = conn.prepareStatement(query);
    statement.executeUpdate();
    return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public static ResultSet find(String query)
{
	Statement statement;
	try {
	statement = conn.createStatement();
	return statement.executeQuery(query);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
}
