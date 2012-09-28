package SiteView.ecc.Modle;

import Siteview.Api.BusinessObject;

public class SMSModel {
	public BusinessObject bo;

	public BusinessObject getBo() {
		return bo;
	}

	public void setBo(BusinessObject bo) {
		this.bo = bo;
	}

	public SMSModel(BusinessObject bo) {
		super();
		this.bo = bo;
	}
	

}
