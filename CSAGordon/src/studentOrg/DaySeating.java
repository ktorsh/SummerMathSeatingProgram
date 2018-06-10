package studentOrg;
import java.util.ArrayList;
import java.util.Collections;

import util.Weekday;
public class DaySeating {
	private Weekday day;
	private ArrayList<Table> tables;
	public DaySeating(){
		tables = new ArrayList<Table>();
	}
	public DaySeating(Weekday d){
		day = d;
		tables = new ArrayList<Table>();
	}
	public void setDay(Weekday d){
		day = d;
	}
	public void addTable(Table t){
		tables.add(t);
	}
	public Table getTable(int n){
		return tables.get(n);
	}
	public int getSize(){
		return tables.size();
	}
	
	public void randomize(){
		Collections.shuffle(tables);
	}

}
