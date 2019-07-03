package dataModel;

import java.util.ArrayList;
import java.util.List;


//acts as a basic database
//have to look at moving data to an SQL database instead

public class Database {
	
	private ArrayList<Person> people;
	
	public Database() {
		people = new ArrayList<Person>();
	}
	
	public void addPerson(Person person) {
		people.add(person);
	}
	
	public List<Person> getPeople(){
		return people;
	}

}
