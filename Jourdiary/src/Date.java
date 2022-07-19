public class Date implements Comparable<Date> {
	private int day;
	private int month;
	private int year;

	Date(){ }

	Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay(){
		return day;
	}

	public int getMonth(){
		return month;
	}

	public int getYear(){
		return year;
	}

	public void setDay(int day){
		this.day = day;
	}

	public void setMonth(int month){
		this.month = month;
	}

	public void setYear(int year){
		this.year = year;
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(day);
		str.append("/");
		str.append(month);
		str.append("/");
		str.append(year);
		return str.toString();
	}
	
	@Override
	public int compareTo(Date date) {
		if(date.getYear() == this.getYear() && date.getMonth() == this.getMonth() && date.getDay() == this.getDay()) return 0;
		else if(date.getYear() > this.getYear()) return 1;
		else if(date.getYear() < this.getYear()) return -1;
		else if(date.getMonth() > this.getMonth()) return 1;
		else if(date.getMonth() < this.getMonth()) return -1;
		else if(date.getDay() > this.getDay()) return 1;
		else if(date.getDay() < this.getDay()) return -1;
		else return 0;
	}

}
