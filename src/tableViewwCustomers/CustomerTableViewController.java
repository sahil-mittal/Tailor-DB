package tableViewwCustomers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tableViewwWorkers.WorkerBean;

/*
 * int oid;
	String cname;
	String mobno;
	String dress;
	String splist;
	Date doac;		//order
	Date date;		//delivery
	String price;
	int status;
 */
public class CustomerTableViewController 
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CustomerBean> tblCustomer;

    PreparedStatement pst;
    Connection con;
    
    ObservableList<CustomerBean> colfill()
    {
    	ObservableList<CustomerBean>ary=FXCollections.observableArrayList();
    	try
    	{
    		pst=con.prepareStatement("select * from customerdb");
    		ResultSet table=pst.executeQuery();
    		
    		while(table.next())
    		{
    			int oid=table.getInt(10);
    			String cname=table.getString(2);
    			String mobno=table.getString(1);
    			String dress=table.getString(3);
    			String splist=table.getString(4);
    			Date doac=table.getDate(11);
    			Date date=table.getDate(7);
    			String price=table.getString(8);
    			int status=table.getInt(12);
    			CustomerBean obj=new CustomerBean(oid,cname,mobno,dress,splist,doac,date,price,status);
    			ary.add(obj);
    		}
    	}
    	catch(SQLException exp)
    	{
    		exp.printStackTrace();
    	}
    	return ary;
    }
    @FXML
    void doFetch(ActionEvent event) 
    {
    	colFill();
    }
    void colFill()
    {
    	TableColumn<CustomerBean, Integer> oidCol=new TableColumn<CustomerBean, Integer>("OID");
    	oidCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, Integer>("oid"));
    	oidCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> cnameCol=new TableColumn<CustomerBean, String>("Name");
    	cnameCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("cname"));
    	cnameCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> mobnoCol=new TableColumn<CustomerBean, String>("M.No.");
    	mobnoCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("mobno"));
    	mobnoCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> dressCol=new TableColumn<CustomerBean, String>("Dress");
    	dressCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("dress"));
    	dressCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> splistCol=new TableColumn<CustomerBean, String>("Specialist");
    	splistCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("splist"));
    	splistCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Date> doacCol=new TableColumn<CustomerBean, Date>("Order");
    	doacCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, Date>("doac"));
    	doacCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Date> dateCol=new TableColumn<CustomerBean, Date>("Delivery");
    	dateCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, Date>("date"));
    	dateCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> priceCol=new TableColumn<CustomerBean, String>("Price");
    	priceCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("price"));
    	priceCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Integer> statusCol=new TableColumn<CustomerBean, Integer>("Status");
    	statusCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, Integer>("status"));
    	statusCol.setMinWidth(100);
    	
    	tblCustomer.getColumns().clear();
    	tblCustomer.getColumns().addAll(oidCol,cnameCol,mobnoCol,dressCol,splistCol,doacCol,dateCol,priceCol,statusCol);
    	ObservableList<CustomerBean>ary=colfill();
    	tblCustomer.setItems(ary);
    }

    
    @FXML
    void initialize() 
    {
    	con=MySqlConnection.doConnect();
        assert tblCustomer != null : "fx:id=\"tblCustomer\" was not injected: check your FXML file 'CustomerTableView.fxml'.";

    }
}
