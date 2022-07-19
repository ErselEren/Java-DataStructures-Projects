
public abstract class UI {
	public static void start_screen() {
		demo();
		while (true) {
			start_screen_menu();
			int input = GetChoiceFromUser.getSubChoice(2, "YOUR CHOICE: ");
			switch (input) {
				case 0:
					System.exit(0);
				case 1:
					while (!loginProcess());
					home_screen();
					break;
				case 2:
					while (!signup());
					break;
			}
		}
	}

	private static void home_screen() {
		while(true) {
			home_screen_menu();
			int input = GetChoiceFromUser.getSubChoice(3, "YOUR CHOICE: ");
			switch (input) {
				case 0:
					System.exit(0);
				case 1:
					user_profile();
					break;
				case 2:
					return;
				case 3: SystemManager.shares();
					break;
			}
		}
	}

	private static void user_profile() {
		while(true) {
			user_profile_menu();
			int input = GetChoiceFromUser.getSubChoice(7, "YOUR CHOICE: ");

			switch (input) {
				case 0:
					return;
				case 1:
					daily_section();
					break;
				case 2:
					to_do();
					break;
				case 3:
					recipes();
					break;
				case 4:
					diet_plan();
					break;
				case 5:
					exercise_plan();
					break;
				case 6:
					hobbies();
					break;
			}
		}
	}

	private static boolean loginProcess() {
		System.out.println("\n\n======>LOGIN<======");
		String id = GetChoiceFromUser.getStringFromUser("ENTER ID: ");
		if (SystemManager.login(id)) {
			System.out.println("LOGIN SUCCESSFUL");
			System.out.printf("Welcome %s\n", SystemManager.getLoggedUser().getName());
			return true;
		} else {
			System.out.println("LOGIN FAILED");
			return false;
		}
	}

	private static boolean signup() {
		System.out.println("\n\n=======>SIGN UP<=======");
		String ID = GetChoiceFromUser.getStringFromUser(">>ENTER ID: ");

		String Name = GetChoiceFromUser.getStringFromUser(">>ENTER NAME: ");

		String Surname = GetChoiceFromUser.getStringFromUser(">>ENTER SURNAME: ");

		User newUser = new User(ID, Name, Surname);
		if (SystemManager.signup(newUser)) {
			System.out.println("|+| SIGNED UP SUCCESSFULLY |+|");
			SystemManager.getUsers().put(ID, newUser);
			return true;
		} else {
			System.out.println("!-!- SIGN UP FAILED -!-!");
			return false;
		}
	}

	private static void daily_section() {
		while(true){
			daily_section_menu();
			int input = GetChoiceFromUser.getSubChoice(3, "YOUR CHOICE: ");
			switch (input) {
				case 0:
					return;
				case 1:
					SystemManager.displayADailySectionWithDate();
					break;
				case 2:
					SystemManager.createNewDailySection();
					break;			
				case 3:
					edit_daily_section();
					break;
			}
		}
	}

	private static void edit_daily_section() {
		System.out.println("ENTER DATE");
		int day = GetChoiceFromUser.getSubChoice(31, ">>DAY: ");
		int month = GetChoiceFromUser.getSubChoice(12, ">>MONTH: ");
		int year = GetChoiceFromUser.getNumber(">>YEAR: ");
		Date date = new Date(day, month, year);
		DailySection temp = new DailySection(date, "unknown", null);
		DailySection ds = SystemManager.getLoggedUser().getNotebook().getDailySections().find(temp);
		
		if (ds == null) System.out.println("DAILY SECTION CANNOT FOUND");
		else{
			System.out.printf("\n 1 - Add New Daily Entry");
			System.out.printf("\n 2 - Set the Menu of the Day");
			System.out.printf("\n 3 - Set the Exercise of the Day");
			int input = GetChoiceFromUser.getSubChoice(3, "\n>>");
			if(input == 1) SystemManager.addNewDailyEntry(ds);
			else if(input == 2) SystemManager.setMenuOfTheDay(ds);
			else if(input == 3) SystemManager.setExerciseOfTheDay(ds);
		}
	}

	private static void to_do() {
		while (true) {
			to_do_menu();
			int input = GetChoiceFromUser.getSubChoice(6, "YOUR CHOICE: ");
			switch (input) {
				case 0:
					return;
				case 1:
					SystemManager.displayCurrentToDos();
					break;
				case 2:
					SystemManager.displayCompletedToDos();
					break;
				case 3:
					SystemManager.createToDoList();
					break;
				case 4:
					SystemManager.editToDoList();
					break;
				case 5:
					SystemManager.markToDoAsCompleted();
					break;
				case 6:
					SystemManager.markTaskAsCompleted();
					break;
			}
		}
	}

	private static void recipes(){
		while(true){
			recipes_menu();
			int input = GetChoiceFromUser.getSubChoice(4, "YOUR CHOICE: ");
			switch (input) {
				case 0:
					return;
				case 1:
					SystemManager.displayAllRecipes();
					break;
				case 2:
					SystemManager.addRecipe();
					break;
				case 3:
					SystemManager.removeRecipe();
					break;
				case 4:
					SystemManager.displayARecipe();
					break;	
			}
		}
	}

	private static void diet_plan(){
		while(true){
			diet_plan_menu();
			int input = GetChoiceFromUser.getSubChoice(4, "YOUR CHOICE: ");
			switch (input) {
				case 0:
					return;
				case 1:
					SystemManager.displayDietPlans();
					break;
				case 2:
					SystemManager.addFoodToDietPlan();
					break;
				case 3:
					SystemManager.removeFoodFromDietPlan();
					break;
				case 4:
					SystemManager.filterFoodsByCalories();
					break;
			}
		}
	}

	private static void exercise_plan(){
		while(true){
			exercise_plan_menu();
			int input = GetChoiceFromUser.getSubChoice(3, "YOUR CHOICE: ");
			switch (input) {
				case 0:
					return;
				case 1:
					SystemManager.displayExercisePlan();
					break;
				case 2:
					SystemManager.displayExerciseOfTheDay();
					break;
				case 3:
					SystemManager.addExercisePlan();
					break;
			}
		}
	}

	private static void hobbies(){
		while(true){
			hobbies_menu();
			int input = GetChoiceFromUser.getSubChoice(3, "YOUR CHOICE: ");
			switch (input) {
				case 0:
					return;
				case 1:
					SystemManager.displayHobbies();
					break;
				case 2:
					SystemManager.addHobby();
					break;
			}
		}
	}

	private static void start_screen_menu(){

		System.out.println("\n\n        *************            ********            **        **        *****            ******            ******          ********          *****            **              ** ");
		System.out.println("               **              **        **          **        **        **   **          **    **            **           **      **         **   **            **          **   ");
		System.out.println("               **             **          **         **        **        **     **        **     **           **          **        **        **     **            **      **     ");
		System.out.println("               **            **            **        **        **        **   **          **      **          **          **        **        **   **                **  **       ");
		System.out.println("               **            **            **        **        **        ****             **      **          **          ************        ****                     **         ");
		System.out.println("        **     **            **            **        **        **        **  **           **      **          **          **        **        **  **                   **         ");
		System.out.println("         **    **             **          **         **        **        **   **          **     **           **          **        **        **   **                  **         ");
		System.out.println("          **  **               **        **           **      **         **    **         **    **            **          **        **        **    **                 **         ");
		System.out.println("           ****                  ********               ******           **     **        ******            ******        **        **        **     **                **         ");

		System.out.println("\n0 - Exit Program");
		System.out.println("1 - Login");
		System.out.println("2 - Sign up");
		System.out.println("===================");
	}

	private static void home_screen_menu(){
		System.out.println("\n\n\n===================");
		System.out.println("0 - Exit Program");
		System.out.println("1 - Profile ");
		System.out.println("2 - Sign out ");
		System.out.println("3 - Home ");
		System.out.println("===================");
	}

	private static void user_profile_menu(){
		System.out.printf("\n\n>>Profile : %s", SystemManager.getLoggedUser().getId());
		System.out.println("\n===========================");
		System.out.println("0 - Back");
		System.out.println("1 - Daily Section");
		System.out.println("2 - ToDo");
		System.out.println("3 - Recipes");
		System.out.println("4 - Diet Plan");
		System.out.println("5 - Exercise Plan");
		System.out.println("6 - Hobbies ");
		System.out.println("==========================");
	}

	private static void recipes_menu(){
		System.out.println("\n\n================================");
		System.out.println("0 - Back");
		System.out.println("1 - Display All Recipes");
		System.out.println("2 - Add a Recipe");
		System.out.println("3 - Remove a Recipe ");
		System.out.println("4 - Display a Recipe ");
		System.out.println("==================================");
	}

	private static void hobbies_menu(){
		System.out.println("\n\n===========================");
		System.out.println("0 - Back");
		System.out.println("1 - Display All Hobbies");
		System.out.println("2 - Add Hobbies");
		System.out.println("============================");
	}

	private static void daily_section_menu() {
		System.out.println("\n\n=================================");
		System.out.println("0 - Back");
		System.out.println("1 - Display a Daily Section in Given Date");
		System.out.println("2 - Create New Daily Section");
		System.out.println("3 - Edit a Daily Section");
		System.out.println("===================================");
	}

	private static void to_do_menu() {
		System.out.println("\n\n====================================");
		System.out.println("0 - Back");
		System.out.println("1 - Display Current To-Dos");
		System.out.println("2 - Display Completed To-Dos");
		System.out.println("3 - Create To-Do List");
		System.out.println("4 - Edit To-Do List");
		System.out.println("5 - Mark a To-Do List as Completed");
		System.out.println("6 - Mark a Task as Completed");
		System.out.println("=======================================");
	}

	private static void diet_plan_menu(){
		System.out.println("\n\n=============================================");
		System.out.println("0 - Back");
		System.out.println("1 - Display My Diet Plan");
		System.out.println("2 - Add Food to Diet Plan");
		System.out.println("3 - Remove Food from Diet Plan");
		System.out.println("4 - Filter Foods in Diet Plan by Calorie");
		System.out.println("===============================================");
	}

	private static void exercise_plan_menu(){
		System.out.println("\n\n===========================================");
		System.out.println("0 - Back");
		System.out.println("1 - Display All My Exercise Plans");
		System.out.println("2 - Suggest an Exercise Plan");
		System.out.println("3 - Add Exercise Plan");
		System.out.println("==============================================");
	}

	public static void demo(){
		//-----------------------------------------------------------------------------------------------------
		User user1 = new User("ersel14", "ersel", "eren");
		Date date1 = new Date(14, 6, 2022);
		Exercise e1 = new Exercise("Aerobics", 144);
		DailySection ds1 = new DailySection(date1, "Cured Sweet & Spicy Walnut", e1);
		ToDo t1 = new ToDo(date1, "erselTodoList1");
		Task task1 = new Task("getting ready for school");
		t1.addTask(task1);
		user1.getNotebook().addCurrentToDo(t1);
		DietPlan d1 = new DietPlan();
		Food f1 = new Food("chocolate milk", 208);
		Food f2 = new Food("white beans", 249);
		Food f3 = new Food("banana", 105);
		Food f4 = new Food("apple", 81);
		d1.add(f1);
		d1.add(f2);
		d1.add(f3);
		d1.add(f4);
		user1.getNotebook().setDietPlans(d1);
		Recipe r4 = new Recipe("pancakes", "Put 100g plain flour, 2 large eggs, 300ml milk, 1 tbsp sunflower or vegetable oil and a pinch of salt into a bowl or large jug, then whisk to a smooth batter.");
		user1.getNotebook().getRecipes().add(r4);
		user1.getNotebook().getDailySections().add(ds1);
		//-----------------------------------------------------------------------------------------------------
		User user2 = new User("thomas10", "Thomas", "Hood");
		Date date2 = new Date(13, 5, 2021);
		Exercise e2 = new Exercise("Running", 288);
		DailySection ds2 = new DailySection(date2, "Rosemary and squash gyoza", e2);
		ToDo t2 = new ToDo(date2, "thomasTodoList1");
		ToDo t7 = new ToDo(new Date(20, 7, 2023), "thomasTodoList2");
		Task task2 = new Task("Complete science project");
		t2.addTask(task2);
		user2.getNotebook().addCurrentToDo(t2);
		user2.getNotebook().addCurrentToDo(t7);
		DietPlan d2 = new DietPlan();
		Food f5 = new Food("Apple juice", 111);
		Food f6 = new Food("grapes", 114);
		d2.add(f5);
		d2.add(f6);
		user2.getNotebook().setDietPlans(d2);
		user2.getNotebook().getDailySections().add(ds2);
		//-----------------------------------------------------------------------------------------------------
		User user3 = new User("CaseyB", "Casey", "Black");
		Date date3 = new Date(12, 4, 2020);
		Exercise e3 = new Exercise("Walking", 133);
		DailySection ds3 = new DailySection(date3, "Milk Chocolate Pancakes", e3);
		ToDo t3 = new ToDo(date3, "caseyTodoList1");
		Task task3 = new Task("getting ready for school");
		Task task4 = new Task("Study math");
		t3.addTask(task3);
		t3.addTask(task4);
		user3.getNotebook().addCurrentToDo(t3);
		DietPlan d3 = new DietPlan();
		user3.getNotebook().setDietPlans(d3);
		Recipe r1 = new Recipe("Easy butter chicken", "In a medium bowl, mix all the marinade ingredients with some seasoning. Chop the chicken into bite-sized pieces and toss with the marinade. Cover and chill in the fridge for 1 hr or overnight.");
		Recipe r2 = new Recipe("Chorizo & mozzarella gnocchi bake", "Heat the oil in a medium pan over a medium heat. Fry the onion and garlic for 8-10 mins until soft. Add the chorizo and fry for 5 mins more. Tip in the tomatoes and sugar, and season. Bring to a simmer, then add the gnocchi and cook for 8 mins, stirring often, until soft. Heat the grill to high.");
		Recipe r3 = new Recipe("teriyaki chicken","Heat the oil in a non-stick pan over a medium heat. Add the chicken and fry for 7 mins, or until golden. Add the garlic and ginger and fry for 2 mins. Stir in the honey, soy sauce, vinegar and 100ml water. Bring to the boil and cook for 2 - 5 mins over a medium heat until the chicken is sticky and coated in a thick sauce." );
		user3.getNotebook().getRecipes().add(r1);
		user3.getNotebook().getRecipes().add(r2);
		user3.getNotebook().getRecipes().add(r3);
		user3.getNotebook().getDailySections().add(ds3);
		//-----------------------------------------------------------------------------------------------------
		User user4 = new User("xXxChelseaxXx", "Chelsea", "Silva");
		Date date4 = new Date(11, 3, 2019);
		Exercise e4 = new Exercise("Bicycling", 252);
		DailySection ds4 = new DailySection(date4, "Pan-Fried Coconut ", e4);
		ToDo t4 = new ToDo(date4, "chelseaTodoList1");
		ToDo t6 = new ToDo(new Date(30, 11, 2022), "chelseaTodoList2");
		Task task5 = new Task("Do the laundry");
		Task task6 = new Task("Clear the table");
		Task task7 = new Task("Wash family car");
		Task task8 = new Task("Do yardwork");
		Task task12 = new Task("Vacumming,sweeping,dusting");
		Task task13 = new Task("Set the table");
		t4.addTask(task5);
		t4.addTask(task6);
		t4.addTask(task7);
		t4.addTask(task8);
		t4.addTask(task5);
		t6.addTask(task12);
		t6.addTask(task13);
		user4.getNotebook().addCurrentToDo(t4);
		DietPlan d4 = new DietPlan();
		user4.getNotebook().setDietPlans(d4);
		user4.getNotebook().getDailySections().add(ds4);
		//-----------------------------------------------------------------------------------------------------
		User user5 = new User("l4r4", "Lara", "Patton");
		Date date5 = new Date(10, 2, 2019);
		Exercise e5 = new Exercise("ropeJumping", 281);
		DailySection ds5 = new DailySection(date5, "Steamed Wasabi Snapper", e5);
		ToDo t5 = new ToDo(date5, "laraTodoList1");
		Task task9 = new Task("Mop the floor");
		Task task10 = new Task("Fold clothes");
		Task task11 = new Task("Prepare launch");
		t5.addTask(task9);
		t5.addTask(task10);
		t5.addTask(task11);
		user5.getNotebook().addCurrentToDo(t5);
		DietPlan d5 = new DietPlan();
		Food f7 = new Food("orange", 65);
		Food f8 = new Food("carrot", 31);
		Food f9 = new Food("potato", 220);
		d5.add(f7);
		d5.add(f8);
		d5.add(f9);
		user5.getNotebook().setDietPlans(d5);
		user5.getNotebook().getDailySections().add(ds5);
		//-----------------------------------------------------------------------------------------------------

		SystemManager.getUsers().put(user1.getId(), user1);
		SystemManager.getUsers().put(user2.getId(), user2);
		SystemManager.getUsers().put(user3.getId(), user3);
		SystemManager.getUsers().put(user4.getId(), user4);
		SystemManager.getUsers().put(user5.getId(), user5);

	}


}
