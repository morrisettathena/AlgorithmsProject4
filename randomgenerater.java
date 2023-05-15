import java.util.Random;

public class randomgenerater {
    public static void main(String args[]) {

        Random r = new Random();


        String s1 = "{0, ";
        String s2 = "{0, ";

        for (int i = 0; i < 15; i++) {
            s1 += r.nextInt(10) + ", ";
            s2 += r.nextInt(5) + ", ";
        }
        s1 = s1.substring(0, s1.length()-1);
        s2 = s2.substring(0, s2.length()-1);

        s1 += "},";
        s2 += "}";

        String s = "int[][] set = ";
        s += "\n" + s1;
        s += "\n" + s2;
        s += "\n};";
        
        System.out.println(s);
    }
}
