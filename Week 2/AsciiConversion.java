import java.util.Scanner;

public class AsciiConversion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a character: ");
        char ch = sc.next().charAt(0);

        int ascii = (int) ch;
        System.out.println("Character: " + ch);
        System.out.println("ASCII Code: " + ascii);

        System.out.println("Next Character: " + (char)(ascii + 1));
        System.out.println("Previous Character: " + (char)(ascii - 1));

        System.out.println("Uppercase Conversion: " + Character.toUpperCase(ch));
        System.out.println("Lowercase Conversion: " + Character.toLowerCase(ch));

        System.out.print("\nEnter an ASCII code (0-127): ");
        int code = sc.nextInt();
        char converted = (char) code;
        System.out.println("ASCII " + code + " corresponds to character: " + converted);

        sc.close();
    }
}
