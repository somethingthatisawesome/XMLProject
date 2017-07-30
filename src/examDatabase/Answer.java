package examDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

public class Answer implements SQLAction {
	public int ID;
	public String Value="";
	public boolean isCorrect;
	public int Question_ID;
	public Answer()
	{
		
	}
	public Answer(int ID)
	{
		findByID(ID);
	}
	@Override
	public boolean insert() {
		String query =  MessageFormat.format("INSERT INTO Answer(Question_ID,Value,isCorrect) "
				+ "VALUES(\"{0}\",N\"{1}\",\"{2}\");"
				,this.Question_ID,this.Value,this.isCorrect).replaceAll("\"", "\'");
		System.out.println(query);
		if(ExamDB.insert(query)!=-1) return true;
		return false;
	}
	@Override
	public boolean remove() {
		// TODO Auto-generated method stub
				String query = "DELETE FROM Answer WHERE ID="+this.ID+";";
				ExamDB.remove(query);
				return false;
	}
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean findByID(int ID) {
		// TODO Auto-generated method stub
				String query ="SELECT * FROM Answer WHERE ID="+ID+";";
				System.out.println(query);
				ResultSet rs = ExamDB.find(query);
				
				try {
					if(rs.next())
					{
					this.ID = rs.getInt("ID");
					this.Question_ID = rs.getInt("Question_ID");
					this.isCorrect = rs.getBoolean("isCorrect");
					this.Value = rs.getString("Value");
					return true;
					}
					else
					{
						return false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
	}
	
}
