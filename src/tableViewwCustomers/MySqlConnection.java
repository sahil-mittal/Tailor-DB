package tableViewwCustomers;

import java.sql.Connection;

import java.sql.DriverManager;

public class MySqlConnection 
{
	public static Connection doConnect()
	{
		Connection con=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/tailordb","root","");
			System.out.println("Conected.........");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String arg[]) 
	{
		doConnect();
	}
}
