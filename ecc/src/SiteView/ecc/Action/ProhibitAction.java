package SiteView.ecc.Action;

import org.eclipse.jface.action.Action;
import java.net.URL;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.util.BundleUtility;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.editors.EccControlInput;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class ProhibitAction extends Action{
	public static EccControlInput eee=new EccControlInput();
	public ProhibitAction(){
		URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
				"Image/ProhibitAction.bmp");
			ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		    setImageDescriptor(temp);
		    setText("��ֹ");
	}
	public void run(){
		ICollection ico = null;
		IEnumerator ie=null;
				if(EccTreeControl.item instanceof GroupModle){//��ֹ���м����
					String GroupId=((GroupModle) EccTreeControl.item).getBo().get_RecId();//�õ�ѡ�����id
					ico=SiteView.ecc.tools.FileTools.getBussCollection("Groups", GroupId, "Ecc");//ͨ���õ�ѡ�����id�ҵ��������еļ����
					ie=ico.GetEnumerator();
					while(ie.MoveNext()){
						BusinessObject bo=(BusinessObject)ie.get_Current();
						if(bo!=null){
							String disable=bo.GetField("disable").get_NativeValue().toString();
							if("false".equals(disable)){
								bo.GetField("disable").SetValue(new SiteviewValue("true"));//true��ʾ��ֹ
								bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
										true);
								IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
								 IEditorPart editor = page.findEditor(eee); 
								 if(editor==null){
									 try {
										page.openEditor(eee, EccControl.ID);
									} catch (PartInitException e1) {
										e1.printStackTrace();
									}  
								 }else{
									((EccControl)editor).createTableItem();
									if(EccControl.item==null){
										((EccControl)editor).tab(null);
									}else{
										((EccControl)editor).tab((BusinessObject)EccControl.item.getData());
									}
								 }
							}
						}
					}
				}else if(EccTreeControl.item instanceof MachineModle){//��ֹ�豸�¼����
					String RecId=((MachineModle) EccTreeControl.item).getBo().get_RecId();//�õ�ѡ���豸��id
					ico=SiteView.ecc.tools.FileTools.getBussCollection("Machine", RecId, "Ecc");//�õ��豸�µļ����
					ie=ico.GetEnumerator();
					while(ie.MoveNext()){
						BusinessObject bo=(BusinessObject)ie.get_Current();
						if(bo!=null){
							String disable=bo.GetField("disable").get_NativeValue().toString();
							if("false".equals(disable)){
								bo.GetField("disable").SetValue(new SiteviewValue("true"));
								bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
										true);
								IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
								 IEditorPart editor = page.findEditor(eee); 
								 if(editor==null){
									 try {
										page.openEditor(eee, EccControl.ID);
									} catch (PartInitException e1) {
										e1.printStackTrace();
									}  
								 }else{
									((EccControl)editor).createTableItem();
									if(EccControl.item==null){
										((EccControl)editor).tab(null);
									}else{
										((EccControl)editor).tab((BusinessObject)EccControl.item.getData());
									}
								 }
							}
						}
					}
				}

	}
}
