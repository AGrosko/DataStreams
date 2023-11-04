import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class streamFiltering {

    private File file;
    private Scanner fileScanner;
    public ArrayList<String> lines;

    public streamFiltering(File f){
            file = f;
            lines = new ArrayList<String>();
            try {
                fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()){
                    lines.add(fileScanner.nextLine());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }

    public List<String> filter(String toFilter){


        return lines.stream().filter(s->s.contains(toFilter)).toList();

    }

    public ArrayList<String> getOG(){
        return lines;

    }

}
