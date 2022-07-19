
public class Food implements Comparable<Food> {
	private String name;
	private Integer cal;

	public Food(String name, Integer cal) {
		this.name = name;
		this.cal = cal;
	}

	public String getNameOfFood() {
		return name;
	}

	public void setNameOfFoods(String name) {
		this.name = name;
	}

	public void setCalOfFood(Integer cal) {
		this.cal = cal;
	}

	public Integer getCalOfFood() {
		return cal;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(name);
		str.append("--->");
		str.append(cal);
		str.append("cal");
		return name + " ---> " + cal + " cal";
	}

	@Override
	public int compareTo(Food o) {
		return cal - o.cal;
	}
}
