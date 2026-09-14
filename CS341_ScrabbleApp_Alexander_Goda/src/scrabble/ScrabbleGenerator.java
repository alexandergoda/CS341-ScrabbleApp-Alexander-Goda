package scrabble;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generates all unique arrangements of the letters entered for Scrabble tiles.
 * The class also validates the tile input before arrangements are generated.
 *
 * @author Alexander Goda
 */
public class ScrabbleGenerator {

    /** Maximum number of Scrabble tiles allowed by the assignment. */
    private static final int MAX_TILES = 7;

    /** Validated tile letters used to create the arrangements. */
    private String tiles;

    /**
     * Creates a ScrabbleGenerator using the supplied tile letters.
     *
     * @param inputTiles letters entered by the user
     * @throws IllegalArgumentException if the input is empty, contains more than
     *         seven letters, or contains a non-letter character
     */
    public ScrabbleGenerator(String inputTiles) {
        tiles = validateInput(inputTiles);
    }

    /**
     * Validates and normalizes the tile input.
     *
     * @param inputTiles letters entered by the user
     * @return validated tile letters in uppercase
     * @throws IllegalArgumentException if the input is invalid
     */
    private String validateInput(String inputTiles) {
        if (inputTiles == null) {
            throw new IllegalArgumentException("Tile input is required.");
        }

        String value = inputTiles.trim();

        if (value.length() == 0) {
            throw new IllegalArgumentException("Tile input is required.");
        }

        if (value.length() > MAX_TILES) {
            throw new IllegalArgumentException("Enter no more than 7 Scrabble tiles.");
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isLetter(value.charAt(i))) {
                throw new IllegalArgumentException("Enter letters only.");
            }
        }

        return value.toUpperCase();
    }

    /**
     * Generates every unique arrangement that uses all entered tiles.
     *
     * @return list containing all unique tile arrangements
     */
    public List<String> generateArrangements() {
        List<String> arrangements = new ArrayList<String>();
        boolean[] used = new boolean[tiles.length()];
        buildArrangements("", used, arrangements);
        return arrangements;
    }

    /**
     * Recursively builds the arrangements one character at a time.
     *
     * @param current current partial arrangement
     * @param used identifies which tile positions have already been used
     * @param arrangements completed arrangements
     */
    private void buildArrangements(String current, boolean[] used, List<String> arrangements) {
        if (current.length() == tiles.length()) {
            arrangements.add(current);
            return;
        }

        Set<Character> usedAtThisPosition = new HashSet<Character>();

        for (int i = 0; i < tiles.length(); i++) {
            char nextTile = tiles.charAt(i);

            if (!used[i] && !usedAtThisPosition.contains(nextTile)) {
                usedAtThisPosition.add(nextTile);
                used[i] = true;
                buildArrangements(current + nextTile, used, arrangements);
                used[i] = false;
            }
        }
    }

    /**
     * Returns the validated tile letters.
     *
     * @return validated tile letters
     */
    public String getTiles() {
        return tiles;
    }
}
