import java.util.Scanner;

public class SpellChecker {
    public static String[] splitSentence(String sentence) {
        int n = sentence.length();
        String[] temp = new String[n];
        int count = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            char c = sentence.charAt(i);
            if (c == ' ' || c == '.' || c == ',' || c == '!' || c == '?') {
                if (start < i) {
                    temp[count++] = sentence.substring(start, i);
                }
                start = i + 1;
            }
        }
        if (start < n) {
            temp[count++] = sentence.substring(start, n);
        }
        String[] words = new String[count];
        for (int i = 0; i < count; i++) words[i] = temp[i];
        return words;
    }

    public static int stringDistance(String w1, String w2) {
        int len1 = w1.length();
        int len2 = w2.length();
        int diff = Math.abs(len1 - len2);
        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            if (w1.charAt(i) != w2.charAt(i)) diff++;
        }
        return diff;
    }

    public static String findClosestWord(String word, String[] dictionary) {
        String best = word;
        int minDist = Integer.MAX_VALUE;
        for (String d : dictionary) {
            int dist = stringDistance(word.toLowerCase(), d.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                best = d;
            }
        }
        if (minDist <= 2) return best;
        return word;
    }

    public static void displayResults(String[] words, String[] dictionary) {
        System.out.printf("%-15s %-15s %-10s %-15s%n", "Word", "Suggestion", "Distance", "Status");
        System.out.println("--------------------------------------------------------------");
        for (String w : words) {
            String suggestion = findClosestWord(w, dictionary);
            int dist = stringDistance(w.toLowerCase(), suggestion.toLowerCase());
            String status = (dist == 0) ? "Correct" : "Misspelled";
            System.out.printf("%-15s %-15s %-10d %-15s%n", w, suggestion, dist, status);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dictionary = {"java","programming","is","fun","spell","checker","simple"};
        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();
        String[] words = splitSentence(sentence);
        System.out.println("\n--- Spell Check Report ---");
        displayResults(words, dictionary);
        sc.close();
    }
}
