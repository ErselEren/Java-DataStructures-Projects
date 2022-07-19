import java.util.ListIterator;

public class Driver {
    
    public static void test_AL_street() throws Exception{
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n===                                             ARRAYLIST STREET                                                    ===");
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n=======================================================================================================================");
        AL_Street AL_street = new AL_Street(100);
        
        // parameters are (position,length,height,owner,color,room_number)
        Building AL_house1 = new House(0,8,4,"ersel","red",30); 
        AL_street.add_building(AL_house1,'l');
        AL_street.view_mode(5);
        
        //this playground will not be added into street
        Building playground2 = new Playground(2,5);
        AL_street.add_building(playground2,'l');

        //house5 wont be added, because 130 is out of bound. Street length is 100
        Building house5 = new House(130,10,5,"henry","yellow",60);
        AL_street.add_building(house5, 'l');
        AL_street.view_mode(5);

        // -1 is not an valid position
        Building house6 = new House(-1,10,5,"henry","yellow",60);
        AL_street.add_building(house6, 'l');
        AL_street.view_mode(5);
        
        //valid building
        Building AL_market1 = new Market(4,16,9,"celal","08.00","21.00");
        AL_street.add_building(AL_market1,'r');
        AL_street.view_mode(5);

        //valid building
        Building AL_office1 = new Office(10,10,12);
        AL_street.add_building(AL_office1,'l');
        AL_street.view_mode(5);

        //valid building
        Building AL_house2 = new House(26,13,7,"eren","blue",17);
        AL_street.add_building(AL_house2,'r');
        AL_street.view_mode(5);

        //valid building
        Building AL_playground1 = new Playground(35,30);
        AL_street.add_building(AL_playground1,'l');
        AL_street.view_mode(5);

        //valid building
        Building AL_market2 = new Market(55,20,10,"mike","09.00","22.00");
        AL_street.add_building(AL_market2,'r');
        AL_street.view_mode(5);

        // First parameter is position, second is side. 3 is left, 4 is right
        AL_street.delete_building(5,3);
        AL_street.delete_building(5,4);
        AL_street.view_mode(5);

        //adding new building after delete
        Building AL_house3 = new House(2,9,5,"john","green",12);
        AL_street.add_building(AL_house3,'l');
        AL_street.view_mode(5);

        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");

        //method tests
        System.out.printf("\n>>>>CLONE TEST<<<<\n");
        House AL_h3 = (House)AL_house3;
        House AL_h4 = (House)AL_h3.clone();
        System.out.printf("\n--->Before changing fields<---");
        System.out.printf("\nHouse3|Owner -> %s , Room Number -> %d",AL_h3.get_owner(),AL_h3.get_room_number());
        System.out.printf("\nHouse4|Owner -> %s , Room Number -> %d",AL_h4.get_owner(),AL_h4.get_room_number());
        AL_h4.set_room_number(33);
        System.out.printf("\n\n--->After changing room-number field<---");
        System.out.printf("\nHouse3|Owner -> %s , Room Number -> %d",AL_h3.get_owner(),AL_h3.get_room_number());
        System.out.printf("\nHouse4|Owner -> %s , Room Number -> %d",AL_h4.get_owner(),AL_h4.get_room_number());


        System.out.printf("\n\n>>>>EQUALS TEST<<<<\n");
        if(AL_h3.equals(AL_h4)==true){
            System.out.printf("H3 and H4 are equal");
        }
        else{
            System.out.printf("H3 and H4 is not equal");
        }
        System.out.printf("\nH3 hashcode : %d",AL_h3.hashCode());
        System.out.printf("\nH4 hashcode : %d",AL_h4.hashCode());
        
        System.out.printf("\n-------------------------------\n");
        
        if(AL_market1.equals(AL_market2)==true){
            System.out.printf("Market1 and Market2 are equal");
        }
        else{
            System.out.printf("Market1 and Market2 is not equal");
        }
        System.out.printf("\nMarket1 hashcode : %d",AL_market1.hashCode());
        System.out.printf("\nMarket2 hashcode : %d",AL_market2.hashCode());
        
        System.out.printf("\n-------------------------------\n");
        
        Office AL_office2 = (Office)AL_office1.clone();
        if(AL_office1.equals(AL_office2)==true){
            System.out.printf("Office1 and Office2 are equal");
        }
        else{
            System.out.printf("Office1 and Office2 is not equal");
        }
        System.out.printf("\nMarket1 hashcode : %d",AL_office1.hashCode());
        System.out.printf("\nMarket2 hashcode : %d",AL_office2.hashCode());

        System.out.printf("\n-------------------------------\n");

        
        
        System.out.printf("\n\n>>>> toString TEST <<<<");
        System.out.printf("\n%s",AL_h3);
        System.out.printf("\n%s",AL_market1);
        System.out.printf("\n%s",AL_office1);
        System.out.printf("\n%s",AL_playground1);



        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");

        //calling view mode methods one by one
        System.out.printf("\n");
        AL_street.view_mode(1);
        System.out.printf("\n");
        AL_street.view_mode(2);
        System.out.printf("\n");
        AL_street.view_mode(3);
        System.out.printf("\n");
        AL_street.view_mode(4);
        System.out.printf("\n");
        AL_street.view_mode(6);
        System.out.printf("\n");

        
        System.out.printf("\n===-------------------------------------------END OF ARRAYLIST STREET-----------------------------------------------===\n\n\n\n");
    }

    public static void test_street() throws Exception{
        //Street street = new Street(input.nextInt());
        Street street = new Street(100);
          
        //street.add_building(house1,'l'); first parameter is obj, second is side. 'l' for left, 'r' for right.

        // parameters are (position,length,height,owner,color,room_number)
        Building house1 = new House(0,8,4,"ersel","red",30); 
        street.add_building(house1,'l');
        street.view_mode(5);
        
        //this playground will not be added into street
        Building playground2 = new Playground(2,5);
        street.add_building(playground2,'l');

        //house5 wont be added, because 130 is out of bound. Street length is 100
        Building house5 = new House(130,10,5,"henry","yellow",60);
        street.add_building(house5, 'l');
        street.view_mode(5);

        // -1 is not an valid position
        Building house6 = new House(-1,10,5,"henry","yellow",60);
        street.add_building(house6, 'l');
        street.view_mode(5);


        //valid building
        Building market1 = new Market(4,16,9,"celal","08.00","21.00");
        street.add_building(market1,'r');
        street.view_mode(5);

        //valid building
        Building office1 = new Office(10,10,12);
        street.add_building(office1,'l');
        street.view_mode(5);

        //valid building
        Building house2 = new House(26,13,7,"eren","blue",17);
        street.add_building(house2,'r');
        street.view_mode(5);

        //valid building
        Building playground1 = new Playground(35,30);
        street.add_building(playground1,'l');
        street.view_mode(5);

        //valid building
        Building market2 = new Market(55,20,10,"mike","09.00","22.00");
        street.add_building(market2,'r');
        street.view_mode(5);

        // First parameter is position, second is side. 3 is left, 4 is right
        street.delete_building(5,3);
        street.delete_building(5,4);
        street.view_mode(5);

        //adding new building after delete
        Building house3 = new House(2,9,5,"john","green",12);
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
    }
    
    public static void test_LL_street() throws Exception{
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n===                                             LINKEDLIST STREET                                                   ===");
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n=======================================================================================================================");

        LL_Street LL_street = new LL_Street(100);
          
         
        //street.add_building(house1,'l'); first parameter is obj, second is side. 'l' for left, 'r' for right.

        // parameters are (position,length,height,owner,color,room_number)
        Building LL_house1 = new House(0,8,4,"ersel","red",30); 
        LL_street.add_building(LL_house1,'l');
        LL_street.view_mode(5);
        
        //this playground will not be added into street
        Building playground2 = new Playground(2,5);
        LL_street.add_building(playground2,'l');

        //house5 wont be added, because 130 is out of bound. Street length is 100
        Building house5 = new House(130,10,5,"henry","yellow",60);
        LL_street.add_building(house5, 'l');
        LL_street.view_mode(5);

        // -1 is not an valid position
        Building house6 = new House(-1,10,5,"henry","yellow",60);
        LL_street.add_building(house6, 'l');
        LL_street.view_mode(5);

        //valid building
        Building LL_market1 = new Market(4,16,9,"celal","08.00","21.00");
        LL_street.add_building(LL_market1,'r');
        LL_street.view_mode(5);

        //valid building
        Building LL_office1 = new Office(10,10,12);
        LL_street.add_building(LL_office1,'l');
        LL_street.view_mode(5);

        //valid building
        Building LL_house2 = new House(26,13,7,"eren","blue",17);
        LL_street.add_building(LL_house2,'r');
        LL_street.view_mode(5);

        //valid building
        Building LL_playground1 = new Playground(35,30);
        LL_street.add_building(LL_playground1,'l');
        LL_street.view_mode(5);

        //valid building
        Building LL_market2 = new Market(55,20,10,"mike","09.00","22.00");
        LL_street.add_building(LL_market2,'r');
        LL_street.view_mode(5);

        // First parameter is position, second is side. 3 is left, 4 is right
        LL_street.delete_building(5,3);
        LL_street.delete_building(5,4);
        LL_street.view_mode(5);

        //adding new building after delete
        Building LL_house3 = new House(2,9,5,"john","green",12);
        LL_street.add_building(LL_house3,'l');
        LL_street.view_mode(5);

        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");

        //method tests
        System.out.printf("\n>>>>CLONE TEST<<<<\n");
        House LL_h3 = (House)LL_house3;
        House LL_h4 = (House)LL_h3.clone();
        System.out.printf("\n--->Before changing fields<---");
        System.out.printf("\nHouse3|Owner -> %s , Room Number -> %d",LL_h3.get_owner(),LL_h3.get_room_number());
        System.out.printf("\nHouse4|Owner -> %s , Room Number -> %d",LL_h4.get_owner(),LL_h4.get_room_number());
        LL_h4.set_room_number(33);
        System.out.printf("\n\n--->After changing room-number field<---");
        System.out.printf("\nHouse3|Owner -> %s , Room Number -> %d",LL_h3.get_owner(),LL_h3.get_room_number());
        System.out.printf("\nHouse4|Owner -> %s , Room Number -> %d",LL_h4.get_owner(),LL_h4.get_room_number());


        System.out.printf("\n\n>>>>EQUALS TEST<<<<\n");
        if(LL_h3.equals(LL_h4)==true){
            System.out.printf("H3 and H4 are equal");
        }
        else{
            System.out.printf("H3 and H4 is not equal");
        }
        System.out.printf("\nH3 hashcode : %d",LL_h3.hashCode());
        System.out.printf("\nH4 hashcode : %d",LL_h4.hashCode());
        
        System.out.printf("\n-------------------------------\n");
        
        if(LL_market1.equals(LL_market2)==true){
            System.out.printf("Market1 and Market2 are equal");
        }
        else{
            System.out.printf("Market1 and Market2 is not equal");
        }
        System.out.printf("\nMarket1 hashcode : %d",LL_market1.hashCode());
        System.out.printf("\nMarket2 hashcode : %d",LL_market2.hashCode());
        
        System.out.printf("\n-------------------------------\n");
        
        Office LL_office2 = (Office)LL_office1.clone();
        if(LL_office1.equals(LL_office2)==true){
            System.out.printf("Office1 and Office2 are equal");
        }
        else{
            System.out.printf("Office1 and Office2 is not equal");
        }
        System.out.printf("\nMarket1 hashcode : %d",LL_office1.hashCode());
        System.out.printf("\nMarket2 hashcode : %d",LL_office2.hashCode());

        System.out.printf("\n-------------------------------\n");

        
        
        System.out.printf("\n\n>>>> toString TEST <<<<");
        System.out.printf("\n%s",LL_h3);
        System.out.printf("\n%s",LL_market1);
        System.out.printf("\n%s",LL_office1);
        System.out.printf("\n%s",LL_playground1);



        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");

        //calling view mode methods one by one
        System.out.printf("\n");
        LL_street.view_mode(1);
        System.out.printf("\n");
        LL_street.view_mode(2);
        System.out.printf("\n");
        LL_street.view_mode(3);
        System.out.printf("\n");
        LL_street.view_mode(4);
        System.out.printf("\n");
        LL_street.view_mode(6);
        System.out.printf("\n");

        System.out.printf("\n===-------------------------------------------END OF LINKEDLIST STREET----------------------------------------------===\n\n\n\n\n");
    }
    
    public static void test_LD_street() throws Exception{
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n===                                             LD_LINKEDLIST STREET                                                ===");
        System.out.printf("\n=======================================================================================================================");
        System.out.printf("\n=======================================================================================================================");

        LD_Street LD_street = new LD_Street(100);
          
         
        //street.add_building(house1,'l'); first parameter is obj, second is side. 'l' for left, 'r' for right.

        // parameters are (position,length,height,owner,color,room_number)
        Building LD_house1 = new House(0,8,4,"ersel","red",30); 
        LD_street.add_building(LD_house1,'l');
        LD_street.view_mode(5);
        
        //this playground will not be added into street
        Building playground2 = new Playground(2,5);
        LD_street.add_building(playground2,'l');

        //house5 wont be added, because 130 is out of bound. Street length is 100
        Building house5 = new House(130,10,5,"henry","yellow",60);
        LD_street.add_building(house5, 'l');
        LD_street.view_mode(5);

        // -1 is not an valid position
        Building house6 = new House(-1,10,5,"henry","yellow",60);
        LD_street.add_building(house6, 'l');
        LD_street.view_mode(5);

        //valid building
        Building LD_market1 = new Market(4,16,9,"celal","08.00","21.00");
        LD_street.add_building(LD_market1,'r');
        LD_street.view_mode(5);

        //valid building
        Building LD_office1 = new Office(10,10,12);
        LD_street.add_building(LD_office1,'l');
        LD_street.view_mode(5);

        //valid building
        Building LD_house2 = new House(26,13,7,"eren","blue",17);
        LD_street.add_building(LD_house2,'r');
        LD_street.view_mode(5);

        //valid building
        Building LD_playground1 = new Playground(35,30);
        LD_street.add_building(LD_playground1,'l');
        LD_street.view_mode(5);

        //valid building
        Building LD_market2 = new Market(55,20,10,"mike","09.00","22.00");
        LD_street.add_building(LD_market2,'r');
        LD_street.view_mode(5);

        // First parameter is position, second is side. 3 is left, 4 is right
        LD_street.delete_building(5,3);
        LD_street.delete_building(5,4);
        LD_street.view_mode(5);

        //adding new building after delete
        Building LD_house3 = new House(2,9,5,"john","green",12);
        LD_street.add_building(LD_house3,'l');
        LD_street.view_mode(5);

        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");

        //method tests
        System.out.printf("\n>>>>CLONE TEST<<<<\n");
        House LD_h3 = (House)LD_house3;
        House LD_h4 = (House)LD_h3.clone();
        System.out.printf("\n--->Before changing fields<---");
        System.out.printf("\nHouse3|Owner -> %s , Room Number -> %d",LD_h3.get_owner(),LD_h3.get_room_number());
        System.out.printf("\nHouse4|Owner -> %s , Room Number -> %d",LD_h4.get_owner(),LD_h4.get_room_number());
        LD_h4.set_room_number(33);
        System.out.printf("\n\n--->After changing room-number field<---");
        System.out.printf("\nHouse3|Owner -> %s , Room Number -> %d",LD_h3.get_owner(),LD_h3.get_room_number());
        System.out.printf("\nHouse4|Owner -> %s , Room Number -> %d",LD_h4.get_owner(),LD_h4.get_room_number());


        System.out.printf("\n\n>>>>EQUALS TEST<<<<\n");
        if(LD_h3.equals(LD_h4)==true){
            System.out.printf("H3 and H4 are equal");
        }
        else{
            System.out.printf("H3 and H4 is not equal");
        }
        System.out.printf("\nH3 hashcode : %d",LD_h3.hashCode());
        System.out.printf("\nH4 hashcode : %d",LD_h4.hashCode());
        
        System.out.printf("\n-------------------------------\n");
        
        if(LD_market1.equals(LD_market2)==true){
            System.out.printf("Market1 and Market2 are equal");
        }
        else{
            System.out.printf("Market1 and Market2 is not equal");
        }
        System.out.printf("\nMarket1 hashcode : %d",LD_market1.hashCode());
        System.out.printf("\nMarket2 hashcode : %d",LD_market2.hashCode());
        
        System.out.printf("\n-------------------------------\n");
        
        Office LD_office2 = (Office)LD_office1.clone();
        if(LD_office1.equals(LD_office2)==true){
            System.out.printf("Office1 and Office2 are equal");
        }
        else{
            System.out.printf("Office1 and Office2 is not equal");
        }
        System.out.printf("\nMarket1 hashcode : %d",LD_office1.hashCode());
        System.out.printf("\nMarket2 hashcode : %d",LD_office2.hashCode());

        System.out.printf("\n-------------------------------\n");

        
        
        System.out.printf("\n\n>>>> toString TEST <<<<");
        System.out.printf("\n%s",LD_h3);
        System.out.printf("\n%s",LD_market1);
        System.out.printf("\n%s",LD_office1);
        System.out.printf("\n%s",LD_playground1);



        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");
        System.out.printf("\n--------------------------------------");

        //calling view mode methods one by one
        System.out.printf("\n");
        LD_street.view_mode(1);
        System.out.printf("\n");
        LD_street.view_mode(2);
        System.out.printf("\n");
        LD_street.view_mode(3);
        System.out.printf("\n");
        LD_street.view_mode(4);
        System.out.printf("\n");
        LD_street.view_mode(6);
        System.out.printf("\n");
        System.out.printf("\n===----------------------------------------END OF LD_LINKEDLIST STREET------------------------------------------===\n\n\n\n");
    
    }
    
    /**
     * in this method, list that I implemented myself "LDLinkedList" is tested independently from Street 
     * Adding and removing Building nodes and iterating through iterator class that I implemented again myself "LDListIterator"  
     */
    public static void test_LDLinkedList(){
        LDLinkedList<Building> LD_building_list = new LDLinkedList<>();
        ListIterator<Building> iterator;

        //creating nodes for list. 
        //first three parameters are common(position, length, height)
        Building LD_house1 = new House(0,8,4,"ersel","red",30);
        Building LD_market1 = new Market(4,16,9,"celal","08.00","21.00");
        Building LD_office1 = new Office(10,10,12);
        Building AL_house2 = new House(26,13,7,"eren","blue",17);
        Building AL_playground1 = new Playground(35,30);
        Building AL_house3 = new House(2,9,5,"john","green",12);

        //adding Building nodes to LD_LinkedList
        //testing add(), addFirst() and addLast() 
        LD_building_list.add(LD_house1);
        LD_building_list.add(LD_market1);
        LD_building_list.addFirst(LD_office1);
        LD_building_list.addFirst(AL_house2);
        LD_building_list.addLast(AL_playground1);
        LD_building_list.addLast(AL_house3);

        System.out.printf("\n\n===---Printing from head to tail---===");
        //listIterator() returns my own implemented iterator class LDListIterator
        iterator = LD_building_list.listIterator();
        while(iterator.hasNext()){ 
            //testing hasNext() and next(), printing positions of added buildings
            System.out.printf("\nPosition : %d",iterator.next().get_position());
        }


        System.out.printf("\n\n===---Printing from tail to head---===");
        while(iterator.hasPrevious()){
            System.out.printf("\nPosition : %d",iterator.previous().get_position());
        }

        

        //Because iterator is at the first position, first element will be removed
        iterator.remove();

        System.out.printf("\n\n===---Printing from head to tail after first element is deleted---===");
        iterator = LD_building_list.listIterator();
        while(iterator.hasNext()){ 
            System.out.printf("\nPosition : %d",iterator.next().get_position());
        }


        //adding removed element AL_house2 
        LD_building_list.add(1,AL_house2);
        iterator = LD_building_list.listIterator();
        while(iterator.hasNext()){ 
            System.out.printf("\nPosition : %d",iterator.next().get_position());
        }
        System.out.printf("\n---------------------------------------");

       

        //iterator indicates last item, then we set last item with AL_playground2
        Building AL_playground2 = new Playground(49,30);
        iterator.set(AL_playground2);
        System.out.printf("\n\n===---Printing from head to tail after set() method---===");
        iterator = LD_building_list.listIterator();
        while(iterator.hasNext()){ 
            System.out.printf("\nPosition : %d",iterator.next().get_position());
        }


        //printing list with .get(index) method
        System.out.printf("\n\n===---Printing from index 0 to size by using get() method---===");
        for(int i=0;i<LD_building_list.size();i++){
            System.out.printf("\nPosition : %d",LD_building_list.get(i).position);
        }

        System.out.printf("\n\nFirst item : %d",LD_building_list.getFirst().position);
        System.out.printf("\nLast item : %d",LD_building_list.getLast().position);

        LD_building_list.remove(1); //removing index=1 

        //printing list with .get(index) method
        System.out.printf("\n\n===---Printing from index 0 to size after -> index=1 is removed---===");
        for(int i=0;i<LD_building_list.size();i++){
            System.out.printf("\nPosition : %d",LD_building_list.get(i).position);
        }


        System.out.printf("\n\n\n");
    }
    
    public static void main(String[] args) throws Exception{
        
        test_LL_street();
        test_LD_street();
        test_LDLinkedList();
        test_street();
        
    }
        
}
