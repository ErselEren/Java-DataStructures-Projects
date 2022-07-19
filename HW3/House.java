import java.util.InputMismatchException;

/**
* House class that takes inherit from Buildings superclass
*/
public class House extends Building{
    
    //Properties of House class
    private int room_number;
    private String color;
    private String owner;
    
    /**
    * No parameter House constructor method
    * Sets position 0, length 1, height 1, room number 1 and  "unknown" for owner and color fields
    */
    House(){
        super(0,1,1);
        room_number = 1;
        owner = new String("unknown");
        color = new String("unknown");
    }
    
    /**
    * 3 parameter Constructor method of House
    * Sets position 0, length 1, and height 1 
    * @param r Room number of building
    * @param o Owner of building
    * @param c Color of building
    */
    House(int r,String c, String o){
        super(0,1,1);
        room_number = r;
        owner = o;
        color = c;
    }

    /**
    * Constructor method of House class 
    * @param p Position of building
    * @param l Length of building
    * @param h Height of building
    * @param o Owner of building
    * @param c Color of building
    * @param r Room number of building
    */
    House(int p, int l, int h, String o, String c, int r){
        super(p,l,h);
        owner = o;
        color = c;
        room_number = r;
    }

    /**
    * @param num Number of rooms
    */
    public void set_room_number(int num){
        room_number = num;
    }

    /**
    * @param c Color of building
    */
    public void set_color(String c){
        color = c;
    }

    /**
    * @param o Owner of building
    */
    public void set_owner(String o){
        owner = o;
    }

    /**
    * Returns owner of building
    */
    public String get_owner(){
        return owner;
    }

    /**
    * First sets common properties which belongs to Building superclass, than gets input specially for House
    */
    @Override
    protected void set_properties(){
        set_position_length_height();
        try{
            System.out.printf("\nEnter |owner|");
            set_owner(input.nextLine());
            System.out.printf("\nEnter |color|");
            set_color(input.nextLine());
            System.out.printf("\nEnter |room_number|");
            set_room_number(input.nextInt());
            input.nextLine();
        }
        catch(InputMismatchException e){
            System.out.printf("Invalid Input %s",e.getMessage());
            input.nextLine();
            position = -1; //that makes this building invalid
        }
    }

    /**
    * Returns number of rooms value of house
    */
    public int get_room_number(){
        return room_number;
    }

    /**
    * Returns color string of house
    */
    public String get_color(){
            return color;
    }

    /**
    * Prints particular property of house
    */
    public void focus(){
        System.out.printf("\nOwner of this house is %s",owner);
    }

    /**
    * Overriden toString method that prints all properties of House
    */
    @Override
    public String toString(){
        return "|House| Position-> "+position+"| Length-> "+length+"| Height-> "+height+"| Owner : "+owner+", Color : "+color+", Number of rooms : "+room_number;
    }

    /**
    * Overriden equals method that compares all properties of building
    */
    @Override
    public boolean equals(Object other){
        if(other==this) return true;

        if(!(other instanceof House)) return false;

        House h = (House)other;
        
        return position==h.get_position()&&length==h.get_length()&&height==get_height()
               &&room_number==h.get_room_number()&& (owner.compareTo(h.get_owner())==0) && (color.compareTo(h.get_color())==0);

    }

    /**
    * Overriden clone() method for House
    */
    @Override
    public Object clone() throws CloneNotSupportedException{
        House obj = (House)super.clone();
        return obj;
    }

    @Override
    public int hashCode() {
    int hash = 14;
    hash = 7 * hash + (int) room_number + (int)position + (int)height + (int)length;
    hash = 7 * hash + (owner == null ? 0 : owner.hashCode());
    hash = 7 * hash + (color == null ? 0 : color.hashCode());
    return hash;
    }
}
