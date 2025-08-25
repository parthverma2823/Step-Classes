import java.util.Scanner;

public class StringMethodsDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a text: ");
        String text = sc.nextLine();

        System.out.println("Original Text: " + text);
        System.out.println("Length: " + text.length());
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        System.out.println("Trimmed: " + text.trim());
        System.out.println("First Character: " + text.charAt(0));
        System.out.println("Last Character: " + text.charAt(text.length()-1));
        System.out.println("Substring (0-5): " + text.substring(0, Math.min(5, text.length())));
        System.out.println("Replaced (a→@): " + text.replace('a','@'));
        System.out.println("Contains 'test': " + text.contains("test"));
        System.out.println("Index of 'a': " + text.indexOf('a'));
        System.out.println("Starts with 'Hello': " + text.startsWith("Hello"));
        System.out.println("Ends with 'end': " + text.endsWith("end"));

        String[] words = text.split(" ");
        System.out.println("Word Count: " + words.length);

        sc.close();
    }
}
