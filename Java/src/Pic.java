import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pic {

    final private boolean horizontal;
    final private int id;
    final private Set<String> tags;

    public Pic(boolean horizontal, Set<String> tags, int id) {
        this.horizontal = horizontal;
        this.tags = tags;
        this.id = id;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public Set<String> getTags() {
        return tags;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        String h = this.horizontal ? "Horizontal" : "Vertical";
        return "Photo is " + h + " and has tags " + tags;
    }
}
