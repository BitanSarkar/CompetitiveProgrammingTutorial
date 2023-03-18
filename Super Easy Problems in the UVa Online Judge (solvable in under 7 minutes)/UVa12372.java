import java.util.Arrays;
import java.util.Scanner;

public class UVa12372 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        int ctr = 0;
        while(t-->0) System.out.println("Case " + ++ctr + ": " + (Arrays.stream(sc.nextLine().trim().toLowerCase().split("\\s+")).map(x -> Long.parseLong(x)).anyMatch(n -> n>20)?"bad":"good"));
        sc.close();
    }
}
