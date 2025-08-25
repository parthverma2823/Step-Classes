import java.util.Scanner;

public class TextProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a text: ");
        String text = sc.nextLine();

        System.out.println("\n--- Text Processing Utility ---");
        System.out.println("Original Text: " + text);
        System.out.println("Length: " + text.length());
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        System.out.println("Trimmed: " + text.trim());
        System.out.println("Replace spaces with '-': " + text.replace(" ", "-"));
        System.out.println("Substring (first 5 chars): " + text.substring(0, Math.min(5, text.length())));
        System.out.println("Index of 'a': " + text.indexOf('a'));
        System.out.println("Last Index of 'a': " + text.lastIndexOf('a'));
        System.out.println("Starts with 'The': " + text.startsWith("The"));
        System.out.println("Ends with 'end': " + text.endsWith("end"));
        System.out.println("Contains 'Java': " + text.contains("Java"));

        String[] words = text.split(" ");
        System.out.println("Word Count: " + words.length);

        if (words.length > 1) {
            System.out.println("First Word: " + words[0]);
            System.out.println("Last Word: " + words[words.length-1]);
        }

        System.out.println("Equals 'Hello': " + text.equals("Hello"));
        System.out.println("EqualsIgnoreCase 'hello': " + text.equalsIgnoreCase("hello"));
        System.out.println("CompareTo 'Java': " + text.compareTo("Java"));

        sc.close();
    }
}
