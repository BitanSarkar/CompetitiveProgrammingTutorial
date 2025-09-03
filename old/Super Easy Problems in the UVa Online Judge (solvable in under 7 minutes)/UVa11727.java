import java.util.Scanner;

public class UVa11727 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 1; i <= t; i++){
            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();
            System.out.println("Case " + i + ": " + (a+b+c-Math.max(a, Math.max(b, c))-Math.min(a, Math.min(b, c))));
        };
        sc.close();
    }
}
