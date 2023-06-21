package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Del {
public static void main(String[] args) throws ClassNotFoundException {
	Class.forName("org.postgresql.Driver");
	Connection con=null;
	try {
		con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/db1","postgres","shrmsd777");
		if(con!=null)
		{
			System.out.println("Connected Successfully");
		}
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery("Select * from student");
		System.out.println("Before deleting");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+ " " + rs.getString(2)+ " " + rs.getDouble(3));
		}
		PreparedStatement ps=con.prepareStatement("delete from student where studentid=?");
		ps.setInt(1, 2);
		int count=ps.executeUpdate();
		System.out.println("Number of rows inserted :" + count);
		st=con.createStatement();
		rs=st.executeQuery("Select * from student");
		System.out.println("After deleting");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+ " " + rs.getString(2)+ " " + rs.getDouble(3));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if(con!=null) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
}
}
