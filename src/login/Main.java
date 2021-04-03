package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application 
{
	
 public void start(Stage primaryStage) 
   {
		try {
				System.out.println("Here main");
				Parent root=(Parent)FXMLLoader.load(getClass().getResource("LoginView.fxml")); 
				Scene scene = new Scene(root,720,700);
				primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
				   
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

