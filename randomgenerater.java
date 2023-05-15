import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class randomgenerater{

    static class Tuple implements Comparable<Tuple> {
        int p;
        int w;
        double ratio;

        public Tuple(int p, int w) {
            this.p = p;
            this.w = w;
            this.ratio = (float)p/(float)w;
        }

        @Override
        public int compareTo(Tuple t) {
            if (Math.abs(this.ratio - t.ratio) < 0.000001)
                return 0;
            else if (this.ratio > t.ratio)
                return -1;
            else
                return 1; 
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", p, w);
        }
    }
    
    public static void main(String args[]) throws IOException {
        int [] inputs = {5, 10, 15, 20, 25, 30, 35, 40, 45};

        File folder = new File("data");
        File[] FILES = folder.listFiles();

        for (File f: FILES) {
            f.delete();
        }

        for (int j = 0; j < inputs.length; j++){
            
            int arr_size = inputs[j];
            String output_file = "data/" + arr_size + ".txt";

            File f = new File(output_file);
            FileWriter writer = new FileWriter(f);
            

            Random r = new Random();
            Tuple[] tl = new Tuple[arr_size];

            for (int i = 0; i < arr_size; i++) {
                int p = r.nextInt(10)+1;
                int w = r.nextInt(5)+1;
                tl[i] = new Tuple(p, w);
            }

            Arrays.sort(tl);

            /*for (int i = 0; i < arr_size; i++) {
                System.out.println(tl[i].ratio);
            }*/

            String s1 = "{0, ";
            String s2 = "{0, ";

            for (int i = 0; i < arr_size; i++) {
                s1 += String.format("%d, ", tl[i].p);
                s2 += String.format("%d, ", tl[i].w);
            }

            s1 = s1.substring(0, s1.length()-2);
            s2 = s2.substring(0, s2.length()-2);

            s1 += "},";
            s2 += "}";

            String s = "int[][] set = {";
            s += "\n" + s1;
            s += "\n" + s2;
            s += "\n};";
            
            //writer.write("" + (arr_size+1) + "\n");
            writer.write(s);
            //System.out.println(s);
            

            writer.close();


        }
        
    }
}
