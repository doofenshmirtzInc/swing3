package gui;

import java.util.EventObject;

public class FormEvent extends EventObject{
	
	//this class will temporarily hold the name and occupation info
	//so that it can be taken to the mainframe from the text fields
	//and then processed
	//Dont want direct communication between objects on the MainFrame
	//want all communication to go through the MainFrame as the go-between
	private String name;
	private String occupation;
	private int ageCategory;
	private String empCat;
	private String taxID;
	private boolean usCitizen;
	private String gender;

	public FormEvent(Object source) {
		//EventObject has a constructor that accepts an Object type
		//this is an extension of that constructor
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public FormEvent(Object source, String name, String occupation, int ageCat, String empCat, String taxID, boolean usCitizen, String gender) {
		super(source);
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCat;
		this.empCat = empCat;
		this.taxID = taxID;
		this.usCitizen = usCitizen;
		this.gender = gender;
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
	
	public int getAgeCategory() {
		return ageCategory;
	}
	
	public String getEmploymentCategory() {
		return this.empCat;
	}
	
	public String getTaxID() {
		return this.taxID;
	}
	
	public boolean isUSCitizen() {
		return usCitizen;
	}
	
	public String getGender() {
		return this.gender;
	}

}
