import java.util.*;

public abstract class SystemManager {
	//------------------FIELDS------------------
	private static HashMap<String, User> users = new HashMap<>();

	private static User loggedUser;
	private static User sharingUser;

	//-----------------PROFILE METHODS-----------------------
	public static HashMap<String, User> getUsers() {
		return users;
	}

	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User loggedUser) {
		SystemManager.loggedUser = loggedUser;
	}

	public static boolean login(String id) {
		loggedUser = users.get(id);
		return loggedUser != null;
	}

	public static boolean signup(User user) {
		if (user == null) return false;
		if (users.containsKey(user.getId())) return false;
		users.put(user.getId(), user);
		loggedUser = user;
		return true;
	}
	//=============================================================

	//---------------------SHARING---------------------------------
	
	public static void shares(){
		//selects random parts from all users in database
		System.out.println("\n\n            *    *    * * *   *     *   *****   ");
		System.out.println("            *    *    *   *   * * * *   *       ");
		System.out.println("            ******    *   *   *  *  *   *****   ");
		System.out.println("            *    *    *   *   *     *   *       ");
		System.out.println("            *    *    * * *   *     *   *****   ");
		System.out.println("\n========================================================================");
		Iterator it = users.entrySet().iterator();
		
		while (it.hasNext()) {
            Map.Entry mapElement = (Map.Entry)it.next();
            sharingUser = (User) mapElement.getValue();
			if(sharingUser!=loggedUser){	
				System.out.printf("\n][][ %s ][][",sharingUser.getId());
				while(true){
					if(print_sharing() == true) break;
				}
				System.out.printf("\n========================================================================");
			}
        }
	}
	
	private static boolean print_sharing(){
		Random rand = new Random();
		int num = rand.nextInt(6);
		if(num==0){ //hobbies
			int last = sharingUser.getHobbies().size();
			if(last!=0){
				System.out.printf("\n ==>> %s is one of my hobbies ",sharingUser.getHobbies().get(last-1));
				return true;
			}
			else return false;
		
		}
		else if(num==1){//completed to-do
			int size = sharingUser.getNotebook().getCompletedToDos().size();
			if(size==0) return false;
			
			System.out.printf("\n ==>> This is one of my completed ToDos <<==\n");
			Iterator<ToDo> it = sharingUser.getNotebook().getCompletedToDos().iterator();
			System.out.println(it.next());
			return true;
		}
		else if(num==2){//current to-do
			int size = sharingUser.getNotebook().getCurrentToDos().size();
			if(size==0) return false;
			
			System.out.printf("\n ==>> This is one of my current ToDos <<==\n");
			Iterator<ToDo> it = sharingUser.getNotebook().getCurrentToDos().iterator();
			System.out.println(it.next());
			return true;
		}
		else if(num==3){//diet plan
			int size= sharingUser.getNotebook().getDietPlans().getMenu().size();
			if(size==0) return false;
			
			System.out.printf("\n ==>> This is my diet plan <<==\n");
			System.out.println(sharingUser.getNotebook().getDietPlans());
			return true;
		}
		else if(num==4){
			int size = sharingUser.getNotebook().getRecipes().getSize();
			if(size == 0) return false;
			System.out.printf("\n ==>> I'm sharing a recipe <<==\n");
			Recipe recipe = sharingUser.getNotebook().getRecipes().root.data;
			System.out.println(recipe);
			return true;
		} 
		else if (num == 6) {//exercise plan
			int size = sharingUser.getNotebook().getExercisePlans().getDailyExercises().getSize();
			if(size == 0) return false;

			System.out.printf("\n ==>> I'm sharing an exercise plan of mine <<==\n");
			int i=0;
			while(true){
				Exercise exercise = sharingUser.getNotebook().getExercisePlans().getDailyExercises().vertex(i);
				if(exercise != null){
					System.out.println(exercise);
					return true;
				}
				i++;
			}
		}
		return false;

	}


	//--------------------DAILY SECTION----------------------------

	public static void displayADailySectionWithDate(){
		int day = GetChoiceFromUser.getSubChoice(31, "\n>>Enter day : ");
		int month = GetChoiceFromUser.getSubChoice(12, "\n>>Enter month : ");
		int year = GetChoiceFromUser.getSubChoice(3000, "\n>>Enter year : ");
		Date date = new Date(day, month, year);
		DailySection ds = loggedUser.getNotebook().getDailySection(date);
		if(ds==null){
			System.out.printf("\n !-!- There isn't daily entry in this date -!-! ");
			return;
		}
		System.out.println(ds);
	}

	public static void createNewDailySection(){
		int day = GetChoiceFromUser.getSubChoice(31, "\n>>Enter day : ");
		int month = GetChoiceFromUser.getSubChoice(12, "\n>>Enter month : ");
		int year = GetChoiceFromUser.getNumber("\n>>Enter year : ");
		Date date = new Date(day,month,year);
		loggedUser.getNotebook().addDailySection(date);
	}

	public static DailySection getDailySection(Date date) {
		return loggedUser.getNotebook().getDailySection(date);
	}

	public static void displayDailySection(Date date) {
		DailySection ds = getDailySection(date);
		System.out.println(ds != null ? ds : "No Result");
	}

	public static void addNewDailyEntry(DailySection ds){
		String entry = GetChoiceFromUser.getStringFromUser("\n>> Enter new daily entry : ");
		ds.addDailyEntry(entry);
	}

	public static void setMenuOfTheDay(DailySection ds){
		String menu = GetChoiceFromUser.getStringFromUser("\n>> Enter menu of the day : ");
		ds.setMenuOfTheDay(menu);
	}

	public static void setExerciseOfTheDay(DailySection ds){
		String exercise = GetChoiceFromUser.getStringFromUser("\n>> Enter exercise of the day : ");
		int cal = GetChoiceFromUser.getNumber("\n>>Enter target calorie of that exercise : ");
		Exercise e = new Exercise(exercise, cal);
		ds.setExerciseOfTheDay(e);
	}


	//-------------------------TO-DO--------------------------------
	public static void displayCurrentToDos() {
		System.out.printf("\n ------->> Current To-Dos <<--------");
		if(loggedUser.getNotebook().getCurrentToDos().size()==0){
			System.out.printf("\n|+| There is no To-Do for now |+|");
		}
		Iterator<ToDo> it = loggedUser.getNotebook().getCurrentToDos().iterator();
		while (it.hasNext()) {
			displayToDo(it.next());
		}
	}

	public static void displayCompletedToDos() {
		System.out.printf("\n ------->> Completed To-Dos <<--------");
		Iterator<ToDo> it = loggedUser.getNotebook().getCompletedToDos().iterator();
		while(it.hasNext()){
			displayToDo(it.next());
		}
	}

	public static void displayToDo(ToDo toDo) {
		if(toDo == null){
			return;
		}
		System.out.printf("\nList Name : %s || DEADLINE : %s\n",toDo.getNameOfList(),toDo.getDeadline());
		Iterator<Task> it = toDo.getTasks().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}	
	}

	public static void createToDoList(){
		String nameOfList = GetChoiceFromUser.getStringFromUser(">>Enter name of list : ");
		int day = GetChoiceFromUser.getSubChoice(31, ">>Please enter day : ");
		int month = GetChoiceFromUser.getSubChoice(12, ">>Please enter month : ");
		int year = GetChoiceFromUser.getNumber(">>Please enter year : ");
		Date date = new Date(year, month, day);

		ToDo newTodo = new ToDo(date, nameOfList);
		loggedUser.getNotebook().addCurrentToDo(newTodo);
	}

	public static void editToDoList() {
		String nameOfList = GetChoiceFromUser.getStringFromUser(">>Enter name of list :");
		ToDo list = loggedUser.getNotebook().getToDoList(nameOfList);

		int choice = GetChoiceFromUser.getSubChoice(2, "\n>>1 - Change deadline \n>>2 - Add new task\n>>");
		if(choice == 1){
			int day = GetChoiceFromUser.getSubChoice(31, ">>Please enter day : ");
			int month = GetChoiceFromUser.getSubChoice(12, ">>Please enter month : ");
			int year = GetChoiceFromUser.getNumber(">>Please enter year : ");
			list.setDeadline(new Date(year, month, day));
		}
		else if(choice == 2){
			String content = GetChoiceFromUser.getStringFromUser(">>Enter new task : ");
			list.addTask(new Task(content));
		}
	}
	
	public static void markToDoAsCompleted(){
		String nameOfList = GetChoiceFromUser.getStringFromUser(">>Enter name of list :");
		ToDo list = loggedUser.getNotebook().getToDoList(nameOfList);
		Iterator<Task> it = list.getTasks().iterator();
		while(it.hasNext()){
			it.next().setCompleted(true);
		}
		loggedUser.getNotebook().getCurrentToDos().remove(list);
		loggedUser.getNotebook().getCompletedToDos().add(list);
	}
	
	public static void markTaskAsCompleted(){
		String taskName = GetChoiceFromUser.getStringFromUser(">> Enter name of task : ");
		Iterator<ToDo> it = loggedUser.getNotebook().getCurrentToDos().iterator();
		while(it.hasNext()){
			ToDo toDo = it.next();
			Iterator<Task> it2 = toDo.getTasks().iterator();
			while(it2.hasNext()){
				Task task = it2.next();
				if(task.getContent().compareTo(taskName) == 0){
					task.setCompleted(true);
					return;
				}
			}
		}
		
	}

	//--------------------EXERCISE PLANS----------------------

	public static void displayExercisePlan() {
		System.out.print(loggedUser.getNotebook().getExercisePlans().toString());
	}

	public static void displayExerciseOfTheDay(){
		loggedUser.getNotebook().getExercisePlans().showDaily();
	}

	public static void addExercisePlan() {
		loggedUser.getNotebook().getExercisePlans().addExercise();
	}

	//-------------------DIET PLANS----------------------------
	public static void displayDietPlans() {
		System.out.printf("\n\n======>Displaying Diet Plan<=======\n");
		Iterator<Food> it = loggedUser.getNotebook().getDietPlans().getIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

	public static void displayDietPlans(NavigableSet<Food> set) {
		Iterator<Food> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

	public static void addFoodToDietPlan() {
		String foodName = GetChoiceFromUser.getStringFromUser(">>Enter name of food : ");
		Integer cal = GetChoiceFromUser.getNumber(">>Enter cal of food : ");
		Food food = new Food(foodName, cal);
		loggedUser.getNotebook().getDietPlans().add(food);
	}

	public static void removeFoodFromDietPlan(){
		String foodName = GetChoiceFromUser.getStringFromUser(">>Enter name of food : ");
		for(Food food : loggedUser.getNotebook().getDietPlans().getMenu()){
			if(food.getNameOfFood().compareTo(foodName)==0){
				loggedUser.getNotebook().getDietPlans().getMenu().remove(food);
				break;
			}
		}
	}

	public static void filterFoodsByCalories(){
		int cal = GetChoiceFromUser.getNumber(">>Enter cal value to filter diet plan : ");
		Food food1 = new Food("a", 0);
		Food food2 = new Food("z", cal);
		System.out.printf("\n\n=====>> Displaying Foods in the Diet Plan Under %d cal <<=====\n",cal);
		NavigableSet<Food> newSet = (NavigableSet<Food>) loggedUser.getNotebook().getDietPlans().getMenu().subSet(food1, food2);
		displayDietPlans(newSet);
	}
	
	
	
	//------------------HOBBIES---------------------------------
	public static void displayHobbies() {
		System.out.printf("\n\n =======> My Hobbies <=======");
		for(int i=0;i<loggedUser.getHobbies().size();i++)
			System.out.printf("\n%d -) %s ",i+1,loggedUser.getHobbies().get(i));
	}

	public static void addHobby() {
		String hobby = GetChoiceFromUser.getStringFromUser(">>Please enter new hobby : ");
		loggedUser.getHobbies().add(hobby);
	}

	//-------------------RECIPES----------------------------------
	public static void displayAllRecipes(){
		System.out.printf("\n\n\n==========>>> Printing All My Recipes <<<===========\n");
		AVLTree<Recipe> tree = loggedUser.getNotebook().getRecipes();
		if(tree.root==null){
			System.out.printf("\n !-!- There is no recipe to show for now -!-! ");
			return;
		}
		printPostorder(tree);
	}

	private static void printPostorder(BinaryTree<Recipe> binaryTree)
    {
		if (binaryTree == null) return;
        printPostorder(binaryTree.getLeftSubtree());
        printPostorder(binaryTree.getRightSubtree());
        System.out.println("--->"+binaryTree.getData().getNameOfRecipe() + " ");
    }

	public static void addRecipe(){
		String name = GetChoiceFromUser.getStringFromUser("\n>>Enter name of recipe : ");
		String content = GetChoiceFromUser.getStringFromUser("\n>>Explain content of recipe : ");
		Recipe recipe = new Recipe(name, content);
		loggedUser.getNotebook().getRecipes().add(recipe);
	}
	public static void removeRecipe(){
		String name = GetChoiceFromUser.getStringFromUser("\n>>Enter name of recipe : ");
		Recipe temp = new Recipe(name);
		Recipe deleted = loggedUser.getNotebook().getRecipes().find(temp);
		if(deleted == null) return;
		loggedUser.getNotebook().getRecipes().delete(deleted);
	}

	public static void displayARecipe(){
		String name = GetChoiceFromUser.getStringFromUser("\n>>Enter name of recipe : ");
		Recipe temp = new Recipe(name);
		Recipe recipe = loggedUser.getNotebook().getRecipes().find(temp);
		if(recipe == null) return;
		System.out.printf("\n\n ======>>> Displaying Recipe of %s <<<======",recipe.getNameOfRecipe());
		System.out.printf("\n===>>> %s",recipe.getContentOfRecipe());
	}



}
