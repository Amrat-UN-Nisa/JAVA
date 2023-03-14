import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBTasks {
	Connection con;
	DBTasks(){
	
	String url = "jdbc:mysql://localhost:3306/LIBRARY_SYSTEM?allowPublicKeyRetrieval=true&useSSL=false";
    String user = "root";
    String password = "eshaturradia123";
        	        try {con = DriverManager.getConnection(url, user, password);
        	        
        	  } catch (SQLException ex) {

        	 Logger lgr = Logger.getLogger(DBTasks.class.getName());
             lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
	}
	
	public String AddBook(String title,String Publisher,String status, String type)
	{
		
		String sql = "INSERT INTO BOOK(title,Publisher,status,type) VALUES(?,?,?,?)";
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, Publisher);
			pst.setString(3, status);
			pst.setString(4,type);
            pst.executeUpdate();
            
            return "A new book has been added to the library";

        } catch (SQLException ex) {

            return ex.getMessage();
        }
	}
	public String UpdateBook(String id,String title,String publisher,String type)
	{
		String sql = "Update BOOK SET title = '"+title + "',publisher = '" +publisher+ "',type =  '"+ type + "' where id = "+ id;
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
            pst.executeUpdate();
            
            return "Book Updated Successfully";

        } catch (SQLException ex) {
            return ex.getMessage();
        }
	}
		
	public String SearchBook(String name)
	{
				
		String sql = "Select * from BOOK where title like '"+ name +"%'";
		String ans="";
		String header = "ID     Title          Publisher          Status            Type\n";
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			
           ResultSet rs= pst.executeQuery();
           while(rs.next())
           { 
        	 ans+=rs.getString(1)+"     "+rs.getString(2)+"          "+rs.getString(3)+"          "+rs.getString(4) +"            "+rs.getString(5) + "\n";
           }
           if (ans == null || ans.isEmpty())
           {
        	   ans = "No Book found";
           }
           else {
        	   ans = header + ans;
           }
           return ans;

        } catch (SQLException ex) {

        	 Logger lgr = Logger.getLogger(DBTasks.class.getName());
             lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
		return null;
	}
		
	public String DeleteBook(String id)
	{
		String sql = "Delete from BOOK where id = "+ id;
		String ans="";
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			
           int rows= pst.executeUpdate();
           if (rows == 0)
           {
               return "Book not Found";   
           }         
           return "Book Deleted Successfully";

        } catch (SQLException ex) {

        	 Logger lgr = Logger.getLogger(DBTasks.class.getName());
             lgr.log(Level.SEVERE, ex.getMessage(), ex);
             return ex.getMessage();
        }
	}
		
	public String AllBOOKS()
	{
		String sql = "Select * from BOOK";
		String ans=null;
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			
			ResultSet rs= pst.executeQuery();
			ans="";
	        while(rs.next())
	        {  
	        	 ans+=rs.getString(1)+"  " + rs.getString(2)+"  "+rs.getString(3)+ "  "+rs.getString(4)+"  " +"\n";                
	        }
	        return ans;

        } catch (SQLException ex) {

        	Logger lgr = Logger.getLogger(DBTasks.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
     		return ex.getMessage();
        }
	}

	public String IssueBook(String bookID, String studentID)
	{
		String checkBook = "Select ID from BOOK where id =" + bookID + " and Status = 'Available';";
		String checkStudent = "Select ID from STUDENT where id ="+ studentID;
		try
		{
			PreparedStatement pst=con.prepareStatement(checkBook);			
			ResultSet rs= pst.executeQuery();
			String bookId="";
			String studentId="";
	        while(rs.next())
	        {  
	        	 bookId = rs.getString(1);                
	        }
	          
	        if (bookId == null || bookId.isEmpty())
	        {
	        	return "No Book found or Book is not Available";
	        }
			pst = con.prepareStatement(checkStudent);			
			rs = pst.executeQuery();
	        while(rs.next())
	        {  
	        	 studentId = rs.getString(1);                
	        }
	        if (studentId == null || studentId.isEmpty())
	        {
	        	return "No Student found";
	        }
			String issueBook = "UPDATE BOOK set Status = 'Issued' where id = "+ bookID;
			pst=con.prepareStatement(issueBook);
            pst.executeUpdate();
            
    		String bookStudentDetails = "INSERT INTO BOOKSTUDENTDETAILS(STUDENT_ID,BOOK_ID) VALUES(?,?)";
			pst=con.prepareStatement(bookStudentDetails);
			pst.setString(1, bookID);
			pst.setString(2, studentID);
            pst.executeUpdate();

	        return "Book has be Issued";
        } 
		catch (SQLException ex) 
		{
        	Logger lgr = Logger.getLogger(DBTasks.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
     		return ex.getMessage();
        }
	}

	public String[] GetBook(String bookID)
	{
		String getBook = "Select * from BOOK where id =" + bookID;
		try
		{
			PreparedStatement pst=con.prepareStatement(getBook);			
			ResultSet rs= pst.executeQuery();
			String[] bookDetails = new String[5];
			Boolean bookFound = false;
	        while(rs.next())
	        {  
				bookDetails[0] = rs.getString(1);
				bookDetails[1] = rs.getString(2);
				bookDetails[2] = rs.getString(4);
				bookDetails[3] = rs.getString(3);
				bookDetails[4] = rs.getString(5);
	        	bookFound = true;
	        }
	          
	        if (bookFound==false)
	        {
	        	return null;
	        }
	        return bookDetails;
        } 
		catch (SQLException ex) 
		{
        	Logger lgr = Logger.getLogger(DBTasks.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
	}

	public String ReturnBook(String bookID)
	{
		String checkBook = "Select ID from BOOK where id = "+ bookID + " and Status = 'Issued'";
		try
		{
			PreparedStatement pst=con.prepareStatement(checkBook);			
			ResultSet rs= pst.executeQuery();
			String bookId="";
	        while(rs.next())
	        {  
	        	 bookId = rs.getString(1);                
	        }
	          
	        if (bookId == null || bookId.isEmpty())
	        {
	        	return "No Book found or Book is already Available";
	        }
			String returnBook = "UPDATE BOOK set Status = 'Available' where id = "+ bookID;
			pst=con.prepareStatement(returnBook);
            pst.executeUpdate();
	        return "Book Returned Successfully";
        } 
		catch (SQLException ex) 
		{
        	Logger lgr = Logger.getLogger(DBTasks.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
     		return ex.getMessage();
        }
	}
	
	public String Login(String username, String password)
	{
		String login = "Select name from USER where username = '"+ username + "'" + " and password = '" + password + "'";
		try
		{
			PreparedStatement pst=con.prepareStatement(login);			
			ResultSet rs= pst.executeQuery();
			String name="";
	        while(rs.next())
	        {  
	        	 name = rs.getString(1);                
	        }
	          
	        if (name == null || name.isEmpty())
	        {
	        	return "Wrong username/password";
	        }
	        return name;
        } 
		catch (SQLException ex) 
		{
        	Logger lgr = Logger.getLogger(DBTasks.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
     		return ex.getMessage();
        }
	}

	

}
	
	
	

