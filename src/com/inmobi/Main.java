/**
 * 
 */
/**
 * @author susheel
 *
 */
package com.inmobi;



import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main  extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("InmobiApp");

        try {
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/MainPage.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }

        
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Shows the person overview scene.
     */
    

    public static void main(String[] args) {
        launch(args);
    }
}