import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int max = n;
        List<Pic> pics = new ArrayList<>();
        scn.nextLine();
        while(n-->0){
            String line = scn.nextLine();
            String[] elems = line.split("\\s+");
            boolean horizontal = (elems[0].equals("H"));
            int k = Integer.valueOf(elems[1]);
            Set<String> tags = new HashSet<>();
            while(k-->0){
                tags.add(elems[k+2]);
            }
            pics.add(new Pic(horizontal, tags, max-n));
        }
        scn.close();

        Slideshow slideshow = new Slideshow(picsToSlides(pics));

        System.out.println(pics);
        System.out.println(slideshow);
        System.out.println(slideshow.calculateScoreSlideshow());

        slideshow.outputSolution();
        System.out.println("FINDING RANDOMLY");
        //shuffleUntilFoundBetter(pics);

    }

    public static void shuffleUntilFoundBetter(List<Pic> pics){
        int score = 0;
        int temp = 0;
        while(true) {
            Collections.shuffle(pics);
            Slideshow slideshow = new Slideshow(picsToSlides(pics));
            temp = slideshow.calculateScoreSlideshow();
            System.out.println(slideshow);
            if(temp>score){
                score = temp;
                System.out.println(score);
                System.out.println(slideshow);
            }
        }
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
