package SiteView.ecc.dialog;

import java.util.List;

import javax.swing.table.TableModel;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;

import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.data.TableDutyInfor;
import SiteView.ecc.editors.TableDuty;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class DutyEditor extends Dialog {
	private Button applyButton;
	private Button closeButton;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Combo combo;
	public BusinessObject bb;
	public TableModle tm;
	public DutyEditor(Shell parent,BusinessObject bb,TableModle tm) {
		super(parent);
		this.bb=bb;
		this.tm=tm;
	}
	protected void configureShell(Shell newShell){
		super.configureShell(newShell);
		newShell.setSize(500,300);
		newShell.setLocation(200,100);
		newShell.setText("编辑值班表信息设置");
	}
	protected Control createDialogArea(Composite parent){
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		Composite container = (Composite) super.createDialogArea(parent);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		Group g=new Group(container,SWT.NONE);
		g.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		GridData gd_g = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_g.heightHint = 204;
		gd_g.widthHint = 419;
		g.setLayoutData(gd_g);
		g.setText("值班表");
		
		Label lblNewLabel = new Label(g, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel.setBounds(45, 23, 105, 18);
		lblNewLabel.setText("\u539F\u503C\u73ED\u8868\u540D\u79F0:");
		
		text = new Text(g, SWT.BORDER);//原值班名称
		text.setBounds(170, 21, 154, 18);
		text.setText(bb.GetField("DutyTableName").get_NativeValue().toString());
		text.setEnabled(false);
		
		Label lblNewLabel_1 = new Label(g, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(45, 55, 105, 18);
		lblNewLabel_1.setText("\u65B0\u503C\u73ED\u8868\u540D\u79F0:");
		
		text_1 = new Text(g, SWT.BORDER);//新值班名称
		text_1.setBounds(170, 54, 152, 18);
		
		Label lblNewLabel_2 = new Label(g, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_2.setText("\u539F\u503C\u73ED\u8868\u63CF\u8FF0:");
		lblNewLabel_2.setBounds(45, 86, 108, 18);
		
		text_2 = new Text(g, SWT.BORDER);//原值班描述
		text_2.setBounds(170, 84, 152, 18);
		text_2.setText(bb.GetField("DutyTableDec").get_NativeValue().toString());
		text_2.setEnabled(false);
		
		Label lblNewLabel_3 = new Label(g, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_3.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_3.setBounds(45, 116, 108, 18);
		lblNewLabel_3.setText("\u65B0\u503C\u73ED\u8868\u63CF\u8FF0:");
		
		text_3 = new Text(g, SWT.BORDER);//新值班描述
		text_3.setBounds(170, 117, 152, 18);
		
		Label lblNewLabel_4 = new Label(g, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_4.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_4.setBounds(45, 150, 108, 18);
		lblNewLabel_4.setText("\u503C\u73ED\u7C7B\u578B:");
		
		combo = new Combo(g, SWT.NONE);//原值班类型
		combo.setBounds(170, 150, 152, 18);
		combo.setText(bb.GetField("DutyTableType").get_NativeValue().toString());
		combo.add("day"); //循环添加选项
        combo.add("day of week");
        combo.add("day of month");
		return container;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		applyButton = createButton(parent, IDialogConstants.OK_ID, "保存",true);
		closeButton=createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
	}
	protected void buttonPressed(int buttonId){
		if(buttonId==IDialogConstants.OK_ID){
			TableDutyInfor.list.remove(tm);
			if(!(text.getText().equals(text_1.getText().trim()))){//值班名称修改 
				bb.GetField("DutyTableName").SetValue(new SiteviewValue(text_1.getText().trim()));
				tm.setDutyTableName(text_1.getText().trim());
			}
			if(!(text_2.getText().equals(text_3.getText().trim()))){//值班描述修改
				bb.GetField("DutyTableDec").SetValue(new SiteviewValue(text_3.getText().trim()));
				tm.setDutyTableDec(text_3.getText().trim());
			}
			  
			if(!(combo.getText().equals(bb.GetField("DutyTableType").get_NativeValue().toString()))){//值班类型修改
				bb.GetField("DutyTableType").SetValue(new SiteviewValue(combo.getText()));
				tm.setDutyTableType(combo.getText().trim());
			}
			bb.SaveObject(ConnectionBroker.get_SiteviewApi(), true,//将修改后的数据存储到数据库
					true);
			tm.setBo(bb);
			TableDutyInfor.list.add(0,tm);
			TableDuty.TableViewer.setInput(TableDutyInfor.list);
			TableDuty.TableViewer.refresh();//将表单上的数据做相应的更改
		}
		this.close();
	}
}
