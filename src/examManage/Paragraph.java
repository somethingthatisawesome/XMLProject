package examManage;

public class Paragraph {
	public String TextContent="";
	public String Numbering="";
	public String Color="";
	public boolean isQuestion=false;
	public String standardized()
	{
		String string = new String(this.TextContent);
		string = string.replaceAll("&", "&amp;");
		string = string.replaceAll("\"", "&quot;");
		string = string.replaceAll("'", "&apos;");
		string = string.replaceAll("<", "&lt;");
		string = string.replaceAll(">", "&gt;");
		return string;
	}
}
