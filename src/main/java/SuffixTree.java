import java.util.List;

public interface SuffixTree {
    public void insert(String text, int index);
    public List<Integer> find (String text);

}
