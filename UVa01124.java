import java.util.Scanner;

public class UVa01124 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        boolean flag = true;
        String res = "";
        while(sc.hasNextLine()){
            line = sc.nextLine();
            res = res + line + "\n";
        }
        sc.close();
        System.out.print(res);
    }
}
