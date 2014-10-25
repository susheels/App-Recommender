package com.inmobi.model;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Trial {

	public static void getAppsWeb(String cat) {
		// TODO Auto-generated method stub
		

		Document doc;
		Document doc2;
		 String appWebsite = null;
		 
		 String appName = null;
		boolean flag = false;
		String linkHref1 = null;
		try {
			String prev=null;
			String prev1 = null;
			//doc2=Jsoup.connect("https://play.google.com/store/search?q="+"sports"+"&c=apps&hl=en").get();

			doc2=Jsoup.connect("https://play.google.com/store/search?q="+cat+"&c=apps&hl=en").get();
			Elements contentmain = doc2.getElementsByClass("card-click-target");
			int count=0;
			int count1 = 0;
			
			
			
			int majorCount = 0;
			
			for(Element c1 : contentmain){
				
				
				
				  Elements a1 = c1.getElementsByTag("a");
				  for(Element d1 : a1){//for loop runs once only
					  linkHref1 = d1.attr("href");
					  
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
					         }

					         count++;
					  }
					  //String linkText = d.text();
					  else{
						  prev = linkHref1;
						  //System.out.println(linkHref1);
						  int z =0;
						  while(linkHref1.charAt(z)!= '='){
							  z++;
							  
						  }
						   appName = linkHref1.substring(++z);//this is the proper app name!!!
						  System.out.println(appName);
						  
						  
								  
						  
						  

					  }
				  
						  

				  }
			
				 

			
			//for one thing !!!!!!!!!!!!!!!!!!!!!!!!
			doc = Jsoup.connect("https://play.google.com"+linkHref1+"&hl=en)").get();
			//String title = doc.title();
			//System.out.println(title);
			Elements content = doc.getElementsByClass("dev-link");
			
			
			for (Element c : content) {
				  Elements a = c.getElementsByTag("a");
				  
				  for(Element d : a){
					  String linkHref = d.attr("href");
					  //if(linkHref.length()>0)
						  //System.out.println("dsd");
					 

					 // System.out.println(linkHref);
					  //String linkText = d.text();
					 if(linkHref.charAt(0) != 'm'){
						 
					  if(linkHref.equals(prev1)|| count1==0){
						  
						  
					         prev1=linkHref;
					         if(count1 == 0){
								  //System.out.println(linkHref);
								  int y = 0;
								  while(linkHref.charAt(y)!= '='){
									  y++;
								  }
								  int t = 0;
								 appWebsite = linkHref.substring(++y);
								  while(appWebsite.charAt(t)!= '&'){
									  t++;
								  }
								  appWebsite = appWebsite.substring(0, t);
								  System.out.println(appWebsite);
								  ArrayTemp.insertAppData(appName,appWebsite);

					         }
					         count1++;
					  }
					  //String linkText = d.text();
					  else{
						  prev1 = linkHref;
						  //System.out.println(linkHref);
						  int y = 0;
						  while(linkHref.charAt(y)!= '='){
							  y++;
						  }
						  int t = 0;
						  appWebsite = linkHref.substring(++y);
						  while(appWebsite.charAt(t)!= '&'){
							  t++;
						  }
						  appWebsite = appWebsite.substring(0, t);
						  System.out.println(appWebsite);
						  ArrayTemp.insertAppData(appName,appWebsite);

						  

					  }
					 }
					 else{
						 appWebsite = null;
					 }
					  
					  
					  
					  
					 

				  }
				  //String linkText = c.text();
				  break;
				}
			if(flag)
				 break;
			
			
			
			
		}
		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayTemp.printAppData();

	

	}
}
