import java.util.*;

class Wordle {

    public void setImpossibleLetters(Set<Character> impossibleLetters) {
        this.impossibleLetters = impossibleLetters;
    }

    public Set<Character> impossibleLetters;
    public String currentGuess;
    public Set<Character> possibleLetters;
    public Set<Character> greenLetters;

    public Set<Character> getGreenLetters() {
        return greenLetters;
    }


    public String getCurrentGuess() {
        return currentGuess;
    }

    public void setCurrentGuess(String currentGuess) {
        this.currentGuess = currentGuess;
        for (int i = 0; i < 5; i++) {
            this.getWord().get(i).setLetter(currentGuess.charAt(i));
        }
    }

    public Wordle(String solution) {
        this.currentGuess = solution;
        List<WordContainer> word = new ArrayList<>();
        for (Character a : solution.toCharArray()) {
            word.add(new WordContainer(a));
        }
        this.word = word;
        this.possibleLetters = new HashSet<>();
        this.impossibleLetters = new HashSet<>();
        this.greenLetters = new HashSet<>();
    }


    public List<WordContainer> getWord() {
        return word;
    }


    public List<WordContainer> word;

    public Set<Character> getPossibleLetters() {
        return possibleLetters;
    }

    public void setPossibleLetters(Set<Character> possibleLetters) {
        this.possibleLetters = possibleLetters;
    }

    public Set<Character> getImpossibleLetters() {
        return impossibleLetters;
    }


    public String findPossibleWord(DictionaryTreeNode node, int index, boolean hasAllPossibleWords, String solution, Set<String> lastGuess) throws Exception {

        Set<String> roughResult = new HashSet<>();
        helper(node.getFollowingChar(), 0, hasAllPossibleWords, roughResult, node);
        Set<String> refined = new HashSet<>(roughResult);
        if (!this.getPossibleLetters().isEmpty()) {
            for (String a : roughResult) {
                Set<Character> possibleLetters = new HashSet<>(this.getPossibleLetters());
                for (int i = 0; i < 5; i++) {
                    if (!this.getWord().get(i).isLetterGreen() && possibleLetters.contains(a.charAt(i)) && !this.getWord().get(i).getPositionImpossibleLetters().contains(a.charAt(i))) {
                        possibleLetters.remove(a.charAt(i));
                    }
                }
                if (!possibleLetters.isEmpty()) {
                    refined.remove(a);
                }
            }
        }
        System.out.println("rough after refined" + refined);

        List<String> resultList = Util.sortResult(new ArrayList<String>(refined));
        System.out.println(resultList);
        if (resultList.isEmpty()) {
            throw new Exception("E");
        }
        for (int i = 0; i < resultList.size(); i++) {
            if (!lastGuess.contains(resultList.get(i))) {
                return resultList.get(i);
            }
        }
        return null;
    }


    private void greenLetter(Map<Character, DictionaryTreeNode> possibleLetters, int index, boolean hasAllPossibleWords, Set<String> result, DictionaryTreeNode head) {
        WordContainer currentContainer = this.getWord().get(index);
        if (possibleLetters.containsKey(currentContainer.getLetter())) {
            helper(
                    possibleLetters.get(currentContainer.getLetter()).getFollowingChar(),
                    index + 1,
                    hasAllPossibleWords,
                    result,
                    head
            );
            if (index == 4) {
                result.add(possibleLetters.get(currentContainer.getLetter()).getCurrentWord());
            }

        }
    }


    private void helper(Map<Character, DictionaryTreeNode> possibleLetters, int index, boolean hasAllPossibleWords, Set<String> result, DictionaryTreeNode head) {

        if (index == 5) {
            return;

        } else if (this.getWord().get(index).isLetterGreen()) {
            greenLetter(possibleLetters, index, hasAllPossibleWords, result, head);
        } else {
            randomWords(possibleLetters, index, hasAllPossibleWords, result, head);

        }

    }

    private void randomWords(Map<Character, DictionaryTreeNode> possibleLetters, int index, boolean hasAllPossibleWords, Set<String> result, DictionaryTreeNode head) {
        Set<Character> set = new HashSet<>(this.getImpossibleLetters());
        Map<Character, DictionaryTreeNode> newMap = new HashMap<>(possibleLetters);
        for (Character a : set) {
            newMap.remove(a);
        }
        if (newMap.isEmpty()) {
            return;
        }
        random(possibleLetters, index, hasAllPossibleWords, result, head, newMap);
    }

    private void random(Map<Character, DictionaryTreeNode> possibleLetters, int index, boolean hasAllPossibleWords, Set<String> result, DictionaryTreeNode head, Map<Character, DictionaryTreeNode> newMap) {
        for (Character a : newMap.keySet()) {
            helper(possibleLetters.get(a).getFollowingChar(), index + 1, hasAllPossibleWords, result, head);
            if (index == 4) {
                result.add(possibleLetters.get(a).getCurrentWord());
            }
        }

    }


}


