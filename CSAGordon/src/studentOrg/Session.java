package studentOrg;
import java.util.ArrayList;
import java.util.HashMap;

import util.SeatGender;
import util.SeatSchool;
import util.Weekday;
public class Session {
	public static final Weekday[] allDays = {Weekday.MONDAY, Weekday.TUESDAY, Weekday.WENDESDAY, Weekday.THURSDAY, Weekday.FRIDAY};
	private int numStudents; 
	private ArrayList<Student> students;
	private HashMap<Weekday,DaySeating> tables; 
	private ArrayList<TableOutline> config; 
	private int sessionNum;
	private String year;
	
	public Session(){
		students = new ArrayList<Student>();
		tables = new HashMap<Weekday,DaySeating>();
		numStudents = students.size();
		config = new ArrayList<TableOutline>();
	}
	public String getYear(){
		return year;
	}
	public void setYear(String s){
		year = s;
	}
	public void setSessionNum(int n){
		sessionNum = n;
	}
	public int getSessionNum(){
		return sessionNum;
	}
	public int getNumStudents(){
		return numStudents;
	}
	public Student getStudent(int n){
		return students.get(n);
	}
	public void addStudent(Student s){
		students.add(s);
		numStudents++;
	}
	
	public DaySeating getDaySeating(Weekday day){
		return tables.get(day);
	}
	public void addDaySeating(Weekday day, DaySeating table){
		tables.put(day, table);
	}
	public void makeTable(int n){
		TableOutline temp = new TableOutline(n);
		config.add(temp);
		System.out.println("a");
	}
	public void makeTable(TableOutline t){
		config.add(t);
	}
	public ArrayList<TableOutline> getConfig(){
		return config;
	}
	public int getNumTables(){
		return config.size(); 
	}
	public int getTableSize(int n){
		return config.get(n).getSize();
	}
	public void setGender(int x, int y, SeatGender g){
		config.get(x).setGender(g, y);
	}
	public void setSchool(int x, int y, SeatSchool s){
		config.get(x).setSchool(s, y);
	}
	public SeatGender getGender(int x, int y){
		return config.get(x).getGender(y);
	}
	public SeatSchool getSchool(int x, int y){
		return config.get(x).getSchool(y);
	}
	public int getNumClemente(int n){
		int count =0;
		for (int i = 0;i<config.get(n).getSize();i++){
			if (config.get(n).getSchool(i) != null && config.get(n).getSchool(i) == SeatSchool.RCMS){
				count++;
			}
		}
		return count; 
	}
	public ArrayList<Student> getClemMen(){
		ArrayList<Student> clemMen = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++){
			if (students.get(i).getGender() == SeatGender.MALE && students.get(i).getMiddleSchool() == SeatSchool.RCMS){
				clemMen.add(students.get(i));
			}	
		}
		return clemMen; 
	}
	public ArrayList<Student> getNonMen(){
		ArrayList<Student> clemMen = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++){
			if (students.get(i).getGender() == SeatGender.MALE && students.get(i).getMiddleSchool() == SeatSchool.NONRCMS){
				clemMen.add(students.get(i));
			}	
		}
		return clemMen; 
	}
	public ArrayList<Student> getClemFem(){
		ArrayList<Student> clemMen = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++){
			if (students.get(i).getGender() == SeatGender.FEMALE && students.get(i).getMiddleSchool() == SeatSchool.RCMS){
				clemMen.add(students.get(i));
			}	
		}
		return clemMen; 
	}
	public ArrayList<Student> getNonFem(){
		ArrayList<Student> clemMen = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++){
			if (students.get(i).getGender() == SeatGender.FEMALE && students.get(i).getMiddleSchool() == SeatSchool.NONRCMS){
				clemMen.add(students.get(i));
			}	
		}
		return clemMen; 
	}
	
	

}
