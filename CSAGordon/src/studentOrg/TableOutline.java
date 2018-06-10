package studentOrg;
import java.util.ArrayList;
import util.*;
public class TableOutline { //most likely wont be used
	private SeatGender[] genderConf;
	private SeatSchool[] schoolConf;
	public TableOutline(int n){
		genderConf = new SeatGender[n];
		schoolConf = new SeatSchool[n];
	}
	public void setGender(SeatGender g, int n){
		genderConf[n] = g;
	}
	public void setSchool(SeatSchool s, int n){
		schoolConf[n] = s;
	}
	public int getSize(){
		return genderConf.length; 
	}
	public SeatGender getGender(int n){
		return genderConf[n];
	}
	public SeatSchool getSchool(int n){
		return schoolConf[n];
	}

}
