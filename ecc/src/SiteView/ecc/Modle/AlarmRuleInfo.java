package SiteView.ecc.Modle;

import Siteview.Api.BusinessObject;

public class AlarmRuleInfo {
	private BusinessObject bo;

	public BusinessObject getBo() {
		return bo;
	}

	public void setBo(BusinessObject bo) {
		this.bo = bo;
	}

	public AlarmRuleInfo(BusinessObject bo) {
		super();
		this.bo = bo;
	}

}
