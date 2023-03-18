import java.util.Scanner;

public class UVa11498 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t;
        while((t = sc.nextInt()) != 0){
            long N = sc.nextLong();
            long M = sc.nextLong();
            while(t-- > 0) {
                long x = sc.nextLong();
                long y = sc.nextLong();
                if(x==N || y==M) System.out.println("divisa");
                else System.out.println((y>M?"N":"S")+(x>N?"E":"O"));
            }
        };
        sc.close();
    }
}
