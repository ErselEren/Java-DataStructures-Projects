import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DietPlan implements Comparable<DietPlan>{
	private NavigableSet<Food> Menu = new TreeSet<Food>();

	public DietPlan() {
		Menu = new TreeSet<Food>();
	}

	public void setDailyMenus(NavigableSet<Food> dailyMenus) {
		this.Menu = dailyMenus;
	}

	public void print_DietPlan(){

	}

	public void add(Food food){
		Menu.add(food);
	}

	public Iterator<Food> getIterator(){
		return Menu.iterator();
	}

	public NavigableSet<Food> getMenu(){
		return Menu;
	}

	@Override
	public int compareTo(DietPlan o) {
		System.out.printf("\n DIET PLAN CLASS COMPARETO");
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("\n==Diet Plan==\n ");
		s.append("\nFoods : \n");
		for (Food f : Menu) {
			s.append("  - ");
			s.append(f);
			//s.append("\n");
		}
		return s.toString();
	}
	
}
