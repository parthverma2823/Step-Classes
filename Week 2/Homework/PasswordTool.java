import java.util.*;

public class PasswordTool {
    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (int i = 0; i < password.length(); i++) {
            int ascii = (int) password.charAt(i);
            if (ascii >= 65 && ascii <= 90) upper++;
            else if (ascii >= 97 && ascii <= 122) lower++;
            else if (ascii >= 48 && ascii <= 57) digit++;
            else if (ascii >= 33 && ascii <= 126) special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    public static int calculateScore(String password, int[] counts) {
        int score = 0;
        if (password.length() > 8) score += (password.length() - 8) * 2;
        if (counts[0] > 0) score += 10;
        if (counts[1] > 0) score += 10;
        if (counts[2] > 0) score += 10;
        if (counts[3] > 0) score += 10;
        String lower = password.toLowerCase();
        if (lower.contains("123") || lower.contains("abc") || lower.contains("qwerty")) score -= 10;
        return score;
    }

    public static String getStrength(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    public static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{};:,.<>?/";

        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));

        String all = upper + lower + digits + special;
        for (int i = 4; i < length; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }

        List<Character> chars = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);

        StringBuilder finalPass = new StringBuilder();
        for (char c : chars) finalPass.append(c);
        return finalPass.toString();
    }

    public static void displayResults(String[] passwords) {
        System.out.printf("%-15s %-7s %-7s %-7s %-7s %-10s %-10s%n", 
                          "Password", "Len", "Upper", "Lower", "Digit", "Special", "Strength");
        System.out.println("-----------------------------------------------------------------");
        for (String p : passwords) {
            int[] counts = analyzePassword(p);
            int score = calculateScore(p, counts);
            System.out.printf("%-15s %-7d %-7d %-7d %-7d %-10d %-10s%n",
                              p, p.length(), counts[0], counts[1], counts[2], counts[3],
                              getStrength(score));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of passwords to analyze: ");
        int n = sc.nextInt();
        sc.nextLine();
        String[] passwords = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i+1) + ": ");
            passwords[i] = sc.nextLine();
        }

        System.out.println("\n--- Password Strength Report ---");
        displayResults(passwords);

        System.out.print("\nEnter desired length for strong password: ");
        int len = sc.nextInt();
        String strongPass = generatePassword(len);
        System.out.println("Generated Strong Password: " + strongPass);

        sc.close();
    }
}
