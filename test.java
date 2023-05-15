import java.io.IOException;
import java.util.Arrays;
import java.io.File;

public class test {
    public static void main(String args[]) throws IOException {
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println(listOfFiles[i].getPath());
        }
    }
}
