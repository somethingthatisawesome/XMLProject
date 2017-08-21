<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%@ page import = "org.apache.commons.fileupload.*" %>
<%@ page import = "org.apache.commons.fileupload.disk.*" %>
<%@ page import = "org.apache.commons.fileupload.servlet.*" %>
<%@ page import = "org.apache.commons.io.output.*" %>
<%@ page import = "userPakage.User" %>
<%
User user = new User();
request.setCharacterEncoding("UTF-8");
   File file ;
   String md5="";
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
   ServletContext context = pageContext.getServletContext();
   String filePath = context.getInitParameter("file-upload");
	String time="";
   // Verify the content type
   String contentType = request.getContentType();
   String examName = "";
   String id = "";
   String filepath="";
   if ((contentType.indexOf("multipart/form-data") >= 0)) {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("D:\\Java"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try { 
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);

         // Process the uploaded file items
         Iterator i = fileItems.iterator();
         
         while ( i.hasNext () ) {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ) {
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();
               String fileName = fi.getName();
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            	//get MD5
            	md5 = user.md5();
               // Write the file
               if( fileName.lastIndexOf("\\") >= 0 ) {
                  file = new File( filePath + 
                  md5) ;
               } else {
                  file = new File( filePath + 
                  md5) ;
               }
               fi.write( file ) ;
               filepath = fileName.substring(fileName.lastIndexOf("\\")+1);
            }
            else
            {
            	if(fi.getFieldName().equals("title"))
            	{
            	examName = fi.getString();
            	
            	System.out.println(examName);}
            	if(fi.getFieldName().equals("id"))
            	{
            	id = fi.getString();
            	}
            }
            
         }
         response.sendRedirect("ExamDB.jsp?file="+md5+"&title="+examName+"&id="+id);
         return;
      } catch(Exception ex) {
         System.out.println(ex);
      }
   } else {
	   out.println("null");
   }
%>