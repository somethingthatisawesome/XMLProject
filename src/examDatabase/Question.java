package examDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Question implements SQLAction {

	public int ID;
	public String Value;
	public boolean isStatic=false;
	public int Exam_ID;
	@Override
	public boolean insert() {
		// TODO Auto-generated method stub
		String query =  MessageFormat.format("INSERT INTO Question(Value,isStatic,Exam_ID) VALUES(N\"{0}\",\"{1}\",\"{2}\");",this.Value,this.isStatic,this.Exam_ID).replaceAll("\"", "\'");
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
		// TODO Auto-generated method stub
		String query = "DELETE FROM Question WHERE ID="+this.ID+";";
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
		String query ="SELECT * FROM Question WHERE ID="+ID+";";
		System.out.println(query);
		ResultSet rs = ExamDB.find(query);
		
		try {
			if(rs.next())
			{
			this.ID = rs.getInt("ID");
			this.Exam_ID = rs.getInt("Exam_ID");
			this.isStatic = rs.getBoolean("isStatic");
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
	public List<Answer> Answers()
	{
		return getAnswersByQuestionID(this.ID);
	}
	public List<Answer> getAnswersByQuestionID(int Question_ID)
	{
		List<Answer> answers = new ArrayList<Answer>();
		String query ="SELECT * FROM Answer WHERE Question_ID="+Question_ID+";";
		System.out.println(query);
		ResultSet rs = ExamDB.find(query);
		
		try {
			while(rs.next())
			{
			Answer answer = new Answer();
			answer.ID = rs.getInt("ID");
			answer.Question_ID = rs.getInt("Question_ID");
			answer.isCorrect = rs.getBoolean("isCorrect");
			answer.Value = rs.getString("Value");
			answers.add(answer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answers;
	}
}
