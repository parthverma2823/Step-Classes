public class StringPerformance {
    public static void main(String[] args) {
        int iterations = 100000;

        long startTime, endTime;

        String str = "";
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            str += "a";
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by String: " + (endTime - startTime) + " ms");

        StringBuilder sb = new StringBuilder();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by StringBuilder: " + (endTime - startTime) + " ms");

        StringBuffer sbuf = new StringBuffer();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            sbuf.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by StringBuffer: " + (endTime - startTime) + " ms");
    }
}
