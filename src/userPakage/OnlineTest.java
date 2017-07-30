package userPakage;
import java.util.List;

import examDatabase.*;
public class OnlineTest {
	int TestID;
	public List<Question> questions;
	public OnlineTest()
	{
		
	}
	public OnlineTest(int Exam_ID)
	{
		this.questions = new Exam(Exam_ID).Questions();
	}
}
