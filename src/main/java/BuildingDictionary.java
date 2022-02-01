import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;


public class BuildingDictionary {
    public static void main(String[] arg) throws Exception {
        URL res = BuildingDictionary.class.getClassLoader().getResource("words.txt");
        File file = Paths.get(res.toURI()).toFile();

        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;
        List<String> wordList = new ArrayList<>();
        Map<Character, Integer> frequency = new HashMap<>();



        while ((st = br.readLine()) != null) {
            wordList.add(st);
            for (Character a : st.toCharArray()) {
                if (frequency.containsKey(a)) {
                    frequency.put(a, frequency.get(a) + 1);
                } else {
                    frequency.put(a, 1);
                }
            }
        }


        DictionaryTreeNode node = new DictionaryTreeNode(null);
        for (String x : wordList) {
            createDictionaryTree(x, node.getFollowingChar(), 0);
        }


//        int totalRecur = 0;
//        for (String a : wordList) {
//            HashSet<String> lastGuess = new HashSet<>();
//            Random random = new Random();
//            String firstGuess = "arose";
//            Wordle wordle = new Wordle(firstGuess);
//            compareWords(a, wordle);
//            boolean correct = false;
//            int guessedTime = 1;
//            while (!correct) {
//                guessedTime++;
//                boolean hasAllTheLetters = (wordle.getGreenLetters().size() + wordle.getPossibleLetters().size()) == 5;
//
//                String guess = wordle.findPossibleWord(node, 0, hasAllTheLetters,a,lastGuess);
//                lastGuess.add(guess);
//                if (guess == null) {
//                    break;
//                }
//                wordle.setCurrentGuess(guess);
//                compareWords(a, wordle);
//                if (a.equals(guess)) {
//                    System.out.println("found in " + guessedTime + "th guess");
//                    correct = true;
//                }
//
//            }
//
//            totalRecur += guessedTime;
//            System.out.println("Current average" + (float) totalRecur / 8636);
//            System.out.println(a);
//        }


            HashSet<String> lastGuess = new HashSet<>();
            Random random = new Random();
            String firstGuess = "arise";
            Wordle wordle = new Wordle(firstGuess);
            compareWords("light", wordle);
            boolean correct = false;
            int guessedTime = 1;
            while (!correct) {
                guessedTime++;
                boolean hasAllTheLetters = (wordle.getGreenLetters().size() + wordle.getPossibleLetters().size()) == 5;

                String guess = wordle.findPossibleWord(node, 0, hasAllTheLetters,"light",lastGuess);
                lastGuess.add(guess);
                System.out.println(guess);
                if (guess == null) {
                    break;
                }
                wordle.setCurrentGuess(guess);
                compareWords("light", wordle);
                if ("light".equals(guess)) {
                    System.out.println("found in " + guessedTime + "th guess");
                    correct = true;
                }

            }

    }



    //
    public static void compareWords(String solution, Wordle wordle) {
        Map<Character, Integer> solutionMap = new HashMap<>();
        for (Character a : solution.toCharArray()) {
            if (solutionMap.containsKey(a)) {
                solutionMap.put(a, solutionMap.get(a) + 1);
            } else {
                solutionMap.put(a, 1);
            }
        }

        Set<Character> newPossibleLetters = new HashSet<>();
        List<WordContainer> currentWord = wordle.getWord();
        for (int i = 0; i < 5; i++) {
            if (solution.charAt(i) == currentWord.get(i).getLetter()) {
                currentWord.get(i).setLetterGreen();
                solutionMap.put(solution.charAt(i), solutionMap.get(solution.charAt(i)) - 1);
                if (solutionMap.get(solution.charAt(i)) == 0) {
                    solutionMap.remove(currentWord.get(i).getLetter());
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (solutionMap.containsKey(currentWord.get(i).getLetter()) && !currentWord.get(i).isLetterGreen()) {
                currentWord.get(i).setColor(Color.YELLOW);
                newPossibleLetters.add(currentWord.get(i).getLetter());
                currentWord.get(i).getPositionImpossibleLetters().add(currentWord.get(i).getLetter());
            } else if (!solutionMap.containsKey(currentWord.get(i).getLetter()) &&!currentWord.get(i).isLetterGreen()) {
                currentWord.get(i).setColor(Color.BLACK);
                wordle.getImpossibleLetters().add(currentWord.get(i).getLetter());
            }
        }

        wordle.setPossibleLetters(newPossibleLetters);

    }

    private static void createDictionaryTree(String word, Map<Character, DictionaryTreeNode> possibleLetters, int index) throws Exception {
        if (word.length() != 5) {
            System.out.println("This one bad");
            return;
        }
        if (index == 4) {
            DictionaryTreeNode node = new DictionaryTreeNode(word.charAt(index));
            node.setCurrentWord(word);
            possibleLetters.put(node.getCurrentChar(), node);
            return;
        }
        if (!possibleLetters.containsKey(word.charAt(index))) {
            DictionaryTreeNode node = new DictionaryTreeNode(word.charAt(index));
            possibleLetters.put(node.getCurrentChar(), node);
            createDictionaryTree(word, node.getFollowingChar(), index + 1);
        } else {
            createDictionaryTree(word, possibleLetters.get(word.charAt(index)).getFollowingChar(), index + 1);
        }


    }
}



