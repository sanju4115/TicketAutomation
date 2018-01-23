package automationFramework;

public class Incidents {
	
	private String caller;
	private String description;
	private String serviceCategory;
	private String assignmentGroup;
	private String assignedTo;
	private String customerUpdate;
	
	
	
	public Incidents(String caller, String description, String serviceCategory, String assignmentGroup, String assignedTo,
			String customerUpdate) {
		super();
		this.caller = caller;
		this.description = description;
		this.serviceCategory = serviceCategory;
		this.assignmentGroup = assignmentGroup;
		this.assignedTo = assignedTo;
		this.customerUpdate = customerUpdate;
	}
	
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssignmentGroup() {
		return assignmentGroup;
	}
	public void setAssignmentGroup(String assignmentGroup) {
		this.assignmentGroup = assignmentGroup;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getCustomerUpdate() {
		return customerUpdate;
	}
	public void setCustomerUpdate(String customerUpdate) {
		this.customerUpdate = customerUpdate;
	}

	public String getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	
	

}
