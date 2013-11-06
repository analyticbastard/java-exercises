package com.zeda.javatest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;





public class JDBCTest {
	
	public  String HOST = "ec2-54-201-8-75.us-west-2.compute.amazonaws.com";
	public  String DB   = "mysql";
	
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultset = null;
	
	public void readDatabase() throws SQLException {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No driver");
			System.exit(-1);
		}
		
		String query = "jdbc:" + DB + "://" + HOST + "/prueba?user=jarriero";
		
		connection = DriverManager
				.getConnection(query);
		
		statement = connection.createStatement();
		resultset = statement.executeQuery("SELECT * FROM iris");
		
		writeMetaData(resultset);
		writeResultSet(resultset);
		
		close();
	}
	
	private void writeMetaData(ResultSet resultSet) throws SQLException {
		System.out.println("The columns in the table are: ");
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		}
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
		}
		System.out.println("");
	}
	
	private void writeResultSet(ResultSet resultset) throws SQLException {
		while (resultset.next()) {
			float at1 = resultset.getFloat(1);
			float at2 = resultset.getFloat("at2");
			float at3 = resultset.getFloat("at3");
			float at4 = resultset.getFloat("at4");
			String type = resultset.getString("type");
			
			String line = "" + at1 + "\t" + at2 + "\t" + at3 
					+ "\t" + at4 + "\t" + type;
			System.out.println(line);
		}
	}
	
	private void close() throws SQLException {
		if (resultset != null) resultset.close();
		if (statement != null) statement.close();
		if (connection != null) connection.close();
	}
	
	public static int test() {
		System.out.println("Test initiated on "
				+ JDBCTest.class.getName());
		
		JDBCTest test = new JDBCTest();
		
		try {
			test.readDatabase();
			//System.in.read();
		} catch (Exception e) {
			System.out.println("Exception " + e);
			
			return -1;
		}
		
		System.out.println("Test successfully finished on "
				+ JDBCTest.class.getName());
		
		return 0;
	}
}
