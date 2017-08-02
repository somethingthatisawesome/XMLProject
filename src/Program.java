import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import examDatabase.Answer;
import examDatabase.Exam;
import examDatabase.ExamDB;
import examDatabase.Question;
import examManage.Paragraph;
import examManage.WordDocument;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String hex = (new HexBinaryAdapter()).marshal(md5.digest((String.valueOf(System.currentTimeMillis())).getBytes()));
			System.out.println(hex);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
