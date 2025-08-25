import java.util.Scanner;

public class StudentNames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] students = {"Alice", "Bob", "Charlie", "David", "Eve"};

        System.out.println("Student List:");
        for (String name : students) {
            System.out.println(name);
        }

        System.out.print("\nEnter a name to search: ");
        String search = sc.nextLine();

        boolean found = false;
        for (String name : students) {
            if (name.equalsIgnoreCase(search)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println(search + " is in the list.");
        } else {
            System.out.println(search + " is not in the list.");
        }

        System.out.println("\nNames in Uppercase:");
        for (String name : students) {
            System.out.println(name.toUpperCase());
        }

        System.out.println("\nNames Lengths:");
        for (String name : students) {
            System.out.println(name + " - " + name.length());
        }

        sc.close();
    }
}
