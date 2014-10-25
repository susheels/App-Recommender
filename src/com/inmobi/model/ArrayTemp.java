package com.inmobi.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import com.inmobi.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class ArrayTemp {
	public static String[][] appData = new String[15][2];
	public static int index = 0;
	public static int layer = 0;
	public static String[] tag;
	public static int[] tagCount  = new int[15];
	public static String[] tagtmp;
	public static String appName;
	public static String l1 = null;
	public static String l21 = null;
	public static String l22 = null;

	
	MainController mc = new MainController();
	
	
	public static void insertAppData(String appName,String appWebsite){
		while(index < 15){
			
			 int i;
             i=0;
             char a=appWebsite.charAt(0);
             while(a!='.')
             {
                 i++;
                 a=appWebsite.charAt(i);
             }
             appWebsite=appWebsite.substring(++i);
             //System.out.println(s);
             int k=appWebsite.indexOf("/");
             
             if(k!=-1)
                   appWebsite=appWebsite.substring(0, k);
			
			appData[index][0] = appName;
			appData[index][1] = appWebsite;
			index++;
			break;
		}
			
			
			
	}
	public static void zeroLayer(String bhurl){
		Document doc;
		Document doc2;
		String temp ;
		int m=0;
		String linkHref1 = null;
		
		String prev = null;
		while(bhurl.charAt(m)!='.')
		{
			m++;
		}
		temp=bhurl.substring(0,++m);
		try {
			doc2=Jsoup.connect("https://play.google.com/store/search?q="+temp+"&c=apps&hl=en").get();
			Elements contentmain = doc2.getElementsByClass("card-click-target");
			
			
			for(Element c1 : contentmain){
				Elements a1 = c1.getElementsByTag("a");
				for(Element d1 : a1){//for loop runs once only
					  linkHref1 = d1.attr("href");
					  
					int count = 0;//remove extra part in url
					if(linkHref1.equals(prev)|| count==0){
						  
					         prev=linkHref1;
					         if(count == 0){
								 // System.out.println(linkHref1);
								  int z =0;
								  while(linkHref1.charAt(z)!= '='){
									  z++;
									  
								  }
								  
								  appName = linkHref1.substring(++z);
								  System.out.println(appName);
								  if(appName.indexOf(temp)!= -1){
									  System.out.println("APP IS : "+appName);
									  
									  
									  
									  break;
								  }
								  else{
									   firstLayer(bhurl);
									   break;
								  }
					         }

					         count++;
					  }
					  //String linkText = d.text();
					  
					  
			}
				break;
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void firstLayer(String bhurl) throws Exception{
		System.out.println(bhurl);
		int g= 0;
		for(g=0;g<15;g++){
			if(appData[g][1].equalsIgnoreCase(bhurl)){
				layer = 1;
				break;
			}
		}
		if(layer ==1){
			System.out.println("APP IS: "+ appData[g][0]);
			//secondLayer(bhurl);//REMOVE THIS
			l1 = appData[g][0];
			
			
			
		}
		else
		{
			secondLayer(bhurl);
			
		}
		
	}
	
	
	private static void secondLayer(String bhurl) throws ParseException {
		//first write a http request to similar web and get tags of bhurl as json and store in single array
		String urlStr = "http://api.similarweb.com/Site/"+bhurl+"/v2/tags?Format=JSON&UserKey=c863d7689cb71cab731e7a356ebc4ee7";
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
			InputStream inStream;
			String line,s2 = null;
			try {
				inStream = socket.getInputStream( );
				BufferedReader rd = new BufferedReader(
				        new InputStreamReader(inStream));
				
				while ((line = rd.readLine()) != null) {
				   s2 = s2+line;
				}
				int y= 0;
				while(s2.charAt(y)!= '{'){
					y++;
					
				}
				s2 = s2.substring(++y);
				s2 =  "{"+s2;
				PrintWriter out = new PrintWriter("C:\\Users\\susheel\\Desktop\\filename.json");
				out.println(s2);
				out.close();
				FileReader reader = new FileReader("C:\\Users\\susheel\\Desktop\\filename.json");
				
				
				//s2 = "{\"Tags\":[{\"Name\":\"online shopping\",\"Score\":0.47959542372464709},{\"Name\":\"books\",\"Score\":0.45345204227732228},{\"Name\":\"online books\",\"Score\":0.39005996690054889},{\"Name\":\"shopping\",\"Score\":0.35420268846834979},{\"Name\":\"india shopping\",\"Score\":0.28195290061501627},{\"Name\":\"india\",\"Score\":0.20656230139218476},{\"Name\":\"online\",\"Score\":0.19210334144428751},{\"Name\":\"online bookstores\",\"Score\":0.11098984450587622},{\"Name\":\"view\",\"Score\":0.10343177629010325},{\"Name\":\"online purchasing\",\"Score\":0.097631668464904536}]}";
				 JSONParser parser = new JSONParser();
		         JSONObject jsonObject = (JSONObject)parser.parse(reader);
		         JSONArray lang = (JSONArray) jsonObject.get("Tags");
// for testing 		         //for(int i=0; i<lang.size(); i++){
		        	//System.out.println("The " + i + " element of the array: "+lang.get(i));
		      	  //}
				tag = new String[lang.size()];
				
		         Iterator i = lang.iterator();
		         int u = 0;
		         	 
     	            // take each value from the json array separately
     	            while (i.hasNext()) {
     	                JSONObject innerObj = (JSONObject) i.next();
     	                System.out.println("Tags "+ innerObj.get("Name"));
     	                tag[u] = (String) innerObj.get("Name");
     	                u++;
     	                
     	                
     	            }
     	            
     	            
     	            
     	            
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch(URISyntaxException e){
			e.printStackTrace();
			
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//////////////////////////////////////////////one app over
		int j= 0;
         for(j = 0; j<15; j++){//major for loop for each app website
         	if((appData[j][1]).equalsIgnoreCase("com")){
				//System.out.println("error 0  ");

         		continue;
         		
         	}
			//System.out.println("error 1 ");

            String urlStr1 = "http://api.similarweb.com/Site/"+appData[j][1]+"/v2/tags?Format=JSON&UserKey=c863d7689cb71cab731e7a356ebc4ee7";
    		URI uri1;
    		try {
    			uri1 = new URI( urlStr1);
    			String host = uri1.getHost( );
    			String path = uri1.getRawPath( );
    			if (path == null || path.length( ) == 0) {
    			    path = "/";
    			}
    			 
    			String query = uri1.getRawQuery( );
    			if (query != null && query.length( ) > 0) {
    			    path += "?" + query;
    			}
				//System.out.println("error 2 ");

    			String protocol = uri1.getScheme( );
    			int port = uri1.getPort( );
    			if (port == -1) {
    			    if (protocol.equals("http")) {
    			        port = 80; // http port
    			    }
    			    else if (protocol.equals("https")) {
    			        port = 443; // https port
    			    }
    			    
    			}
    			
				//System.out.println("error 3 ");

    			Socket socket = new Socket( host, port ); 
    			PrintWriter request = new PrintWriter( socket.getOutputStream() );
    			request.print(  "GET " + path + " HTTP/1.1\r\n" +
    			                       "Host: " + host + "\r\n" +			                       
    			                       "Connection: close\r\n\r\n");
    			request.flush( ); 
				//System.out.println("error 4 ");

    			InputStream inStream;
    			String line = null;
    			String s2 = null;
    			try {
    				inStream = socket.getInputStream( );
    				BufferedReader rd = new BufferedReader(
    				        new InputStreamReader(inStream));
    				//System.out.println("error 5 ");

    				while ((line = rd.readLine()) != null) {
    				   s2 = s2+line;
    				}
    				//System.out.println("error 6 ");

    				int y= 0;
    				while(s2.charAt(y)!= '{'){
    					y++;
    					
    				}
    				//System.out.println("error 7 ");

    				s2 = s2.substring(++y);
    				s2 =  "{"+s2;
    				//System.out.println("error  8");
    				PrintWriter out = new PrintWriter("C:\\Users\\susheel\\Desktop\\filename1.json");
    				out.println(s2);
    				out.close();
    				FileReader reader = new FileReader("C:\\Users\\susheel\\Desktop\\filename1.json");
    				
    				
    				//s2 = "{\"Tags\":[{\"Name\":\"online shopping\",\"Score\":0.47959542372464709},{\"Name\":\"books\",\"Score\":0.45345204227732228},{\"Name\":\"online books\",\"Score\":0.39005996690054889},{\"Name\":\"shopping\",\"Score\":0.35420268846834979},{\"Name\":\"india shopping\",\"Score\":0.28195290061501627},{\"Name\":\"india\",\"Score\":0.20656230139218476},{\"Name\":\"online\",\"Score\":0.19210334144428751},{\"Name\":\"online bookstores\",\"Score\":0.11098984450587622},{\"Name\":\"view\",\"Score\":0.10343177629010325},{\"Name\":\"online purchasing\",\"Score\":0.097631668464904536}]}";
    				 JSONParser parser = new JSONParser();
    		         JSONObject jsonObject = (JSONObject)parser.parse(reader);
    		         String message = (String) jsonObject.get("Message");
    		         if(message != null){
    		        	 continue;
    		         }
    		         JSONArray lang = (JSONArray) jsonObject.get("Tags");
    // for testing 		         //for(int i=0; i<lang.size(); i++){
    		        	//System.out.println("The " + i + " element of the array: "+lang.get(i));
    		      	  //}
    				tagtmp = new String[lang.size()];
    				
    		         Iterator i = lang.iterator();
    		         int u = 0;
    		         	 
         	            // take each value from the json array separately
         	            while (i.hasNext()) {
         	                JSONObject innerObj = (JSONObject) i.next();
         	                System.out.println("Tags other "+ innerObj.get("Name"));
         	                tagtmp[u] = (String) innerObj.get("Name");
         	                u++;
         	                
         	                
         	            }
        				//System.out.println("error 9 ");

         	            int counter = 0;
         	            for(int p = 0;p<tag.length;p++){
         	            	for(int o = 0;o<tagtmp.length;o++){
         	            		if(tag[p].equalsIgnoreCase(tagtmp[o])){
         	            			counter++;
         	            			
         	            			
         	            		}
         	            	}
         	            }
        				//System.out.println("error 10  ");

         	            tagCount[j] = counter;
        				//System.out.println("error 11 ");

         	            
         	            
         	            
    				} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    		}catch(URISyntaxException e){
    			e.printStackTrace();
    			
    		}
    		catch(UnknownHostException e){
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
         			
         			
         			
         }
         int max = 0;
         int ind = 0;
         for(int x = 0;x<15;x++){
        	 if(tagCount[x] > max){
        		 max = tagCount[x];
        		 ind = x;
        	 }
        	 
         }
         int ind2 = 0;
         int max2 = 0;
         for(int x = 0;x<15;x++){
        	 if(tagCount[x] > max2 && tagCount[x] <= max && x!=ind ){
        		 max2 = tagCount[x];
        		 ind2 = x;
        	 }
        	 
         }
         
         
         System.out.println("APPS ARE :"+appData[ind][0]+" :  "+appData[ind2][0]);
         
         l21 = appData[ind][0];
         l22 = appData[ind2][0];
        
        
         
        
         
		
		
		
	}
	public static void printAppData(){
		for(int k =0;k<15;k++){
			System.out.print(appData[k][0]+" ");
			System.out.println(appData[k][1]);
			

			
		}
	}
	

}
