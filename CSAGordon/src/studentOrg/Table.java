package studentOrg;
import java.util.List;
import java.util.Random;

import util.SeatGender;
import util.SeatSchool;

import java.util.ArrayList;
import java.util.Collections; 

public class Table {
	private Student[] myMembers;
	public Table(){
		myMembers = new Student[4];
	}
	/*public Table(ArrayList<Student> members){
		myMembers = new Student;
		for (int i = 0;i<members.size();i++){
			myMembers.add(members.get(i));
		}
	}*/
	public Table(int n){
		myMembers = new Student[n];
	}
	public void set(int n, Student s){
		myMembers[n] = s;
	}
	public Student get(int n){
		return myMembers[n];
	}
	public int getLength(){
		return myMembers.length;
	}
	public void randomize()
	{
		int index;
		Student temp;
	    Random random = new Random();
	    for (int i = myMembers.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = myMembers[index];
	        myMembers[index] = myMembers[i];
	        myMembers[i] = temp;
	    }
	}

}
