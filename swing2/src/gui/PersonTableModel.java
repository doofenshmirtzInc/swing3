package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dataModel.Person;

public class PersonTableModel extends AbstractTableModel {
	
	private List<Person> database;
	private String[] columnNames = {
			"ID",
			"Name",
			"Occupation",
			"Age Category",
			"Employment Category",
			"US Citizen",
			"Tax ID"
	};
	
	public PersonTableModel() {
		
	}
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}



	public void setData(List<Person> database) {
		this.database = database;
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		
		//num of columns will always be seven b/c always storing seven items of data
		//on each person: ID, name, occupation, ageCat, employment, UsCitizen, taxID 
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return database.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		Person person = database.get(row);
		
		switch(col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.getAgeCategory();
		case 4:
			return person.getEmpCat();
		case 5:
			return person.isCitizen();
		case 6:
			return person.getTaxId();
		}
		
		return null;
	}

}
