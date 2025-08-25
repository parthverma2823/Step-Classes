import java.util.Scanner;

public class StringInputProcessing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        
        System.out.println("Original String: " + input);
        System.out.println("Length: " + input.length());
        System.out.println("Uppercase: " + input.toUpperCase());
        System.out.println("Lowercase: " + input.toLowerCase());
        System.out.println("First Character: " + input.charAt(0));
        System.out.println("Last Character: " + input.charAt(input.length() - 1));
        System.out.println("Replaced (a→@): " + input.replace('a','@'));
        System.out.println("Contains 'test': " + input.contains("test"));
        System.out.println("Substring (0-3): " + input.substring(0, Math.min(3, input.length())));
        
        String[] words = input.split(" ");
        System.out.println("Words Count: " + words.length);
        
        sc.close();
    }
}
