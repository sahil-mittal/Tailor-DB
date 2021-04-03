package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button login;
    
    @FXML
    private PasswordField pswd;

    URL url;
	Media media;
	MediaPlayer mediaplayer;
	
    void playClick()
    {
    	url=getClass().getResource("click.mp3");
		media=new Media(url.toString());
		mediaplayer=new MediaPlayer(media);
		mediaplayer.play();
    }
    void doAction(String path)
    {
    	System.out.println(login.getText());
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource(path)); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
  		//to hide the opened window
			 
			   Scene scene1=(Scene)login.getScene();
			   scene1.getWindow().hide();
			 
		}
    	catch(Exception e)
		{
			e.printStackTrace();
		}

    }
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Alert");
    	alert.setContentText(msg);
    	alert.show();
    }
    @FXML
    void doLogin(ActionEvent event) 
    {
    	String password="iamSahil";
    	if(password.equals(pswd.getText()))
    	{
    		playClick();
    		doAction("dashBoard/DashBoardView.fxml");
    	}
    	else
    	{
    		showMsg("Invalid Password");
    		pswd.clear();
    	}
    }
    
   

    @FXML
    void initialize() {
        assert pswd != null : "fx:id=\"pswd\" was not injected: check your FXML file 'LoginView.fxml'.";
    }
}
