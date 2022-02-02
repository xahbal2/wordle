import java.util.*;

public class Start {
    public static void main(String[] arg) throws Exception {
       DictionaryTreeNode node = BuildingDictionary.buildDictionary();

        System.out.println("Hi. This is Wordle solver. Go to Wordle and type in your first answer. I recommend starting with \"arise\".");
        System.out.println("Then tell me what you typed in Wordle");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next().toLowerCase();
        System.out.println("Now tell the colors. Y = yellow, g = green, b = black. For example: YYGBB ");

        String color = scanner.next().toLowerCase().toLowerCase();
        Wordle wordle = new Wordle(word);
        wordle.assignColor(color);
        Set<String> previousGuess = new HashSet<>();
        previousGuess.add(word);
        List<String> possibleResults = wordle.findPossibleWord(node,0,previousGuess);
        System.out.println("Try \""+ possibleResults.get(0)+ "\" or any of the following "+ possibleResults );
        for(int i = 0 ; i< 5; i++){
            System.out.println("Now tell me what you word you have chosen");
            word = scanner.next().toLowerCase();
            previousGuess.add(word);
            wordle.setCurrentGuess(word);
            System.out.println("And colors?");
            color = scanner.next().toLowerCase();
            wordle.assignColor(color);
            possibleResults = wordle.findPossibleWord(node, 0, previousGuess);
            System.out.println("Try \"" + possibleResults.get(0) + "\" or any of the following " + possibleResults);

        }
    }

}
