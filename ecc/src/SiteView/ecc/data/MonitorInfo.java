package SiteView.ecc.data;



public class MonitorInfo {
	private Long id;
	private String status;
	private String monitorname;
	private String desc;
	private String lastupdateDate;

	public MonitorInfo(Long id, String status,String monitorname, String desc, String lastupdateDate) {
		this.id = id;
		this.status = status;
		this.monitorname = monitorname;
		this.desc = desc;
		this.lastupdateDate = lastupdateDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMonitorname() {
		return monitorname;
	}

	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLastupdateDate() {
		return lastupdateDate;
	}

	public void setLastupdateDate(String lastupdateDate) {
		this.lastupdateDate = lastupdateDate;
	}
}
