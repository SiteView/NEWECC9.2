package SiteView.ecc.Modle;

import Siteview.Api.BusinessObject;

public class MonitorModle {
	private BusinessObject bo;

	public BusinessObject getBo() {
		return bo;
	}

	public void setBo(BusinessObject bo) {
		this.bo = bo;
	}

	public MonitorModle(BusinessObject bo) {
		super();
		this.bo = bo;
	}
}
