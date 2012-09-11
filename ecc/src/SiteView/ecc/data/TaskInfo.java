package SiteView.ecc.data;

import java.util.ArrayList;
import java.util.List;

import SiteView.ecc.Modle.TaskModel;

public class TaskInfo {
	public static List<TaskModel> list;
	
	public static List<TaskModel> getTaskInfo(){
		list = new ArrayList<TaskModel>();
		TaskModel task1=new TaskModel();
		task1.setName("绝对时间任务计划");
		task1.setInstruction("绝对时间任务计划的添加，根据需要设置工作时间，监测器则根据定义时间进行工作。");
		list.add(task1);
		
		TaskModel task2=new TaskModel();
		task2.setName("时间段任务计划");
		task2.setInstruction("时间段任务计划的添加，根据需要设置工作时间，监测器则根据定义时间进行工作。");
		list.add(task2);
		
		TaskModel task3=new TaskModel();
		task3.setName("相对时间任务计划");
		task3.setInstruction("相对时间任务计划的添加，根据需要设置工作时间，支持间断与连续时间设置，监测器则根据定义时间进行工作。");
		list.add(task3);
		return list;
	}

}
