package com.inmobi.model;


import java.sql.*;

public  class BrowserSqliteConnect
{
  public static Connection connect()
  {
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:C:/Users/susheel/AppData/Local/Google/Chrome/User Data/Default/History");
      System.out.println("Opened database successfully");

    } catch ( Exception e ) {
    	System.out.print("error");
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      
    }
	return c;
  }
}