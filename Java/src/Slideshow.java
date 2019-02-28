import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Slideshow {

    private List<Slide> slides;
    private int score;

    public Slideshow(List<Slide> slides) {
        this.slides = slides;
        this.score = -1;
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
        this.score = result;
        return result;
    }

    public int swap2slidesRand(){
        int scoreOriginal = this.score;
        Random r = new Random();
        int index1 = r.nextInt(this.slides.size());
        int index2 = r.nextInt(this.slides.size());
        int firstpass = 0;

        if(index1 != 0) {
            firstpass -= this.slides.get(index1).calcuteScoreTransition(this.slides.get(index1 - 1));
        }
        if(index2 != 0) {
            firstpass -= this.slides.get(index2).calcuteScoreTransition(this.slides.get(index2 - 1));
        }
        if(index1 != this.slides.size()-1) {
            firstpass -= this.slides.get(index1).calcuteScoreTransition(this.slides.get(index1 + 1));
        }
        if(index2 != this.slides.size()-1) {
            firstpass -= this.slides.get(index2).calcuteScoreTransition(this.slides.get(index2 + 1));
        }

        if(index2 != 0) {
            firstpass += this.slides.get(index1).calcuteScoreTransition(this.slides.get(index2 - 1));
        }
        if(index1 != 0) {
            firstpass += this.slides.get(index2).calcuteScoreTransition(this.slides.get(index1 - 1));
        }
        if(index2 != this.slides.size()-1) {
            firstpass += this.slides.get(index1).calcuteScoreTransition(this.slides.get(index2 + 1));
        }
        if(index1 != this.slides.size()-1) {
            firstpass += this.slides.get(index2).calcuteScoreTransition(this.slides.get(index1 + 1));
        }



        if(firstpass > 0){

            this.score+= firstpass;
            Slide temp = this.slides.get(index1);
            this.slides.set(index1, this.slides.get(index2));
            this.slides.set(index2, temp);
        }

        return this.score;
    }


    public void outputSolution() throws IOException {
        File file = new File("C:\\Users\\guyle\\Desktop\\GoogleHashCode2019\\dataset\\c_memorable_moments_output.txt");
        file.delete();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(Integer.toString(this.slides.size()) + " ");
        writer.newLine();
        for(Slide s : this.slides){
            String res = s.getP0().getId() + " ";
            if(!s.isSingle()){
                res += s.getP1().getId() + " ";
            }
            writer.write(res);
            writer.newLine();
        }
        writer.close();
    }

    @Override
    public String toString(){
        return slides.toString();
    }


}
