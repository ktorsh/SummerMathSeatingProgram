package algo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import studentOrg.*;
import util.*;
public class StudentSorter {
	double female;
	double clemente;
	double sitSame;
	
	double percClemFem;
	double percClemMen;
	double percNonFem;
	double percNonMen;
	
	int numClemFem;
	int numClemMen;
	int numNonFem;
	int numNonMen;
	
	int numStudents;
	Session sess;
	public StudentSorter(Session s){
		female = 6.0;
		clemente = 4.0;
		sitSame = 0.5;
		sess = s;
		numStudents = sess.getNumStudents();
		numClemFem = 0;
		numClemMen = 0;
		numNonFem = 0;
		numNonMen = 0;
	}
	private void makeTables(){ //makes the proper table size for each table
		int temp = numStudents;
		while (temp>0){
			System.out.println("hi");
			if (temp == 9 || temp == 7 || temp == 6 || temp == 3){
				sess.makeTable(3);
				temp = temp - 3;
			}
			else {
				sess.makeTable(4);
				temp = temp - 4;
			}
			System.out.println(temp);
		}
	}
	private void updateStats(){
		if (numStudents != 0){
			percClemFem = (double) numClemFem / numStudents;
			percClemMen = (double) numClemMen / numStudents;
			percNonFem = (double) numNonFem / numStudents;
			percNonMen = (double) numNonMen / numStudents;
		}
	}
	private void calculateStats(){
		
		for (int i = 0; i < sess.getNumStudents(); i++){
			if (sess.getStudent(i).getGender() == SeatGender.FEMALE && sess.getStudent(i).getMiddleSchool() == SeatSchool.RCMS){
				numClemFem++;
			}
			if (sess.getStudent(i).getGender() == SeatGender.MALE && sess.getStudent(i).getMiddleSchool() == SeatSchool.RCMS){
				numClemMen++;
			}
			if (sess.getStudent(i).getGender() == SeatGender.FEMALE && sess.getStudent(i).getMiddleSchool() == SeatSchool.NONRCMS){
				numNonFem++;
			}
			if (sess.getStudent(i).getGender() == SeatGender.MALE && sess.getStudent(i).getMiddleSchool() == SeatSchool.NONRCMS){
				numNonMen++;
			}
			
		}
		percClemFem = (double) numClemFem/numStudents;
		percClemMen = (double) numClemMen/numStudents;
		percNonFem = (double) numNonFem/numStudents;
		percNonMen = (double) numNonMen/numStudents;
		System.out.println(percClemFem);
		System.out.println(percClemMen);
		System.out.println(percNonFem);
		System.out.println(percNonMen);
	}
	
	public void makeGirls(){
		// int tempNumStudents = numStudents;
		
		for (int i = 0; i<sess.getNumTables(); i++){
			System.out.println(percClemFem + percNonFem);
			if ((percClemFem + percNonFem)>0.75){ //if there is 75% or more girls: make all girls
				int n; 
				if (sess.getTableSize(i) == 4){
					n=4;
				}
				else {
					n=3;
				}
				int girlIrit = n;
				if (numClemFem + numNonFem < n){
					girlIrit = numClemFem + numNonFem;
				}
				for (int j=0;j<girlIrit;j++){
					if (percClemFem*Math.random() > percNonFem*Math.random()){
						sess.setGender(i, j, SeatGender.FEMALE);
						sess.setSchool(i, j, SeatSchool.RCMS);
						numClemFem--;
						numStudents--;
						updateStats();
					}
					else{
						sess.setGender(i, j, SeatGender.FEMALE);
						sess.setSchool(i, j, SeatSchool.NONRCMS);
						numNonFem--;
						numStudents--;
						updateStats();
					}
				}
				for (int k=0;k<n-girlIrit;k++){
					if ((percClemMen*Math.random() > percNonMen*Math.random())){
						sess.setGender(i, n-1, SeatGender.MALE);
						sess.setSchool(i, n-1, SeatSchool.RCMS);	
						numClemMen--;
						numStudents--;
						updateStats();
					}
					else {
						sess.setGender(i, n-1, SeatGender.MALE);
						sess.setSchool(i, n-1, SeatSchool.NONRCMS);
						numNonMen--;
						numStudents--;
						updateStats();
					}
				}
				
			}
			else if ((percClemFem + percNonFem)>0.5 || (numClemFem + numNonFem)==3){ //if there is more than 50% of girls: make n-1 girls
				int tempnumClem = 0; 
				int n; 
				if (sess.getTableSize(i) == 4){
					n=4;
				}
				else {
					n=3;
				}
				int girlIrit = n-1;
				for (int j = 0;j<girlIrit;j++){
					if (percClemFem*Math.random() > percNonFem*Math.random() && tempnumClem<=2){
						sess.setGender(i, j, SeatGender.FEMALE);
						sess.setSchool(i, j, SeatSchool.RCMS);	
						numClemFem--; 
						numStudents--;
						updateStats();
						tempnumClem++; 
					}
					else{
						sess.setGender(i, j, SeatGender.FEMALE);
						sess.setSchool(i, j, SeatSchool.NONRCMS);
						numNonFem--;
						numStudents--;
						updateStats();
					}
				}
				
				if ((percClemMen*Math.random() > percNonMen*Math.random()) && tempnumClem<=2){
					sess.setGender(i, n-1, SeatGender.MALE);
					sess.setSchool(i, n-1, SeatSchool.RCMS);	
					numClemMen--;
					numStudents--;
					updateStats();
					tempnumClem++;
				}
				else {
					sess.setGender(i, n-1, SeatGender.MALE);
					sess.setSchool(i, n-1, SeatSchool.NONRCMS);
					numNonMen--;
					numStudents--;
					updateStats();
				}
				
			}
				
			else { //less than 50% girls: make 2 girls
				int tempnumClem = 0;
				int n; 
				if (sess.getTableSize(i) == 4){
					n=4;
				}
				else {
					n=3;
				}
				int girlIrit = 2;
				if (numClemFem + numNonFem < 2){
					girlIrit = numClemFem + numNonFem;
				}
				for (int g = 0; g<girlIrit; g++){
					if (percClemFem*Math.random() > percNonFem*Math.random()){
						sess.setGender(i, g, SeatGender.FEMALE);
						sess.setSchool(i, g, SeatSchool.RCMS);	
						numClemFem--; 
						numStudents--;
						updateStats();
						tempnumClem++;
					}
					else {
						sess.setGender(i, g, SeatGender.FEMALE);
						sess.setSchool(i, g, SeatSchool.NONRCMS);
						numNonFem--;
						numStudents--;
						updateStats();			
					}
				}
				for (int b=0; b<n-girlIrit;b++){
					if ((percClemMen*Math.random() > percNonMen*Math.random()) && tempnumClem <= 2){
						sess.setGender(i, girlIrit + b, SeatGender.MALE);
						sess.setSchool(i, girlIrit+b, SeatSchool.RCMS);	
						numClemMen--;
						numStudents--;
						updateStats();
						tempnumClem++;
					}
					else {
						sess.setGender(i, girlIrit + b, SeatGender.MALE);
						sess.setSchool(i, girlIrit + b, SeatSchool.NONRCMS);
						numNonMen--;
						numStudents--;
						updateStats();
					}
				}
			}
	}
	}
	public void assignStudents(){
		ArrayList<Student> clemMen = sess.getClemMen();
		ArrayList<Student> clemFem = sess.getClemFem();
		ArrayList<Student> nonMen = sess.getNonMen();
		ArrayList<Student> nonFem = sess.getNonFem();
		Collections.shuffle(clemMen);
		Collections.shuffle(clemFem);
		Collections.shuffle(nonMen);
		Collections.shuffle(clemFem);
		for (int x=0; x<Session.allDays.length;x++){
			DaySeating seats = new DaySeating(Session.allDays[x]);
			
			ArrayList<Student> tempclemMen = new ArrayList<Student>(clemMen);
			ArrayList<Student> tempclemFem = new ArrayList<Student>(clemFem);
			ArrayList<Student> tempnonMen = new ArrayList<Student>(nonMen);
			ArrayList<Student> tempnonFem = new ArrayList<Student>(nonFem);
			Collections.shuffle(tempclemMen);
			Collections.shuffle(tempclemFem);
			Collections.shuffle(tempnonMen);
			Collections.shuffle(tempclemFem);
			for (int y=0; y<sess.getNumTables(); y++){
				Table t = new Table(sess.getTableSize(y));
				int curStudents=0;
				for (int z=0; z<sess.getTableSize(y); z++){
					if (sess.getGender(y,z) == SeatGender.MALE && sess.getSchool(y,z) == SeatSchool.RCMS){
						int index =0;
						for (int a=0; a<tempclemMen.size(); a++){
							boolean hasSat = false;
							for (int b=0; b<curStudents;b++){
								if (t.get(b).shareTable(tempclemMen.get(a))){
									hasSat = true;
								}
							}
							if (!hasSat){
								index = a;
							}
						}
						t.set(z,tempclemMen.remove(index));
						curStudents++;
						//t.set(z, tempclemMen.remove((int) Math.random()*tempclemMen.size()));
					}
					else if (sess.getGender(y,z) == SeatGender.FEMALE && sess.getSchool(y,z) == SeatSchool.RCMS){
						int index =0;
						for (int a=0; a<tempclemFem.size(); a++){
							boolean hasSat = false;
							for (int b=0; b<curStudents;b++){
								if (t.get(b).shareTable(tempclemFem.get(a))){
									hasSat = true;
								}
							}
							if (!hasSat){
								index = a;
							}
						}
						t.set(z,tempclemFem.remove(index));
						curStudents++;
						//t.set(z, tempclemFem.remove((int) Math.random()*tempclemFem.size()));
					}
					else if (sess.getGender(y,z) == SeatGender.MALE && sess.getSchool(y,z) == SeatSchool.NONRCMS){
						int index =0;
						for (int a=0; a<tempnonMen.size(); a++){
							boolean hasSat = false;
							for (int b=0; b<curStudents;b++){
								if (t.get(b).shareTable(tempnonMen.get(a))){
									hasSat = true;
								}
							}
							if (!hasSat){
								index = a;
							}
						}
						t.set(z,tempnonMen.remove(index));
						curStudents++;
						//t.set(z, tempnonMen.remove((int) Math.random()*tempnonMen.size()));
					}
					else{
						int index =0;
						for (int a=0; a<tempnonFem.size(); a++){
							boolean hasSat = false;
							for (int b=0; b<curStudents;b++){
								if (t.get(b).shareTable(tempnonFem.get(a))){
									hasSat = true;
								}
							}
							if (!hasSat){
								index = a;
							}
						}
						t.set(z,tempnonFem.remove(index));
						curStudents++;
						//t.set(z, tempnonFem.remove((int) Math.random()*tempnonFem.size()));
					}
					System.out.println(t.get(z));

				}
				t.randomize();
				seats.addTable(t);
				
			}
			seats.randomize();
			sess.addDaySeating(Session.allDays[x], seats);
		}
	}
	public void sort(){
		makeTables();
		calculateStats();
		makeGirls();
		assignStudents();
	}


}
