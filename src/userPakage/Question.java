package userPakage;

import java.util.ArrayList;
import java.util.List;

import examManage.Paragraph;

public class Question {
	public List<Answer> answer;
	public Paragraph pr;
	public Question()
	{
		this.answer = new ArrayList<Answer>();
	}
	public List<Answer> getANSWERS()
	{
		return this.answer;
	}
	public String getTEXT()
	{
		return this.pr.TextContent;
	}
	public String getID()
	{
		return this.pr.ID;
	}
}
