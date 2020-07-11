package Program;

import java.io.Serializable;

public class Date implements Serializable{

	private static int[] months= {31,28,31,30,31,30,31,31,30,31,30,31};
	
	private int day;
	private int month;
	private int year;
	
	public Date(int year,int month,int day) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	private void setYear(int year2) {
		if(year2<0 || year2>=2024) {
			this.year=2020;
		}
		else {
			this.year=year2;
		}
	}

	private void setMonth(int month2) {
		if(month2<0 || month2>12) {
			this.month=1;
		}
		else {
			this.month=month2;
		}
	}

	private void setDay(int day2) {
		if(day2>months[month] || day2<0) {
			this.day=1;
		}
		else {
			this.day=day2;
		}
	}
	
	@Override
	public String toString() {
		return day+"/"+month+"/"+year;
	}
}
