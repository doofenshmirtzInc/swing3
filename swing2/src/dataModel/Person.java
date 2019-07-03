package dataModel;

public class Person {

	private static int count = 0;
	private int id;
	private String name;
	private AgeCategory ageCategory;
	private Gender gender;
	private EmploymentCategory empCat;
	private String taxId;
	private boolean isCitizen;
	private String occupation;
	
	
	public Person(String name, String occupation, AgeCategory ageCat, EmploymentCategory empCat, String taxId, boolean usCitizen, Gender gender) {
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCat;
		this.empCat = empCat;
		this.taxId = taxId;
		this.isCitizen = usCitizen;
		this.gender = gender;
		
		this.id = count;
		count++;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public EmploymentCategory getEmpCat() {
		return empCat;
	}
	public void setEmpCat(EmploymentCategory empCat) {
		this.empCat = empCat;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public boolean isCitizen() {
		return isCitizen;
	}
	public void setCitizen(boolean isCitizen) {
		this.isCitizen = isCitizen;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
}
