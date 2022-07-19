import java.util.ArrayList;
import java.util.Objects;

public class User {
    
    private String name;
    
    private String surname;
    
    private final String id;

    private ArrayList<String> hobbies;

    private Notebook notebook;

    public User(String id){
        this(id, "unknown_NAME","unknown_SURNAME");
    }

    public User(String id, String name, String surname){
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.hobbies = new ArrayList<>();
        this.notebook = new Notebook();
    }

    /**
     * Setter method for name of user
     * @param name Name of user
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setter method for surname of user
     * @param surname Surname of user
     */
    public void setSurname(String surname){
        this.surname = surname;
    }

    /**
     * Getter method for name of user
     * @return Returns name of user
     */
    public String getName(){
        return name;
    }

    /**
     * Getter method for surname of user
     * @return Returns surname of user
     */
    public String getSurname(){
        return surname;
    }

    /**
     * Getter method for ID of user
     * @return Returns ID of user
     */
    public String getId(){
        return id;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> Hobbies) {
        this.hobbies = Hobbies;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    @Override
    public String toString() {
        return "\nUSER: \n" +
                "    Name: " + name +
                "\n    Surname: " + surname +
                "\n    ID: " + id + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
