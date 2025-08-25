public class StringPractice {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = new String("World");
        char[] arr = {'J','a','v','a'};
        String s3 = new String(arr);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println(s1 + " " + s2);
        System.out.println(s1.toUpperCase());
        System.out.println(s2.toLowerCase());
        System.out.println(s3.substring(1,3));
        System.out.println(s1.concat(" " + s3));
        System.out.println(s1.length());
        System.out.println(s2.charAt(2));
        System.out.println(s3.replace('a','o'));
    }
}
`