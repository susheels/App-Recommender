
package com.inmobi.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class HttpRequest {
	public void request(String urlsplit){
		// some URL

		String urlStr = "http://api.similarweb.com/Site/"+urlsplit+"/v2/category?Format=JSON&UserKey=c863d7689cb71cab731e7a356ebc4ee7";
		URI uri;

		try {
			uri = new URI( urlStr);
			String host = uri.getHost( );
			String path = uri.getRawPath( );
			if (path == null || path.length( ) == 0) {
			    path = "/";
			}
			 
			String query = uri.getRawQuery( );
			if (query != null && query.length( ) > 0) {
			    path += "?" + query;
			}
			
			String protocol = uri.getScheme( );
			int port = uri.getPort( );
			if (port == -1) {
			    if (protocol.equals("http")) {
			        port = 80; // http port
			    }
			    else if (protocol.equals("https")) {
			        port = 443; // https port
			    }
			    
			}
			
			Socket socket = new Socket( host, port ); 
			PrintWriter request = new PrintWriter( socket.getOutputStream() );
			request.print(  "GET " + path + " HTTP/1.1\r\n" +
			                       "Host: " + host + "\r\n" +			                       
			                       "Connection: close\r\n\r\n");
			request.flush( ); 
			
			String s = getCategory(socket);

			
			//System.out.println(s);
			//json parse
			try{
				JSONParser parser=new JSONParser();
				String s1 = "[0,"+s+"]";
		         Object obj = parser.parse(s1);
		         JSONArray array = (JSONArray)obj;
		         
		        // System.out.println("The 2nd element of array");
		         //System.out.println(array.get(1));
		         //System.out.println();

		         JSONObject obj3 = (JSONObject)array.get(1);

		        // System.out.println("Field \"1\"");
		         String s3 =  obj3.get("Category").toString(); 
		         //System.out.println(s3);
		         String s4 = null;
		         if(s3.indexOf('/')!= -1){
		        	 s4  = s3.substring(s3.indexOf('/')+1);
		         }else{
		         int i = 0;
		         while(s3.charAt(i)!= '/'){
		        	 i++;
		        	 
		         }
		         s4 = s3.substring(0,i);
		         }
		         
		         s4=s4.toLowerCase();
		         s4 = s4.replace('_', ' ');
		         System.out.println(s4);
		         Trial.getAppsWeb(s4);

		         
		         

		         
		      }catch(ParseException pe){
		         System.out.println("position: " + pe.getPosition());
		         System.out.println(pe);
		      }
			
			

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public String getCategory(Socket socket){
		InputStream inStream;
		String line = null;
		String s2 = null;
		try {
			inStream = socket.getInputStream( );
			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(inStream));
			
			while ((line = rd.readLine()) != null) {
			   s2 = line;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s2 ;
	}
}


