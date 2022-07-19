import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Driver {
    
    public static class Street{
        protected Buildings[] left; //stores buildings of left side of street
        protected Buildings[] right; //stores buildings of right side of street
        private int[][] sillhoutte; //2D int array to draw sillhouette
        private int length_of_street; //length value of street
        private int choice; //user input
        private int[] left_control_array; //helper array for add and delete
        private int[] right_control_array; //helper array for add and delete
        private Scanner input = new Scanner(System.in); //Scanner object to read input from user
        private int index_of_deleted; //Index of building that will be deleted
        private Buildings newBuild; //Building reference for adding new building
        private int chosen_position;
        
        /**
         * Constructor with non-parameter 
         * Assing 100 unit as default
         */
        Street(){
            length_of_street = 100;
            left_control_array = new int[length_of_street];
            right_control_array = new int[length_of_street];
            Arrays.fill(left_control_array,0);
            Arrays.fill(right_control_array,0);
        }
        

        /**
         * Constructor with an integer parameter 
         * Assing l value for street length
         */
        Street(int l){
            length_of_street = l;
            left_control_array = new int[length_of_street];
            right_control_array = new int[length_of_street];
            Arrays.fill(left_control_array,0);
            Arrays.fill(right_control_array,0);
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
            int left_remaining = left_control_array.length;
            int right_remaining = right_control_array.length;
             
            for(int i=0;i<left.length;i++) //subtracting length of every building from length of street
                left_remaining-=left[i].get_length();
            System.out.printf("\nRemaining lands on the left side : %d",left_remaining); //remaining length in left side of street

            for(int i=0;i<right.length;i++)
                right_remaining-=right[i].get_length();
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
            for(int i=0;i<left.length;i++){
                if(left[i] instanceof House) house++;
                else if(left[i] instanceof Office) office++;
                else if(left[i] instanceof Market) market++;
                else if(left[i] instanceof Playgrounds) playground++;
            }

            for(int i=0;i<right.length;i++){
                if(right[i] instanceof House) house++;
                else if(right[i] instanceof Office) office++;
                else if(right[i] instanceof Market) market++;
                else if(right[i] instanceof Playgrounds) playground++;
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
            for(int i=0;i<left.length;i++){
                if(left[i] instanceof Playgrounds){
                    playground_count++;
                    playground_length += left[i].get_length();
                }
            }
            
            //counting playground buildings by using instanceof
            for(int i=0;i<right.length;i++){
                if(right[i] instanceof Playgrounds){
                    playground_count++;
                    playground_length += right[i].get_length();
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
            for(int i=0;i<left.length;i++){
                if(left[i] instanceof House) lengthHouse += left[i].get_length();
                else if(left[i] instanceof Office) lengthOffice += left[i].get_length();
                else if(left[i] instanceof Market) lengthMarket += left[i].get_length();
            }

            //searching right side of street by using instanceof
            for(int i=0;i<right.length;i++){
                if(right[i] instanceof House) lengthHouse += left[i].get_length();
                else if(right[i] instanceof Office) lengthOffice += left[i].get_length();
                else if(right[i] instanceof Market) lengthMarket += left[i].get_length();
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
            sillhoutte = new int[max_height+2][length_of_street+2];//creating 2D int array to draw sillhouute
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
            for(int i=0;i<sillhoutte.length;i++){
               for(int j=0;j<sillhoutte[i].length;j++){
                   if(sillhoutte[i][j]==1){
                        if((sillhoutte[i][j+1]==1||sillhoutte[i][j+1]==2)&&
                           (sillhoutte[i][j-1]==1||sillhoutte[i][j-1]==2)&&
                           (sillhoutte[i+1][j]==1||sillhoutte[i+1][j]==2)&&
                           (sillhoutte[i-1][j]==1||sillhoutte[i-1][j]==2))
                        {
                           sillhoutte[i][j]=2; 
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
            for(int i=0;i<sillhoutte.length;i++){
                for(int j=0;j<sillhoutte[i].length;j++){
                    if(sillhoutte[i][j]==1){
                        if(sillhoutte[i][j-1]!=1&&sillhoutte[i][j+1]!=1) System.out.printf("|"); //vertical walls are '|'
                        else System.out.printf("*"); //horizontal walls are '*'
                    }
                    else System.out.printf(" ");
                }
                System.out.printf("\n");
            }
            for(int i=0;i<sillhoutte[0].length;i++){
                if(i%5==0) System.out.printf("%d",i);
                else System.out.printf(" ",i);
            }
            System.out.printf("\n");
            for(int i=0;i<sillhoutte[0].length;i++)
                System.out.printf("_",i);
            
        }

        /**
         * Turns all marked indexes(2) into 0 for printing outlines
         */
        private void edit_sillhouette(){
            for(int i=0;i<sillhoutte.length;i++)
                for(int j=0;j<sillhoutte[i].length;j++)
                    if(sillhoutte[i][j]==2) sillhoutte[i][j] = 0;
        }

        /**
         * Fills sillhouette with 0 and 1, ones are buildings, zeros are empty.
         */
        private void fill_sillhoutte(){
            //filling with 0
            for(int i=0;i<sillhoutte.length;i++)
                for(int j=0;j<sillhoutte[i].length;j++)
                    sillhoutte[i][j] = 0;
            
            //sets indexes that corresponds buildings with 1
            if(left!=null)
                for(int k=0;k<left.length;k++)
                    for(int i=sillhoutte.length-2 ; i>sillhoutte.length-left[k].get_height()-2; i--)
                        for(int j=left[k].position+1; j<left[k].position+left[k].length+1;j++)
                            sillhoutte[i][j]=1;
            
            //sets indexes that corresponds buildings with 1                            
            if(right!=null)
                for(int k=0;k<right.length;k++)
                    for(int i=sillhoutte.length-2 ; i>sillhoutte.length-right[k].get_height()-2; i--)
                        for(int j=right[k].position+1; j<right[k].position+right[k].length+1;j++)
                            sillhoutte[i][j]=1; 
                                             
        }

        /**
         * Find height of building with max height
         */
        private int find_max_height(){
            int max_height = 0;
            
            //first checks left side of street
            if(left!=null)
                for(int i=0;i<left.length;i++)
                    if(max_height<left[i].get_height())
                        max_height = left[i].get_height();
            
                        //then checks right side of street
            if(right!=null)
                for(int i=0;i<right.length;i++)
                    if(max_height<right[i].get_height())
                        max_height = right[i].get_height(); 
            
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
            
            newBuild = null;
            try{
                //finds build type according to user input and create building
                if(building.compareTo("house")==0) newBuild = new House();
                else if(building.compareTo("market")==0) newBuild = new Market();
                else if(building.compareTo("office")==0) newBuild = new Office();
                else if(building.compareTo("playground")==0) newBuild = new Playgrounds();
                else throw new Exception("INVALID INPUT");

                //user sets properties of building
                newBuild.set_properties();
                
                //if choice is 1, user choosed left side of street, 2 is right
                if(choice==1 && find_space(left_control_array,newBuild)) left = add_process(newBuild, left, left_control_array);
                else if(choice==2 && find_space(right_control_array,newBuild)) right = add_process(newBuild, right, right_control_array);
            }catch(Exception e){
                System.out.printf("%s",e.getMessage());
            }
            
        }

        /**
         * adding new Building into chosen side of street
         * @param newBuild New Building that will be added to street
         * @param street Array of Buildings that stores existing Buildings objects
         * @param control_array Helper int array to check availability of adding. It is 1D array with length of street
         */
        private Buildings[] add_process(Buildings newBuild, Buildings[] street, int[] control_array){
            Buildings[] temp;
            
            //if street is null control
            if(street == null){
                temp = new Buildings[1];
                temp[0] = newBuild;
            }
            else{ //passing street to temp 
                temp = new Buildings[street.length+1];
                for(int i=0;i<street.length;i++)
                    temp[i] = street[i];
                    
                temp[street.length] = newBuild;
            }

            //Marking helper control array according to added building
            for(int i=0;i<temp.length;i++)
                for(int j=0;j<temp[i].length;j++)
                    control_array[ j+temp[i].position ] = 1;
            
            return temp;        
        }

        /**
         * This method gets 2 parameter, building object and side choice
         * @param obj Building object which will be added
         * @param side Side choice 
         */
        public void add_building(Buildings obj,char side){
            if(side=='l' && find_space(left_control_array,obj)) left = add_process(obj, left, left_control_array);
            else if(side=='r' && find_space(right_control_array,obj)) right = add_process(obj, right, right_control_array);
        }



        /**
         * @param street Helper int array that indicates occupied areas
         * @param build Building which will be added to street
         */
        private boolean find_space(int[] street, Buildings build){
            
            //bound control
            if(build.get_position() + build.get_length() > length_of_street || build.get_position()<0)  return false;

            //if point from position and positon+length of building is 1, it means that area is belongs to another building
            for(int i=build.get_position() ; i<build.get_position()+build.get_length() ; i++)
                if(street[i]==1)
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
                System.out.printf("BURADA %s",e.getMessage());
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
                throw new ArrayIndexOutOfBoundsException("Chosen position is INVALID");

                if(choice==3){ //search left side of street 
                    for(int i=0;i<left.length;i++){
                        if(chosen_position>=left[i].get_position() && chosen_position<=left[i].get_position()+left[i].get_length()-1){
                            index_of_deleted = i;
                            left = delete_process(left,left_control_array);
                        }
                    }
                }
                else if(choice==4){ //search left side of street 
                    for(int i=0;i<right.length;i++){
                        if(chosen_position>=right[i].get_position() && chosen_position<=right[i].get_position()+right[i].get_length()-1){
                            index_of_deleted = i;
                            right = delete_process(right,right_control_array);
                        }
                    }
                }
                else throw new Exception("CHOICE CAN BE 3 or 4");

                //if index_of_deleted is still -1, there is no building in that position
                if(index_of_deleted == -1) System.out.printf("\nTHIS POSITION IS UNOCCUPIED");
                else System.out.printf("\nBUILDING IS DELETED");
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
        private Buildings[] delete_process(Buildings[] street, int[] control_array){
            Buildings[] temp = new Buildings[street.length-1];
            for(int i=0, j=0;i<street.length;i++)
                if(i!=index_of_deleted)
                    temp[j++] = street[i];
            
            for(int i=street[index_of_deleted].get_position();i<street[index_of_deleted].get_position()+street[index_of_deleted].get_length();i++)
                control_array[i] = 0;
            
            return temp;
        }
        
        /**
         * Calling focus methods of all buildings
         */
        public void street_focus(){
            for(int i=0;i<left.length;i++)
                left[i].focus();
            for(int i=0;i<right.length;i++)
                right[i].focus();                
        }
    
    
    }
    
    
    
    public static void main(String[] args) throws Exception{
        
        Scanner input = new Scanner(System.in);
        System.out.printf("\nEnter length of the street : ");
        //Street street = new Street(input.nextInt());
        Street street = new Street(100);
          
         
        //street.add_building(house1,'l'); first parameter is obj, second is side. 'l' for left, 'r' for right.

        // parameters are (position,length,height,owner,color,room_number)
        Buildings house1 = new House(0,8,4,"ersel","red",30); 
        street.add_building(house1,'l');
        street.view_mode(5);
        
        //this playground will not be added into street
        Buildings playground2 = new Playgrounds(2,5);
        street.add_building(playground2,'l');

        //house5 wont be added, because 130 is out of bound. Street length is 100
        Buildings house5 = new House(130,10,5,"henry","yellow",60);
        street.add_building(house5, 'l');
        street.view_mode(5);

        // -1 is not an valid position
        Buildings house6 = new House(-1,10,5,"henry","yellow",60);
        street.add_building(house6, 'l');
        street.view_mode(5);

        //valid building
        Buildings market1 = new Market(4,16,9,"celal","08.00","21.00");
        street.add_building(market1,'r');
        street.view_mode(5);

        //valid building
        Buildings office1 = new Office(10,10,12);
        street.add_building(office1,'l');
        street.view_mode(5);

        //valid building
        Buildings house2 = new House(26,13,7,"eren","blue",17);
        street.add_building(house2,'r');
        street.view_mode(5);

        //valid building
        Buildings playground1 = new Playgrounds(35,30);
        street.add_building(playground1,'l');
        street.view_mode(5);

        //valid building
        Buildings market2 = new Market(55,20,10,"mike","09.00","22.00");
        street.add_building(market2,'r');
        street.view_mode(5);

        // First parameter is position, second is side. 3 is left, 4 is right
        street.delete_building(5,3);
        street.delete_building(5,4);
        street.view_mode(5);

        //adding new building after delete
        Buildings house3 = new House(2,9,5,"john","green",12);
        street.add_building(house3,'l');
        street.view_mode(5);

        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");

        //method tests
        System.out.printf("\n>>>>CLONE TEST<<<<\n");
        House h3 = (House)house3;
        House h4 = (House)h3.clone();
        System.out.printf("\n--->Before changing fields<---");
        System.out.printf("\nHouse3|Owner -> %s , Room Number -> %d",h3.get_owner(),h3.get_room_number());
        System.out.printf("\nHouse4|Owner -> %s , Room Number -> %d",h4.get_owner(),h4.get_room_number());
        h4.set_room_number(33);
        System.out.printf("\n\n--->After changing room-number field<---");
        System.out.printf("\nHouse3|Owner -> %s , Room Number -> %d",h3.get_owner(),h3.get_room_number());
        System.out.printf("\nHouse4|Owner -> %s , Room Number -> %d",h4.get_owner(),h4.get_room_number());


        System.out.printf("\n\n>>>>EQUALS TEST<<<<\n");
        if(h3.equals(h4)==true){
            System.out.printf("H3 and H4 are equal");
        }
        else{
            System.out.printf("H3 and H4 is not equal");
        }
        System.out.printf("\nH3 hashcode : %d",h3.hashCode());
        System.out.printf("\nH4 hashcode : %d",h4.hashCode());
        
        System.out.printf("\n-------------------------------\n");
        
        if(market1.equals(market2)==true){
            System.out.printf("Market1 and Market2 are equal");
        }
        else{
            System.out.printf("Market1 and Market2 is not equal");
        }
        System.out.printf("\nMarket1 hashcode : %d",market1.hashCode());
        System.out.printf("\nMarket2 hashcode : %d",market2.hashCode());
        
        System.out.printf("\n-------------------------------\n");
        
        Office office2 = (Office)office1.clone();
        if(office1.equals(office2)==true){
            System.out.printf("Office1 and Office2 are equal");
        }
        else{
            System.out.printf("Office1 and Office2 is not equal");
        }
        System.out.printf("\nMarket1 hashcode : %d",office1.hashCode());
        System.out.printf("\nMarket2 hashcode : %d",office2.hashCode());

        System.out.printf("\n-------------------------------\n");

        
        
        System.out.printf("\n\n>>>> toString TEST <<<<");
        System.out.printf("\n%s",h3);
        System.out.printf("\n%s",market1);
        System.out.printf("\n%s",office1);
        System.out.printf("\n%s",playground1);



        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");

        //calling view mode methods one by one
        System.out.printf("\n");
        street.view_mode(1);
        System.out.printf("\n");
        street.view_mode(2);
        System.out.printf("\n");
        street.view_mode(3);
        System.out.printf("\n");
        street.view_mode(4);
        System.out.printf("\n");
        street.view_mode(6);
        System.out.printf("\n");



        while(true){
            System.out.printf("\n\n\n1-Edit mode\n2-View mode\n3-Terminate Program\n");
            int choice = input.nextInt();
            if(choice==1) street.menu_edit_street();
            else if(choice==2) street.menu_view_mode();
            else if(choice==3) System.exit(0);
        }

    
    }
}
