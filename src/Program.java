import java.util.List;

import examDatabase.Answer;
import examDatabase.Exam;
import examDatabase.ExamDB;
import examDatabase.Question;
import examManage.Paragraph;
import examManage.WordDocument;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordDocument word = new WordDocument("C:\\Users\\trihm\\Desktop\\Mẫu_Hoàn chỉnh.docx");
		ExamDB examDB = new ExamDB();
		Exam exam = new Exam("Phân Tích");
		Question question = new Question();
		Answer answer = new Answer();
		int tempQuestionID=0;
		exam.insert();
		int Exam_ID = exam.ID;
		System.out.println(exam.ID);
		for(Paragraph pr:word.paragraphs)
		{
			if(pr.isQuestion==true)
			{
				question.Exam_ID = Exam_ID;
				if(pr.Color!="")
				{
				question.isStatic = true;
				}
				else
				{
					question.isStatic = false;
				}
				question.Value = pr.TextContent;
				question.insert();
				tempQuestionID = question.ID;
			}
			else
			{
				answer.Question_ID = tempQuestionID;
				answer.Value = pr.TextContent;
				if(pr.Color!="")
				{
					answer.isCorrect = true;
				}
				else
				{
					answer.isCorrect = false;
				}
				answer.insert();
			}
		}
	}

}
