import java.util.Objects;

public class Recipe implements Comparable<Recipe> {
    private String nameOfRecipe;
    private String contentOfRecipe;

    public Recipe(String name) {
        nameOfRecipe = name;
        contentOfRecipe = new String("unknown_CONTENT");
    }

    public Recipe(String name, String content) {
        nameOfRecipe = name;
        contentOfRecipe = content;
    }

    public void setNameOfRecipe(String name) {
        nameOfRecipe = name;
    }

    public void setContentOfRecipe(String content) {
        contentOfRecipe = content;
    }

    public String getNameOfRecipe() {
        return nameOfRecipe;
    }

    public String getContentOfRecipe() {
        return contentOfRecipe;
    }

    @Override
    public String toString() {
        return "\nRecipe: " + nameOfRecipe +
                "\nContent: " + contentOfRecipe + "\n";
    }

    @Override
    public int compareTo(Recipe other) {
        return nameOfRecipe.compareTo(other.nameOfRecipe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return compareTo(recipe) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfRecipe);
    }
}
