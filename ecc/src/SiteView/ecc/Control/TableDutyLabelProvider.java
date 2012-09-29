package SiteView.ecc.Control;

import java.math.BigDecimal;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import siteview.windows.forms.ImageHelper;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.EmailModle;
import SiteView.ecc.Modle.SMSModel;
import SiteView.ecc.Modle.TableModle;
import Siteview.Api.BusinessObject;



public class TableDutyLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex==3&&element instanceof TableModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/edit.jpg");
		}else if(element instanceof EmailModle){
			if(columnIndex==0){
				return ImageHelper.LoadImage(Activator.PLUGIN_ID, "Image/emailName.bmp");
			}else if(columnIndex==1){
				BusinessObject bo=((EmailModle)element).getBo();
				if((Boolean)bo.GetField("IsStop").get_NativeValue()){
					return ImageHelper.LoadImage(Activator.PLUGIN_ID,"Image/promiss.bmp" );
				}
				return ImageHelper.LoadImage(Activator.PLUGIN_ID, "Image/stop.bmp");
			}else if(columnIndex==2){
				return ImageHelper.LoadImage(Activator.PLUGIN_ID, "Image/mail.bmp");
			}else if(columnIndex==3 ){
				return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/edit.jpg");
			}
		}else if(element instanceof SMSModel){
			if(columnIndex==0){
				return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/messageSetUp.jpg");
			}else if(columnIndex==1){
				BusinessObject bo=((SMSModel)element).getBo();
				if((Boolean)bo.GetField("IsStop").get_NativeValue()){
					return ImageHelper.LoadImage(Activator.PLUGIN_ID,"Image/promiss.bmp" );
				}
				return ImageHelper.LoadImage(Activator.PLUGIN_ID, "Image/stop.bmp");
			}else if(columnIndex==2){
				return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/message.jpg");
			}else if(columnIndex==3 ){
				return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/edit.jpg");
			}
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof TableModle){
			TableModle tm=(TableModle) element;
			if(columnIndex==0){
				return tm.getDutyTableName();
			}else if(columnIndex==1){
				return tm.getDutyTableType();
			}else if(columnIndex==2){
				return tm.getDutyTableDec();
			}
		}else if(element instanceof EmailModle){
			EmailModle email=(EmailModle) element;
			BusinessObject bo=email.getBo();
			if(columnIndex==0){
				return bo.GetField("SetName").get_NativeValue().toString();
			}else if(columnIndex==1){
				return ((Boolean)bo.GetField("IsStop").get_NativeValue())?"ÔÊÐí":"½ûÖ¹";
			}else if(columnIndex==2){
				return bo.GetField("MailAddress").get_NativeValue().toString();
			}
		}else if(element instanceof SMSModel){
			SMSModel email=(SMSModel) element;
			BusinessObject bo=email.getBo();
			if(columnIndex==0){
				return bo.GetField("SetName").get_NativeValue().toString();
			}else if(columnIndex==1){
				return ((Boolean)bo.GetField("IsStop").get_NativeValue())?"ÔÊÐí":"½ûÖ¹";
			}else if(columnIndex==2){
				BigDecimal big = new BigDecimal(bo.GetField("MobliePhone").get_NativeValue().toString());
				return big.toPlainString();
			}
		}
		return null;
	}

	
}
