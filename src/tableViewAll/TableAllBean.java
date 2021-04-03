package tableViewAll;

import java.sql.Date;

public class TableAllBean 
{
	int oid;
	String cname;
	String mobno;
	String dress;
	String splist;
	Date doac;
	Date date;
	String price;
	public TableAllBean(int oid, String cname, String mobno, String dress, String splist, Date doac, Date date,
			String price) {
		super();
		this.oid = oid;
		this.cname = cname;
		this.mobno = mobno;
		this.dress = dress;
		this.splist = splist;
		this.doac = doac;	//date of order
		this.date = date;	//date of delivery
		this.price = price;
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
}
