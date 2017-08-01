package userPakage;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.runtime.QuotedStringTokenizer;
public class OnlineTest {
	public List<Question> questions;
	public OnlineTest()
	{
		this.questions = new ArrayList<Question>();
	}
	public List<Question> getQUESTIONS()
	{
		return this.questions;
	}
}
