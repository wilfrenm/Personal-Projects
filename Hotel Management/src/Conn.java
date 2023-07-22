import java.sql.*;
public class Conn {//Step 4: Executing mysql Queries
	Connection c;
	Statement s;
	Conn()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//1. Register the driver
			//Very very Important below format 
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem","root","wilfren@123");//Step 2:creating the connection,format--> database name hotelmanagement created in mysql
			s=c.createStatement();//Step 3: Creating Statement
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		new Conn();
	}

}
