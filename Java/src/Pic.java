import java.util.List;

public class Pic {

    final private boolean horizontal;
    final private List<String> tags;

    public Pic(boolean horizontal, List<String> tags) {
        this.horizontal = horizontal;
        this.tags = tags;
    }

    @Override
    public String toString(){
        String h = this.horizontal ? "Horizontal" : "Vertical";
        return "Photo is " + h + " and has tags " + tags;
    }
}
