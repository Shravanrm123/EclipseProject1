package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateRec {
public static void main(String[] args) throws ClassNotFoundException {
	Class.forName("org.postgresql.Driver");
	Connection con=null;
	try {
		con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/db1","postgres","shrmsd777");
		if(con!=null)
		{
			System.out.println("Connected Successfully");
		}
		PreparedStatement ps=con.prepareStatement("update student set grades=? where studentId=?");
		ps.setDouble(1, 85);
		ps.setInt(2, 1);
		int count=ps.executeUpdate();
		System.out.println("Number of rows inserted :" + count);
		ps=con.prepareStatement("select * from student where studentId=?");
		ps.setInt(1, 1);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+ " "+ rs.getString(2)+ " "+ rs.getDouble(3));
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
