package tableViewAll;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import tableViewwCustomers.CustomerBean;

public class TableAllViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TableAllBean> tblAll;

    @FXML
    private TextField txtNo;

    @FXML
    private ComboBox<String> comboWname;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private DatePicker date;

    void colFill()
    {
    	TableColumn<TableAllBean, Integer> oidCol=new TableColumn<TableAllBean, Integer>("OID");
    	oidCol.setCellValueFactory(new PropertyValueFactory<TableAllBean, Integer>("oid"));
    	oidCol.setMinWidth(100);
    	
    	TableColumn<TableAllBean, String> cnameCol=new TableColumn<TableAllBean, String>("Cust. Name");
    	cnameCol.setCellValueFactory(new PropertyValueFactory<TableAllBean, String>("cname"));
    	cnameCol.setMinWidth(100);
    	
    	TableColumn<TableAllBean, String> mobnoCol=new TableColumn<TableAllBean, String>("M.No.");
    	mobnoCol.setCellValueFactory(new PropertyValueFactory<TableAllBean, String>("mobno"));
    	mobnoCol.setMinWidth(100);
    	
    	TableColumn<TableAllBean, String> dressCol=new TableColumn<TableAllBean, String>("Dress");
    	dressCol.setCellValueFactory(new PropertyValueFactory<TableAllBean, String>("dress"));
    	dressCol.setMinWidth(100);
    	
    	TableColumn<TableAllBean, String> splistCol=new TableColumn<TableAllBean, String>("Specialist");
    	splistCol.setCellValueFactory(new PropertyValueFactory<TableAllBean, String>("splist"));
    	splistCol.setMinWidth(100);
    	
    	TableColumn<TableAllBean, Date> doacCol=new TableColumn<TableAllBean, Date>("Order");
    	doacCol.setCellValueFactory(new PropertyValueFactory<TableAllBean, Date>("doac"));
    	doacCol.setMinWidth(100);
    	
    	TableColumn<TableAllBean, Date> dateCol=new TableColumn<TableAllBean, Date>("Delivery");
    	dateCol.setCellValueFactory(new PropertyValueFactory<TableAllBean, Date>("date"));
    	dateCol.setMinWidth(100);
    	
    	TableColumn<TableAllBean, String> priceCol=new TableColumn<TableAllBean, String>("Price");
    	priceCol.setCellValueFactory(new PropertyValueFactory<TableAllBean, String>("price"));
    	priceCol.setMinWidth(100);
    	/*
    	TableColumn<CustomerBean, Integer> statusCol=new TableColumn<CustomerBean, Integer>("Status");
    	statusCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, Integer>("status"));
    	statusCol.setMinWidth(100);*/
    	
    	tblAll.getColumns().clear();
    	tblAll.getColumns().addAll(oidCol,cnameCol,mobnoCol,dressCol,splistCol,doacCol,dateCol,priceCol);
    	
    }
    
    void docallStat(int n)
    {
    	colFill();
        PreparedStatement pst=null;

    	try
    	{
    		pst=con.prepareStatement("select * from customerdb where splist=? and status=?");//0   -   >1
			pst.setString(1, comboWname.getSelectionModel().getSelectedItem());
			pst.setInt(2, n);

    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	ObservableList<TableAllBean> ary=fetchAllRecords(pst);
    	tblAll.setItems(ary);
    }
    @FXML
    void doFind(ActionEvent event) throws SQLException 
    {
    	if(comboStatus.getSelectionModel().getSelectedIndex()==0) //new 
		{
			docallStat(1);
		}
		else if(comboStatus.getSelectionModel().getSelectedIndex()==1)//in-progress
		{
			docallStat(2);
		}
		else if(comboStatus.getSelectionModel().getSelectedIndex()==2)//finished
		{
			docallStat(0);
		}
    }
    
    ObservableList<TableAllBean> fetchAllRecords(PreparedStatement pst)
    {
    	ObservableList<TableAllBean> ary=FXCollections.observableArrayList();
    	try{
    		
    		ResultSet table= pst.executeQuery();
    		while(table.next())
    		{
				int oid=table.getInt("oid");
				String cname=table.getString("cname");
				String mobno=table.getString("mobno");
				String dress=table.getString("dress");
				String splist=table.getString("splist");
				Date doac=table.getDate("doac");
				Date date=table.getDate("date");
				String price=table.getString("price");
				//int status=table.getInt("status");
    			TableAllBean obj=new TableAllBean(oid,cname,mobno,dress,splist,doac,date,price);
    			ary.add(obj);
    		}
    		
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	return ary;

    }

    void Full()
    {
    	colFill();
        PreparedStatement pst=null;
        try 
        {
			pst=con.prepareStatement("select * from customerdb");
		} 
        catch (SQLException e) 
        {
        	e.printStackTrace();
		}
        ObservableList<TableAllBean> ary=fetchAllRecords(pst);
    	tblAll.setItems(ary);
    }
    
    @FXML
    void doFull(ActionEvent event) 
    {
    	Full();
    }

    void GetAll()
    {
    	colFill();
        PreparedStatement pst=null;
        try {
			pst=con.prepareStatement("select * from customerdb where date<=?");
			pst.setDate(1, Date.valueOf(date.getValue()));
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        ObservableList<TableAllBean> ary=fetchAllRecords(pst);
    	tblAll.setItems(ary);
    }
    
    @FXML
    void doGetAll(ActionEvent event) 
    {
    	GetAll(); 
    }

    void search()
    {
    	colFill();
        PreparedStatement pst=null;
        try 
        {
			pst=con.prepareStatement("select * from customerdb where mobno=?");
			pst.setString(1, txtNo.getText());
		} 
        catch (SQLException e) 
        {
        	e.printStackTrace();
		}
        ObservableList<TableAllBean> ary=fetchAllRecords(pst);
    	tblAll.setItems(ary);
    }
    
    @FXML
    void doSearch(ActionEvent event) 
    {
    	search();
    }
    Connection con;
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Command");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    /************************************************/
    
    void workerin()
    {
        PreparedStatement pst=null;

    	try 
		{
			pst=con.prepareStatement("select wname from workersdb");
			ResultSet table=pst.executeQuery();
			comboWname.getItems().clear();
			while(table.next())
			{
				String splist=table.getString("wname");
				comboWname.getItems().add(splist);
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
    }
    
    void statusCmbo()
    {
    	comboStatus.getItems().clear();
		ArrayList<String>list=new ArrayList<String>(Arrays.asList("New","In-progress","Finished"));
		comboStatus.getItems().addAll(list);
		comboStatus.getSelectionModel().select(null);
    }
    
    @FXML
    void initialize() 
    {
    	con=MySqlConnection.doConnect();
   		
    	workerin();
    	statusCmbo();
    	
        assert tblAll != null : "fx:id=\"tblAll\" was not injected: check your FXML file 'TableAllView.fxml'.";
        assert txtNo != null : "fx:id=\"txtNo\" was not injected: check your FXML file 'TableAllView.fxml'.";
        assert comboWname != null : "fx:id=\"comboWname\" was not injected: check your FXML file 'TableAllView.fxml'.";
        assert comboStatus != null : "fx:id=\"comboStatus\" was not injected: check your FXML file 'TableAllView.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'TableAllView.fxml'.";

    }
}

