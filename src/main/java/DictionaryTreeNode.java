
import java.util.*;

public class DictionaryTreeNode {


    public Map<Character, DictionaryTreeNode> getFollowingChar() {
        return followingChar;
    }


    public Map<Character, DictionaryTreeNode> followingChar;
    public String currentWord;
    public Character currentChar;
    public int layer;

    public DictionaryTreeNode(Character currentChar) {
        this.currentChar = currentChar;
        Map<Character, DictionaryTreeNode> followingChar = new HashMap<>();
        this.followingChar = followingChar;
    }



    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public Character getCurrentChar() {
        return currentChar;
    }



    public void print() {
        Deque<DictionaryTreeNode> q = new ArrayDeque<>();
        q.offerFirst(this);
        List<Integer> sizeList = new ArrayList<>();
        int group = this.getFollowingChar().size();

        int size = this.getFollowingChar().size();
        while (!q.isEmpty()) {
            if (size == 0) {
                System.out.println("\n");
                size = q.size();
                System.out.println("current length of line " + size);
            }
            if(group == 0 ){
                System.out.print(" ");
                group = sizeList.get(0);
                sizeList.remove(0);
            }
            DictionaryTreeNode cur = q.pollLast();
            if (cur != null && cur.getFollowingChar() != null) {
                for (Character a : cur.getFollowingChar().keySet()) {
                    q.offerFirst(cur.getFollowingChar().get(a));
                }
                sizeList.add(cur.getFollowingChar().size());
            }
            if (cur.getCurrentChar() != null) {
                System.out.print(cur.getCurrentChar());
                size--;
                group --;
            }
        }
        System.out.println("\n");
    }

    public String findWord(String word, DictionaryTreeNode node, int index) {
        if (index == 5) {
            if (word.equals(node.getCurrentWord())) {
                System.out.println(node.getCurrentWord());
                return node.getCurrentWord();
            } else {
                return "Dude WTF";
            }
        }
        if (node.getFollowingChar().containsKey(word.charAt(index))) {
            System.out.println("found char " + word.charAt(index));

            return findWord(word, node.getFollowingChar().get(word.charAt(index)), index + 1);
        } else {
            return "Dude again WTF";
        }


    }


}

