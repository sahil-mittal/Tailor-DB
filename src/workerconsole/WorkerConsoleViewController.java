package workerconsole;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class WorkerConsoleViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNo;

    @FXML
    private ImageView image;

    @FXML
    private TextField txtAddress;

    @FXML
    private ComboBox<String> comboSpl;

    @FXML
    private TextField txtSel;

    String picpath="";
    @FXML
    void doBrowse(ActionEvent event) 
    {
    	choosepic();
    }

    void choosepic()
    {
    	FileChooser r=new FileChooser();
    	File s=r.showOpenDialog(null);
    	if(s!=null)
    	{
    		try
    		{
    			Image images=new Image(new FileInputStream(s.getAbsolutePath().toString()));
    			image.setImage(images);
    			picpath=s.getPath().toString();
    			System.out.println("Here");
    		}
    		catch(Exception exp)
    		{
    			exp.printStackTrace();
    		}
    	}
    }
    @FXML
    void doDelete(ActionEvent event) 
    {
    	try
    	{
    		pst=con.prepareStatement("delete from workersdb where wname=?");
    		pst.setString(1, txtName.getText());
    		pst.executeUpdate();

    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	showMsg("Worker's information successfully removed from the Data Base");
    }
    

    @FXML
    void doFetch(ActionEvent event) 
    {
    	fetch();
    }
    public void fetch()
    {
    	try
    	{
    		pst=con.prepareStatement("select * from workersdb where wname=?");
    		pst.setString(1, txtName.getText());
    		ResultSet table=pst.executeQuery();
    		boolean jasus=false;
    		while(table.next())
    		{
    			jasus=true;
    			String name=table.getString("wname");
    			String mob=table.getString("mobile");
    			String path=table.getString("picpath");
    			String address=table.getString("address");
    			String spl=table.getString("spl");
    			Image images=new Image(new FileInputStream(path));
            	image.setImage(images);
            	//
            	picpath=path;
            	//
            	txtAddress.setText(address);
            	txtNo.setText(mob);
            	txtSel.setText(spl);
            	System.out.println(name+" "+mob+" "+path+" "+address+" "+spl);
    		}
    		if(jasus==false)
    		{
    			showMsg("Invaid name. Records Not Found");
    		}
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }

    @FXML
    void doNew(ActionEvent event) 
    {
    	txtAddress.clear();
    	txtName.clear();
    	txtNo.clear();
    	txtSel.setText(null);
    	image.setImage(null);
    	picpath="";
		comboSpl.getSelectionModel().select(0);
		spl="";
    }

    @FXML
    void doSave(ActionEvent event) 
    {
    	saveToSql();
    }
    public void saveToSql()
    {
    	//ArrayList<String>list=new ArrayList<String>();
    	try
    	{
    		pst=con.prepareStatement("insert into workersdb values (?,?,?,?,?,current_date)");
    		pst.setString(1, txtName.getText());
    		pst.setString(2, txtNo.getText());
    		pst.setString(3, picpath);
    		pst.setString(4, txtAddress.getText());
    		pst.setString(5, txtSel.getText());
    		pst.executeUpdate();
    		showMsg("Saved to Database");
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }
    String spl="";
    @FXML
    void doSpl(ActionEvent event) 
    {
    	spl();
    }
    void spl()
    {
      	String s2=comboSpl.getSelectionModel().getSelectedItem();
    	if(s2==null||s2=="Select")
    		return;
    	else
    	{
    		spl+=(s2+", ");
    		txtSel.setText(spl);
    	}
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	try
    	{
    		pst=con.prepareStatement("update workersdb set mobile=?,address=?,spl=?,picpath=? where wname=?");
    		pst.setString(5, txtName.getText());
    		pst.setString(1, txtNo.getText());
    		pst.setString(4, picpath);
    		pst.setString(2, txtAddress.getText());
    		pst.setString(3, txtSel.getText());
    		pst.executeUpdate();
    		showMsg("Updated to Database");
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }
    
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Command");
    	alert.setContentText(msg);
    	alert.show();
    }
    Connection con;
    PreparedStatement pst;
    @FXML
    void initialize() 
    {
    	con=MySqlConnection.doConnect();
		System.out.println("Here1");
		comboSpl.getItems().clear();
		ArrayList<String>list=new ArrayList<String>(Arrays.asList("Select","Suit","Shirt","Pant","Kurta Pyjama"));
		comboSpl.getItems().addAll(list);
		comboSpl.getSelectionModel().select(0);
		
    }
   
    
    
}
