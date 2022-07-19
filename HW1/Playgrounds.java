import java.util.InputMismatchException;

/**
* Playground class that takes inherit from Buildings superclass
*/
public class Playgrounds extends Buildings{
        
        /**
        * No parameter constructor, position 0, length 1, height is always 1
        */
        Playgrounds(){
            this(0,1);
        }
        
        /**
        * Constructor for Playgrounds
        * @param p Position
        * @param l Length
        * Height is 1
        */
        Playgrounds(int p, int l){
            super(p,l,2);
        }

        /**
        * Prints particular property of focus
        */
        public void focus(){
            System.out.printf("\nLength of this playground is %d",length);
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
        * Returns position of Playground
        */
        public int get_position(){
            return position;
        }

        /**
        * Returns heigth of Playgrounds
        */
        public int get_length(){
            return length;
        }

        /**
        * Playground has only 2 fields which are position and length
        */
        @Override
        protected void set_properties(){
            try{
                System.out.printf("\nEnter |position|");
                position = input.nextInt();
                input.nextLine();
                System.out.printf("\nEnter |length|");
                length = input.nextInt();
                input.nextLine();
                height = 2;
            }catch(InputMismatchException e){
                System.out.printf("Invalid Input %s",e.getMessage());
                input.nextLine();
                position = -1; //that makes this building invalid
            }
            
    }

    /**
    * Overriden toString method that prints all properties of Playrounds
    */
    @Override
    public String toString(){
        return "|Playground| Position-> "+position+"| Length-> "+length+"| Height-> "+height;
    }

    /**
    * Overriden equals method that compares all properties of building
    */
    @Override
    public boolean equals(Object other){
        if(other==this) return true;

        if(!(other instanceof Playgrounds)) return false;

        Playgrounds h = (Playgrounds)other;
        
        return position==h.get_position()&&length==h.get_length();
    }

    /**
    * Overriden clone() method for Playgrounds
    */
    @Override
    public Object clone() throws CloneNotSupportedException{
        Playgrounds obj = (Playgrounds)super.clone();
        return obj;
    }

    @Override
    public int hashCode() {
        int hash = 14;
        hash = 7 * hash + (int)position + (int)height + (int)length;
        return hash;
    }
}

