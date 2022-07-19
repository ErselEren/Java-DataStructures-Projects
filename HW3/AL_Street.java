import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//ArrayList Street
public class AL_Street {
    protected ArrayList<Building> left;
    protected ArrayList<Building> right;
    private ArrayList<ArrayList<Integer>> sillhouette;
    private int length_of_street;
    private int choice;
    private Scanner input = new Scanner(System.in);
    private int index_of_deleted;
    private Building newBuilding;
    private int chosen_position;

    AL_Street(){
        length_of_street = 100;
        left = new ArrayList<Building>();
        right = new ArrayList<Building>();
    }

    AL_Street(int l){
        length_of_street = l;
        left = new ArrayList<Building>();
        right = new ArrayList<Building>();
    }


    /**
    * Menu and getting input for editing street 
    * @throws Exception
    */
    public void menu_edit_street() throws Exception{
        System.out.printf("---------------------");
        System.out.printf("\n1-Add left side");
        System.out.printf("\n2-Add right side");
        System.out.printf("\n3-Delete left side");
        System.out.printf("\n4-Delete right side\n");
        try{
            choice = input.nextInt();
            input.nextLine();
            edit_street(choice);
        }
        catch(Exception e){
            System.out.printf("%s",e.getMessage());
        }
            
    }


    /**
    * Calling add or delete building methods by choice
    * @param Input that taken from user in edit street menu
    * @throws Exception
    */
    public void edit_street(int choice) throws Exception{
        if(choice==1||choice==2) add_building();
        else if(choice==3||choice==4) get_delete_input();
        else throw new Exception("INVALID CHOICE");
    }

    
    /**
    * Menu and getting input for viewing mode
    * @throws Exception
    */
    public void menu_view_mode() throws Exception{
        System.out.printf(" \n 1-Display the total remaining length of lands on the street");
        System.out.printf(" \n 2-Display the list of buildings on the street");
        System.out.printf(" \n 3-Display the number and ratio of length of playgrounds in the street");
        System.out.printf(" \n 4-Calculate the total length of street occupied by the markets,houses of offices");                   
        System.out.printf(" \n 5-Display the skyline sillhouette of the street");                          
        System.out.printf(" \n 6-Focus\n--->");   
        try{
            choice = input.nextInt();
            input.nextLine();
            view_mode(choice);
        }
            catch(Exception e){
            System.out.printf("%s",e.getMessage());
        }     
    }

    
    /**
    * Calling related methods by choice
    * @param choice Input that taken from user in view mode menu
    * @throws Exception
    */
    public void view_mode(int choice) throws Exception{
        if(choice==1) remaining_lands();
        else if(choice==2) list_of_buildings();
        else if(choice==3) playground_ratio();
        else if(choice==4) occupied_length();
        else if(choice==5) sillhouette();
        else if(choice==6) street_focus();
        else throw new Exception("INVALID CHOICE");
    }

    
    /**
    * Calculating remaining lands which is not occupied by buildings
    */
    public void remaining_lands(){
        int left_remaining = length_of_street;
        int right_remaining = length_of_street;
             
        for(int i=0;i<left.size();i++) //subtracting length of every building from length of street
            left_remaining-=left.get(i).get_length();
        System.out.printf("\nRemaining lands on the left side : %d",left_remaining); //remaining length in left side of street

        for(int i=0;i<right.size();i++)
            right_remaining-=right.get(i).get_length();
        System.out.printf("\nRemaining lands on the right side : %d",right_remaining);
            
        System.out.printf("\nTotal remaining lands on the street : %d",left_remaining+right_remaining);
    }

    
    /**
    * This method counts both side of street and lists number of building that exist.
    */
    public void list_of_buildings(){
        //counters for buildings
        int house = 0;
        int playground = 0;
        int market = 0;
        int office = 0;
        
        //finds out type of building by using instanceof 
        for(int i=0;i<left.size();i++){
            if(left.get(i) instanceof House) house++;
            else if(left.get(i) instanceof Office) office++;
            else if(left.get(i) instanceof Market) market++;
            else if(left.get(i) instanceof Playground) playground++;
        }

        for(int i=0;i<right.size();i++){
            if(right.get(i) instanceof House) house++;
            else if(right.get(i) instanceof Office) office++;
            else if(right.get(i) instanceof Market) market++;
            else if(right.get(i) instanceof Playground) playground++;
        }

        //listing results
        System.out.printf("\nThere are %d House in street.",house);
        System.out.printf("\nThere are %d Office in street.",office);
        System.out.printf("\nThere are %d Market in street.",market);
        System.out.printf("\nThere are %d Playground in street.",playground);
    
    }


    /**
    * Displays the number and ratio of length of playgrounds in the street
    */
    public void playground_ratio(){
        int total_length_of_street = length_of_street * 2; //length of street with both side
        int playground_count = 0; //total playground number
        int playground_length = 0; //total length occupied by playgrounds
        
        //counting playground buildings by using instanceof
        for(int i=0;i<left.size();i++){
            if(left.get(i) instanceof Playground){
                playground_count++;
                playground_length += left.get(i).get_length();
            }
        }
        
        //counting playground buildings by using instanceof
        for(int i=0;i<right.size();i++){
            if(right.get(i) instanceof Playground){
                playground_count++;
                playground_length += right.get(i).get_length();
            }
        }
        
        //calculating ratio 
        float ratio = 100*playground_length/total_length_of_street;
        
        System.out.printf("\nTotal number of playground buildings : %d ",playground_count);
        System.out.printf("\nRatio of length of playgrounds : %f",ratio);
    
    }

    
    /**
    * Calculates the total length of street occupied by markets, houses or offices
    */
    public void occupied_length(){
        //total length of buildings 
        int lengthHouse = 0;
        int lengthOffice = 0;
        int lengthMarket = 0;
        
        //searching left side of street by using instanceof
        for(int i=0;i<left.size();i++){
            if(left.get(i) instanceof House) lengthHouse += left.get(i).get_length();
            else if(left.get(i) instanceof Office) lengthOffice += left.get(i).get_length();
            else if(left.get(i) instanceof Market) lengthMarket += left.get(i).get_length();
        }

        //searching right side of street by using instanceof
        for(int i=0;i<right.size();i++){
            if(right.get(i) instanceof House) lengthHouse += left.get(i).get_length();
            else if(right.get(i) instanceof Office) lengthOffice += left.get(i).get_length();
            else if(right.get(i) instanceof Market) lengthMarket += left.get(i).get_length();
        }

        System.out.printf("\nTotal length of street occupied by Houses : %d ",lengthHouse);
        System.out.printf("\nTotal length of street occupied by Market : %d ",lengthMarket);
        System.out.printf("\nTotal length of street occupied by Office : %d ",lengthOffice);
    }

    
    /**
    * Draws sillhoutte of street 
    */
    public void sillhouette(){
            
        int max_height = find_max_height();//max height of street to create 2D array
        //sillhoutte = new int[max_height+2][length_of_street+2];//creating 2D int array to draw sillhouute
        sillhouette = new ArrayList<ArrayList<Integer>>();
        
        for(int i=0;i<max_height+2;i++){
            sillhouette.add(new ArrayList<Integer>());
            for(int j=0;j<length_of_street+2;j++){
                sillhouette.get(i).add(j, 0);
            }
        }

        fill_sillhoutte(); //first fills sillhoutte with buildings
        mark_sillhouette(); //then decides and marks which indexes will be removed
        edit_sillhouette(); //removes marked index to print silhoutte like in PDF(inside of buildings are empty, only outlines of buildings)
        print_sillhouette(); //prints last version of 2D int array
    }

    
    /**
    * This method marks the indexes which is not outline index
    */
    private void mark_sillhouette(){
        //checks left right up and down of every index, if all direction of this index is 1 or 2(belongs to building) then this index is not outline
        for(int i=0;i<sillhouette.size();i++){
            for(int j=0;j<sillhouette.get(i).size();j++){
                if(sillhouette.get(i).get(j)==1){
                    if((sillhouette.get(i).get(j+1)==1||sillhouette.get(i).get(j+1)==2)&&
                        (sillhouette.get(i).get(j-1)==1||sillhouette.get(i).get(j-1)==2)&&
                        (sillhouette.get(i+1).get(j)==1||sillhouette.get(i+1).get(j)==2)&&
                        (sillhouette.get(i-1).get(j)==1||sillhouette.get(i-1).get(j)==2))
                    {
                        sillhouette.get(i).set(j,2); 
                    }
                }
                
            }
        } 
    }

    
    /**
    * Prints sillhouette of street
    */
    private void print_sillhouette(){
        System.out.printf("\n");
            for(int i=0;i<sillhouette.size();i++){
                for(int j=0;j<sillhouette.get(i).size();j++){
                    if(sillhouette.get(i).get(j)==1){
                        if(sillhouette.get(i).get(j-1)!=1&&sillhouette.get(i).get(j+1)!=1) System.out.printf("|"); //vertical walls are '|'
                        else System.out.printf("*"); //horizontal walls are '*'
                    }
                    else System.out.printf(" ");
                }
                System.out.printf("\n");
            }
            for(int i=0;i<sillhouette.get(0).size();i++){
                if(i%5==0) System.out.printf("%d",i);
                else System.out.printf(" ",i);
            }
            System.out.printf("\n");
            for(int i=0;i<sillhouette.get(0).size();i++)
                System.out.printf("_",i);
    }

    
    /**
    * Turns all marked indexes(2) into 0 for printing outlines
    */
    private void edit_sillhouette(){
        for(int i=0;i<sillhouette.size();i++)
            for(int j=0;j<sillhouette.get(i).size();j++)
                if(sillhouette.get(i).get(j)==2)sillhouette.get(i).set(j,0);
    }


    /**
    * Fills sillhouette with 0 and 1, ones are buildings, zeros are empty.
    */
    private void fill_sillhoutte(){
        
        //filling with 0
        for(int i=0;i<sillhouette.size();i++){
            for(int j=0;j<sillhouette.get(i).size();j++){
                sillhouette.get(i).set(j,0);
            }
        }
            
        //sets indexes that corresponds buildings with 1
        if(left!=null){
            for(int k=0;k<left.size();k++){
                for(int i=sillhouette.size()-2 ; i > sillhouette.size()-left.get(k).get_height()-2; i--){
                    for(int j=left.get(k).position+1; j<left.get(k).position + left.get(k).length+1;j++){
                        sillhouette.get(i).set(j,1);
                    }
                }
            }
        }
                   
        //sets indexes that corresponds buildings with 1                            
        if(right!=null)
            for(int k=0;k<right.size();k++)
                for(int i=sillhouette.size()-2 ; i>sillhouette.size()-right.get(k).get_height()-2; i--)
                    for(int j=right.get(k).position+1; j< right.get(k).position + right.get(k).length+1;j++)
                        sillhouette.get(i).set(j,1);    
        
    }

    
    /**
    * Find height of building with max height
    */
    private int find_max_height(){
        int max_height = 0;
            
        //first checks left side of street
        if(left!=null)
            for(int i=0;i<left.size();i++)
                if(max_height<left.get(i).get_height())
                    max_height = left.get(i).get_height();
        
                    //then checks right side of street
        if(right!=null)
            for(int i=0;i<right.size();i++)
                if(max_height<right.get(i).get_height())
                    max_height = right.get(i).get_height(); 
        
        return max_height; 
    
    }


    /**
    * Gets input from user, creates building and adds it into street
    * @throws Exception
    */
    public void add_building() throws Exception{
        //gets building type input from user
        System.out.printf("Enter building type: ");
        String building = input.nextLine();
        newBuilding = null;

        try{
            //finds build type according to user input and create building
            if(building.compareTo("house")==0) newBuilding = new House();  //choice is house
            else if(building.compareTo("market")==0) newBuilding = new Market(); //choice is house
            else if(building.compareTo("office")==0) newBuilding = new Office(); //choice is house
            else if(building.compareTo("playground")==0) newBuilding = new Playground(); //choice is house
            else throw new Exception("INVALID INPUT"); //choice is INVALID

            //user sets properties of building
            newBuilding.set_properties();
            
            //if choice is 1, user choosed left side of street, 2 is right
            if(choice==1 && find_space(left,newBuilding)) 
                add_process(newBuilding, left);
            else if(choice==2 && find_space(right,newBuilding)) 
                add_process(newBuilding, right);
        }
        catch(Exception e)
        {
            System.out.printf("%s",e.getMessage());
        }
    }

    
    /**
    * This method gets 2 parameter, building object and side choice
    * @param obj Building object which will be added
    * @param side Side choice 
    */
    public void add_building(Building obj,char side){    
        if(side=='l' && find_space(left,obj)) add_process(obj, left);
        else if(side=='r' && find_space(right,obj)) add_process(obj, right);
    } 

    
    /**
    * adding new Building into chosen side of street
    * @param newBuild New Building that will be added to street
    * @param street Array of Buildings that stores existing Buildings objects
    * @param control_array Helper int array to check availability of adding. It is 1D array with length of street
    */
    private void add_process(Building newBuilding, ArrayList<Building> street){          
        street.add(newBuilding);     
    }

    
    /**
    * @param street Helper int array that indicates occupied areas
    * @param build Building which will be added to street
    */
    private boolean find_space(ArrayList<Building> street, Building build){    
        
        //bound control
        if(build.get_position() + build.get_length() > length_of_street || build.get_position()<0)  return false;
        if(street == null) return true;

        for(int i=0;i<street.size();i++)
            if(build.get_position() >= street.get(i).get_position() && build.get_position() <= street.get(i).get_position() + street.get(i).get_length())
                return false;
        
        return true;     
    } 
    
    
    /**
    * @param l Length value which is taken from user
    */
    public void set_length_of_street(int l){
        length_of_street = l;
    }

    
    /**
    * Gets position input from user then passes it delete_building method
    * @throws Exception
    */
    public void get_delete_input() throws Exception{
        
        System.out.printf("\nEnter a position that belongs to building you want yo delete : ");
        
        try{
            chosen_position = input.nextInt();
            input.nextLine(); //to fix endline character issue
            delete_building(chosen_position, choice);
        }
        catch(InputMismatchException e){
            System.out.printf("%s",e.getMessage());
            input.nextLine();
        }
            
    }

    
    /**
    * @param chosen_position Position value given by user
    * Input doesn't have to be beginning position of building.
    * @throws Exception
    */
    public void delete_building(int chosen_position, int choice) throws Exception{
        index_of_deleted = -1;
            
            try{
                //first check bound of street
                if(chosen_position<0||chosen_position>length_of_street)
                    //throw new ArrayIndexOutOfBoundsException("Chosen position is INVALID");

                
                //search left side of street 
                if(choice==3){ 
                    for(int i=0;i<left.size();i++){
                        if(chosen_position>=left.get(i).get_position() && chosen_position<=left.get(i).get_position()+left.get(i).get_length()-1){
                            index_of_deleted = i;
                            delete_process(left);
                        }
                    }
                }
                
                //search right side of street 
                else if(choice==4){ 
                    for(int i=0;i<right.size();i++){
                        if(chosen_position>=right.get(i).get_position() && chosen_position<=right.get(i).get_position()+right.get(i).get_length()-1){
                            index_of_deleted = i;
                            delete_process(right);
                        }
                    }
                }
                else throw new Exception("CHOICE CAN BE 3 or 4");
                /*
                //if index_of_deleted is still -1, there is no building in that position
                if(index_of_deleted == -1) 
                    System.out.printf("\nTHIS POSITION IS UNOCCUPIED");
                else 
                    System.out.printf("\nBUILDING IS DELETED");
                */    
            }
            catch(Exception e){
                System.out.printf("%s",e.getMessage());
            }
    }


    /**
    * Function deletes building according to index_of_deleted value which is set before this method
    * And returns new left or right side of street array
    * @param street Side of street that we add building
    * @param control_array stores which areas are occupied 
    */
    private void delete_process(ArrayList<Building> street){  
        street.remove(index_of_deleted);             
    }

    
    /**
    * Calling focus methods of all buildings
    */
    public void street_focus(){
        for(int i=0;i<left.size();i++)
            left.get(i).focus();
        for(int i=0;i<right.size();i++)
            right.get(i).focus();                
    }



}
