import java.util.HashSet;
import java.util.Set;

public class WordContainer {
    public Character letter;

    public Set<Character> getPositionImpossibleLetters() {
        return positionImpossibleLetters;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color color;



    public Set<Character> positionImpossibleLetters;

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public boolean isLetterGreen() {
        return isLetterGreen;
    }

    public void setLetterGreen() {
        isLetterGreen = true;
        this.color = Color.GREEN;
    }

    public boolean isLetterGreen = false;

    public WordContainer( Character letter) {
        this.letter = letter;
        this.isLetterGreen = false;
        this.positionImpossibleLetters = new HashSet<>();
        this.color = Color.BLACK;
    }

}
