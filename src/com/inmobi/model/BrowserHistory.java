package com.inmobi.model;

import java.sql.*;

import com.inmobi.MainController;



public class BrowserHistory {
	Statement stmt = null;
	Connection c = null;
	
	public void getHistory(){
	try {
	      
	      c = BrowserSqliteConnect.connect();
	      
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT url  FROM urls WHERE visit_count >= 3  ORDER BY visit_count DESC;" );
	      while ( rs.next() ) {
	         
	         String  url = rs.getString("url");
	         //int visit_count =  rs.getInt("visit_count");
	         //int id = rs.getInt("id");
	        
	         System.out.println( "URl = " + url );
	        // System.out.println( "VISIT Count = " + visit_count );
	         String s;
             int i;
             i=0;
             char a=url.charAt(0);
             while(a!='.')
             {
                 i++;
                 a=url.charAt(i);
             }
             s=url.substring(++i);
             System.out.println(s);
             String s2;
             int k=s.indexOf("/");
             
             
             s2=s.substring(0, k);
             System.out.println(s2);
             HttpRequest hr = new HttpRequest();

             hr.request(s2);
             
             ArrayTemp.zeroLayer(s2);
             
             
             break;// PLEASE REMOVE IT !!!

	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	  }
}
