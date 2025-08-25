import java.util.Scanner;

public class SimpleTextProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String text = sc.nextLine();

        System.out.println("\n--- Text Processing ---");
        System.out.println("Original Text: " + text);
        System.out.println("Length: " + text.length());
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        System.out.println("First Character: " + text.charAt(0));
        System.out.println("Last Character: " + text.charAt(text.length() - 1));
        System.out.println("Replaced (a→@): " + text.replace('a', '@'));
        System.out.println("Contains 'java': " + text.toLowerCase().contains("java"));
        System.out.println("Substring (0-5): " + text.substring(0, Math.min(5, text.length())));

        String[] words = text.split(" ");
        System.out.println("\nWords in the sentence:");
        for (String word : words) {
            System.out.println(word + " (Length: " + word.length() + ")");
        }

        System.out.print("\nEnter a word to search: ");
        String search = sc.nextLine();

        boolean found = false;
        for (String word : words) {
            if (word.equalsIgnoreCase(search)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println(search + " is present in the text.");
        } else {
            System.out.println(search + " is not present in the text.");
        }

        sc.close();
    }
}
