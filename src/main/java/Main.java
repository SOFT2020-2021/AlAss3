import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Tree shakethis = new Tree();
        String path = "shakespeare-complete-works.txt";
        String del = "[^a-zA-Z]";
        String[] testa = Files.lines(Paths.get(path))
                .flatMap(line -> Stream.of(line.split(del)))
                .filter(word -> !word.isEmpty())
                .map(word -> word.toLowerCase())
                .toArray(String[]::new);

        //remove all ascii code
        for (int i = 0; i < testa.length; i++) {
            testa[i] = testa[i].replaceAll("[^\\x00-\\x7F]", "");
            testa[i] = testa[i].replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
            testa[i] = testa[i].replaceAll("\\p{C}", "");
        }

        //Insert into the tree
        for(int x = 0 ; x < testa.length ; x++)
        {
            shakethis.insert(testa[x],x);
        }
        // Replace findtext to find a string in the text
        String findtext = "the";
        List<Integer> testing;
        testing = shakethis.find(findtext);

        if(testing==null) System.out.println("Sorry this search is not found");

        if (testing != null) {
            for(int x = 0 ; x < testing.size();x++    )
            {
                if(x>= 21) break;
                for(int i = 0;i<5;i++){
                    System.out.print(testa[testing.get(x)+i]+ " ");
                }
                System.out.println();
                //System.out.println(testa[testing.get(x)] +" "+ testing.get(x));
            }
        }

    }
}
