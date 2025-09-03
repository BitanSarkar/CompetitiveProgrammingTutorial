import java.util.Scanner;

public class UVa11364 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            long a = sc.nextLong();
            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;
            while(a-- > 0) {
                long x = sc.nextLong();
                min = Math.min(x,min);
                max = Math.max(x,max);
            }
            System.out.println((2*(max-min)));
        };
        sc.close();
    }
}
