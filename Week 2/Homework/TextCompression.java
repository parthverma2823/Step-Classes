import java.util.*;

public class TextCompression {
    public static Object[] countFrequencies(String text) {
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int unique = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean found = false;
            for (int j = 0; j < unique; j++) {
                if (chars[j] == c) {
                    freq[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                chars[unique] = c;
                freq[unique] = 1;
                unique++;
            }
        }

        char[] resultChars = new char[unique];
        int[] resultFreq = new int[unique];
        for (int i = 0; i < unique; i++) {
            resultChars[i] = chars[i];
            resultFreq[i] = freq[i];
        }
        return new Object[]{resultChars, resultFreq};
    }

    public static String[][] createCodes(char[] chars, int[] freq) {
        Integer[] idx = new Integer[chars.length];
        for (int i = 0; i < idx.length; i++) idx[i] = i;

        Arrays.sort(idx, (a, b) -> freq[b] - freq[a]);

        String[][] codes = new String[chars.length][2];
        String symbols = "!@#$%^&*()_+=-{}[]|:;<>?,./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < idx.length; i++) {
            codes[i][0] = String.valueOf(chars[idx[i]]);
            codes[i][1] = String.valueOf(symbols.charAt(i));
        }
        return codes;
    }

    public static String compress(String text, String[][] codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (String[] row : codes) {
                if (row[0].charAt(0) == c) {
                    sb.append(row[1]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static String decompress(String compressed, String[][] codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < compressed.length(); i++) {
            char c = compressed.charAt(i);
            for (String[] row : codes) {
                if (row[1].charAt(0) == c) {
                    sb.append(row[0]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void displayAnalysis(String text, String compressed, String decompressed, char[] chars, int[] freq, String[][] codes) {
        System.out.println("\n--- Character Frequency ---");
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i] + " : " + freq[i]);
        }

        System.out.println("\n--- Compression Codes ---");
        for (String[] row : codes) {
            System.out.println("'" + row[0] + "' -> " + row[1]);
        }

        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);

        double ratio = (double) compressed.length() / text.length();
        double efficiency = (1 - ratio) * 100;
        System.out.printf("\nCompression Efficiency: %.2f%%\n", efficiency);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to compress: ");
        String text = sc.nextLine();

        Object[] data = countFrequencies(text);
        char[] chars = (char[]) data[0];
        int[] freq = (int[]) data[1];

        String[][] codes = createCodes(chars, freq);
        String compressed = compress(text, codes);
        String decompressed = decompress(compressed, codes);

        displayAnalysis(text, compressed, decompressed, chars, freq, codes);

        sc.close();
    }
}
