import java.util.InputMismatchException;

/**
* Market class that takes inherit from Buildings superclass
*/
public class Market extends Buildings{
    //Market class fields
    private String owner;
    private String opening_time;
    private String closing_time;


    /**
    * No parameter Market constructor
    */
    Market(){
        this(0,1,1,"unknown","unknown","unknown");
    }

    /**
    * 3 parameter Market constructor
    * @param owner Owner
    * @param open Opening-time
    * @param clos Closing-time
    */
    Market(String own, String open, String clos){
        this(0,1,1,own,open,clos);
    }

    /**
    * Market class constructor
    * @param p Position
    * @param l Length
    * @param h Height
    * @param owner Owner
    * @param open Opening-time
    * @param clos Closing-time
    */
    Market(int p, int l, int h,String own, String open, String clos){
        super(p,l,h);
        owner = own;
        opening_time = open;
        closing_time = clos;
    }
    
    /**
    * @param s Owner
    */
    public void set_owner(String s){
        owner = s;
    }
    
    /**
    * @param s Opening-time
    */
    public void set_opening(String s){
        opening_time = s;
    }
    
    /**
    * @param s Closing-time
    */
    public void set_closing(String s){
        closing_time = s;
    }

    /**
    * Returns owner string
    */
    public String get_owner(){
        return owner;
    }

    /**
    * Returns opening-time string
    */
    public String get_opening(){
        return opening_time;
    }

    /**
    * Returns closing time
    */
    public String get_closing(){
        return closing_time;
    }

    /**
    * First sets common properties then market's fields
    */
    @Override
    protected void set_properties(){
        set_position_length_height();
        try{
            System.out.printf("\nEnter |owner|");
            set_owner(input.nextLine());
            System.out.printf("\nEnter |opening time|");
            set_opening(input.nextLine());
            System.out.printf("\nEnter |closing time|");
            set_closing(input.nextLine());
        }catch(InputMismatchException e){
            System.out.printf("Invalid Input %s",e.getMessage());
            input.nextLine();
            position = -1; //that makes this building invalid
        }
        
    }

    /**
    * Prints particular property of focus
    */
    public void focus(){
        System.out.printf("\nClosing time of this market is %s",closing_time);
    }

    /**
    * Overriden toString method that prints all properties of Market
    */
    @Override
    public String toString(){
        return "|Market| Position-> "+position+"| Length-> "+length+"| Height-> "+height+"| Owner : "+owner+", Opening Time : "+opening_time+", Closing Time : "+closing_time;
    }

    /**
    * Overriden equals method that compares all properties of building
    */
    @Override
    public boolean equals(Object other){
        if(other==this) return true;

        if(!(other instanceof Market)) return false;

        Market h = (Market)other;
        
        return position==h.get_position()&&length==h.get_length()&&height==get_height()
               && (owner.compareTo(h.get_owner())==0)&& (opening_time.compareTo(h.opening_time)==0)&&(closing_time.compareTo(h.closing_time)==0);
    }

    /**
    * Overriden clone() method for Market
    */
    @Override
    public Object clone() throws CloneNotSupportedException{
        Market obj = (Market)super.clone();
        return obj;
    }
    
    @Override
    public int hashCode() {
    int hash = 14;
    hash = 7 * hash + (int) position+ (int)height + (int)length;
    hash = 7 * hash + (owner == null ? 0 : owner.hashCode());
    hash = 7 * hash + (opening_time == null ? 0 : closing_time.hashCode());
    hash = 7 * hash + (closing_time == null ? 0 : closing_time.hashCode());
    return hash;
    }

}
