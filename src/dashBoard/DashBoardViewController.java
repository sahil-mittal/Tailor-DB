package dashBoard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DashBoardViewController 
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource(path)); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
  		//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */
		}
    	catch(Exception e)
		{
			e.printStackTrace();
		}

    }
    @FXML
    void doCustTable(MouseEvent event) 
    {
    	playClick();

    	doAction("tableViewwCustomers/CustomerTableView.fxml");
    }

    @FXML
    void doDressDelivered(MouseEvent event) 
    {
    	playClick();

    	doAction("dressdelivered/DressDeliveredView.fxml");
    }

    @FXML
    void doMeasurements(MouseEvent event) 
    {
    	playClick();

    	doAction("measurementrec/MeasurementRecView.fxml");
    }

    @FXML
    void doOrdreceived(MouseEvent event) 
    {
    	playClick();

    	doAction("dressreceived/DressReceivedView.fxml");
    }

    @FXML
    void doTbl(MouseEvent event) 
    {
    	playClick();

    	doAction("tableViewAll/TableAllView.fxml");
    }

    @FXML
    void doWo(MouseEvent event) {

    }

    @FXML
    void doWorkerConsole(MouseEvent event) 
    {
    	playClick();

    	doAction("workerconsole/WorkerConsoleView.fxml");
    }

    @FXML
    void doWorkerTable(MouseEvent event) 
    {
    	playClick();

    	doAction("tableViewwWorkers/WorkerTableView.fxml");
    }

    @FXML
    void initialize() {

    }
}
