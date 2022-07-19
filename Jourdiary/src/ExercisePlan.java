import java.util.*;

public class ExercisePlan {

	private AdjacencyListMatrix<Exercise> dailyExercises;
	private Exercise E[];
	private int burnedCals;


	/**
     * Constructor that initiliazes the exercise plan with deafult recommended routines.
     * 
     */
	public ExercisePlan() {
		burnedCals = 0;
		Exercise Aerobics = new Exercise("Aerobics", 144);
		Exercise weightLifting = new Exercise("Weight Lifting", 108);
		Exercise Stretching = new Exercise("Stretching", 144);
		Exercise Calisthenics = new Exercise("Calisthenics", 162);
		Exercise stairStepMachine = new Exercise("Stair Step Machine", 216);
		Exercise Bicycling = new Exercise("Bicycling", 252);
		Exercise ellipticalTrainer = new Exercise("Elliptical Trainer", 324);
		Exercise Walking = new Exercise("Walking", 133);
		Exercise Running = new Exercise("Running", 288);
		Exercise ropeJumping = new Exercise("Rope Jumping", 281);
		E = new Exercise[10];
		E[0] = Aerobics;
		E[1] = weightLifting;
		E[2] = Stretching;
		E[3] = Calisthenics;
		E[4] = stairStepMachine;
		E[5] = Bicycling;
		E[6] = ellipticalTrainer;
		E[7] = Walking;
		E[8] = Running;
		E[9] = ropeJumping;
		dailyExercises = new AdjacencyListMatrix<>(10, true, E);
		dailyExercises.insert(new Edge<Exercise>(Aerobics, Walking));
		dailyExercises.insert(new Edge<Exercise>(Walking, Stretching));
		dailyExercises.insert(new Edge<Exercise>(Stretching, ellipticalTrainer));
		dailyExercises.insert(new Edge<Exercise>(ellipticalTrainer, ropeJumping));
		dailyExercises.insert(new Edge<Exercise>(Calisthenics, Bicycling));
		dailyExercises.insert(new Edge<Exercise>(Bicycling, Aerobics));
		dailyExercises.insert(new Edge<Exercise>(Running, stairStepMachine));
		dailyExercises.insert(new Edge<Exercise>(stairStepMachine, weightLifting));
		dailyExercises.insert(new Edge<Exercise>(weightLifting, ropeJumping));
		dailyExercises.insert(new Edge<Exercise>(ropeJumping, Calisthenics));
	}

	public AdjacencyListMatrix<Exercise> getDailyExercises() {
		return dailyExercises;
	}

	public void setDailyExercises(AdjacencyListMatrix<Exercise> dailyExercises) {
		this.dailyExercises = dailyExercises;
	}

	public void showDaily()
	{
		Random rand = new Random();
		int r = rand.nextInt(10);
		Iterator<Edge<Exercise>> itr = dailyExercises.edgeIterator(dailyExercises.vertex(r));
		Iterator<Edge<Exercise>> itr2 = dailyExercises.edgeIterator(dailyExercises.vertex(r));
		while(itr.hasNext() && itr2.hasNext())
		{
			System.out.println(itr.next().toString());
			burnedCals += itr2.next().getDest().getCal();
		}
	}

	public int ExerciseStatistics()
	{
		return burnedCals;
	}

	public void addExercise()
	{
		String n = GetChoiceFromUser.getStringFromUser(">>Please enter the exercise name: ");
		int c = GetChoiceFromUser.getNumber(">>Please enter the calories this routine burns: ");

		Exercise E = new Exercise(n, c);		
		dailyExercises.insertVertex(E);
		Random rand = new Random();
		int r = rand.nextInt(10);
		dailyExercises.insert(new Edge<Exercise>(dailyExercises.vertex(r), E));
	}


	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("\nAerobics : 144");
		return str.toString();
	}

}