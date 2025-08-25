import java.util.*;

public class FileOrganizer {
    static class FileData {
        String originalName;
        String baseName;
        String extension;
        String category;
        String newName;
        String subCategory;
        boolean valid;
    }

    public static FileData extractFileInfo(String filename) {
        FileData fd = new FileData();
        fd.originalName = filename;

        int dot = filename.lastIndexOf('.');
        if (dot == -1 || dot == filename.length() - 1) {
            fd.valid = false;
            fd.baseName = filename;
            fd.extension = "";
            return fd;
        }

        fd.baseName = filename.substring(0, dot);
        fd.extension = filename.substring(dot + 1).toLowerCase();
        fd.valid = filename.matches("[A-Za-z0-9._-]+");
        return fd;
    }

    public static String categorize(FileData fd) {
        switch (fd.extension) {
            case "txt": case "doc": case "pdf": return "Document";
            case "jpg": case "png": case "gif": return "Image";
            case "mp3": case "wav": return "Audio";
            case "mp4": case "avi": return "Video";
            case "java": case "c": case "py": return "Code";
            default: return "Unknown";
        }
    }

    public static String generateNewName(FileData fd, int index) {
        String date = "2025";
        String base = fd.category + "_" + date;
        return base + "_" + (index + 1) + "." + fd.extension;
    }

    public static String analyzeContent(FileData fd) {
        if (!fd.extension.equals("txt")) return "General";
        String nameLower = fd.baseName.toLowerCase();
        if (nameLower.contains("resume")) return "Resume";
        if (nameLower.contains("report")) return "Report";
        if (nameLower.contains("code")) return "Code";
        return "General";
    }

    public static void displayReport(List<FileData> files) {
        System.out.printf("%-20s %-12s %-20s %-12s %-10s%n", 
                          "Original Name", "Category", "New Name", "SubCategory", "Valid");
        System.out.println("---------------------------------------------------------------------------------");
        Map<String,Integer> counts = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            FileData f = files.get(i);
            System.out.printf("%-20s %-12s %-20s %-12s %-10s%n", 
                              f.originalName, f.category, f.newName, f.subCategory, f.valid);
            counts.put(f.category, counts.getOrDefault(f.category, 0) + 1);
        }

        System.out.println("\n--- Category Counts ---");
        for (String cat : counts.keySet()) {
            System.out.println(cat + ": " + counts.get(cat));
        }
    }

    public static void batchRenameCommands(List<FileData> files) {
        System.out.println("\n--- Batch Rename Commands ---");
        for (FileData f : files) {
            if (!f.originalName.equals(f.newName)) {
                System.out.println("rename " + f.originalName + " -> " + f.newName);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileData> files = new ArrayList<>();

        System.out.print("Enter number of files: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter filename " + (i + 1) + ": ");
            String fname = sc.nextLine();

            FileData fd = extractFileInfo(fname);
            fd.category = categorize(fd);
            fd.subCategory = analyzeContent(fd);
            fd.newName = generateNewName(fd, i);

            files.add(fd);
        }

        displayReport(files);
        batchRenameCommands(files);

        sc.close();
    }
}
