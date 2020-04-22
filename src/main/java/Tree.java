import java.util.ArrayList;
import java.util.List;

public class Tree implements SuffixTree {

    private Node rootNode;

    Tree(){rootNode = new Node();}



    private static class Node {
        private Node[] children;
        private List<Integer> indices;
        Node() {
            children = new Node[26];
            indices = new ArrayList<>(0);
        }
        Node getChild(char index) {
            if (index < 'a' || index > 'z') return null;
            return children[index - 'a'];
        }

        void addChild(char index) {
            if (index < 'a' || index > 'z') return;
            Node node = new Node();
            children[index - 'a'] = node;
        }

        List<Integer> getIndices()
        {
            return indices;
        }

        void addIndex(int index)
        {
            indices.add(index);
        }
    }




    public void insert(String text, int index) {

        for(int x = 0;x< text.length();x++)
        {
            Node currentNode = rootNode;
            String temptext = text.substring(x);
            for (int i = 0;i < temptext.length();i++)
            {
                char keyChar = temptext.charAt(i);

                if (currentNode.getChild(keyChar) == null) {
                    currentNode.addChild(keyChar);
                }
                currentNode = currentNode.getChild(keyChar);
                currentNode.addIndex(index);
            }
        }
    }

    public List<Integer> find(String text) {
        Node currentNode = rootNode;
        for (int x = 0; x < text.length();x++){
            char keyChar = text.charAt(x);

            if (currentNode.getChild(keyChar) == null) {
                return null;
            }
            currentNode = currentNode.getChild(keyChar);
        }
        return currentNode.getIndices();
    }
}

