package tester;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrabble.ScrabbleGenerator;

/**
 * Tests the ScrabbleGenerator class used by the Scrabble application.
 *
 * @author Alexander Goda
 */
public class ScrabbleGeneratorTester {

    private static int testsPassed = 0;
    private static int testsRun = 0;

    /**
     * Runs all ScrabbleGenerator tests.
     *
     * @param args command-line arguments are not used
     */
    public static void main(String[] args) {
        System.out.println("CS341 ScrabbleGenerator Tests");
        System.out.println("----------------------------");

        testThreeLetters();
        testExactlySevenLetters();
        testFewerThanSevenLetters();
        testRepeatedLetters();
        testMoreThanSevenLetters();
        testNonLetterInput();
        testEmptyInput();

        System.out.println();
        System.out.println("Tests passed: " + testsPassed + " of " + testsRun);
    }

    /** Test 1: Three different letters should create 3! = 6 arrangements. */
    private static void testThreeLetters() {
        ScrabbleGenerator generator = new ScrabbleGenerator("abc");
        List<String> results = generator.generateArrangements();

        boolean passed = results.size() == 6
                && results.contains("ABC")
                && results.contains("ACB")
                && results.contains("BAC")
                && results.contains("BCA")
                && results.contains("CAB")
                && results.contains("CBA");

        report("Test 1 - Three letters produce six arrangements", passed);
    }

    /** Test 2: Seven different letters should create 7! = 5040 arrangements. */
    private static void testExactlySevenLetters() {
        ScrabbleGenerator generator = new ScrabbleGenerator("ABCDEFG");
        List<String> results = generator.generateArrangements();
        report("Test 2 - Seven letters produce 5040 arrangements", results.size() == 5040);
    }

    /** Test 3: Fewer than seven letters are accepted. */
    private static void testFewerThanSevenLetters() {
        ScrabbleGenerator generator = new ScrabbleGenerator("DOG");
        List<String> results = generator.generateArrangements();
        report("Test 3 - Fewer than seven letters are accepted", results.size() == 6);
    }

    /** Test 4: Repeated letters should not create duplicate visible arrangements. */
    private static void testRepeatedLetters() {
        ScrabbleGenerator generator = new ScrabbleGenerator("AAB");
        List<String> results = generator.generateArrangements();
        Set<String> unique = new HashSet<String>(results);

        boolean passed = results.size() == 3
                && unique.size() == 3
                && unique.contains("AAB")
                && unique.contains("ABA")
                && unique.contains("BAA");

        report("Test 4 - Repeated letters do not duplicate arrangements", passed);
    }

    /** Test 5: More than seven letters must be rejected. */
    private static void testMoreThanSevenLetters() {
        report("Test 5 - More than seven letters are rejected", throwsInputError("ABCDEFGH"));
    }

    /** Test 6: Non-letter input must be rejected. */
    private static void testNonLetterInput() {
        report("Test 6 - Non-letter input is rejected", throwsInputError("ABC1"));
    }

    /** Test 7: Empty input is rejected. */
    private static void testEmptyInput() {
        report("Test 7 - Empty input is rejected", throwsInputError(""));
    }

    /**
     * Checks whether invalid input causes the expected exception.
     *
     * @param input input to test
     * @return true when an IllegalArgumentException is thrown
     */
    private static boolean throwsInputError(String input) {
        try {
            new ScrabbleGenerator(input);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    /**
     * Displays the result of one test.
     *
     * @param testName description of the test
     * @param passed whether the test passed
     */
    private static void report(String testName, boolean passed) {
        testsRun++;

        if (passed) {
            testsPassed++;
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
        }
    }
}
