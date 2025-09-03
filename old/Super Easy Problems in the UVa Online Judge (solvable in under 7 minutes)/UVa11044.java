import java.util.Scanner;

public class UVa11044 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0) System.out.println((sc.nextInt()/3)*(sc.nextInt()/3));
        sc.close();
    }
}
