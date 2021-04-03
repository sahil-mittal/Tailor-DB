package dressdelivered;

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
import measurementrec.MySqlConnection;

public class DressDeliveredViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtOid;

    @FXML
    private TextField txtDress;

    @FXML
    private TextField txtPrice;

    @FXML
    void doDeliver(ActionEvent event) 
    {
    	try 
    	{
			pst=con.prepareStatement("select * from customerdb where oid=?");
			pst.setString(1, txtOid.getText());
			ResultSet table=pst.executeQuery();
			boolean jasus=false;
			while(table.next())
			{
				jasus=true;
				String dress=table.getString("dress");
				String price=table.getString("price");
				txtDress.setText(dress);
				txtPrice.setText(price);
			}
			if(jasus==false)
			{
				showMsg("Invalid oid");
			}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	try 
    	{
    		pst=con.prepareStatement("update customerdb set status=? where oid=?");
    		pst.setInt(2, Integer.parseInt(txtOid.getText()));
    		pst.setInt(1, 0);
    		pst.executeUpdate();
			showMsg("Order Delivered");

    	}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
    PreparedStatement pst;
    Connection con;
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

        assert txtOid != null : "fx:id=\"txtOid\" was not injected: check your FXML file 'DressDeliveredView.fxml'.";
        assert txtDress != null : "fx:id=\"txtDress\" was not injected: check your FXML file 'DressDeliveredView.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'DressDeliveredView.fxml'.";

    }
}
