package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dataModel.AgeCategory;
import dataModel.Database;
import dataModel.EmploymentCategory;
import dataModel.Gender;
import dataModel.Person;

public class Controller {
	
	Database database = new Database();
	
	public List<Person> getPeople(){
		return database.getPeople();
	}
	
	public void addPerson(String name, String occupation, int ageCatId, String empCat, boolean isUs, String taxId, String gender) {
		
		AgeCategory ageCat = switchToEnum(ageCatId);
		EmploymentCategory empCategory = changeToEnum(empCat);
		Gender genderCat = changeToEnumerated(gender);
		
		Person person = new Person(name, occupation, ageCat, empCategory, isUs, taxId, genderCat);
		
		database.addPerson(person);
	}
	
	private AgeCategory switchToEnum(int ageCatId) {
		AgeCategory ageCat = null;
		
		switch(ageCatId) {
		case 0:
			ageCat = AgeCategory.CHILD;
			break;
		case 1:
			ageCat = AgeCategory.ADULT;
			break;
		case 2:
			ageCat = AgeCategory.SENIOR;
			break;
		}
		
		return ageCat;
	}
	
	private EmploymentCategory changeToEnum(String empCat) {
		EmploymentCategory empCategory;
		
		if(empCat.equals("Employed")) {
			empCategory = EmploymentCategory.EMPLOYED;
		}else if(empCat.equals("Self-Employed")) {
			empCategory = EmploymentCategory.SELF_EMPLOYED;
		}else if(empCat.equals("Unemployed")) {
			empCategory = EmploymentCategory.UNEMPLOYED;
		}else {
			empCategory = EmploymentCategory.OTHER;
		}
		
		return empCategory;
	}
	
	
	private Gender changeToEnumerated(String gender) {
		Gender genderCat;
		
		if(gender.equals("Male")) {
			genderCat = Gender.MALE;
		}else if(gender.equals("Female")){
			genderCat = Gender.FEMALE;
		}else {
			genderCat = Gender.OTHER;
		}
		
		return genderCat;
	}
	
	public void saveToFile(File file) throws IOException {
		database.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException{
		database.loadFromFile(file);
	}

	public void removePerson(int row) {
		database.removePersonAtIndex(row);
	}
}
