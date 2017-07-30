package examDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Exam implements SQLAction {

	public int ID;
	public String Title;
	public Exam()
	{
		
	}
	public Exam(String Title) {
		this.Title = Title;
	}
	public Exam(int ID)
	{
		ExamDB e = new ExamDB();
		findByID(ID);
	}
	@Override
	public boolean insert() {
		String query =  MessageFormat.format("INSERT INTO Exam(Title) VALUES(N\"{0}\");"
				,this.Title).replaceAll("\"", "\'");
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
	@Override
	public boolean findByID(int ID) {
		// TODO Auto-generated method stub
				String query ="SELECT * FROM Exam WHERE ID="+ID+";";
				System.out.println(query);
				ResultSet rs = ExamDB.find(query);
				
				try {
					if(rs.next())
					{
					this.ID = rs.getInt("ID");
					this.Title = rs.getString("Title");
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
}
