package tableViewwWorkers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import measurementrec.MySqlConnection;

public class WorkerTableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<WorkerBean> tblWorker;

    void columnfill()
    {
    	TableColumn<WorkerBean, String> wnameCol=new TableColumn<WorkerBean, String>("Name");
    	wnameCol.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("wname"));
    	wnameCol.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> mobileCol=new TableColumn<WorkerBean, String>("Contact");
    	mobileCol.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("mobile"));
    	mobileCol.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> addressCol=new TableColumn<WorkerBean, String>("Address");
    	addressCol.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("address"));
    	addressCol.setMinWidth(100);

    	TableColumn<WorkerBean, String> splCol=new TableColumn<WorkerBean, String>("Specialization");
    	splCol.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("spl"));
    	splCol.setMinWidth(100);
    	
    	TableColumn<WorkerBean, Date> dorCol=new TableColumn<WorkerBean, Date>("DOR");
    	dorCol.setCellValueFactory(new PropertyValueFactory<WorkerBean, Date>("dor"));
    	dorCol.setMinWidth(100);
    	
    	tblWorker.getColumns().clear();
    	tblWorker.getColumns().addAll(wnameCol,mobileCol,addressCol,splCol,dorCol);
    	ObservableList<WorkerBean>arr= fetchAllRecords();
    	tblWorker.setItems(arr);
    }
    ObservableList<WorkerBean> fetchAllRecords()
    {
    	ObservableList<WorkerBean> ary= FXCollections.observableArrayList();
    	try
    	{
    		pst=con.prepareStatement("select * from workersdb");
    		ResultSet table=pst.executeQuery();
    		
    		while(table.next())
    		{
    			String wname=table.getString("wname");
    			String mobile=table.getString("mobile");
    			String address=table.getString("address");
    			String spl=table.getString("spl");
    			Date dor=table.getDate("dor");
    			WorkerBean obj=new WorkerBean(wname,mobile,address,spl,dor);
    			ary.add(obj);
    		}
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	return ary;
    }
    
    @FXML
    void doFetch(ActionEvent event) 
    {
    	columnfill();
    }
    PreparedStatement pst;
    Connection con;
    @FXML
    void initialize() 
    {
    	con=MySqlConnection.doConnect();
        assert tblWorker != null : "fx:id=\"tblWorker\" was not injected: check your FXML file 'WorkerTableView.fxml'.";

    }
}