import java.util.InputMismatchException;
import java.util.Scanner;

/**
* Building class that inherits House,Market,Office and Playgrounds
*/
abstract public class Buildings implements Cloneable{
    
    //Properties of buildigns
    protected int position;
    protected int length;
    protected int height;
    protected Scanner input = new Scanner(System.in); //All subclasses will use this Scanner
    
    Buildings(){
        
        //left = new int[20];
        //right = new int[20];
    }
    Buildings(int p,int l, int h){
        position = p;
        length = l;
        height = h;
    }

    /**
    * Returns length of building
    */
    protected void set_properties(){
        //intentionally empty
    }


    /**
    * Setting position, length and height of building by user input
    */
    protected void set_position_length_height(){
        try{
            System.out.printf("\nEnter |position|");
            position = input.nextInt();
            input.nextLine(); //to fix endline character problem
            System.out.printf("\nEnter |length|");
            length = input.nextInt();
            input.nextLine();
            System.out.printf("\nEnter |height|");
            height = input.nextInt();
            input.nextLine();
        }catch(InputMismatchException e){
            System.out.printf("Invalid Input %s",e.getMessage());
            input.nextLine();
            position = -1; //that makes this building invalid
        }
        
    }

    /**
    * Focus method
    */
    public void focus(){
        //intentionally empty
    }

    /**
    * @param p Position
    */
    public void set_position(int p){
        position = p;
    }
    
    /**
    * @param l Length
    */
    public void set_length(int l){
        length = l;
    }

    /**
    * @param h Height
    */
    public void set_height(int h){
        height = h;
    }

    /**
    * Returns length of building
    */
    public int get_length(){
        return length;
    }

    /**
    * Returns position of building
    */
    public int get_position(){
        return position;
    }

    /**
    * Returns height of building
    */
    public int get_height(){
        return height;
    }

    /**
    * Overriden clone() method
    */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Buildings temp = (Buildings)super.clone();
        return temp;
    }


    
}
