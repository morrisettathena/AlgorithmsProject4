import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readIn {
    public static int[][] readData(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        s.nextLine();
        String s1 = s.nextLine().strip();
        s1 = s1.substring(1, s1.length()-2);
        String s2 = s.nextLine().strip();
        s2 = s2.substring(1, s2.length()-1);

        String[] profitString = s1.split(", ");
        String[] weightString = s2.split(", ");

        
        int[] profit = new int[profitString.length];
        int[] weight = new int[weightString.length];

        for (int i = 0; i < profitString.length; i++) {

            profit[i] = Integer.parseInt(profitString[i]);
            weight[i] = Integer.parseInt(weightString[i]);
        }

        int[][] data = {profit, weight};
        s.close();

        return data;
    }
}
