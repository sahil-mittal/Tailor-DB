package dressreceived;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class DressReceivedViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtOid;

    Connection con;
    PreparedStatement pst;
    
    @FXML
    void doReceive(ActionEvent event) 
    {
    	try 
    	{
    		pst=con.prepareStatement("update customerdb set status=? where oid=?");
    		pst.setInt(2, Integer.parseInt(txtOid.getText()));
    		pst.setInt(1, 2);
    		pst.executeUpdate();
			showMsg("Order Received");

    	}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	/*try 
    	{
			/*pst=con.prepareStatement("SELECT status FROM customerdb WHERE oid=?");
			pst.setInt(1, Integer.parseInt(txtOid.getText()));
			ResultSet table=pst.executeQuery();
			if(status==1)
			{
				status=2;
				
			}
			else if(status==0)
			{
				showMsg("Order delivered");
			}
			
		} 
    	
    	*/
    }
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Command");
    	alert.setContentText(msg);
    	alert.show();
    }

    @FXML
    void initialize() 
    {
    	con=MySqlConnection.doConnect();
        assert txtOid != null : "fx:id=\"txtOid\" was not injected: check your FXML file 'DressReceivedView.fxml'.";

    }
}
