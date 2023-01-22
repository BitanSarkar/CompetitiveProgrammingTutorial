import java.util.Scanner;

public class UVa11547 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            long n = sc.nextLong();
            System.out.println(Math.abs(((315*n+36962)%100 - (315*n+36962)%10)/10));
        };
        sc.close();
    }
}
