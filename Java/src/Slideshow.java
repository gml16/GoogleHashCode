import java.util.List;

public class Slideshow {
    private List<Slide> slides;

    public Slideshow(List<Slide> slides) {
        this.slides = slides;
    }

    public int calculateScoreSlideshow(){
        int result = 0;
        if(slides.isEmpty()){
            return result;
        }
        Slide prev = this.slides.get(0);
        for (int i = 1; i < this.slides.size(); i++) {
            result += this.slides.get(i).calcuteScoreTransition(prev);
            prev = this.slides.get(i);
        }
        return result;
    }


    public void outputSolution(){
        System.out.println(this.slides.size());
        for(Slide s : slides){
            String res = s.getP0().getId() + " ";
            if(!s.isSingle()){
                res += s.getP1().getId() + " ";
            }
            System.out.println(res);
        }
    }

    @Override
    public String toString(){
        return slides.toString();
    }


}
