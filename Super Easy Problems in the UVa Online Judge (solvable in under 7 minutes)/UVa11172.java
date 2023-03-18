import java.util.Scanner;

public class UVa11172 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            long a = sc.nextLong();
            long b = sc.nextLong();
            System.out.println(a>b?">":a<b?"<":"=");
        };
        sc.close();
    }
}
