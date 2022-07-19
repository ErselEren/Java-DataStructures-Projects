import java.util.*;

public class Notebook {
        private final SkipList<DailySection> dailySections;
        private final HashSet<ToDo> currentToDos;
        private final HashSet<ToDo> completedToDos;

        private DietPlan dietPlans;
        private ExercisePlan exercisePlans;
        private AVLTree<Recipe> recipes;

    public Notebook() {
        dailySections = new SkipList<>();
        currentToDos = new HashSet<>();
        completedToDos = new HashSet<>();
        dietPlans = new DietPlan();
        recipes = new AVLTree<Recipe>();

        //these part could be changed
        exercisePlans = new ExercisePlan();
    }

    public SkipList<DailySection> getDailySections() {
        return dailySections;
    }

    public DailySection getDailySection(Date date) {
        DailySection tempDS = new DailySection(date, "unknown", null);
        return dailySections.find(tempDS);
    }

    public boolean addDailySection(Date date) {
        DailySection tempDS = new DailySection(date, "unknown", null);
        if (dailySections.contains(tempDS)) return false;
        
        dailySections.add(new DailySection(date, "unknown", null));
        return true;
    }

    public HashSet<ToDo> getCurrentToDos() {
        return currentToDos;
    }

    public void addCurrentToDo(ToDo toDo) {
        this.currentToDos.add(toDo);
    }

    public HashSet<ToDo> getCompletedToDos() {
        return completedToDos;
    }

    public void addCompletedToDo(ToDo completedToDo) {
        this.completedToDos.add(completedToDo);
    }

    public DietPlan getDietPlans() {
        return dietPlans;
    }

    public void setDietPlans(DietPlan dietPlans) {
        this.dietPlans = dietPlans;
    }

    public ExercisePlan getExercisePlans() {
        return exercisePlans;
    }

    public void setExercisePlans(ExercisePlan exercisePlans) {
        this.exercisePlans = exercisePlans;
    }

    
    //-----------------------------------------------------------------------------
    
    public ToDo getToDoList(String name){
        Iterator<ToDo> it = currentToDos.iterator();
        while(it.hasNext()){
            ToDo temp = it.next();
            if(temp.getNameOfList().compareTo(name)==0)
                return temp;
        }
        return null;
    }

    public AVLTree<Recipe> getRecipes(){
        return recipes;
    }

}
