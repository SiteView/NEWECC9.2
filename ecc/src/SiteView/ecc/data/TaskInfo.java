package SiteView.ecc.data;

import java.util.ArrayList;
import java.util.List;

import SiteView.ecc.Modle.TaskModel;

public class TaskInfo {
	public static List<TaskModel> list;
	
	public static List<TaskModel> getTaskInfo(){
		list = new ArrayList<TaskModel>();
		TaskModel task1=new TaskModel();
		task1.setName("����ʱ������ƻ�");
		task1.setInstruction("����ʱ������ƻ�����ӣ�������Ҫ���ù���ʱ�䣬���������ݶ���ʱ����й�����");
		list.add(task1);
		
		TaskModel task2=new TaskModel();
		task2.setName("ʱ�������ƻ�");
		task2.setInstruction("ʱ�������ƻ�����ӣ�������Ҫ���ù���ʱ�䣬���������ݶ���ʱ����й�����");
		list.add(task2);
		
		TaskModel task3=new TaskModel();
		task3.setName("���ʱ������ƻ�");
		task3.setInstruction("���ʱ������ƻ�����ӣ�������Ҫ���ù���ʱ�䣬֧�ּ��������ʱ�����ã����������ݶ���ʱ����й�����");
		list.add(task3);
		return list;
	}

}
