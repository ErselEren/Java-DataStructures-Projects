import java.util.InputMismatchException;

/**
* Office class that takes inherit from Buildings superclass
*/
public class Office extends Building{
    //Office class fields
    private String job_type;
    private String owner;
    
    /**
    * No parameter constructor for Office class
    * Sets position 0, length 1, height 1, "unknown" for owner and jobtype
    */
    Office(){
        this(0,1,1,"unknown","unknown");
    }
    
    /**
    * Office 2 parameter constructor
    * @param j Jobtype
    * @param o Owner
    */
    Office(String j, String o){
        this(0,1,1,j,o);
    }


    Office(int p, int l, int h){
        position = p;
        length = l;
        height = h;
        job_type = new String("unknown");
        owner = new String("unknown");
    }
    
    /**
    * Constructor method of House class 
    * @param p Position of building
    * @param l Length of building
    * @param h Height of building
    * @param o Owner of building
    * @param j Job-type
    */
    Office(int p, int l, int h,String o, String j){
        super(p,l,h);
        owner = o;
        job_type = j;
    }

    /**
    * @param s Job-type
    */
    public void set_jobtype(String s){
        job_type = s;
    }

    /**
    * @param s Owner
    */
    public void set_owner(String s){
        owner = s;
    }

    /**
    * Returns owner string of Office
    */
    public String get_owner(){
        return owner;
    }

    /**
    * Returns job-type string of Office
    */
    public String get_jobtype(){
        return job_type;
    }

    /**
    * First sets common properties which belongs to Building superclass, than gets input specially for Office
    */
    @Override
    protected void set_properties(){
        set_position_length_height();
        try{
            System.out.printf("\nEnter |owner|");
            set_owner(input.nextLine());
            System.out.printf("\nEnter |jobtype|");
            set_jobtype(input.nextLine());
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
        System.out.printf("\nJob-type of this office is %s",job_type);
    }

    /**
    * Overriden toString method that prints all properties of Office
    */
    @Override
    public String toString(){
        return "|Office| Position-> "+position+"| Length-> "+length+"| Height-> "+height+"| Owner : "+owner+", Job-type : "+job_type;
    }

    /**
    * Overriden equals method that compares all properties of building
    */
    @Override
    public boolean equals(Object other){
        if(other==this) return true;

        if(!(other instanceof Office)) return false;

        Office h = (Office)other;
        
        return position==h.get_position()&&length==h.get_length()&&height==get_height()
               &&(owner.compareTo(h.get_owner())==0) && (job_type.compareTo(h.get_jobtype())==0);

    }

    /**
    * Overriden clone() method for Office
    */
    @Override
    public Object clone() throws CloneNotSupportedException{
        Office obj = (Office)super.clone();
        return obj;
    }

    @Override
    public int hashCode() {
    int hash = 14;
    hash = 7 * hash + (int) position+ (int)height + (int)length;
    hash = 7 * hash + (owner == null ? 0 : owner.hashCode());
    hash = 7 * hash + (job_type == null ? 0 : job_type.hashCode());
    return hash;
    }

}

