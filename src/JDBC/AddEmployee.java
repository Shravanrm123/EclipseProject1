package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddEmployee {
public static void main(String[] args) throws ClassNotFoundException {
	Class.forName("org.postgresql.Driver");
	Connection con=null;
	try {
		con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/db1","postgres","shrmsd777");
		if(con!=null)
		{
			System.out.println("Connected Successfully");
		}
		//First Part
		PreparedStatement ps=con.prepareStatement("insert into employee(empname,address,email,phonenumber,loc,qualification)values(?,?,?,?,?,?)returning eid");
				
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter employee name");
		ps.setString(1,sc.nextLine());
		System.out.println("Enter address");
		ps.setString(2,sc.nextLine());
		System.out.println("Enter email");
		ps.setString(3,sc.nextLine());
		System.out.println("Enter phonenumber");
		ps.setString(4,sc.nextLine());
		System.out.println("Enter location");
		ps.setString(5,sc.nextLine());
		System.out.println("Enter qualification");
		ps.setString(6,sc.nextLine());
	
			ResultSet rs=ps.executeQuery();
				rs.next();
				//Second part
				int empid=rs.getInt(1);
				System.out.println("The generated employee id is:"+empid);
				System.out.println("Enter number of work experience");
				int n=sc.nextInt();
				PreparedStatement ps1=con.prepareStatement("insert into workexperience(empid,yearsofexp,companyname,salary,designation)"+"values(?,?,?,?,?)");
				for(int i=0;i<n;i++)
			{
			ps1.setInt(1, empid);
			System.out.println("Enter the years of experience");
			ps1.setInt(2, sc.nextInt());
			System.out.println("Enter the company name");
			ps1.setString(3, sc.next());
			System.out.println("Enter the salary");
			ps1.setInt(4, sc.nextInt());
			System.out.println("Enter the designation");
			ps1.setString(5, sc.next());
			ps1.addBatch();
				}
				ps1.executeBatch();
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
