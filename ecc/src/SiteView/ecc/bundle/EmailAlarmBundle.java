package SiteView.ecc.bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.util.email.MailSenderInfo;
import my.util.email.SimpleMailSender;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import siteview.IAutoTaskExtension;
import siteview.windows.forms.ImageHelper;
import system.Collections.ICollection;
import system.Collections.IEnumerator;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Text;

import SiteView.ecc.Activator;
import SiteView.ecc.Modle.EmailModle;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.editors.EmailSetUp;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;

public class EmailAlarmBundle implements IAutoTaskExtension {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Combo text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Tree tree;

	public EmailAlarmBundle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		 MailSenderInfo mailInfo = new MailSenderInfo();    
	     mailInfo.setMailServerHost("mail.dragonflow.com");    
	     mailInfo.setMailServerPort("25");    
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName("lihua.zhong");    
	     mailInfo.setPassword("123456");//您的邮箱密码    
	     mailInfo.setFromAddress("lihua.zhong@dragonflow.com");    
	     mailInfo.setToAddress("lihua.zhong@dragonflow.com");    
	     mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");    
	     mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");    
	        //这个类主要来发送邮件   
	     SimpleMailSender sms = new SimpleMailSender();   
	         //sms.sendTextMail(mailInfo);//发送文体格式    
	         sms.sendHtmlMail(mailInfo);//发送html格式   
		return null;
	}

	@Override
	public boolean hasCustomUI() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void creatConfigUI(Composite parent, Map<String, String> params) {
		// TODO Auto-generated method stub
		if(EmailSetUp.list==null){
			EmailSetUp.list=new ArrayList();
			ICollection ic=FileTools.getBussCollection("MailType", "receiver", "EccMail");
			IEnumerator ien=ic.GetEnumerator();
			while(ien.MoveNext()){
				BusinessObject bo=(BusinessObject) ien.get_Current();
				EmailModle m=new EmailModle(bo);
				EmailSetUp.list.add(m);
			}
		}
		parent.setLayout(new FillLayout());
		Composite group=new Composite(parent, SWT.NONE);
		group.setLayout(new FillLayout());
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		SashForm sashForm = new SashForm(group, SWT.NONE);
		sashForm.setLocation(0, 0);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new FillLayout());
		tree = new Tree(composite, SWT.BORDER | SWT.CHECK);
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tree.setVisible(true);
		tree.setHeaderVisible(true);
		createTreeItem(tree);
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		Label label = new Label(composite_1, SWT.NONE);
		label.setText("\u62A5\u8B66\u540D\u79F0\uFF1A");
		label.setBounds(10, 10, 68, 12);
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(100, 7, 162, 18);
		if(params.get("AlarmName")!=null){
			text.setText(params.get("AlarmName"));
		}
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBounds(10, 37, 84, 12);
		label_1.setText("\u90AE\u4EF6\u63A5\u6536\u5730\u5740\uFF1A");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(100, 34, 162, 18);
		if(params.get("SendAddress")!=null){
			text_1.setText(params.get("SendAddress"));
		}
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(10, 71, 84, 12);
		label_2.setText("\u5176\u4ED6\u90AE\u4EF6\u5730\u5740\uFF1A");
		
		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setBounds(100, 68, 162, 18);
		if(params.get("OtherAddress")!=null){
			text_2.setText(params.get("OtherAddress"));
		}
		
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(10, 101, 84, 12);
		lblNewLabel.setText("Email\u6A21\u7248\uFF1A");
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_3.setBounds(10, 130, 54, 12);
		label_3.setText("\u5347\u7EA7\u6B21\u6570\uFF1A");
		
		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_4.setBounds(10, 160, 84, 12);
		label_4.setText("\u5347\u7EA7\u63A5\u6536\u5730\u5740\uFF1A");
		
		text_3 = new Combo(composite_1, SWT.BORDER);
		text_3.setBounds(100, 98, 162, 18);
		int i=0;
		text_3.add("其他");
		for(EmailModle email:EmailSetUp.list){
			BusinessObject bo=email.getBo();
			String s=bo.GetField("SetName").get_NativeValue().toString();
			text_3.add(s);
			i++;
			if(params.get("EmailModle")!=null&&params.get("EmailModle").equals(s)){
				text_3.select(i);
				}
			}
		text_4 = new Text(composite_1, SWT.BORDER);
		text_4.setBounds(100, 127, 162, 18);
		if(params.get("UpgradeCount")!=null){
			text_4.setText(params.get("UpgradeCount"));
		}
		
		text_5 = new Text(composite_1, SWT.BORDER);
		text_5.setBounds(100, 157, 162, 18);
		if(params.get("UpgradeAddress")!=null){
			text_5.setText(params.get("UpgradeAddress"));
		}
		
		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_5.setBounds(10, 184, 54, 12);
		label_5.setText("\u505C\u6B62\u6B21\u6570\uFF1A");
		
		Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_6.setBounds(10, 208, 54, 12);
		label_6.setText("\u62A5\u8B66\u5217\u8868\uFF1A");
		
		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_7.setBounds(10, 232, 54, 12);
		label_7.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");
		
		text_6 = new Text(composite_1, SWT.BORDER);
		text_6.setBounds(100, 181, 162, 18);
		if(params.get("StopCount")!=null){
			text_6.setText(params.get("StopCount"));
		}
		
		text_7 = new Text(composite_1, SWT.BORDER);
		text_7.setBounds(100, 205, 162, 18);
		if(params.get("AlarmList")!=null){
			text_7.setText(params.get("AlarmList"));
		}
		
		text_8 = new Text(composite_1, SWT.BORDER);
		text_8.setBounds(100, 229, 162, 18);
		if(params.get("AlarmTactics")!=null){
			text_8.setText(params.get("AlarmTactics"));
		}
		sashForm.setWeights(new int[] {175, 272});
	}

	@Override
	public Map<String, String> getConfig() {
		Map map=new HashMap<String,String>();
		map.put("AlarmName", text.getText());
		map.put("SendAddress", text_1.getText());
		map.put("OtherAddress", text_2.getText());
		map.put("EmailModle", text_3.getText());
		map.put("UpgradeCount", text_4.getText());
		map.put("UpgradeAddress", text_5.getText());
		map.put("StopCount", text_6.getText());
		map.put("AlarmList", text_7.getText());
		map.put("AlarmTactics", text_8.getText());
		return map;
	}
	public static void createTreeItem(Tree tree) {
		for (TreeItem s1 : tree.getItems()) {
			s1.dispose();
		}
		TreeItem treeItem = new TreeItem(tree, SWT.NONE | SWT.CHECK);
		treeItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		treeItem.setText("Ecc9.2");
		treeItem.setImage(ImageHelper.LoadImage(Activator.PLUGIN_ID,
				"icons/logo.jpg"));
		for(int i=0;i<SiteViewData.groups_0.size();i++){
			if(SiteViewData.groups_0.get(0) instanceof GroupModle){
				GroupModle group=SiteViewData.groups_0.get(i);
				BusinessObject bo=group.getBo();
				String s=bo.GetField("GroupName").get_NativeValue().toString();
				TreeItem treeItem1 = new TreeItem(treeItem, SWT.NONE | SWT.CHECK);
				treeItem1.setText(s);
				treeItem1.setData(group);
				String id=bo.get_RecId();
				treeItem1.setImage(ImageHelper.LoadImage(Activator.PLUGIN_ID,"icons/node.jpg"));
				createItem(group, treeItem1);
				treeItem1.setExpanded(true);
			}
		}
		treeItem.setExpanded(true);
	}
	public static void createItem(GroupModle group, TreeItem treeItem12) {
		List<GroupModle> subgroup=group.getGroups();
		for(int i=0;i<subgroup.size();i++){
			GroupModle g=subgroup.get(i);
			BusinessObject bo=g.getBo();
			String subid=bo.get_RecId();
			TreeItem treeItem2 = new TreeItem(treeItem12, SWT.NONE
					| SWT.CHECK);
			treeItem2.setText(bo.GetField("GroupName").get_NativeValue().toString());
			treeItem2.setData(g);
			treeItem2.setImage(ImageHelper.LoadImage(Activator.PLUGIN_ID,
					"icons/node.jpg"));
			createItem(g, treeItem2);
		}
		List<MachineModle> machines=group.getMachines();
		for(int i=0;i<machines.size();i++){
			MachineModle machine=machines.get(i);
			BusinessObject bo=machine.getBo();
			TreeItem treeItem3 = new TreeItem(treeItem12, SWT.NONE
					| SWT.CHECK);
			treeItem3.setText(bo.GetField("ServerAddress")
					.get_NativeValue().toString());
			treeItem3.setData(bo);
			String macid = bo.get_RecId();
			treeItem3.setImage(ImageHelper.LoadImage(Activator.PLUGIN_ID,
					"icons/shebei.jpg"));
		}
	}

}
