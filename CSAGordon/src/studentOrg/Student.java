package studentOrg;
import java.util.ArrayList;

import util.*;
public class Student {
	private String firstName;
	private String lastName;
	private String stringSchool; 
	private String house;
	private SeatGender gender;
	private SeatSchool middleSchool;
	private String seat;
	private ArrayList<Integer> tableHist;
	
	
	public Student(){
		firstName = "John";
		lastName = "Doe";
		house = "SMCS"; 
		gender = SeatGender.MALE;
		middleSchool = SeatSchool.RCMS;
	}
	public Student(String first, String last, String h, String g, String strSchool){
		/*if (gender != S || gender != "Female"){
			throw new IllegalArgumentException("Gender is not correctly specified");
		}*/
		
		firstName = first;
		lastName = last;
		house = h; 
		if (g.equals("M")){
			gender = SeatGender.MALE;
		}
		else{ 
			gender = SeatGender.FEMALE;
		}
		stringSchool = strSchool;
		if (strSchool.equals("Roberto W. Clemente")){
			middleSchool = SeatSchool.RCMS;
		}
		else {
			middleSchool = SeatSchool.NONRCMS;
		}
		tableHist = new ArrayList<Integer>();
	}
	
	public Student(Student other){
		firstName = other.firstName;
		lastName = other.lastName;
		gender = other.gender;
		middleSchool = other.middleSchool;
	}
	public int getTableHist(int n){
		return tableHist.get(n);
	}
	public void addTableHist(int table){
		tableHist.add(table);
	}
	public int getHistSize(){
		return tableHist.size();
	}
	public boolean shareTable(Student s){
		for (int i=0; i<s.getHistSize(); i++){
			if (tableHist.contains(s.getTableHist(i))){
				return true;
			}
		}
		return false;
	}
	public void setSeat(String s){
		seat=s;
	}
	public String getStringSchool() {
		return stringSchool;
	}
	public void setStringSchool(String stringSchool) {
		this.stringSchool = stringSchool;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getSeat(){
		return seat;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String myFirstName) {
		this.firstName = myFirstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String myLastName) {
		this.lastName = myLastName;
	}
	public SeatGender getGender() {
		return gender;
	}
	public void setGender(SeatGender myGender) {
		this.gender = myGender;
	}
	public SeatSchool getMiddleSchool() {
		return middleSchool;
	}
	public void setMiddleSchool(SeatSchool myMiddleSchool) {
		this.middleSchool = myMiddleSchool;
	}
	public String toString(){
		return firstName + " "+ lastName + " "+ gender + " "+ stringSchool+" "+ house;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleSchool == null) ? 0 : middleSchool.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleSchool != other.middleSchool)
			return false;
		return true;
	}
	
	

}
