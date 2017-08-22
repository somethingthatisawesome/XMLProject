package examDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import userPakage.User;

public class Test implements SQLAction {
	public int ID;
	public int exam_ID;
	public int user_ID;
	public String fileName;
	public double mark;
	public Exam exam;
	public String date;
	public Test(int exam_ID,int user_ID,String fileName,double mark)
	{
		this.exam_ID = exam_ID;
		this.user_ID = user_ID;
		this.fileName = fileName;
		this.mark = mark;
	}
	public Test()
	{
		
	}
	@Override
	public boolean insert() {
		ExamDB db = new ExamDB();
		// TODO Auto-generated method stub
		String query =  MessageFormat.format("INSERT INTO Test(Exam_ID,User_ID,Mark,FileName) VALUES(\"{0}\",\"{1}\",\"{2}\",\"{3}\");"
				,this.exam_ID,this.user_ID,this.mark,this.fileName).replaceAll("\"", "\'");
		System.out.println(query);
		int ID = ExamDB.insert(query);
		if(ID!=-1)
		{
			this.ID = ID;
			return true;
		}
		return false;
	}
	public boolean removeByExamID(int ID)
	{
		String query = "DELETE FROM Test WHERE Exam_ID="+ID+";";
		ExamDB.remove(query);
		return false;
	}
	@Override
	public boolean remove() {
		// TODO Auto-generated method stub
		
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
		return false;
	}
	public List<Test> findByUserID(int UserID)
	{
		List<Test> tests = new ArrayList<Test>();
		String query ="SELECT * FROM Test WHERE User_ID="+UserID+";";
		System.out.println(query);
		ResultSet rs = ExamDB.find(query);
		
		try {
			while(rs.next())
			{
			Test test = new Test();
			test.ID = rs.getInt("ID");
			test.exam_ID = rs.getInt("Exam_ID");
			test.user_ID = rs.getInt("User_ID");
			test.date = rs.getString("Date");
			test.mark = rs.getDouble("Mark");
			test.fileName = rs.getString("FileName");
			tests.add(test);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tests.size());
		return tests;
	}
	public int getID()
	{
		return this.ID;
	}
	public Exam Exam()
	{
		this.exam = new Exam(this.exam_ID);
		return this.exam;
	}
	public String getEXAMTITLE()
	{
		System.out.println(Exam().Title);
		return Exam().Title;
	}
	public String getDATE()
	{
		return this.date;
	}
	public int getEXAMTIME()
	{
		return Exam().Time;
	}
	public double getMARK()
	{
		return this.mark*10;
	}
}
