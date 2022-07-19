import java.util.ArrayList;

public class DailySection implements Comparable<DailySection>{
	private final Date date;

	private ArrayList<String> dailyEntries;

	private String menuOfTheDay;

	private Exercise exerciseOfTheDay;

	DailySection(Date date, String menuOfTheDay, Exercise exerciseOfTheDay) {
		this.date = date;
		this.menuOfTheDay = menuOfTheDay;
		this.exerciseOfTheDay = exerciseOfTheDay;
		dailyEntries = new ArrayList<>();
	}

	public Date getDate() {
		return date;
	}

	public String getMenuOfTheDay() {
		return menuOfTheDay;
	}

	public Exercise getExerciseOfTheDay() {
		return exerciseOfTheDay;
	}

	public ArrayList<String> getDailyEntries() {
		return dailyEntries;
	}

	public void setDailyEntries(ArrayList<String> dailyEntries) {
		this.dailyEntries = dailyEntries;
	}

	public void addDailyEntry(String entry){
		dailyEntries.add(entry);
	}

	public void setMenuOfTheDay(String menu){
		menuOfTheDay = menu;
	}

	public void setExerciseOfTheDay(Exercise e){
		exerciseOfTheDay = e;
	}

	@Override
	public int compareTo(DailySection ds){
		return date.compareTo(ds.getDate());
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("\n =======> ");
		str.append(getDate());
		str.append(" <=======\n");
		str.append("==>Menu of the day : ");
		str.append(menuOfTheDay);
		str.append("\n==>Exercise of the day :");
		str.append(exerciseOfTheDay);
		str.append("\n\n __| Daily Entries |__");
		for(int i=0;i<dailyEntries.size();i++){
			str.append("\n");
			str.append(dailyEntries.get(i));
		}
		str.append("\n--------------END----------------\n");
		return str.toString();
	}
}
