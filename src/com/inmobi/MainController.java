package com.inmobi;

import com.inmobi.model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MainController {
	 @FXML
	    private   Label l0;
	    @FXML
	    private   Label l1;
	    @FXML
	    private   Label l21;
	    @FXML
	    private   Label l22;
	    
	    
	    
	    @FXML
	    private void initialize() {
	    	
	    
	    }	
	   
	    
	    @FXML
	    private void handleStart() {
	    	BrowserHistory bh = new BrowserHistory();
			bh.getHistory();
	    
	    }
	    @FXML
	    private void handleExit(){
	    	System.exit(0);
	    }
	    public void recieve0(String s){
	    	l0.setText(ArrayTemp.appName);
	    }
	    public  void recieve1(String s){
	    	l1.setText(ArrayTemp.l1);
	    }
	    public   void recieve2(String s1,String s2){
	    	this.l21.setText(ArrayTemp.l21);
	    	this.l22.setText(ArrayTemp.l22);
	    	
 	
	    }

}
