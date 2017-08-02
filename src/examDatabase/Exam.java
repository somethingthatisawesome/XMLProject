package examDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Exam implements SQLAction {

	public int ID;
	public String Title;
	public int Time;
	public String Date;
	public int User_ID;
	public Exam()
	{
		
	}
	public Exam(String Title,int time,int user_id) {
		this.Title = Title;
		this.Time = time;
		this.User_ID = user_id;
	}
	public Exam(int ID)
	{
		ExamDB e = new ExamDB();
		findByID(ID);
	}
	@Override
	public boolean insert() {
		String query =  MessageFormat.format("INSERT INTO Exam(Title,Time,User_ID) VALUES(N\"{0}\",\"{1}\",\"{2}\");"
				,this.Title,this.Time,this.User_ID).replaceAll("\"", "\'");
		System.out.println(query);
		int ID = ExamDB.insert(query);
		if(ID!=-1)
		{
			this.ID = ID;
			return true;
		}
		return false;
	}
	@Override
	public boolean remove() {
		String query = "DELETE FROM Exam WHERE ID="+this.ID+";";
		ExamDB.remove(query);
		return false;
	}
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
	public List<Exam> getALL()
	{
		List<Exam> exs= new ArrayList<Exam>();
		String query ="SELECT * FROM Exam;";
		System.out.println(query);
		ResultSet rs = ExamDB.find(query);
		
		try {
			while(rs.next())
			{
			Exam ex = new Exam();
			ex.ID = rs.getInt("ID");
			ex.Title = rs.getString("Title");
			ex.User_ID = rs.getInt("User_ID");
			ex.Time = rs.getInt("Time");
			ex.Date = rs.getString("Date");
			System.out.println(ex.ID);
			exs.add(ex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exs;
	}
	@Override
	public boolean findByID(int ID) {
		// TODO Auto-generated method stub
				String query;
				query ="SELECT * FROM Exam WHERE ID="+ID+";";
				System.out.println(query);
				ResultSet rs = ExamDB.find(query);
				
				try {
					if(rs.next())
					{
					this.ID = rs.getInt("ID");
					this.Title = rs.getString("Title");
					this.User_ID = rs.getInt("User_ID");
					this.Time = rs.getInt("Time");
					this.Date = rs.getString("Date");
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
	public List<Question> Questions()
	{
		return getQuestionByExamID(this.ID);
	}
	public List<Question> getQuestionByExamID(int exam_ID)
	{
		List<Question> questions = new ArrayList<Question>();
		String query ="SELECT * FROM Question WHERE Exam_ID="+exam_ID+";";
		System.out.println(query);
		ResultSet rs = ExamDB.find(query);
		
		try {
			while(rs.next())
			{
			Question question = new Question();
			question.ID = rs.getInt("ID");
			question.isStatic = rs.getBoolean("isStatic");
			question.Value = rs.getString("Value");
			question.Exam_ID = rs.getInt("Exam_ID");
			questions.add(question);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}
	public String getTITLE()
	{
		return this.Title;
	}
	public String getID()
	{
		return String.valueOf(this.ID);
	}
	public String getDATE()
	{
		return this.Date;
	}
	public int getTIME()
	{
		return this.Time;
	}
	public int getUSERID()
	{
		return this.User_ID;
	}
}
