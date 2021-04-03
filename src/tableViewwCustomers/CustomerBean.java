package tableViewwCustomers;

import java.sql.Date;

public class CustomerBean 
{
	int oid;
	String cname;
	String mobno;
	String dress;
	String splist;
	Date doac;		//order
	Date date;		//delivery
	String price;
	int status;
	
	public CustomerBean() {}

	public CustomerBean(int oid, String cname, String mobno, String dress, String splist, Date doac, Date date,
			String price, int status) {
		super();
		this.oid = oid;
		this.cname = cname;
		this.mobno = mobno;
		this.dress = dress;
		this.splist = splist;
		this.doac = doac;
		this.date = date;
		this.price = price;
		this.status = status;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public String getDress() {
		return dress;
	}

	public void setDress(String dress) {
		this.dress = dress;
	}

	public String getSplist() {
		return splist;
	}

	public void setSplist(String splist) {
		this.splist = splist;
	}

	public Date getDoac() {
		return doac;
	}

	public void setDoac(Date doac) {
		this.doac = doac;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
