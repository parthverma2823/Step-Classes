import java.util.*;

public class CSVAnalyzer {
    public static String[][] parseCSV(String input) {
        List<String[]> rows = new ArrayList<>();
        int start = 0;
        List<String> currentRow = new ArrayList<>();
        boolean inQuotes = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                currentRow.add(input.substring(start, i));
                start = i + 1;
            } else if ((c == '\n' || c == '\r') && !inQuotes) {
                currentRow.add(input.substring(start, i));
                rows.add(currentRow.toArray(new String[0]));
                currentRow.clear();
                start = i + 1;
            }
        }
        if (start < input.length()) {
            currentRow.add(input.substring(start));
        }
        if (!currentRow.isEmpty()) {
            rows.add(currentRow.toArray(new String[0]));
        }

        return rows.toArray(new String[0][0]);
    }

    public static String[][] cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null) {
                    data[i][j] = data[i][j].trim().replaceAll("^\"|\"$", "");
                }
            }
        }
        return data;
    }

    public static void analyzeData(String[][] data) {
        int rows = data.length - 1; 
        int cols = data[0].length;
        System.out.println("\n--- Data Analysis ---");

        for (int j = 0; j < cols; j++) {
            boolean numeric = true;
            List<Double> nums = new ArrayList<>();
            Set<String> unique = new HashSet<>();
            int missing = 0;

            for (int i = 1; i < data.length; i++) {
                String val = data[i][j];
                if (val == null || val.isEmpty()) {
                    missing++;
                    continue;
                }
                boolean isNum = val.chars().allMatch(c -> (c >= '0' && c <= '9') || c == '.');
                if (isNum) {
                    nums.add(Double.parseDouble(val));
                } else {
                    numeric = false;
                    unique.add(val);
                }
            }

            System.out.println("Column: " + data[0][j]);
            if (numeric && !nums.isEmpty()) {
                double min = Collections.min(nums);
                double max = Collections.max(nums);
                double avg = nums.stream().mapToDouble(a -> a).average().orElse(0);
                System.out.printf("  Type: Numeric | Min: %.2f | Max: %.2f | Avg: %.2f\n", min, max, avg);
            } else {
                System.out.println("  Type: Categorical | Unique values: " + unique.size());
            }
            if (missing > 0) {
                System.out.println("  Missing values: " + missing);
            }
        }
    }

    public static void formatTable(String[][] data) {
        int cols = data[0].length;
        int[] widths = new int[cols];
        for (int j = 0; j < cols; j++) {
            int max = data[0][j].length();
            for (int i = 1; i < data.length; i++) {
                if (data[i][j] != null && data[i][j].length() > max) {
                    max = data[i][j].length();
                }
            }
            widths[j] = max + 2;
        }

        System.out.println("\n--- Formatted Table ---");
        for (int i = 0; i < data.length; i++) {
            StringBuilder row = new StringBuilder("|");
            for (int j = 0; j < cols; j++) {
                String val = data[i][j] == null ? "" : data[i][j];
                row.append(String.format(" %-" + widths[j] + "s|", val));
            }
            System.out.println(row.toString());
        }
    }

    public static void summaryReport(String[][] data) {
        int total = data.length - 1;
        int cols = data[0].length;
        int missing = 0;
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] == null || data[i][j].isEmpty()) missing++;
            }
        }
        int totalFields = total * cols;
        double completeness = 100.0 * (totalFields - missing) / totalFields;

        System.out.println("\n--- Summary Report ---");
        System.out.println("Total Records: " + total);
        System.out.println("Total Columns: " + cols);
        System.out.println("Missing Fields: " + missing);
        System.out.printf("Data Completeness: %.2f%%\n", completeness);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end with empty line):");
        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            input.append(line).append("\n");
        }

        String[][] parsed = parseCSV(input.toString());
        parsed = cleanData(parsed);

        formatTable(parsed);
        analyzeData(parsed);
        summaryReport(parsed);

        sc.close();
    }
}
