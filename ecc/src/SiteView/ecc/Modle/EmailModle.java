package SiteView.ecc.Modle;

import Siteview.Api.BusinessObject;

public class EmailModle {
	public String MailTitle;
	public String MailContent;
	public String MailModle;
	public BusinessObject bo;
	
	public EmailModle(BusinessObject bo) {
		super();
		this.bo = bo;
	}

	public EmailModle(String mailTitle, String mailContent, String mailModle,
			BusinessObject bo) {
		super();
		MailTitle = mailTitle;
		MailContent = mailContent;
		MailModle = mailModle;
		this.bo = bo;
	}

	public BusinessObject getBo() {
		return bo;
	}

	public void setBo(BusinessObject bo) {
		this.bo = bo;
	}

	public String getMailTitle() {
		return MailTitle;
	}

	public void setMailTitle(String mailTitle) {
		MailTitle = mailTitle;
	}

	public String getMailContent() {
		return MailContent;
	}

	public void setMailContent(String mailContent) {
		MailContent = mailContent;
	}

	public String getMailModle() {
		return MailModle;
	}

	public void setMailModle(String mailModle) {
		MailModle = mailModle;
	}
	
}
