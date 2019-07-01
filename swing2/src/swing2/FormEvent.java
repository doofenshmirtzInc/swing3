package swing2;

import java.util.EventObject;

public class FormEvent extends EventObject{
	
	//this class will temporarily hold the name and occupation info
	//so that it can be taken to the mainframe from the text fields
	//and then processed
	//Dont want direct communication between objects on the MainFrame
	//want all communication to go through the MainFrame as the go-between
	private String name;
	private String occupation;

	public FormEvent(Object source) {
		//EventObject has a constructor that accepts an Object type
		//this is an extension of that constructor
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public FormEvent(Object source, String name, String occupation) {
		super(source);
		
		this.name = name;
		this.occupation = occupation;
	}
	
	
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

}
