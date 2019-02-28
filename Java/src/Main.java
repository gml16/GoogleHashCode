import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\guyle\\Desktop\\GoogleHashCode2019\\dataset\\d_pet_pictures.txt"));
        String line;
        br.readLine();
        int id = 0;
        List<Pic> pics = new ArrayList<>();
        String[] elems;
        while ((line = br.readLine()) != null){
            elems = line.split("\\s+");
            boolean horizontal = (elems[0].equals("H"));
            int k = Integer.valueOf(elems[1]);
            Set<String> tags = new HashSet<>();
            while(k-->0){
                tags.add(elems[k+2]);
            }
            pics.add(new Pic(horizontal, tags, id));
            id++;
        }
        Slideshow slideshow = new Slideshow(picsToSlides(pics));
        slideshow = swapTwoElementsRandomly(slideshow);
        slideshow.outputSolution();
        slideshow.calculateScoreSlideshow();
    }

    public static Slideshow swapTwoElementsRandomly(Slideshow slideshow){
        int score = 0;
        int temp;
        int attempts = 5000000;
        Slideshow bestSS = slideshow;
        slideshow.calculateScoreSlideshow();
        while(attempts-->0) {
            temp = slideshow.swap2slidesRand();
            if(attempts%50000==0){
                System.out.println((attempts/50000) + " - " + temp + " - " + score);
            }
            if(temp>score){
                score = temp;
                bestSS = slideshow;
            }
        }
        System.out.println(score);
        return bestSS;
    }

    public static List<Slide> picsToSlides(List<Pic> pics){
        List<Slide> res = new ArrayList<>();
        List<Pic> ver = new ArrayList<>();
        for(Pic p : pics){
            if(p.isHorizontal()){
                res.add(new Slide(p));
            } else {
                ver.add(p);
            }
        }
        for(int i = 0; i < ver.size() - ver.size()%2; i+=2){
            res.add(new Slide(ver.get(i), ver.get(i+1)));
        }
        return res;
    }
}
