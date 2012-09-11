package SiteView.ecc.Modle;

public class TaskModel {
	private String name;
	
	private String instruction;

	public TaskModel() {
	}
	
	public TaskModel(String name, String instruction) {
		super();
		this.name = name;
		this.instruction = instruction;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	

}
