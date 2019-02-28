import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        List<Pic> pics = new ArrayList<>();
        scn.nextLine();
        while(n-->0){
            String line = scn.nextLine();
            String[] elems = line.split("\\s+");
            boolean horizontal = (elems[0] == "H");
            int k = Integer.valueOf(elems[1]);
            List<String> tags = new ArrayList<>();
            while(k-->0){
                tags.add(elems[k+2]);
            }
            pics.add(new Pic(horizontal, tags));
        }
        scn.close();

        System.out.println(pics);
    }
}
