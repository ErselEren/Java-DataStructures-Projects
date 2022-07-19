public class Exercise implements Comparable<Exercise> {
    public String name;
    public int cal;

    public Exercise(String n, int cal) {
        this.name = n;
        this.cal = cal;
    }

    public String getName()
    {
        return name;
    }

    public int getCal() {
        return cal;
    }

    @Override
    public String toString() {
        return "->" + name + ": " + cal ;
    }

    @Override
    public int compareTo(Exercise o) {
        return cal - o.cal;
    }
}
