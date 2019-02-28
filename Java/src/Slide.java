import java.util.HashSet;
import java.util.Set;

public class Slide {

    final private Pic p0;
    final private Pic p1;
    final private boolean single;
    final private Set<String> tags;

    public Slide(Pic p0, Pic p1) {
        assert (!p0.isHorizontal() && !p1.isHorizontal());
        this.p0 = p0;
        this.p1 = p1;
        this.tags = p0.getTags();
        this.tags.addAll(p1.getTags());
        this.single = false;
    }

    public Slide(Pic p0) {
        assert (p0.isHorizontal());
        this.p0 = p0;
        this.tags = p0.getTags();
        this.p1 = null;
        this.single = true;
    }


    public Pic getP0() {
        return p0;
    }

    public Pic getP1() {
        return p1;
    }

    public boolean isSingle() {
        return single;
    }


    public int calcuteScoreTransition(Slide other){
        Set<String> intersection = new HashSet<>(this.tags);
        intersection.retainAll(other.tags);
        Set<String> onlyS1 = new HashSet<>(this.tags);
        onlyS1.removeAll(other.tags);
        Set<String> onlyS2 = new HashSet<>(other.tags);
        onlyS2.removeAll(this.tags);
        return Math.min(intersection.size(), Math.min(onlyS1.size(), onlyS2.size()));
    }

    @Override
    public String toString(){
        String res = "Slide has tags " + tags + " with pic " + p0.getId();
        if(!this.single){
            res += " and pic " + p1.getId();
        }
        return res;
    }
}
