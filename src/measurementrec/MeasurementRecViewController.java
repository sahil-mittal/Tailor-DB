package measurementrec;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MeasurementRecViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNo;

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<String> comboDress;

    @FXML
    private ComboBox<String> comboSplist;

    @FXML
    private TextArea txtMmts;

    @FXML
    private ComboBox<Integer> comboDays;

    @FXML
    private DatePicker dod;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtWIsh;
    
    @FXML
    void doDay(ActionEvent event) 
    {
		

    }
    
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    String data=df.format(new java.util.Date());
    LocalDate date=LocalDate.parse(data);

    @FXML
    void doDod(ActionEvent event) 
    {
    	try 
		{
    		LocalDate dateNew=dod.getValue();
    		if(dateNew==null)
    			return;
    		long diff=ChronoUnit.DAYS.between(date, dateNew);
    		//////
    		int days=Integer.parseInt(diff+"");
    		//////
    		comboDays.getSelectionModel().select(days);
    		System.out.println("Hello world");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }
    @FXML
    void doDress(ActionEvent event) 
    {
    	String dress=comboDress.getSelectionModel().getSelectedItem();
		try 
		{
			pst=con.prepareStatement("select wname from workersdb where spl like ?");
			pst.setString(1,"%"+ dress+ "%");	
			ResultSet table=pst.executeQuery();
			comboSplist.getItems().clear();
			while(table.next())
			{
				String splist=table.getString("wname");
				comboSplist.getItems().add(splist);
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doNew(ActionEvent event) 
    {
    	txtMmts.clear();
    	txtName.clear();
    	txtNo.clear();
    	txtPrice.clear();
    	txtWIsh.clear();
    	comboDays.getSelectionModel().select(null);
    	comboDress.getSelectionModel().select(0);
    	comboSplist.getSelectionModel().select(null);
    	dod.getEditor().clear();
    }
    PreparedStatement pst;
    Connection con;
    @FXML
    void doSave(ActionEvent event) 
    {
    	try
    	{
    		pst=con.prepareStatement("insert into customerdb values (?,?,?,?,?,?,?,?,?,null,current_date(),1)");
    		pst.setString(1, txtNo.getText());
    		pst.setString(2, txtName.getText());
    		pst.setString(3, comboDress.getEditor().getText());
    		pst.setString(4, comboSplist.getEditor().getText());
    		pst.setString(5, txtMmts.getText());
    		pst.setInt(6, Integer.parseInt(comboDays.getEditor().getText()));
    		pst.setDate(7, Date.valueOf(dod.getValue()));
    		pst.setString(8, txtPrice.getText());
    		pst.setString(9, txtWIsh.getText());
    		//pst.execute();
    		pst.executeUpdate();
    		ResultSet table=pst.getGeneratedKeys();
    	     while(table.next())
    	     {
    	    	 showMsg("Order placed successfully for odered id :"+ table.getInt(1)); 
    	     }
    		//showMsg("Order placed successfully.(Oid-)");

    	}
    	
    	catch(SQLException exp)
    	{
    		exp.printStackTrace();
    	}
    	
    }

    @FXML
    void doSearch(ActionEvent event) 
    {
    	try
    	{
    		pst=con.prepareStatement("select * from customerdb where mobno=? and dress=?");
    		pst.setString(1, txtNo.getText());
    		pst.setString(2, comboDress.getSelectionModel().getSelectedItem());

    		ResultSet table=pst.executeQuery();
    		boolean jasus=false;
    		while(table.next())
    		{
    			jasus=true;
    			String mobno=table.getString("mobno");
    			String cname=table.getString("cname");
    			String dress=table.getString("dress");
    			String mmnts=table.getString("mmnts");
    			//
    			int days=table.getInt("days");
    			//
    			String date=table.getDate("date").toString();
    			String price=table.getString("price");
    			String wish=table.getString("wish");
    			String splist=table.getString("splist");
     			txtName.setText(cname);
    			txtMmts.setText(mmnts);
    			comboDress.getSelectionModel().select(dress);
    			comboDays.getSelectionModel().select(days);
    			txtPrice.setText(price);
    			txtWIsh.setText(wish);
    			txtMmts.setText(mmnts);
    			comboSplist.getSelectionModel().select(splist);
    			dod.getEditor().setText(date);
    			//Date picker???????????????
    			System.out.println(mobno+" "+cname+" "+dress+" "+mmnts+" "+days+" "+date+" "+price+" "+wish+" "+splist);
    		}
    		//pst.executeUpdate();
    		if(jasus==false)
    			showMsg("Customer records not found on the DataBase.");

    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }

    @FXML
    void doSpl(ActionEvent event) {

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
		System.out.println("Here1");
		comboDress.getItems().clear();
		ArrayList<String>list=new ArrayList<String>(Arrays.asList("Select","Suit","Shirt","Pant","Kurta Pyjama"));
		comboDress.getItems().addAll(list);
		comboDress.getSelectionModel().select(0);
		for(int i=1;i<100;i++)
		{
			comboDays.getItems().add(i);
		}
		 
        assert txtNo != null : "fx:id=\"txtNo\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";
        assert comboDress != null : "fx:id=\"comboDress\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";
        assert comboSplist != null : "fx:id=\"comboSplist\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";
        assert txtMmts != null : "fx:id=\"txtMmts\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";
        assert comboDays != null : "fx:id=\"comboDays\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";
        assert dod != null : "fx:id=\"dod\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";
        assert txtWIsh != null : "fx:id=\"txtWIsh\" was not injected: check your FXML file 'MeasurementRecView.fxml'.";

    }
}
